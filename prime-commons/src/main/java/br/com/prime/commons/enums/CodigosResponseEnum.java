package br.com.prime.commons.enums;

public enum CodigosResponseEnum {
	
	SUCESSO("200");
	
	private String label;
	
	private CodigosResponseEnum(String label){
		this.label = label;
	}
	
	public String getLabel(){
		return label;
	}
}
