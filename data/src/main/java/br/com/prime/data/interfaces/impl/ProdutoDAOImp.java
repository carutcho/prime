package br.com.prime.data.interfaces.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.prime.commons.entity.Produto;
import br.com.prime.data.interfaces.ProdutoDAO;

@Repository
public class ProdutoDAOImp implements ProdutoDAO{

	@PersistenceContext
	private EntityManager manager;
	
	public void inserirProduto(Produto produto) {
		manager.persist(produto);
		System.out.println("Inseriu no banco");
	}

	public void buscarProduto(Long id) {
		System.out.println("Buscou no banco");
	}
}
