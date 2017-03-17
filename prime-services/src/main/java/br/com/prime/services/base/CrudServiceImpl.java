package br.com.prime.services.base;

import java.io.Serializable;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.Transactional;

import br.com.prime.commons.data.persistence.Persistent;
import br.com.prime.commons.exceptions.ServiceBusinessException;
import br.com.prime.commons.regras.RegraDeNegocio;
import br.com.prime.commons.regras.comuns.ValidarEntidade;
import br.com.prime.data.exception.PersistenceValidateException;
import br.com.prime.data.persistence.CrudDao;

public abstract class CrudServiceImpl<T extends Persistent, D extends CrudDao<T>> implements CrudService<T> {

	private static final long serialVersionUID = -3584132647978946856L;

	@Autowired
	private Environment properties;
	
	protected Logger log = LoggerFactory.getLogger(getClass());
    
	protected final D dao;

	public CrudServiceImpl(D dao) {
		this.dao = dao;
	}

	@Transactional
    public void remover(T entity) throws ServiceBusinessException {
		try {
			dao.remover(entity);
		} catch (PersistenceValidateException e) {

			if (entity!=null){
				log.error("metodo: remover - " + entity.toString() + " - Exception :[" + e.getMessage() + "] - Cause:[" + e.getCause() +"]");
        	}else{
        		log.error("metodo: totalregistros - [entidade nula] - Exception :[" + e.getMessage() + "] - Cause:[" + e.getCause() +"]");
        	}
			throw new ServiceBusinessException(e.getMessages());
		}
	}

    public void remover(Long id) throws ServiceBusinessException{
		try {
			dao.remover(id);
		} catch (PersistenceValidateException e) {
			log.error("metodo: remover - id: [" + id + "] - Exception :[" + e.getMessage() + "] - Cause:[" + e.getCause() +"]");
			throw new ServiceBusinessException(e.getMessages());
		}
	}

	public Collection<T> listarTodos() throws ServiceBusinessException {
	    try {
	        return dao.listarTodos();
        } catch (PersistenceValidateException e) {
        	log.error("metodo: listarTodos - Exception :[" + e.getMessage() + "] - Cause:[" + e.getCause() +"]");
            throw new ServiceBusinessException(e.getMessages());
        }
	}

    public Collection<T> buscarTodosOrdenados(String campo, Boolean quantidadeRegistros){
        return dao.buscarTodosOrdenados(campo, quantidadeRegistros);
    }

    public Collection<T> buscarPorRange(final int posicaoInicial, final int quantidadeRegistros) throws ServiceBusinessException {
        try {
            return dao.buscarPorRange(posicaoInicial, quantidadeRegistros);
        } catch (PersistenceValidateException e) {
        	log.error("metodo: buscarPorRange - posIni[" + posicaoInicial + "] e qtdRegistros: ["+ quantidadeRegistros +"] - Exception :[" + e.getMessage() + "] - Cause:[" + e.getCause() +"]");
            throw new ServiceBusinessException(e.getMessages());
        }
	}

	public Collection<T> buscarPorRangeOrdenado(int posicaoInicial, int quantidadeRegistros, String campo, Boolean ordem){
        return dao.buscarPorRangeOrdenado(posicaoInicial, quantidadeRegistros, campo, ordem);
	}

    public Integer totalRegistros() throws ServiceBusinessException {
        try {
			return dao.totalRegistros();
		} catch (PersistenceValidateException e) {
			log.error("metodo: totalregistros - Exception :[" + e.getMessage() + "] - Cause:[" + e.getCause() +"]");
			throw new ServiceBusinessException(e.getMessages());
		}
	}

    public T buscarPorId(Serializable id) throws ServiceBusinessException {
	    try {
	        return dao.buscarPorId(id);
        } catch (PersistenceValidateException e) {
        	log.error("metodo: buscarPorId - id: " + id + " - Exception :[" + e.getMessage() + "] - Cause:[" + e.getCause() +"]");
            throw new ServiceBusinessException(e.getMessages());
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
        	if (entity != null){
        		log.error("metodo: inserir - " + entity.toString() + " - Exception :[" + e.getMessage() + "] - Cause:[" + e.getCause() +"]");
        	}else{
        		log.error("metodo: totalregistros - [entidade nula] - Exception :[" + e.getMessage() + "] - Cause:[" + e.getCause() +"]");
        	}
            throw new ServiceBusinessException(e.getMessages());
        }
    }


    @Transactional
    public T atualizar(T entity) throws ServiceBusinessException {
        try {
            dao.atualizar(entity);
            return dao.buscarPorId(entity.getId());
        } catch (PersistenceValidateException e) {
        	
        	if (entity!=null){
        		log.error("metodo: atualizar - " + entity.toString() + " - Exception :[" + e.getMessage() + "] - Cause:[" + e.getCause() +"]");
        	}else{
        		log.error("metodo: totalregistros - [entidade nula] - Exception :[" + e.getMessage() + "] - Cause:[" + e.getCause() +"]");
        	}
            throw new ServiceBusinessException(e.getMessages());
        }
    }

    public void validar(T entity) throws ServiceBusinessException {
    	RegraDeNegocio regras = new ValidarEntidade<T>(entity);
		regras.executar();
	}

	protected Environment getProperties() {
		return properties;
	}
}
