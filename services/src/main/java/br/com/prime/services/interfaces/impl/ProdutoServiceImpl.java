package br.com.prime.services.interfaces.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.commons.exceptions.ServiceBusinessException;
import br.com.prime.commons.entity.Produto;
import br.com.prime.data.exception.PersistenceValidateException;
import br.com.prime.data.interfaces.ProdutoDAO;
import br.com.prime.services.base.CrudServiceImpl;
import br.com.prime.services.interfaces.ProdutoService;

@Service
public class ProdutoServiceImpl extends CrudServiceImpl<Produto, ProdutoDAO> implements ProdutoService{

	//TODO: internacionalizar
	private static final String FALHA_AO_GENERICA = "Falha ao realizar a operação, contate um adm";
	
	private static final long serialVersionUID = 1L;

	@Autowired
	public ProdutoServiceImpl(ProdutoDAO dao) {
		super(dao);
	}
	
	public void incluirProduto(Produto produto) throws ServiceBusinessException{
		
		try {
			dao.inserirProduto(produto);
		} catch (PersistenceValidateException e) {		
			throw new ServiceBusinessException(FALHA_AO_GENERICA);
		}
	}

	public void buscarProduto(Produto produto) throws ServiceBusinessException{
		
		try {
			dao.buscarProduto(produto);
		} catch (PersistenceValidateException e) {
			throw new ServiceBusinessException(FALHA_AO_GENERICA);
		}
	}
}
