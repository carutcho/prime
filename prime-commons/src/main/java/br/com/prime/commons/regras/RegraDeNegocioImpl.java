package br.com.prime.commons.regras;

import static br.com.prime.commons.utils.Utils.isNotEmpty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import br.com.prime.commons.exceptions.ServiceBusinessException;

public abstract class RegraDeNegocioImpl<T> implements RegraDeNegocio{
	
	@Autowired
	private Environment properties;
	
	protected Logger logger = (Logger) LoggerFactory.getLogger(getClass());
	
	private Map<String, Object> dependencias = new HashMap<String, Object>();
	
	public RegraDeNegocioImpl() { }
	
	public RegraDeNegocioImpl(Map<String, Object> dependencias) { 
		this.setDependencias(dependencias);
	}
	
	public void executar() throws ServiceBusinessException { }
	
	public Collection<Object> executarRetornoLista() throws ServiceBusinessException {
		return new ArrayList<Object>();
	}
	
	public Map<Object,Object> executarRetornoMapa() throws ServiceBusinessException {
		return new HashMap<Object, Object>();
	}
	
	public Object executarRetorno() throws ServiceBusinessException {
		return new Object();
	}
	
	@SuppressWarnings("rawtypes")
	public Collection<Object> executarRetornoLista(Collection<RegraDeNegocioImpl> regras) throws ServiceBusinessException {
		
		Collection<Object> retornoRegraCollection = new ArrayList<Object>();

		if (isNotEmpty(regras)){
			for (RegraDeNegocioImpl regraDeNegocio : regras) {
				retornoRegraCollection.add(regraDeNegocio.executarRetorno());
			}
		}
		return retornoRegraCollection;
	}
	
	@SuppressWarnings("rawtypes")
	public Map<Object,Object>  executarRetornoMapa(Collection<RegraDeNegocioImpl> regras) throws ServiceBusinessException {
		return new HashMap<Object, Object>();
	}
	
	@SuppressWarnings("rawtypes")
	public Object executarRetorno(Collection<RegraDeNegocioImpl> regras) throws ServiceBusinessException {
		return new Object();
	}

	@SuppressWarnings("rawtypes")
	public void executar(Collection<RegraDeNegocioImpl> regras) throws ServiceBusinessException {
		if (isNotEmpty(regras)){
			for (RegraDeNegocioImpl regraDeNegocio : regras) {
				regraDeNegocio.executar();
			}
		}
	}

	public void executar(T object) throws ServiceBusinessException {}

	
	public Map<String, Object> getDependencias() {
		return dependencias;
	}

	public void setDependencias(Map<String, Object> dependencias) {
		this.dependencias = dependencias;
	}

	public Environment getProperties() {
		return properties;
	}

	public void setProperties(Environment properties) {
		this.properties = properties;
	}
}
