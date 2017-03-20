package br.com.prime.commons.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.http.HttpStatus;

public class RespostaPadraoJson implements Serializable {

	private static final long serialVersionUID = 1L;

	private HttpStatus codigo;
	private Collection<Object> retorno;
	private Collection<String> mensagens;
	
	public RespostaPadraoJson(HttpStatus codigo) {
		this.codigo = codigo;
	}
	
	public RespostaPadraoJson(HttpStatus codigo, String mensagem) {
		this.codigo = codigo;
		this.mensagens = gerarMensagem(mensagem);
	}
	
	public RespostaPadraoJson(HttpStatus codigo, String mensagem, Collection<Object> retorno) {
		this.codigo = codigo;
		this.mensagens = gerarMensagem(mensagem);
		this.retorno = retorno;
	}
	
	private List<String> gerarMensagem(String mensagem){
		List<String> mensagens = new ArrayList<String>();
		mensagens.add(mensagem);
		return mensagens;
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

	public Collection<String> getMensagens() {
		return mensagens;
	}

	public void setMensagem(Collection<String> mensagens) {
		this.mensagens = mensagens;
	}
}
