package br.com.prime.webservice.controller.base;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import br.com.commons.exceptions.ServiceBusinessException;

public abstract class AbstractCrudServiceImpl <P, D> {
	
	protected final D dao;
	
	private P entity;
	
	private List<P> entityList;
	
	private Class<P> persistentClass;
	
	@SuppressWarnings("unchecked")
	public AbstractCrudServiceImpl(D dao){
		this.dao = dao;
		this.persistentClass = (Class<P>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	protected void entityNewInstance() throws ServiceBusinessException {

		try {
            entity = persistentClass.newInstance();
        } catch (InstantiationException e) {
            throw new ServiceBusinessException("Falha interna, procure um administrador");
            //TODO: LOG
        } catch (IllegalAccessException e) {
        	throw new ServiceBusinessException("Falha interna, procure um administrador");
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

	public D getDao() {
		return dao;
	}	
	
	public D setDao() {
		return dao;
	}
	
}
