package br.com.prime.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import br.com.prime.commons.entity.Usuario;
import br.com.prime.services.interfaces.UsuarioService;
import br.com.prime.webservice.controller.base.AbstractCrudBean;

@RestController
@Transactional
public class UsuarioController extends AbstractCrudBean<Usuario, UsuarioService>  {

	@Autowired
	public UsuarioController(UsuarioService service) {
		super(service);
	}

}
