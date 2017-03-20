package br.com.prime.commons.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.prime.commons.data.persistence.Persistent;

@Entity
@Table(name = "produto", uniqueConstraints = { @UniqueConstraint( columnNames = {"id"} ) })
public class Produto implements Persistent{
    
	private static final long serialVersionUID = 1L;

	@Id 
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;
	
	@NotEmpty
	@Column(name="nome")
	private String nome;
	
	@NotEmpty
	@Column(name="descricao")
    private String descricao;
    
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String getLabel() {
		return "Produto";
	}

	public String getName() {
		return null;
	}
	
	
}