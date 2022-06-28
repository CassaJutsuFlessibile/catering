package it.uniroma3.siw.spring.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Buffet;
import it.uniroma3.siw.spring.model.Chef;
import it.uniroma3.siw.spring.repository.ChefRepository;

@Service
public class ChefService {

	@Autowired
	private ChefRepository chefRepository;
	
	@Transactional
	public void save(Chef chef) {
		chefRepository.save(chef);
	}
	
	@Transactional
	public void saveAll(List<Chef> chef) {
		chefRepository.saveAll(chef);
	}
	
	@Transactional
	public void deleteById(Long id) {
		chefRepository.deleteById(id);
	}
	
	@Transactional
	public void delete(Chef chef) {
		chefRepository.delete(chef);
	}
	
	public Chef findById(Long id) {
		return chefRepository.findById(id).get();
	}
	
	public List<Chef> findAll() {
		List<Chef> chef = new ArrayList<>();
		for(Chef c : chefRepository.findAll()) 
			chef.add(c);
		return chef;
	}
	
	public boolean alreadyExists(Chef chef) {
		return chefRepository.existsByNomeAndCognomeAndNazionalita
				(chef.getNome(), chef.getCognome(), chef.getNazionalita());
	}
	
	@Transactional
	public void addBuffet(Chef chef, Buffet buffet) {
		chef.addBuffet(buffet);
		chefRepository.save(chef);
	}
	
	@Transactional
	public void removeBuffetFromChef(Chef chef, Buffet buffet) {
		chef.removeBuffet(buffet);
		chefRepository.save(chef);
	}

	public Chef createChef() {
		return new Chef();
	}
	
}