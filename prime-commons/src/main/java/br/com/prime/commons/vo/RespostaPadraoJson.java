package br.com.prime.commons.vo;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.http.HttpStatus;

public class RespostaPadraoJson implements Serializable {

	private static final long serialVersionUID = 1L;

	private HttpStatus codigo;
	private Collection<Object> retorno;
	private String mensagem;
	
	public RespostaPadraoJson(HttpStatus codigo) {
		this.codigo = codigo;
	}
	
	public RespostaPadraoJson(HttpStatus codigo, String mensagem) {
		this.codigo = codigo;
		this.mensagem = mensagem;
	}
	
	public RespostaPadraoJson(HttpStatus codigo, String mensagem, Collection<Object> retorno) {
		this.codigo = codigo;
		this.mensagem = mensagem;
		this.retorno = retorno;
	}
	
	public RespostaPadraoJson(HttpStatus codigo, Collection<Object> retorno) {
		this.codigo = codigo;
		this.retorno = retorno;
	}

	public Collection<Object> getRetorno() {
		return retorno;
	}
	
	public void setRetorno(Collection<Object> retorno) {
		this.retorno = retorno;
	}
	
	public HttpStatus getCodigo() {
		return codigo;
	}
	
	public void setCodigo(HttpStatus codigo) {
		this.codigo = codigo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}
