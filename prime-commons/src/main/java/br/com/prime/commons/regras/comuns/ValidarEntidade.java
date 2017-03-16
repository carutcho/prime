package br.com.prime.commons.regras.comuns;

import java.util.Set;

import javax.validation.ConstraintViolation;

import br.com.prime.commons.data.persistence.Persistent;
import br.com.prime.commons.exceptions.ServiceBusinessException;
import br.com.prime.commons.regras.Validador;

public class ValidarEntidade<T extends Persistent> extends Validador<T> {
	
	private T entity;
	
	public ValidarEntidade(T entity) {
		this.entity = (T) entity;
	}
	
	@Override
	public void executar() throws ServiceBusinessException {
		Set<ConstraintViolation<T>> constraints = getValidator().validate(entity);
		for (ConstraintViolation<?> violation:constraints) {
		    getMensagens().add(violation.getMessage());
		}
		
		if (super.getMensagens().size() > 0){
			throw new ServiceBusinessException(getMensagens());
		}
	}
}
