package br.com.prime.webservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.prime.commons.entity.Produto;
import br.com.prime.commons.exceptions.ServiceBusinessException;
import br.com.prime.services.interfaces.ProdutoService;
import br.com.prime.webservice.controller.base.AbstractCrudBean;

@RestController
@Transactional
public class ProdutoController extends AbstractCrudBean<Produto, ProdutoService>  {

	private Produto produto;

	@Autowired
	public ProdutoController(ProdutoService service) {
		super(service);
	}

	@RequestMapping("/produto/buscar")
	public void buscarProduto(){
		try {
			super.buscarPorId(1L);
		} catch (ServiceBusinessException e) {
			System.out.println(getProperties().getProperty("msg.erro.produto.consultar"));
		}
	}

	@RequestMapping("/produto/inserir")
	public void inserirProduto(){
		try {		
			produto = gerarProduto();
			super.inserir(produto);
			System.out.println(getProperties().getProperty("msg.sucesso.produto.inserir"));
		} catch (ServiceBusinessException e) {
			System.out.println(getProperties().getProperty("msg.erro.produto.inserir"));
		}
	}

	@RequestMapping("/produto/remover")
	public void remover(){
		try {				
			super.remover(produto.getId());
			System.out.println(getProperties().getProperty("msg.sucesso.produto.inserir"));
		} catch (ServiceBusinessException e) {
			System.out.println(getProperties().getProperty("msg.erro.produto.inserir"));
		}
	}
	
	@RequestMapping("/produto/atualizar")
	public Produto atualizar(){
		try {		
			produto = gerarProduto();
			produto = super.atualzar(produto);
			System.out.println(getProperties().getProperty("msg.sucesso.produto.inserir"));
		} catch (ServiceBusinessException e) {
			System.out.println(getProperties().getProperty("msg.erro.produto.inserir"));
		}			
		return produto;
	}
	
	@RequestMapping("/produto/listar")
	public List<Produto> listar(){
		return super.getEntityList();
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
