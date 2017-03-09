package br.com.prime.services.interfaces;

import br.com.prime.commons.entity.Produto;
import br.com.prime.commons.exceptions.ServiceBusinessException;
import br.com.prime.services.base.CrudService;

public interface ProdutoService extends CrudService<Produto>{

	public void incluirProduto(Produto produto) throws ServiceBusinessException;

	public void buscarProdutoPorId(Long produto) throws ServiceBusinessException;
}
