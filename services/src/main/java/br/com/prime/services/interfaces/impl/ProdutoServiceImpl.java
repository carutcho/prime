package br.com.prime.services.interfaces.impl;

import org.springframework.stereotype.Service;

import br.com.prime.commons.entity.Produto;
import br.com.prime.data.interfaces.ProdutoDAO;
import br.com.prime.data.interfaces.impl.ProdutoDAOImp;
import br.com.prime.services.interfaces.ProdutoService;

@Service
public class ProdutoServiceImpl implements ProdutoService{

	ProdutoDAO dao = new ProdutoDAOImp();
	
	public void incluirProduto(Produto produto) {
		dao.inserirProduto(produto);
	}

	public void buscarProduto(Long codigo) {
		dao.buscarProduto(codigo);
	}

}
