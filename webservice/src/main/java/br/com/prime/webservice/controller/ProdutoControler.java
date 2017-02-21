package br.com.prime.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.prime.services.interfaces.ProdutoService;

@RestController
public class ProdutoControler  {
	
	@Autowired
	private ProdutoService  service;

	@RequestMapping("/produto/buscar")
	public void buscarProduto(){
		System.out.println("buscou por teste");
		service.buscarProduto(1L);
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
