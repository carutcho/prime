package br.com.prime.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.prime.commons.entity.Produto;
import br.com.prime.commons.enums.CodigosResponseEnum;
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
	public String buscarProduto(){
		try {
			return respostaSucesso(super.buscarPorId(1L));
		} catch (ServiceBusinessException e) {
			return respostaErro(getProperties().getProperty("msg.erro.produto.consultar"));
		}
	}

	@RequestMapping("/produto/inserir")
	public String inserirProduto(){
		try {		
			produto = gerarProduto();
			super.inserir(produto);
			return respostaSucesso(CodigosResponseEnum.CREATED, getProperties().getProperty("msg.sucesso.produto.inserir"));
		} catch (ServiceBusinessException e) {
			return respostaErro(getProperties().getProperty("msg.erro.produto.inserir"));
		}
	}

	@RequestMapping("/produto/remover")
	public String remover(){
		try {				
			super.remover(produto.getId());
			return respostaSucesso(CodigosResponseEnum.NOCONTENT, getProperties().getProperty("msg.sucesso.produto.inserir"));
		} catch (ServiceBusinessException e) {
			return respostaErro(getProperties().getProperty("msg.erro.produto.inserir"));
		}
	}
	
	@RequestMapping("/produto/atualizar")
	public String atualizar(){
		try {		
			produto = gerarProduto();
			produto = super.atualzar(produto);
			return respostaSucesso(CodigosResponseEnum.OK, getProperties().getProperty("msg.sucesso.produto.inserir"));
		} catch (ServiceBusinessException e) {
			return respostaErro(getProperties().getProperty("msg.erro.produto.inserir"));
		}			
		
	}
	
	@RequestMapping("/produto/listar")
	public String listar(){
		return respostaSucesso(super.getEntityList());
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
