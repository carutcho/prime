package br.com.prime.commons.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.prime.commons.data.persistence.Persistent;

@Entity
public class Perfil implements Persistent, GrantedAuthority{
    
	private static final long serialVersionUID = -1666485144169961305L;

    @Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
        
    @Column(name = "nome", nullable = false)
    private String nome;
    
    @Column(name = "sigla")
    private String sigla;
    
    @Column(name = "admin")
    boolean admin;
    
    @Column(name="datacadastro")
	private Date dataCadastro;
    
    @ManyToOne
	@JoinColumn(name="idusuariocadastro")
	private Usuario usuarioCadastro;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Usuario getUsuarioCadastro() {
		return usuarioCadastro;
	}

	public void setUsuarioCadastro(Usuario usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}

	public String getLabel() {
		return "Perfil";
	}

	public String getName() {
		return null;
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
		Perfil other = (Perfil) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String getAuthority() {
		return sigla;
	}

	public String toString(){
		try {			
			return mapper.writeValueAsString("["+getLabel()+"]"+ this);
		} catch (JsonProcessingException e) {
			return null;
		}
	}
	
}
