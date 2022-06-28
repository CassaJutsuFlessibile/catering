package it.uniroma3.siw.spring.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Piatto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(unique = true)
	private String nome;
	
	private String descrizione;
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private List<Ingrediente> ingredienti;
	
	public Piatto() {
		this.ingredienti = new ArrayList<>();
	}

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

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public List<Ingrediente> getIngredienti() {
		return ingredienti;
	}

	public void setIngredienti(List<Ingrediente> ingredienti) {
		this.ingredienti = ingredienti;
	}
	
	public void addIngrediente(Ingrediente ingrediente) {
		this.ingredienti.add(ingrediente);
	}

	public void removeIngrediente(Ingrediente ingrediente) {
		this.ingredienti.remove(ingrediente);
	}
	
	@Override
	public int hashCode() {
		return this.nome.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj.getClass() != Piatto.class)
			return false;
		Piatto that = (Piatto) obj;
		return this.nome.equals(that.getNome());
	}
}
