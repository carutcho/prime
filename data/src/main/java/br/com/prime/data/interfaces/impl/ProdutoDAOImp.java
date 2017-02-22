package br.com.prime.data.interfaces.impl;

import org.springframework.stereotype.Repository;

import br.com.prime.commons.entity.Produto;
import br.com.prime.data.exception.PersistenceValidateException;
import br.com.prime.data.interfaces.ProdutoDAO;
import br.com.prime.data.persistence.hibernate.HibernateTemplateCrudDao;

@Repository
public class ProdutoDAOImp extends HibernateTemplateCrudDao<Produto> implements ProdutoDAO{

	private static final long serialVersionUID = 1L;

	public void inserirProduto(Produto produto) throws PersistenceValidateException {
		inserir(produto);
		System.out.println("Inseriu no banco");
	}

	public void buscarProduto(Produto produto) throws PersistenceValidateException {
		buscarPorId(produto.getId());
		System.out.println("Buscou no banco");
	}
}
