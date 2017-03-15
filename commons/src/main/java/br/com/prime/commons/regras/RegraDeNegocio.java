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

public abstract class RegraDeNegocio{
	
	@Autowired
	private Environment properties;
	
	protected Logger logger = (Logger) LoggerFactory.getLogger(getClass());
	
	private Object retornoRegra;

	private Map<String, Object> dependencias = new HashMap<String, Object>();
	
	public RegraDeNegocio() { }
	
	public RegraDeNegocio(Map<String, Object> dependencias) { 
		this.setDependencias(dependencias);
	}
	
	private Collection<Object> retornoRegraCollection = new ArrayList<Object>();
	
	private Map<Object, Object> retornoRegraMap;
	
	public void executar() throws Exception { }
	
	public Collection<Object> executarRetornoLista() throws Exception {
		return retornoRegraCollection;
	}
	
	public Map<Object,Object> executarRetornoMapa() throws Exception {
		return retornoRegraMap;
	}
	
	public Object executarRetorno() throws Exception {
		return retornoRegra;
	}
	
	public Collection<Object> executarRetornoLista(Collection<RegraDeNegocio> regras) throws Exception {
		
		if (isNotEmpty(regras)){
			for (RegraDeNegocio regraDeNegocio : regras) {
				retornoRegraCollection.add(regraDeNegocio.executarRetorno());
			}
		}
		return retornoRegraCollection;
	}
	
	public Map<Object,Object>  executarRetornoMapa(Collection<RegraDeNegocio> regras) throws Exception {
		return retornoRegraMap;
	}
	
	public Object executarRetorno(Collection<RegraDeNegocio> regras) throws Exception {
		return retornoRegra;
	}

	public void executar(Collection<RegraDeNegocio> regras) throws Exception {
		if (isNotEmpty(regras)){
			for (RegraDeNegocio regraDeNegocio : regras) {
				regraDeNegocio.executar();
			}
		}
	}

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
