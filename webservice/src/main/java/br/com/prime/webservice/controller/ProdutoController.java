package br.com.prime.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.commons.controller.AbstractCrudBean;
import br.com.commons.exceptions.ServiceBusinessException;
import br.com.prime.commons.entity.Produto;
import br.com.prime.services.interfaces.ProdutoService;

//TODO: testar property
//@PropertySource(value ="casspath:teste.propertiers")
@RestController
@Transactional
public class ProdutoController extends AbstractCrudBean<Produto, ProdutoService>  {

	//TODO: property testar !!!!!
	/*@Autowired
	private Environment environment;
	@PropertySource(value = "classpath:hibernate-dev.properties")
	*/
	
	@Autowired
	public ProdutoController(ProdutoService service) {
		super(service);
	}

	@RequestMapping("/produto/buscar")
	public void buscarProduto(){
		
		try {
			getService().buscarProduto(new Produto());
			System.out.println("buscou por teste");
		} catch (ServiceBusinessException e) {
			System.out.println("Falha ao buscar");
		}
	}
	

	@RequestMapping("/produto/incluir")
	public void incluirProduto(){
		System.out.println("Incluindo o produto");
		Produto produto = new Produto();
		produto.setNome("Caneta");
		produto.setDescricao("produto boladão wque usa para escrever!");
		try {
			getService().incluirProduto(produto);
			System.out.println("buscou por teste");
		} catch (ServiceBusinessException e) {
			System.out.println("buscou por teste");
		}
	}
	
/*	@GetMapping("/produto/buscar/{id}")	
	public void buscarProdutoGeral(@PathVariable("id") Long id){
		System.out.println("buscou sem teste");
		service.buscarProduto(id);
	}
	
	@PostMapping(value = "/produto/criar")
	public void criarProduto(@RequestBody Produto produto){
		service.incluirProduto(new Produto());
	}*/
	
}
