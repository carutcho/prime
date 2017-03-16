package br.com.prime.commons.enums;

public enum CodigosResponseEnum {
	
	OK("200"),
	CREATED("201"),
	NOCONTENT("204"),
	ERRO("400"),
	UNAUTHORIZED("401"), //sem acesso a pagina
	FORBIDDEN("403"), 	 //sem acesso a uma funcionalidade da pagina
	NOTFOUND("404"),
	METHODNOTALLOWED("405"), //Metodo nao permitido (cabecalho mal desenvolvido ou metodo n existente)
	INTERNALSERVERERROR("500"); //FUDEU erro que n tem como usuario corrigir.
	
	private String label;
	
	private CodigosResponseEnum(String label){
		this.label = label;
	}
	
	public String getLabel(){
		return label;
	}
}
