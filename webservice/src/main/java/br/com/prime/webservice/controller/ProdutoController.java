package br.com.prime.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.commons.controller.AbstractCrudBean;
import br.com.commons.exceptions.ServiceBusinessException;
import br.com.prime.commons.entity.Produto;
import br.com.prime.services.interfaces.ProdutoService;

@RestController
@Transactional
public class ProdutoController extends AbstractCrudBean<Produto, ProdutoService>  {

	
	@Autowired
	private Environment ev;
	
	@Autowired
	public ProdutoController(ProdutoService service) {
		super(service);
	}

	@RequestMapping("/produto/buscar")
	public void buscarProduto(){
		try {
			getService().buscarProdutoPorId(1L);
		} catch (ServiceBusinessException e) {
			System.out.println(ev.getProperty("msg.erro.produto.consultar"));
		}
	}
	

	@RequestMapping("/produto/incluir")
	public void incluirProduto(){
		
		Produto produto = gerarProduto();
		try {
			getService().incluirProduto(produto);
			System.out.println(ev.getProperty("msg.sucesso.produto.inserir"));
		} catch (ServiceBusinessException e) {
			System.out.println(ev.getProperty("msg.erro.produto.inserir"));
		}
	}

	private Produto gerarProduto() {
		Produto produto = new Produto();
		produto.setNome("Caneta");
		produto.setDescricao("produto boladão wque usa para escrever!");
		return produto;
	}
	
}
