package it.uniroma3.siw.spring.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Ingrediente;
import it.uniroma3.siw.spring.repository.IngredienteRepository;

@Service
public class IngredienteService {
	
	@Autowired
	private IngredienteRepository ingredienteRepository;
	
	@Transactional
	public void save(Ingrediente ingrediente) {
		ingredienteRepository.save(ingrediente);
	}
	
	@Transactional
	public void saveAll(List<Ingrediente> ingredienti) {
		ingredienteRepository.saveAll(ingredienti);
	}
	
	@Transactional
	public void deleteById(Long id) {
		ingredienteRepository.deleteById(id);
	}
	
	@Transactional
	public void delete(Ingrediente ingrediente) {
		ingredienteRepository.delete(ingrediente);
	}
	
	public Ingrediente findById(Long id) {
		return ingredienteRepository.findById(id).get();
	}
	
	public List<Ingrediente> findAll() {
		List<Ingrediente> ingredienti = new ArrayList<>();
		for(Ingrediente i: ingredienteRepository.findAll()) 
			ingredienti.add(i);
		return ingredienti;
	}
	
	public boolean alreadyExists(Ingrediente ingrediente) {
		return ingredienteRepository.existsByNomeAndOrigine(ingrediente.getNome(), ingrediente.getOrigine());
	}
}
