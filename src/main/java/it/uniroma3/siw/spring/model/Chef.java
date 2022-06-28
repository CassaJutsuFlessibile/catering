package it.uniroma3.siw.spring.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"nome", "cognome", "nazionalita"}))
public class Chef {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
		
	private String nome;
		
	private String cognome;
	
	private String nazionalita;
	
	@OneToMany(mappedBy = "chef", cascade = {CascadeType.PERSIST, CascadeType.MERGE},
			   fetch = FetchType.EAGER)
	private List<Buffet> buffet;
	
	public Chef(String nome, String cognome, String nazionalita, List<Buffet> buffet) {
		this.nome = nome;
		this.cognome = cognome;
		this.nazionalita = nazionalita;
		this.buffet = buffet;
	}
	
	public Chef() {
		this.buffet = new ArrayList<>();
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

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNazionalita() {
		return nazionalita;
	}

	public void setNazionalita(String nazionalita) {
		this.nazionalita = nazionalita;
	}

	public List<Buffet> getBuffet() {
		return buffet;
	}

	public void setBuffet(List<Buffet> buffet) {
		this.buffet = buffet;
	}
	
	public void addBuffet(Buffet buffet) {
		this.buffet.add(buffet);
	}
	
	public void removeBuffet(Buffet buffet) {
		this.buffet.remove(buffet);
	}

	@Override
	public int hashCode() {
		return this.nome.hashCode() +
			   this.cognome.hashCode() +
			   this.nazionalita.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj.getClass() != Chef.class)
			return false;
		Chef that = (Chef) obj;
		return this.nome.equals(that.getNome()) &&
			   this.cognome.equals(that.getCognome()) &&
			   this.nazionalita.equals(that.getNazionalita());
	}
	
	@Override
	public String toString() {
		return this.nome + " " + this.cognome;
	}
	
}
