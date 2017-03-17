package br.com.prime.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
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

	@RequestMapping(value = "/inserir", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> inserirProduto(){
		try {		
			setEntity(gerarProduto());
			super.inserir(getEntity());
			return respostaSucesso(HttpStatus.CREATED, getProperties().getProperty("msg.sucesso.produto.inserir"));
		} catch (ServiceBusinessException e) {
			return respostaErro(e.getMessage());
		}
	}

	@RequestMapping(value = "/remover/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<String> removerProduto(@PathVariable("id") long id){
		try {				
			super.remover(id);
			return respostaSucesso(HttpStatus.NO_CONTENT, getProperties().getProperty("msg.sucesso.produto.remover"));
		} catch (ServiceBusinessException e) {
			return respostaErro(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/atualizar", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> atualizarProduto(){
		try {		
			setEntity(gerarProduto());
			return respostaSucesso(HttpStatus.OK, getProperties().getProperty("msg.sucesso.produto.atualizar"), super.atualizar(getEntity()));
		} catch (ServiceBusinessException e) {
			return respostaErro(getProperties().getProperty(e.getMessage()));
		}			
		
	}
	
	@RequestMapping(value = "/listar", method=RequestMethod.GET)
	public ResponseEntity<String> listarProduto(){		
		try {
			return respostaSucesso(super.listar());
		} catch (ServiceBusinessException e) {
			return respostaErro(e.getMessage());
		}
	}

	//TODO: abstrair por entidade !!!! buscar o conversor de entidade que o sergio sugeriu, fazer uma abstração 
	// para caso acha entidadade dentro da entidade e não tiver ID, inserir essa entidade e caso exista, buscar essa 
	//entidade no banco
	private Produto gerarProduto() {
		Produto produto = new Produto();
		produto.setNome("Caneta");
		produto.setDescricao("produto boladão wque usa para escrever!");
		return produto;
	}	
}
