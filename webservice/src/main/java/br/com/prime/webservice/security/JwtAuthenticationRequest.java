package br.com.prime.webservice.security;

import java.io.Serializable;

public class  JwtAuthenticationRequest implements Serializable {

    private static final long serialVersionUID = -8445943548965154778L;

    private String usuario;
    private String senha;

    public JwtAuthenticationRequest() {
        super();
    }

    public JwtAuthenticationRequest(String usuario, String senha) {
        this.setUsuario(usuario);
        this.setSenha(senha);
    }

    public String getUsuario() {
        return this.usuario;
    }

    public void setUsuario(String username) {
        this.usuario = username;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String password) {
        this.senha = password;
    }
}
