package it.uniroma3.siw.spring.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Ingrediente;
import it.uniroma3.siw.spring.model.Piatto;
import it.uniroma3.siw.spring.repository.PiattoRepository;

@Service
public class PiattoService {
	
	@Autowired
	private PiattoRepository piattoRepository;
	
	@Transactional
	public void save(Piatto piatto) {
		piattoRepository.save(piatto);
	}
	
	@Transactional
	public void saveAll(List<Piatto> piatti) {
		piattoRepository.saveAll(piatti);
	}
	
	@Transactional
	public void deleteById(Long id) {
		piattoRepository.deleteById(id);
	}
	
	@Transactional
	public void delete(Piatto piatto) {
		piattoRepository.delete(piatto);
	}
	
	public Piatto findById(Long id) {
		return piattoRepository.findById(id).get();
	}
	
	public List<Piatto> findAll() {
		List<Piatto> piatti = new ArrayList<>();
		for(Piatto p : piattoRepository.findAll())
			piatti.add(p);
		return piatti;
	}
	
	public boolean alreadyExists(Piatto piatto) {
		return piattoRepository.existsByNome(piatto.getNome());
	}
	
	@Transactional
	public void addIngrediente(Piatto piatto, Ingrediente ingrediente) {
		piatto.addIngrediente(ingrediente);
		piattoRepository.save(piatto);
	}

	@Transactional
	public void removeIngredienteFromPiatto(Piatto piatto, Ingrediente ingrediente) {
		piatto.removeIngrediente(ingrediente);
		piattoRepository.save(piatto);
	}

	public List<Piatto> findPiattiNotInBuffet(List<Piatto> piattiPresenti) {
		List<Piatto> piatti = this.findAll();
		for(Piatto p : piattiPresenti)
			piatti.remove(p);
		return piatti;
	}
}
