package br.com.prime.commons.exceptions;

import java.util.ArrayList;
import java.util.Collection;

public class ServiceBusinessException extends Exception{

	private static final long serialVersionUID = 1L;

	private final Collection<String> messages;
	
	public ServiceBusinessException(Collection<String>  messages){
		this.messages = messages;
	}
	
	public ServiceBusinessException(String message) {
		Collection<String> messages = new ArrayList<String>();
		messages.add(message);
		this.messages = messages;
	}

	public Collection<String> getMessages() {
		return messages;
    }
}
