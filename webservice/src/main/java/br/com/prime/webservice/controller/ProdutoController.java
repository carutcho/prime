package br.com.prime.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.prime.commons.entity.Produto;
import br.com.prime.services.interfaces.ProdutoService;
import br.com.prime.webservice.controller.base.AbstractCrudBean;

@RestController
@Transactional
@RequestMapping(value = "/produto", headers = "Accept=application/json", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ProdutoController extends AbstractCrudBean<Produto, ProdutoService>  {

	@Autowired
	public ProdutoController(ProdutoService service) {
		super(service);
	}
}
