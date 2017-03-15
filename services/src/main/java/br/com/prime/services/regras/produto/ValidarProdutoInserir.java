package br.com.prime.services.regras.produto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.prime.commons.entity.Produto;
import br.com.prime.commons.exceptions.ServiceBusinessException;
import br.com.prime.commons.regras.RegraDeNegocio;
import br.com.prime.data.interfaces.ProdutoDAO;

public class ValidarProdutoInserir extends RegraDeNegocio {

	Produto produto;
	ProdutoDAO dao;
	
	public ValidarProdutoInserir(Produto produto, ProdutoDAO dao) {
		this.produto = produto;
		this.dao = dao;
	}
	
	@Override
	public void executar() throws ServiceBusinessException {
		List<RegraDeNegocio> regrasValidar = new ArrayList<RegraDeNegocio>();
		
		//TODO: Desenhar uma estrutura padão de validação com RN onde valida existente (default) e valida Obrgatorios Default
		//Pensar em usar o GetLabel da entidade para ajudar na mensagem padrão!
		//Usar o abstract crud bean para poder fazer as ações de inclusão padrão, podendo ser reescritas.. assim como no FW Regex!
		regrasValidar.add(new ValidarObrigatoriosProduto(produto));
		regrasValidar.add(new ValidarProdutoExistente(produto, dao));
		
		executar(regrasValidar);
	}
	
	@Override
	public void executar(Collection<RegraDeNegocio> regras) throws ServiceBusinessException {
		try {
			super.executar(regras);
		} catch (Exception e) {
			throw new ServiceBusinessException(e.getMessage());
		}
	}
		
}
