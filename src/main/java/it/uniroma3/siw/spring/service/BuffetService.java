package it.uniroma3.siw.spring.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Buffet;
import it.uniroma3.siw.spring.model.Chef;
import it.uniroma3.siw.spring.model.Piatto;
import it.uniroma3.siw.spring.repository.BuffetRepository;

@Service
public class BuffetService {

	@Autowired
	private BuffetRepository buffetRepository;
	
	@Autowired
	private PiattoService piattoService;
	
	@Autowired
	private ChefService chefService;
	
	@Transactional
	public void save(Buffet buffet, Chef chef) {
		buffet.setChef(chef);
		chef.addBuffet(buffet);
		buffetRepository.save(buffet);
	}
	
	@Transactional
	public void saveAll(List<Buffet> buffet) {
		buffetRepository.saveAll(buffet);
	}
	
	@Transactional
	public void deleteById(Long id) {
		buffetRepository.deleteById(id);
	}
	
	@Transactional
	public void delete(Buffet buffet) {
		buffetRepository.delete(buffet);
	}
	
	public Buffet findById(Long id) {
		return buffetRepository.findById(id).get();
	}
	
	public List<Buffet> findAll() {
		List<Buffet> buffet = new ArrayList<>();
		for(Buffet b : buffetRepository.findAll())
			buffet.add(b);
		return buffet;
	}
	
	public boolean alreadyExists(Buffet buffet) {
		return buffetRepository.existsByNome(buffet.getNome());
	}
	
	public List<Buffet> findAllByChef(Chef chef) {
		List<Buffet> buffets = new ArrayList<Buffet>();
		for(Buffet b : buffetRepository.findAllByChef(chef)) {
			buffets.add(b);
		}
		return buffets;
	}
	
	@Transactional
	public void addPiatto(Buffet buffet, Piatto piatto) {
		buffet.addPiatto(piatto);
		buffetRepository.save(buffet);
	}

	@Transactional
	public void removePiattoFromBuffet(Long buffetId, Long piattoId) {
		Buffet buffet = this.findById(buffetId);
		Piatto piatto = piattoService.findById(piattoId);
		buffet.removePiatto(piatto);
		buffetRepository.save(buffet);
	}
	
	public List<Piatto> findPiattiNotInBuffet(Long idBuffet) {
		Buffet buffet = this.findById(idBuffet);
		return piattoService.findPiattiNotInBuffet(buffet.getPiatti());
	}

	public Buffet createBuffet() {
		return new Buffet();
	}

	@Transactional
	public void addPiattoToBuffet(Long buffetId, Long piattoId) {
		Buffet buffet = this.findById(buffetId);
		Piatto piatto = piattoService.findById(piattoId);
		buffet.addPiatto(piatto);
		buffetRepository.save(buffet);
	}

	@Transactional
	public void removeBuffet(Long buffetId) {
		Buffet buffet = this.findById(buffetId);
		buffet.setPiatti(new ArrayList<>());
		chefService.removeBuffetFromChef(buffet.getChef(), buffet);
		this.delete(buffet);
	}
}
