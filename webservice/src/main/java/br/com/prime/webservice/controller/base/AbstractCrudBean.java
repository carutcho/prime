package br.com.prime.webservice.controller.base;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import br.com.prime.commons.exceptions.ServiceBusinessException;

public abstract class AbstractCrudBean <P, S> {

	
	@Autowired
	private Environment properties;
	
	private final S service;
	
	private P entity;
	
	private List<P> entityList;
	
	private Class<P> persistentClass;
	
	@SuppressWarnings("unchecked")
	public AbstractCrudBean(S service){
		this.service = service;
		this.persistentClass = (Class<P>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	protected void entityNewInstance() throws ServiceBusinessException {

		try {
            entity = persistentClass.newInstance();
        } catch (InstantiationException e) {
        	throw new ServiceBusinessException(getProperties().getProperty("msg.erro.generica.falha.generica"));
            //TODO: LOG
        } catch (IllegalAccessException e) {
        	throw new ServiceBusinessException(getProperties().getProperty("msg.erro.generica.falha.generica"));
            //TODO: LOG
        }
    }

	public P getEntity() {
		return entity;
	}

	public void setEntity(P entity) {
		this.entity = entity;
	}

	public List<P> getEntityList() {
		return entityList;
	}

	public void setEntityList(List<P> entityList) {
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
}
