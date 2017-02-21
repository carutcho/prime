package br.com.prime.services.interfaces;

import br.com.prime.commons.entity.Produto;

public interface ProdutoService{

	public void incluirProduto(Produto codigo);

	public void buscarProduto(Long codigo);
}
