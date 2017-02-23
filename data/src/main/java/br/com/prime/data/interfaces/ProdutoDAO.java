package br.com.prime.data.interfaces;

import br.com.prime.commons.entity.Produto;
import br.com.prime.data.exception.PersistenceValidateException;
import br.com.prime.data.persistence.CrudDao;

public interface ProdutoDAO extends CrudDao<Produto> {

	public void inserirProduto(Produto produto) throws PersistenceValidateException;
	
	public void buscarProdutoPorId(Long produto) throws PersistenceValidateException;
}
