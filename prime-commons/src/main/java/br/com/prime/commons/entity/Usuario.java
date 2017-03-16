package br.com.prime.commons.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.prime.commons.data.persistence.Persistent;
import br.com.prime.crypto.CryptoBcypt;
import br.com.prime.crypto.interfaces.Crypto;

@Entity
public class Usuario implements Persistent, UserDetails, Cloneable{
    
	private static final long serialVersionUID = 1L;

	public Usuario(){
		
	}
	
	public Usuario(
		 	Long id,
			String nome,
			String login,
			String senha,
			boolean ativo,
			List<Perfil> perfis, 
			String descricao,
			Date ultimaAtualizacao
	    ) {
		 	this.id = id;
			this.nome = nome;
			this.login = login;
			this.senha = senha;
			this.ativo = ativo;
			this.perfis = perfis;
			this.descricao = descricao;
			this.setUltimaAtualizacao(ultimaAtualizacao);
	    }
	
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "generic_seq_gen")
    @SequenceGenerator(name = "generic_seq_gen", sequenceName = "usuarios_seq")
	private Long id;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="login")
	private String login;
	
	@Column(name="senha")
	private String senha;
	
	@Column(name = "ativo")
    private Boolean ativo;
	
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Perfil> perfis = new ArrayList<Perfil>();
	
	@Column(name="descricao")
    private String descricao;
    
	@Column(name="ultimaatualizacao")
    private Date ultimaAtualizacao;	
	
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

	public String getNome() {
		return nome;
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		Crypto bCrypt = new CryptoBcypt();
		this.senha = bCrypt.encriptarToString(senha);
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public List<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String getLabel() {
		return "Usuário";
	}

	public String getName() {
		return null;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return getPerfis();
	}

	public String getPassword() {
		return getSenha();
	}

	public String getUsername() {
		return getLogin();
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return getAtivo();
	}

	public Date getUltimaAtualizacao() {
		return ultimaAtualizacao;
	}

	public void setUltimaAtualizacao(Date ultimaAtualizacao) {
		this.ultimaAtualizacao = ultimaAtualizacao;
	}
	
	@Override
	public Usuario clone(){
		try {
			Usuario u = (Usuario) super.clone();
			u.setSenha("******");
			u.perfis = new ArrayList<Perfil>(perfis);
			return u;
		} catch (CloneNotSupportedException e) {
			return null;
		}
		
	}
	
	public String toString(){
		try {			
			return mapper.writeValueAsString("["+getLabel()+"]"+ this.clone());
		} catch (JsonProcessingException e) {
			return null;
		}
	}
}