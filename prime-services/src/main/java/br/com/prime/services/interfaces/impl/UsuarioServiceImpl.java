package br.com.prime.services.interfaces.impl;

import static br.com.prime.commons.utils.Utils.isEmpty;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.prime.commons.entity.Usuario;
import br.com.prime.data.interfaces.UsuarioDAO;
import br.com.prime.services.base.CrudServiceImpl;
import br.com.prime.services.interfaces.UsuarioService;

@Service
@Transactional
public class UsuarioServiceImpl extends CrudServiceImpl<Usuario, UsuarioDAO> implements UsuarioService, UserDetailsService {

	private static final long serialVersionUID = 1L;

	public UsuarioServiceImpl(UsuarioDAO dao) {
		super(dao);
	}

	public Usuario buscarPorLogin(String login) {
		return dao.buscarPorLogin(login);
	}

	/**
	 *  Neste metodo, podera tratar regras mais complexas de acesso dos perfis (roles)
	 *  No exemplo atual, busca apenas o usuário, com seus perfis e a classe SecurityConfig trata os acessos.
	 *  Podem ser criados metodos complementares para buscar os perfis do usuário como exemplo da utilização de Privileges (do spring) 
	 *  ou outra forma mais complexa de informar o perfil (role) do usuário.
	 */
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

		Usuario usuario = dao.buscarPorLogin(login);
		if (isEmpty(usuario)) {
			throw new UsernameNotFoundException(getProperties().getProperty("msg.erro.usuario.inexistente"));
		}
	
		return usuario;
	}
}
