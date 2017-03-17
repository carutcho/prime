package br.com.prime.webservice.controller.base;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import br.com.prime.commons.data.persistence.Persistent;
import br.com.prime.commons.exceptions.ServiceBusinessException;
import br.com.prime.commons.utils.GeradorMensagensRetorno;
import br.com.prime.services.base.CrudService;
import br.com.prime.services.base.Service;

public abstract class AbstractCrudBean <P, S> extends GeradorMensagensRetorno{

	
	@Autowired
	private Environment properties;
	
	private final S service;
	
	Service servico;
	
	private P entity;
	
	private Collection<P> entityList;
	
	private Class<P> persistentClass;
	
	protected Logger log = LoggerFactory.getLogger(getClass());
	

	@SuppressWarnings("unchecked")
	public AbstractCrudBean(S service){
		this.service = service;
		this.servico = (Service) service;
		this.persistentClass = (Class<P>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	@PostConstruct
	protected void initAbstract(){
		try {
			entityNewInstance();
		} catch (ServiceBusinessException e) {}
	}
	
	protected void entityNewInstance() throws ServiceBusinessException {

		try {
            entity = persistentClass.newInstance();
        } catch (InstantiationException e) {
        	log.error("metodo: entityNewInstance - [entidade nula] - Exception :[" + e.getMessage() + "] - Cause:[" + e.getCause() +"]");
        	throw new ServiceBusinessException(getProperties().getProperty("msg.erro.generica.falha.generica"));
        } catch (IllegalAccessException e) {
        	//TODO: LOG... vlidar se IllegaAcessException é falha de acesso de login, caso seja, enviar mensagem de erro
        	// de acesso ao inves de genérica
        	log.error("metodo: entityNewInstance - [entidade nula] - Exception :[" + e.getMessage() + "] - Cause:[" + e.getCause() +"]");
        	throw new ServiceBusinessException(getProperties().getProperty("msg.erro.generica.falha.generica"));
        }
    }

	public P getEntity() {
		return entity;
	}

	public void setEntity(P entity) {
		this.entity = entity;
	}

	public Collection<P> getEntityList() throws ServiceBusinessException {
		return listar(getEntity());
	}

	public void setEntityList(Collection<P> entityList) {
		this.entityList = entityList;
	}

	public Class<P> getPersistentClass() {
		return persistentClass;
	}

	public void setPersistentClass(Class<P> persistentClass) {
		this.persistentClass = persistentClass;
	}

	public S getService() {
		return service;
	}

	public Environment getProperties() {
		return properties;
	}

	public void setProperties(Environment properties) {
		this.properties = properties;
	}
	
	public void inserir() throws ServiceBusinessException{
		this.inserir(getEntity());
	}

	@SuppressWarnings("unchecked")
	public void inserir(P entity) throws ServiceBusinessException {
		((CrudService<Persistent>) servico).inserir((Persistent) entity);
	}

	@SuppressWarnings("unchecked")
	public P buscarPorId(Long id) throws ServiceBusinessException {
		return (P) ((CrudService<Persistent>) servico).buscarPorId(id);
	}
	
	@SuppressWarnings("unchecked")	
	public void remover(Long id) throws ServiceBusinessException {
		((CrudService<Persistent>) servico).remover(id);
	}
	
	public void remover() throws ServiceBusinessException {
		remover(getEntity());
	}

	@SuppressWarnings("unchecked")
	public void remover(P entity) throws ServiceBusinessException {
		((CrudService<Persistent>) servico).remover((Persistent) entity);
	}

	public P atualizar() throws ServiceBusinessException {
		return atualizar(getEntity());
	}
	
	@SuppressWarnings("unchecked")
	public P atualizar(P entity) throws ServiceBusinessException {
		return (P) ((CrudService<Persistent>) servico).atualizar((Persistent) entity);
	}
	
	@SuppressWarnings("unchecked")
	public Collection<P> listar(P entity) throws ServiceBusinessException {
		return (Collection<P>) ((CrudService<Persistent>) service).buscarTodosOrdenados("id", true);
	}
	
	public Collection<P> listar() throws ServiceBusinessException {
		return listar(getEntity());
	}
}
