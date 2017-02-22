package br.com.prime.data.persistence.hibernate;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.metadata.ClassMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import br.com.prime.data.exception.PersistenceValidateException;
import br.com.prime.data.persistence.CrudDao;
import br.com.prime.data.persistence.Persistent;

/**
 * ImplementaÃ§Ã£o da interface {@link CrudDao} para uso com Spring
 * {@link HibernateTemplate}.
 *
 * @author <a href="mailto:gewtonarq@gmail.com">Gewton Jhames</a>
 * @author <a href="mailto:misaelbarreto@gmail.com">Misael Barreto</a>
 * @author <a href="mailto:rrafaelpinto@gmail.com">Rafael Pinto</a>
 *
 * @param <T>
 *            tipo genÃ©rico que serÃ¡ persistido
 * @see {@link CrudDao}
 *
 * @version 0.3.0.Final
 * @since 0.1.0.Final
 */
/*
 * TODO: Fazer um estudo com calma sobre o tratamento de exceÃ§Ãµes e assim rever
 * se as exceÃ§Ãµes aqui lanÃ§adas, e na arquitetura como um todo, estÃ£o de fato
 * utilizando a melhor prÃ¡tica.
 * 
 * Aproveitar e rever os locais em que deve usar o DataAcessExceptionTranslator
 * para deixar a mensagem mais amigÃ¡vel para o usuÃ¡rio final.
 */

public abstract class HibernateTemplateCrudDao<T extends Persistent> implements CrudDao<T>{

	private static final long serialVersionUID = 7815903224454495303L;

	private final Class<T> persistentClass;

	protected Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	protected SessionFactory sessionFactory;

	@Autowired
	protected Validator validator;

	/**
	 * Cria <tt>HibernateTemplateCrudDao</tt> e localiza a classe concreta que
	 * estÃ¡ sendo manipulada.
	 */
	@SuppressWarnings("unchecked")
	public HibernateTemplateCrudDao() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	protected Session getSession() {
		try {
			return sessionFactory.getCurrentSession();
			
		} catch (HibernateException e) {
			return sessionFactory.openSession();
		}
	}

	public void validar(T entity) throws PersistenceValidateException {
		Set<ConstraintViolation<T>> constraintViolations = validator.validate(entity);
		List<String> errors = new ArrayList<String>();
		for (ConstraintViolation<T> constraintViolation : constraintViolations) {
			errors.add(constraintViolation.getMessage());
		}
		if (!errors.isEmpty()) {
			throw new PersistenceValidateException(errors);
		}
	}

	public void inserir(T entity) throws PersistenceValidateException {
		try {
			validar(entity);
			getSession().save(entity);

		} catch (PersistenceValidateException e) {
			throw new PersistenceValidateException(e);
			//TODO: Por log
		} catch (org.springframework.dao.DataAccessException e) {
			throw new PersistenceValidateException(e);
			//TODO: Por log
		} catch (Exception e) {
			throw new PersistenceValidateException(e);
			//TODO: Por log
		}
	}

	public void atualizar(T entity) throws PersistenceValidateException {
		try {
			validar(entity);
			getSession().merge(entity);
		} catch (PersistenceValidateException e) {
			throw new PersistenceValidateException(e);
			//TODO: Por log
		} catch (org.springframework.dao.DataAccessException e) {
			throw new PersistenceValidateException(e);
			//TODO: Por log
		} catch (Exception e) {
			throw new PersistenceValidateException(e);
			//TODO: Por log
		}
	}

	@SuppressWarnings("unchecked")
	public T buscarPorId(Serializable id) throws PersistenceValidateException {
		try {
			return (T) getSession().get(persistentClass, id);
		} catch (org.springframework.dao.DataAccessException e) {
			throw new PersistenceValidateException(e);
			//TODO: Por log
		}
	}

	public Integer totalRegistros() throws PersistenceValidateException {
		try {
			Long result = (Long) getSession().createQuery("select count(*) from " + persistentClass.getName() + " persistent ")
											.setMaxResults(1)
											.uniqueResult();
			
			return (result != null) ? result.intValue() : 0;
		} catch (Exception e) {
			throw new PersistenceValidateException(e);
		}
	}

	public List<T> listarTodos() throws PersistenceValidateException {
		return findByCriteria();
	}

	@SuppressWarnings("unchecked")
	public List<T> buscarTodosOrdenados(String campo, Boolean ordem) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		if (campo != null) {
			if (ordem) {
				criteria.addOrder(Order.asc(campo));
			} else {
				criteria.addOrder(Order.desc(campo));
			}
		}
		return criteria.list();
	}

	public List<T> buscarPorRange(int posicaoInicial, int quantidadeRegistros) throws PersistenceValidateException {
		return findByCriteria(posicaoInicial, quantidadeRegistros);
	}

	@SuppressWarnings("unchecked")
	private List<T> executeCriteria(int firstResult, int maxResults, DetachedCriteria criteria) {
		Criteria executableCriteria = criteria.getExecutableCriteria(getSession());
		executableCriteria.setFirstResult(firstResult);
		executableCriteria.setMaxResults(maxResults);
		
		return executableCriteria.list();
	}

	
	/**
	 * Faz a consulta da quantidade de registros (projeÃ§Ã£o para count) a partir
	 * de um criteria jÃ¡ montado.
	 * 
	 * @param criteria
	 *            - Criteria criado por outros mÃ©todos com suas regras
	 *            especÃ­ficas
	 */
	protected Integer countByCriteria(DetachedCriteria criteria) {

		/*
		 * Obs: Por algum motivo, a projeÃ§Ã£o rowCount nÃ£o retorna um valor
		 * correto caso a entidade em questÃ£o possua algum atributo coleÃ§Ã£o, com
		 * mapeamento OneToMany e carregamento EAGER. Verifiquei que o criteria
		 * estÃ¡ totalmente correto, que os registros trazidos pelo criteria
		 * tambÃ©m estÃ£o corretos, mas ao jogar a projeÃ§Ã£o
		 * "Projections.rowCount()" a consulta executada ao final sempre Ã© algo
		 * do tipo "select count(*) from entidade", ou seja, os relacionamentos
		 * EAGER nÃ£o sÃ£o levados em conta pela projeÃ§Ã£o. Ver se hÃ¡ uma maneira
		 * de contornar essa situaÃ§Ã£o, mesmo considerando nÃ£o ser uma boa
		 * prÃ¡tica usar mapeamendo EAGER.
		 */
		criteria.setProjection(Projections.rowCount());
		Criteria executableCriteria = criteria.getExecutableCriteria(getSession());
		
		Long result = (Long) executableCriteria.setMaxResults(1).uniqueResult();
		return (result != null) ? result.intValue() : 0;
	}

	/**
	 * Executa uma consulta HQL da quantidade de registros. A consulta Ã©
	 * executada com o HibernateTemplate. Sendo assim, jÃ¡ deve vir pronta com os
	 * parÃ¢metros declarados com "?". <br/>
	 * Exemplo:
	 * <p>
	 * SELECT count(e) FROM Entity e WHERE e.propriedade1 = ? AND e.propriedade2
	 * = ?
	 * </p>
	 * <p>
	 * SerÃ¡ executado da seguinte forma:<br/>
	 * ht.find(query, params);
	 * </p>
	 * 
	 * @param query
	 *            - HQL com a consulta que realiza o count:
	 *            "SELECT count(e) FROM Entity ..."
	 * @param params
	 *            - ParÃ¢metros da consulta
	 */
	protected int countFromQuery(String query, Object[] params) {
		
		Query queryObject = getSession().createQuery(query);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				queryObject.setParameter(i, params[i]);
			}
		}

		Long count = (Long) queryObject.setMaxResults(1).uniqueResult();
		return (count != null) ? count.intValue() : 0;
	}

	/**
	 * Mesmo que {@link #countFromQuery(query, params)}, onde "params" igual a
	 * null.
	 */
	protected int countFromQuery(String query) {
		return countFromQuery(query, null);
	}

	@SuppressWarnings("unused")
	private ClassMetadata getClassMetadata() {
		return sessionFactory.getClassMetadata(getPersistentClass());
	}

	public void remover(T entity) throws PersistenceValidateException {
		try {
			getSession().delete(entity);
		} catch (org.springframework.dao.DataAccessException e) {
			throw new PersistenceValidateException(e);
			//TODO: Por log
		} catch (Exception e) {
			throw new PersistenceValidateException(e);
			//TODO: Por log
		}
	}

	@Transactional
	public void remover(Long id) throws PersistenceValidateException {
		remover(buscarPorId(id));
	}

	/**
	 * Localiza entidades por vÃ¡rios {@link Criterion}.
	 *
	 * @param criterion
	 *            array com critÃ©rios de busca
	 * @return lista com as entidades encontradas
	 * @throws PersistenceValidateException 
	 * @see #findByCriteria(int, int, Criterion...)
	 */
	private List<T> findByCriteria(Criterion... criterion) throws PersistenceValidateException {
		return findByCriteria(0, 0, criterion);
	}

	/**
	 * Localiza entidades por {@link Criterion}.
	 *
	 * @param firstResult
	 *            posiÃ§Ã£o do registro inicial. A primeira posiÃ§Ã£o Ã© 0. ForneÃ§a
	 *            um valor maior ou igual a 0 (ZERO).
	 * @param maxResult
	 *            quantidade a ser retornada a partir do registro inicial. Caso
	 *            seja informado 0, serÃ£o retornadas todas as instÃ¢ncias a
	 *            partir do especificado em <code>firstResult</code>. ForneÃ§a um
	 *            valor maior ou igual a 0(ZERO).
	 * @param criterion
	 *            critÃ©rios de busca
	 * @return lista com as entidades encontradas
	 * @throws PersistenceValidateException 
	 *
	 * @throws IllegalArgumentException
	 *             caso os parÃ¢metros <code>firstResult</code> e
	 *             <code>maxResult</code> estejam fora do especificado.
	 * @throws DataAccessException
	 *             caso ocorram problemas de acesso ao repositÃ³rio de dados.
	 *
	 * @see HibernateTemplateCrudDao#findAll(int, int)
	 * @see HibernateTemplateCrudDao#findByAttributes(Persistent, int, int)
	 */
	private List<T> findByCriteria(int firstResult, int maxResults, Criterion... criterion) throws PersistenceValidateException {
		List<T> result = null;

		if (firstResult < 0) {
			throw new IllegalArgumentException("Para o parÃ¢metro firstResult, forneÃ§a um valor maior ou igual a zero");
		}
		if (maxResults < 0) {
			throw new IllegalArgumentException("Para o parÃ¢metro maxResult, forneÃ§a um valor maior ou igual a zero");
		}

		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(getPersistentClass());
			for (Criterion c : criterion) {
				criteria.add(c);
			}

			result = executeCriteria(firstResult, maxResults, criteria);
		} catch (Exception e) {
			throw new PersistenceValidateException(e);
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	public List<T> buscarTodosOrdenados(int primeiroRegistro, int quantidadeRegistros, String campo, Boolean ordem) {
		Criteria createCriteria = getSession().createCriteria(getPersistentClass());
		if (campo != null) {
			if (ordem) {
				createCriteria.addOrder(Order.asc(campo));
			} else {
				createCriteria.addOrder(Order.desc(campo));
			}
		}
		
		return createCriteria.setMaxResults(quantidadeRegistros).setFirstResult(primeiroRegistro).list();
	}
}
