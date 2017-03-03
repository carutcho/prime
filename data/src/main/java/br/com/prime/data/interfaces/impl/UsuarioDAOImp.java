package br.com.prime.data.interfaces.impl;

import org.springframework.stereotype.Repository;

import br.com.prime.commons.entity.Usuario;
import br.com.prime.data.interfaces.UsuarioDAO;
import br.com.prime.data.persistence.hibernate.HibernateTemplateCrudDao;

@Repository
public class UsuarioDAOImp extends HibernateTemplateCrudDao<Usuario> implements UsuarioDAO{

	private static final long serialVersionUID = 1L;

	public Usuario buscarPorLogin(String login) {
			
		Usuario usuario = (Usuario) getSession()
				.createQuery("SELECT u FROM Usuario u WHERE u.login = :login")
				.setParameter("login", login)
				.uniqueResult();

		return  usuario;
	}
}
