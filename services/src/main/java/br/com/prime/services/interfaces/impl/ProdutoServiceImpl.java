package br.com.prime.services.interfaces.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import br.com.commons.exceptions.ServiceBusinessException;
import br.com.prime.commons.entity.Produto;
import br.com.prime.data.exception.PersistenceValidateException;
import br.com.prime.data.interfaces.ProdutoDAO;
import br.com.prime.services.base.CrudServiceImpl;
import br.com.prime.services.interfaces.ProdutoService;

@Service
public class ProdutoServiceImpl extends CrudServiceImpl<Produto, ProdutoDAO> implements ProdutoService{

	private static final long serialVersionUID = 1L;
	
	//Mensagens 
	private static final String MSG_ERRO_GENERICA_FALHA_GENERICA = "msg.erro.generica.falha.generica";

	@Autowired
	private Environment ev;
	
	@Autowired
	public ProdutoServiceImpl(ProdutoDAO dao) {
		super(dao);
	}

	public void incluirProduto(Produto produto) throws ServiceBusinessException{
		
		try {
			//TODO: esturar criar RN para este caso de validação validar(produto);
			dao.inserirProduto(produto);
		} catch (PersistenceValidateException e) {		
			throw new ServiceBusinessException(ev.getProperty(MSG_ERRO_GENERICA_FALHA_GENERICA));
		}
	}

	public void buscarProdutoPorId(Long idProduto) throws ServiceBusinessException{
		
		try {
			dao.buscarProdutoPorId(idProduto);
		} catch (PersistenceValidateException e) {
			throw new ServiceBusinessException(ev.getProperty(MSG_ERRO_GENERICA_FALHA_GENERICA));
		}
	}	
}
