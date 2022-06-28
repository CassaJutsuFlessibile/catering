package it.uniroma3.siw.spring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"nome", "origine"}))
public class Ingrediente {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String nome;
	
	private String origine;
	
	private String descrizione;
	
	public Ingrediente() {}

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

	public String getOrigine() {
		return origine;
	}

	public void setOrigine(String origine) {
		this.origine = origine;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	@Override
	public int hashCode() {
		return this.nome.hashCode() + this.origine.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj.getClass() != Ingrediente.class)
			return false;
		Ingrediente that = (Ingrediente) obj;
		return this.nome.equals(that.getNome()) &&
			   this.origine.equals(that.getOrigine());
	}
}
