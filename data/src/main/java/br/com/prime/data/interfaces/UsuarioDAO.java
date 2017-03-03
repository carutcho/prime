package br.com.prime.data.interfaces;

import br.com.prime.commons.entity.Usuario;
import br.com.prime.data.persistence.CrudDao;

public interface UsuarioDAO extends CrudDao<Usuario> {

	Usuario buscarPorLogin(String login);

}
