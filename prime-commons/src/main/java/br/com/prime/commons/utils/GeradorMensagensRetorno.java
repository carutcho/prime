package br.com.prime.commons.utils;

import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import br.com.prime.commons.enums.CodigosResponseEnum;
import br.com.prime.commons.vo.RespostaPadraoJson;

public class GeradorMensagensRetorno {

	ObjectWriter mapper = new ObjectMapper().writerWithDefaultPrettyPrinter();

	protected String respostaErro(String mensagem) {
		try {
			return mapper.writeValueAsString(new RespostaPadraoJson(CodigosResponseEnum.ERRO, mensagem));
		} catch (JsonProcessingException e) {
			return null;
		}
	}

	protected String respostaErro(CodigosResponseEnum codigo, String mensagem) {
		try {
			return mapper.writeValueAsString(new RespostaPadraoJson(codigo, mensagem));
		} catch (JsonProcessingException e) {
			return null;
		}
	}

	protected String respostaSucesso(CodigosResponseEnum codigo, String mensagem) {
		try {
			return mapper.writeValueAsString(new RespostaPadraoJson(codigo, mensagem));
		} catch (JsonProcessingException e) {
			return null;
		}
	}

	protected String respostaSucesso(String mensagem) {
		try {
			return mapper.writeValueAsString(new RespostaPadraoJson(CodigosResponseEnum.OK, mensagem));
		} catch (JsonProcessingException e) {
			return null;
		}
	}

	protected String respostaSucesso(Object retorno) {
		Collection<Object> objRetorno = new ArrayList<Object>();
		objRetorno.add(retorno);
		try {
			return mapper.writeValueAsString(new RespostaPadraoJson(CodigosResponseEnum.OK, objRetorno));
		} catch (JsonProcessingException e) {
			return null;
		}
	}

	protected String respostaSucesso(Collection<Object> retorno) {
		try {
			return mapper.writeValueAsString(new RespostaPadraoJson(CodigosResponseEnum.OK, retorno));
		} catch (JsonProcessingException e) {
			return null;
		}
	}

	protected String respostaSucesso(String mensagem, Object retorno) {
		Collection<Object> erro = new ArrayList<Object>();
		erro.add(retorno);
		try {
			return mapper.writeValueAsString(new RespostaPadraoJson(CodigosResponseEnum.OK, mensagem, erro));
		} catch (JsonProcessingException e) {
			return null;
		}
	}

	protected String respostaSucesso(CodigosResponseEnum codigo, String mensagem, Object retorno) {
		Collection<Object> erro = new ArrayList<Object>();
		erro.add(retorno);
		try {
			return mapper.writeValueAsString(new RespostaPadraoJson(codigo, mensagem, erro));
		} catch (JsonProcessingException e) {
			return null;
		}
	}

	protected String respostaSucesso(CodigosResponseEnum codigo, String mensagem, Collection<Object> retorno) {
		try {
			return mapper.writeValueAsString(new RespostaPadraoJson(codigo, mensagem, retorno));
		} catch (JsonProcessingException e) {
			return null;
		}
	}

	protected String respostaSucesso(String mensagem, Collection<Object> retorno) {
		try {
			return mapper.writeValueAsString(new RespostaPadraoJson(CodigosResponseEnum.OK, mensagem, retorno));
		} catch (JsonProcessingException e) {
			return null;
		}
	}

}
