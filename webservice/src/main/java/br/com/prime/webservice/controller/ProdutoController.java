package br.com.prime.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.prime.commons.entity.Produto;
import br.com.prime.commons.exceptions.ServiceBusinessException;
import br.com.prime.services.interfaces.ProdutoService;
import br.com.prime.webservice.controller.base.AbstractCrudBean;

@RestController
@Transactional
@RequestMapping(value = "/produto/", headers = "Accept=application/json", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ProdutoController extends AbstractCrudBean<Produto, ProdutoService>  {

	@Autowired
	public ProdutoController(ProdutoService service) {
		super(service);
	}

	@RequestMapping(value = "/buscar/{id}", method = RequestMethod.GET)
	public ResponseEntity<String> buscarProduto(@PathVariable("id") long id){
		try {
			return respostaSucesso(super.buscarPorId(id));
		} catch (ServiceBusinessException e) {
			return respostaErro(e.getMessage());
		}
	}

/*	@RequestMapping(value = "/inserir", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> inserirProduto(@RequestBody Produto produto){
		try {								
			return respostaSucesso(HttpStatus.CREATED, getProperties().getProperty("msg.sucesso.produto.inserir"), super.inserir(produto));
		} catch (ServiceBusinessException e) {
			return respostaErro(e.getMessage());
		}
	}
*/
	/*@RequestMapping(value = "/remover/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<String> removerProduto(@PathVariable("id") long id){
		try {				
			super.remover(id);
			return respostaSucesso(HttpStatus.NO_CONTENT, getProperties().getProperty("msg.sucesso.produto.remover"));
		} catch (ServiceBusinessException e) {
			return respostaErro(e.getMessage());
		}
	}*/
	
	/*@RequestMapping(value = "/atualizar", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> atualizarProduto(@RequestBody Produto produto){
		try {					
			return respostaSucesso(HttpStatus.OK, getProperties().getProperty("msg.sucesso.produto.atualizar"), super.atualizar(produto));
		} catch (ServiceBusinessException e) {
			return respostaErro(e.getMessages());
		}			
	}*/
	
	@RequestMapping(value = "/listar", method=RequestMethod.GET)
	public ResponseEntity<String> listarProduto(){		
		try {
			return respostaSucesso(super.listar());
		} catch (ServiceBusinessException e) {
			return respostaErro(e.getMessage());
		}
	}

}
