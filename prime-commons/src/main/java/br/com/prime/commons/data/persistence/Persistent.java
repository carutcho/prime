package br.com.prime.commons.data.persistence;

import java.io.Serializable;

import com.fasterxml.jackson.databind.ObjectMapper;

public interface Persistent extends Serializable{

	ObjectMapper mapper = new ObjectMapper();
	
	public Long getId();

    public String getLabel();

    public String getName();
}
