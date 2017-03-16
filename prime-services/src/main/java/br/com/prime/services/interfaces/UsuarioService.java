package br.com.prime.services.interfaces;

import br.com.prime.commons.entity.Usuario;
import br.com.prime.services.base.CrudService;

public interface UsuarioService extends CrudService<Usuario>{

	Usuario buscarPorLogin(String login);
}
