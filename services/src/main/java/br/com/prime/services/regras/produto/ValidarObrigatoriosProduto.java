package br.com.prime.services.regras.produto;

import static br.com.prime.commons.utils.Utils.*;

import br.com.prime.commons.entity.Produto;
import br.com.prime.commons.exceptions.ServiceBusinessException;
import br.com.prime.commons.regras.RegraDeNegocio;

public class ValidarObrigatoriosProduto extends RegraDeNegocio {

	private static final String NOME_DO_PRODUTO_NÃO_PODE_SER_VAZIO = "msg.erro.produto.nome.vazio";
	
	Produto produto;
	
	public ValidarObrigatoriosProduto(Produto produto) {
		this.produto = produto;
	}
	
	@Override
	public void executar() throws Exception {
		if (isNotEmpty(produto) && isEmpty(produto.getNome())){
			throw new ServiceBusinessException(getProperties().getProperty(NOME_DO_PRODUTO_NÃO_PODE_SER_VAZIO));
		}
	}
}
