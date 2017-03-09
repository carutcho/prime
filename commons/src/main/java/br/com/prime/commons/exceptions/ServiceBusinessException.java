package br.com.prime.commons.exceptions;

public class ServiceBusinessException extends Exception{

	private static final long serialVersionUID = 1L;

	public ServiceBusinessException(String message){
		super(message);
	}
}
