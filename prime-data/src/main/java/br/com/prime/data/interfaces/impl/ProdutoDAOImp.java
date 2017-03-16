package br.com.prime.data.interfaces.impl;

import org.springframework.stereotype.Repository;

import br.com.prime.commons.entity.Produto;
import br.com.prime.data.interfaces.ProdutoDAO;
import br.com.prime.data.persistence.hibernate.HibernateTemplateCrudDao;

@Repository
public class ProdutoDAOImp extends HibernateTemplateCrudDao<Produto> implements ProdutoDAO{

	private static final long serialVersionUID = 1L;
}
