package br.com.prime.data.persistence.hibernate;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.prime.commons.data.persistence.Persistent;
import br.com.prime.data.exception.PersistenceValidateException;
import br.com.prime.data.persistence.CrudDao;

@Transactional
public abstract class HibernateTemplateCrudDao<T extends Persistent> implements CrudDao<T>{

	private static final long serialVersionUID = 7815903224454495303L;

	//Mensagens
	private static final String MSG_ERRO_GENERICA_FALHA_GENERICA 	= "msg.erro.generica";
	private static final String MSG_ERRO_GENERICA_REGSTRO_EXISTENTE = "msg.erro.generica.regstro.existente";
	private static final String MSG_ERRO_GENERICA_FALHA_INSERIR 	= "msg.erro.generica.falha.inserir";
	private static final String MSG_ERRO_GENERICA_FALHA_REMOVER 	= "msg.erro.generica.falha.remover";
	private static final String MSG_ERRO_GENERICA_FALHA_ATUALIZAR 	= "msg.erro.generica.falha.atualizar";
	private static final String MSG_ERRO_PARAMENTO_MENOR_ZERO		= "msg.erro.generica.paramento.menor.zero";

	@Autowired
	private Environment ev;
	
	protected Logger log = LoggerFactory.getLogger(getClass());

	ObjectMapper mapper = new ObjectMapper();
	
	@PersistenceContext(name="genEntityManagerFactory") 
	private EntityManager manager;

	private final Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public HibernateTemplateCrudDao() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	protected Session getSession() {
		return manager.unwrap(Session.class);
	}

	public void validarInseir(T entity) throws PersistenceValidateException {
		T entidade = buscarPorId(entity.getId());
		if (entidade != null){			
			throw new PersistenceValidateException(ev.getProperty(MSG_ERRO_GENERICA_REGSTRO_EXISTENTE), null);			
		}
	}

	public void validarAtualizar(T entity) throws PersistenceValidateException {
		T entidade = buscarPorId(entity.getId());
		if (entidade == null){			
			throw new PersistenceValidateException(ev.getProperty(MSG_ERRO_GENERICA_FALHA_ATUALIZAR), null);			
		}
	}
	
	public T inserir(T entity) throws PersistenceValidateException {
		try {
			validarInseir(entity);
			getSession().save(entity);
			return buscarPorId(entity.getId());
		} catch (PersistenceValidateException e) {
			throw new PersistenceValidateException(ev.getProperty(MSG_ERRO_GENERICA_FALHA_INSERIR), e.getCause());
		} catch (org.springframework.dao.DataAccessException e) {
			log.warn(ev.getProperty(MSG_ERRO_GENERICA_FALHA_INSERIR) + entity);
			throw new PersistenceValidateException(ev.getProperty(MSG_ERRO_GENERICA_FALHA_INSERIR), e.getCause());
		} catch (Exception e) {
			log.error(ev.getProperty(MSG_ERRO_GENERICA_FALHA_INSERIR) + entity + e.getCause());
			throw new PersistenceValidateException(ev.getProperty(MSG_ERRO_GENERICA_FALHA_INSERIR), e.getCause());
		}
	}

	public void atualizar(T entity) throws PersistenceValidateException {
		try {
			validarAtualizar(entity);
			getSession().merge(entity);
		} catch (PersistenceValidateException e) {
			throw new PersistenceValidateException(ev.getProperty(MSG_ERRO_GENERICA_FALHA_ATUALIZAR), e.getCause());
		} catch (org.springframework.dao.DataAccessException e) {
			log.warn(ev.getProperty(MSG_ERRO_GENERICA_FALHA_ATUALIZAR) + entity);
			throw new PersistenceValidateException(ev.getProperty(MSG_ERRO_GENERICA_FALHA_ATUALIZAR), e.getCause());
		} catch (Exception e) {
			log.error(ev.getProperty(MSG_ERRO_GENERICA_FALHA_ATUALIZAR) + entity + e.getCause());
			throw new PersistenceValidateException(ev.getProperty(MSG_ERRO_GENERICA_FALHA_ATUALIZAR), e.getCause());
		}
	}

	/*@SuppressWarnings("unchecked")
	public T buscarPorId(Serializable id) throws PersistenceValidateException {
		try {
			return (T) getSession().buscarPorId(id);
		} catch (org.springframework.dao.DataAccessException e) {
			//TODO: verificar se data acess é questao de acesso a busca (permissao de usuario, caso seja ajusta a menagem de erro)
			log.warn(ev.getProperty(MSG_ERRO_GENERICA_FALHA_GENERICA) + id);
			throw new PersistenceValidateException(ev.getProperty(MSG_ERRO_GENERICA_FALHA_GENERICA), e.getCause());
		}
	}*/

	public Integer totalRegistros() throws PersistenceValidateException {
		try {
			Long result = (Long) getSession().createQuery("select count(*) from " + persistentClass.getName() + " persistent ")
											.setMaxResults(1)
											.uniqueResult();
			
			return (result != null) ? result.intValue() : 0;
		} catch (Exception e) {
			log.error(ev.getProperty(MSG_ERRO_GENERICA_FALHA_GENERICA) + e.getCause());
			throw new PersistenceValidateException(ev.getProperty(MSG_ERRO_GENERICA_FALHA_GENERICA), e.getCause());
		}
	}

	public List<T> listarTodos() throws PersistenceValidateException {
		return findByCriteria();
	}

	@SuppressWarnings("unchecked")
	public T buscarPorId(Long id) throws PersistenceValidateException {
		try {
			Criteria criteria = getSession().createCriteria(getPersistentClass()).add(Restrictions.eqOrIsNull("id", id));
			return (T) criteria.uniqueResult();
		} catch (Exception e) {
			log.error(ev.getProperty(MSG_ERRO_GENERICA_FALHA_GENERICA) + e.getCause());
			throw new PersistenceValidateException(ev.getProperty(MSG_ERRO_GENERICA_FALHA_GENERICA), e.getCause());
		}
	}
		
	@SuppressWarnings("unchecked")
	public List<T> buscarTodosOrdenados(String campo, Boolean ordem) throws PersistenceValidateException {
		try {
			Criteria criteria = getSession().createCriteria(getPersistentClass());
			if (campo != null) {
				if (ordem) {
					criteria.addOrder(Order.asc(campo));
				} else {
					criteria.addOrder(Order.desc(campo));
				}
			}
			
			return criteria.list();
		} catch (Exception e) {
			log.error(ev.getProperty(MSG_ERRO_GENERICA_FALHA_GENERICA) + e.getCause());
			throw new PersistenceValidateException(ev.getProperty(MSG_ERRO_GENERICA_FALHA_GENERICA), e.getCause());
		}
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

	protected int countFromQuery(String query) {
		return countFromQuery(query, null);
	}

	public void remover(T entity) throws PersistenceValidateException {
		try {
			getSession().delete(entity);
		} catch (org.springframework.dao.DataAccessException e) {
			log.warn(ev.getProperty(MSG_ERRO_GENERICA_FALHA_REMOVER) + entity);
			throw new PersistenceValidateException(ev.getProperty(MSG_ERRO_GENERICA_FALHA_REMOVER), e.getCause());
		} catch (Exception e) {
			log.error(ev.getProperty(MSG_ERRO_GENERICA_FALHA_REMOVER) + entity + e.getCause());
			throw new PersistenceValidateException(ev.getProperty(MSG_ERRO_GENERICA_FALHA_REMOVER), e.getCause());
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
			throw new PersistenceValidateException(ev.getProperty(MSG_ERRO_PARAMENTO_MENOR_ZERO), null);
		}
		if (maxResults < 0) {
			throw new PersistenceValidateException(ev.getProperty(MSG_ERRO_PARAMENTO_MENOR_ZERO), null);
		}

		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(getPersistentClass());
			for (Criterion c : criterion) {
				criteria.add(c);
			}

			result = executeCriteria(firstResult, maxResults, criteria);
		} catch (Exception e) {
			log.error(ev.getProperty(MSG_ERRO_GENERICA_FALHA_REMOVER) + e.getCause());
			throw new PersistenceValidateException(ev.getProperty(MSG_ERRO_GENERICA_FALHA_REMOVER), e.getCause());
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	public List<T> buscarPorRangeOrdenado(int primeiroRegistro, int quantidadeRegistros, String campo, Boolean ordem) {
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
