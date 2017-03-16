package br.com.prime.commons.regras;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public abstract class Validador<T> extends RegraDeNegocioImpl<T>{
	
	private List<String> mensagens = new ArrayList<String>();
	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private Validator validator = getFactory().getValidator();
	
	public List<String> getMensagens() {
		return mensagens;
	}
	
	public void setMensagens(List<String> mensagens) {
		this.mensagens = mensagens;
	}
	
	public ValidatorFactory getFactory() {
		return factory;
	}
	
	public void setFactory(ValidatorFactory factory) {
		this.factory = factory;
	}
	
	public Validator getValidator() {
		return validator;
	}
	
	public void setValidator(Validator validator) {
		this.validator = validator;
	}
}
