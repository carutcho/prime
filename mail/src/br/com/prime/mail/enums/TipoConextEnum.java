package br.com.prime.mail.enums;

public enum TipoConextEnum {
	
	TEXT_HTML("text/html"),
	TEXT_HTML_UTF8("text/html; charset=utf-8");
	
	private String label;
	
	private TipoConextEnum(String label){
		this.label = label;
	}
	
	public String getLabel(){
		return label;
	}
}