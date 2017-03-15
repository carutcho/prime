package br.com.prime.services.interfaces.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.prime.commons.entity.Produto;
import br.com.prime.commons.exceptions.ServiceBusinessException;
import br.com.prime.data.exception.PersistenceValidateException;
import br.com.prime.data.interfaces.ProdutoDAO;
import br.com.prime.services.base.CrudServiceImpl;
import br.com.prime.services.interfaces.ProdutoService;
import br.com.prime.services.regras.produto.ValidarProdutoInserir;

@Service
public class ProdutoServiceImpl extends CrudServiceImpl<Produto, ProdutoDAO> implements ProdutoService{

	private static final long serialVersionUID = 1L;
	
	//Mensagens 
	private static final String MSG_ERRO_GENERICA_FALHA_GENERICA = "msg.erro.generica.falha.generica";

	@Autowired
	public ProdutoServiceImpl(ProdutoDAO dao) {
		super(dao);
	}

	public void incluirProduto(Produto produto) throws ServiceBusinessException{
		
		try {
			ValidarProdutoInserir validar = new ValidarProdutoInserir(produto, dao);
			validar.executar();
			
			dao.inserirProduto(produto);
		} catch (PersistenceValidateException e) {		
			throw new ServiceBusinessException(getProperties().getProperty(MSG_ERRO_GENERICA_FALHA_GENERICA));
		}
	}

	public void buscarProdutoPorId(Long idProduto) throws ServiceBusinessException{
		
		try {
			dao.buscarProdutoPorId(idProduto);
		} catch (PersistenceValidateException e) {
			throw new ServiceBusinessException(getProperties().getProperty(MSG_ERRO_GENERICA_FALHA_GENERICA));
		}
	}	
}
