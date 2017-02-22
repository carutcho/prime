package br.com.prime.data.exception;

import java.util.Collection;

public class PersistenceValidateException extends Exception {

    private static final long serialVersionUID = 1L;

    private Collection<String> messages;

    public PersistenceValidateException(String message) {
        super(message);
    }

    public PersistenceValidateException(String message, Throwable cause) {
        super(message);
    }    
    
    public PersistenceValidateException(Collection<String> messages) {
        setMessages(messages);
    }

    public PersistenceValidateException(Throwable cause) {
        super(cause);
    }

    public Collection<String> getMessages() {
        return messages;
    }

    public void setMessages(Collection<String> messages) {
        this.messages = messages;
    }

    public String getMessage() {
        if(messages==null) {
            return super.getMessage();
        }
        String retorno = "";
        for (String msg : messages) {
            retorno += msg + " ";
        }
        return retorno.trim();
    }

}
