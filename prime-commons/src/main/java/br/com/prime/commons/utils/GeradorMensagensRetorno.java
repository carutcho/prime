package br.com.prime.commons.utils;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import br.com.prime.commons.vo.RespostaPadraoJson;

public class GeradorMensagensRetorno {

	ObjectWriter mapper = new ObjectMapper().writerWithDefaultPrettyPrinter();

	protected ResponseEntity<String> respostaErro(String mensagem) {
		String mensagemRetorno = null;
		try {
			mensagemRetorno = mapper.writeValueAsString(new RespostaPadraoJson(HttpStatus.BAD_REQUEST, mensagem));
		} catch (JsonProcessingException e) {
			mensagemRetorno = null;
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagemRetorno);
	}

	protected ResponseEntity<String> respostaErro(HttpStatus codigo, String mensagem) {
		String mensagemRetorno = null;
		try {
			mensagemRetorno = mapper.writeValueAsString(new RespostaPadraoJson(codigo, mensagem));
		} catch (JsonProcessingException e) {}
		
		return ResponseEntity.status(codigo).body(mensagemRetorno);
	}

	protected ResponseEntity<String> respostaSucesso(HttpStatus codigo, String mensagem) {
		String mensagemRetorno = null;
		try {
			mensagemRetorno = mapper.writeValueAsString(new RespostaPadraoJson(codigo, mensagem));
		} catch (JsonProcessingException e) {}
		return ResponseEntity.status(codigo).body(mensagemRetorno);
	}

	protected ResponseEntity<String> respostaSucesso(String mensagem) {
		String mensagemRetorno = null;
		try {
			mensagemRetorno = mapper.writeValueAsString(new RespostaPadraoJson(HttpStatus.OK, mensagem));
		} catch (JsonProcessingException e) { }
		return ResponseEntity.status(HttpStatus.OK).body(mensagemRetorno);
	}

	protected ResponseEntity<String> respostaSucesso(Object retorno) {
		String mensagemRetorno = null;
		Collection<Object> objRetorno = new ArrayList<Object>();
		objRetorno.add(retorno);
		try {
			mensagemRetorno = mapper.writeValueAsString(new RespostaPadraoJson(HttpStatus.OK, objRetorno));
		} catch (JsonProcessingException e) { }
		return ResponseEntity.status(HttpStatus.OK).body(mensagemRetorno);
	}

	protected ResponseEntity<String> respostaSucesso(Collection<Object> retorno) {
		String mensagemRetorno = null;
		try {
			mensagemRetorno = mapper.writeValueAsString(new RespostaPadraoJson(HttpStatus.OK, retorno));
		} catch (JsonProcessingException e) {}
		return ResponseEntity.status(HttpStatus.OK).body(mensagemRetorno);
	}

	protected ResponseEntity<String> respostaSucesso(String mensagem, Object retorno) {
		String mensagemRetorno = null;
		Collection<Object> erro = new ArrayList<Object>();
		erro.add(retorno);
		try {
			mensagemRetorno = mapper.writeValueAsString(new RespostaPadraoJson(HttpStatus.OK, mensagem, erro));
		} catch (JsonProcessingException e) {}
		
		return ResponseEntity.status(HttpStatus.OK).body(mensagemRetorno);
	}

	protected ResponseEntity<String> respostaSucesso(HttpStatus codigo, String mensagem, Object retorno) {
		String mensagemRetorno = null;
		Collection<Object> objRetorno = new ArrayList<Object>();
		objRetorno.add(retorno);
		try {
			mensagemRetorno = mapper.writeValueAsString(new RespostaPadraoJson(codigo, mensagem, objRetorno));
		} catch (JsonProcessingException e) {}
		
		return ResponseEntity.status(codigo).body(mensagemRetorno);
	}

	protected ResponseEntity<String> respostaSucesso(HttpStatus codigo, String mensagem, Collection<Object> retorno) {
		String mensagemRetorno = null;
		try {
			mensagemRetorno = mapper.writeValueAsString(new RespostaPadraoJson(codigo, mensagem, retorno));
		} catch (JsonProcessingException e) { }
		
		return ResponseEntity.status(codigo).body(mensagemRetorno);
	}

	protected ResponseEntity<String> respostaSucesso(String mensagem, Collection<Object> retorno) {
		String mensagemRetorno = null;
		try {
			mensagemRetorno = mapper.writeValueAsString(new RespostaPadraoJson(HttpStatus.OK, mensagem, retorno));
		} catch (JsonProcessingException e) {}
		
		return ResponseEntity.status(HttpStatus.OK).body(mensagemRetorno);
	}

}
