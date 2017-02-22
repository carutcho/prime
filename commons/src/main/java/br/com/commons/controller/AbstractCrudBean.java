package br.com.commons.controller;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import br.com.commons.exceptions.ServiceBusinessException;

public abstract class AbstractCrudBean <P, S> {
	
	//TODO: internacionalizar
	private static final String FALHA_AO_GENERICA = "Falha ao realizar a operação, contate um adm";

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
            throw new ServiceBusinessException(FALHA_AO_GENERICA);
            //TODO: LOG
        } catch (IllegalAccessException e) {
        	throw new ServiceBusinessException(FALHA_AO_GENERICA);
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
}
