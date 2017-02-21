package br.com.prime.data.interfaces.impl;

import br.com.prime.commons.entity.Produto;
import br.com.prime.data.interfaces.ProdutoDAO;

public class ProdutoDAOImp implements ProdutoDAO{

	public void inserirProduto(Produto produto) {
		System.out.println("Inseriu no banco");
	}

	public void buscarProduto(Long id) {
		System.out.println("Buscou no banco");
	}
}
