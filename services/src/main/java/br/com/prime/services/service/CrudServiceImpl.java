package br.com.prime.services.service;

import java.io.Serializable;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import br.com.commons.exceptions.ServiceBusinessException;
import br.com.prime.data.exception.PersistenceValidateException;
import br.com.prime.data.persistence.CrudDao;
import br.com.prime.data.persistence.Persistent;

/**
 * @author <a href="mailto:gewtonarq@gmail.com">Gewton Jhames</a>
 * @author <a href="mailto:misaelbarreto@gmail.com">Misael Barreto</a>
 * @author <a href="mailto:rrafaelpinto@gmail.com">Rafael Pinto</a>
 * 
 * @param <T>
 *            tipo que serÃ¡ persistido
 */

public abstract class CrudServiceImpl<T extends Persistent, D extends CrudDao<T>> implements CrudService<T> {

	private static final long serialVersionUID = -3584132647978946856L;

	protected Logger log = LoggerFactory.getLogger(getClass());
    
	protected final D dao;

	/**
	 * Cria <code>CrudServiceImpl</code> recebendo uma instancia de {@link CrudDao}.
	 *
	 * @param dao instancia
	 */
	public CrudServiceImpl(D dao) {
		this.dao = dao;
	}

	@Transactional
    public void remover(T entity) throws ServiceBusinessException {
		try {
			dao.remover(entity);
		} catch (PersistenceValidateException e) {
			throw new ServiceBusinessException(e.getMessage());
			//TODO: Por log
		}
	}

    public void remover(Integer id) throws ServiceBusinessException{
		try {
			dao.remover(id);
		} catch (PersistenceValidateException e) {
			throw new ServiceBusinessException(e.getMessage());
			// TODO: Por log
		}
	}

	public Collection<T> listarTodos() throws ServiceBusinessException {
	    try {
	        return dao.listarTodos();
        } catch (PersistenceValidateException e) {
            throw new ServiceBusinessException(e.getMessage());
            //TODO: Por log
        }
	}

    public Collection<T> buscarTodosOrdenados(String posicaoInicial, Boolean quantidadeRegistros){
        return dao.buscarTodosOrdenados(posicaoInicial, quantidadeRegistros);
    }

    public Collection<T> buscarPorRange(final int posicaoInicial, final int quantidadeRegistros) throws ServiceBusinessException {
        try {
            return dao.buscarPorRange(posicaoInicial, quantidadeRegistros);
        } catch (PersistenceValidateException e) {
            throw new ServiceBusinessException(e.getMessage());
        }
	}

	public Collection<T> buscarPorRangeOrdenado(int posicaoInicial, int quantidadeRegistros, String campo, Boolean ordem){
        return dao.buscarPorRangeOrdenado(posicaoInicial, quantidadeRegistros, campo, ordem);
	}

    public Integer totalRegistros() throws ServiceBusinessException {
        try {
			return dao.totalRegistros();
		} catch (PersistenceValidateException e) {
			throw new ServiceBusinessException(e.getMessage());
			//TODO: Por log
		}
	}

    public T buscarPorId(Serializable id) throws ServiceBusinessException {
	    try {
	        return dao.buscarPorId(id);
        } catch (PersistenceValidateException e) {
            throw new ServiceBusinessException(e.getMessage());
        }
	}

    public Class<T> getPersistentClass() {
		return dao.getPersistentClass();
	}

    @Transactional
    public void inserir(T entity) throws ServiceBusinessException {
        try {
            dao.inserir(entity);
        } catch (PersistenceValidateException e) {
            throw new ServiceBusinessException(e.getMessage());
            //TODO: Por log
        }
    }


    @Transactional
    public void atualizar(T entity) throws ServiceBusinessException {
        try {
            dao.atualizar(entity);
        } catch (PersistenceValidateException e) {
            throw new ServiceBusinessException(e.getMessage());
            //TODO: Por log
        }
    }


    public void validar(T entity) throws ServiceBusinessException {
		try {
            dao.validar(entity);
        } catch (PersistenceValidateException e) {
            throw new ServiceBusinessException(e.getMessage());
        }
	}

}
