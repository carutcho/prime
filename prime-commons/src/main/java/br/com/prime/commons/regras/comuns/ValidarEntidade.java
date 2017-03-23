package br.com.prime.commons.regras.comuns;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.springframework.core.env.Environment;

import br.com.prime.commons.data.persistence.Persistent;
import br.com.prime.commons.exceptions.ServiceBusinessException;
import br.com.prime.commons.regras.Validador;

public class ValidarEntidade<T extends Persistent> extends Validador<T> {
	
	private Environment properties;
	
	private T entity;
	
	public ValidarEntidade(T entity, Environment properties) {
		this.entity = (T) entity;
		this.properties = properties;
	}
	
	@Override
	public void executar() throws ServiceBusinessException {
		Set<ConstraintViolation<T>> constraints = getValidator().validate(entity);
		for (ConstraintViolation<?> violation:constraints) {
		    getMensagens().add(properties.getProperty(violation.getMessage()));
		}
		
		if (super.getMensagens().size() > 0){
			throw new ServiceBusinessException(getMensagens());
		}
	}
}

/*List<String> mensagens = new ArrayList<String>();
for (ConstraintViolation violation : e.getConstraintViolations()) {
    if (violation.getConstraintDescriptor() != null) {
        mensagens.add(properties.getProperty(violation.getConstraintDescriptor().getMessageTemplate()));
    }
}		    

if (isNotEmpty(mensagens)){ 				
	throw new ServiceBusinessException(mensagens);
}else{
}*/
//}