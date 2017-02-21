package br.com.prime.data.interfaces;

import br.com.prime.commons.entity.Produto;

public interface ProdutoDAO {

	public void inserirProduto(Produto produto);
	
	public void buscarProduto(Long id);
}
