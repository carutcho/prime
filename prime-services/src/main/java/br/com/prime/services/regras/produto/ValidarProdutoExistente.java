package br.com.prime.services.regras.produto;


import static br.com.prime.commons.utils.Utils.isNotEmpty;

import br.com.prime.commons.entity.Produto;
import br.com.prime.commons.exceptions.ServiceBusinessException;
import br.com.prime.commons.regras.RegraDeNegocioImpl;
import br.com.prime.data.config.MensagensGenericas;
import br.com.prime.data.exception.PersistenceValidateException;
import br.com.prime.data.interfaces.ProdutoDAO;

public class ValidarProdutoExistente extends RegraDeNegocioImpl<Produto> {

	Produto produto;
	ProdutoDAO dao;
	
	public ValidarProdutoExistente(Produto produto, ProdutoDAO dao) {
		this.produto = produto;
		this.dao = dao;
	}
	
	@Override
	public void executar() throws ServiceBusinessException {
		try {
			Produto produtoBanco = dao.buscarPorId(produto.getId());
			if (isNotEmpty(produtoBanco)){
				throw new ServiceBusinessException(getProperties().getProperty("msg.erro.produto.existente"));
			}
		} catch (PersistenceValidateException e) {		
			logger.error(MensagensGenericas.MSG_ERRO_PRODUTO_LOG_CONSULTA + produto.toString());
			throw new ServiceBusinessException(getProperties().getProperty(MensagensGenericas.MSG_ERRO_GENERICA_FALHA_GENERICA));
		}
	}
}
