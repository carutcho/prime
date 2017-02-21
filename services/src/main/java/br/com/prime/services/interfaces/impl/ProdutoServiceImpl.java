package br.com.prime.services.interfaces.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.commons.controller.AbstractCrudServiceImpl;
import br.com.prime.commons.entity.Produto;
import br.com.prime.data.interfaces.ProdutoDAO;
import br.com.prime.services.interfaces.ProdutoService;

@Service
public class ProdutoServiceImpl extends AbstractCrudServiceImpl<Produto, ProdutoDAO> implements ProdutoService{

	@Autowired
	public ProdutoServiceImpl(ProdutoDAO dao) {
		super(dao);
	}
	
	public void incluirProduto(Produto produto) {
		dao.inserirProduto(produto);
	}

	public void buscarProduto(Long codigo) {
		dao.buscarProduto(codigo);
	}

}
