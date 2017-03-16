package br.com.prime.commons.regras;

import br.com.prime.commons.exceptions.ServiceBusinessException;

public interface RegraDeNegocio {

	public void executar() throws ServiceBusinessException;
	
}
