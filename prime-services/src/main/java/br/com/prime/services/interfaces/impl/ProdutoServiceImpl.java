package br.com.prime.services.interfaces.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.prime.commons.entity.Produto;
import br.com.prime.data.interfaces.ProdutoDAO;
import br.com.prime.services.base.CrudServiceImpl;
import br.com.prime.services.interfaces.ProdutoService;

@Service
public class ProdutoServiceImpl extends CrudServiceImpl<Produto, ProdutoDAO> implements ProdutoService{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	public ProdutoServiceImpl(ProdutoDAO dao) {
		super(dao);
	}

}
