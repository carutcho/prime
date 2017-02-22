package br.com.prime.commons.data.persistence;

import java.io.Serializable;

public interface Persistent extends Serializable{

	public Long getId();

    public String getLabel();

    public String getName();
    
}
