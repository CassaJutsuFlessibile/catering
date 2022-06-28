package it.uniroma3.siw.spring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Buffet;
import it.uniroma3.siw.spring.model.Chef;

public interface BuffetRepository extends CrudRepository<Buffet, Long> {

	public boolean existsByNome(String nome);

	public List<Buffet> findAllByChef(Chef chef);

}
