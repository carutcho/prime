package br.com.prime.commons.vo;

import java.io.Serializable;
import java.util.Collection;

import br.com.prime.commons.enums.CodigosResponseEnum;

public class RespostaPadraoJson implements Serializable {

	private static final long serialVersionUID = 1L;

	private CodigosResponseEnum codigo;
	private Collection<Object> retorno;
	private String mensagem;
	
	public RespostaPadraoJson(CodigosResponseEnum codigo) {
		this.codigo = codigo;
	}
	
	public RespostaPadraoJson(CodigosResponseEnum codigo, String mensagem) {
		this.codigo = codigo;
		this.mensagem = mensagem;
	}
	
	public RespostaPadraoJson(CodigosResponseEnum codigo, String mensagem, Collection<Object> retorno) {
		this.codigo = codigo;
		this.mensagem = mensagem;
		this.retorno = retorno;
	}
	
	public RespostaPadraoJson(CodigosResponseEnum codigo, Collection<Object> retorno) {
		this.codigo = codigo;
		this.retorno = retorno;
	}

	public Collection<Object> getRetorno() {
		return retorno;
	}
	
	public void setRetorno(Collection<Object> retorno) {
		this.retorno = retorno;
	}
	
	public CodigosResponseEnum getCodigo() {
		return codigo;
	}
	
	public void setCodigo(CodigosResponseEnum codigo) {
		this.codigo = codigo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}
