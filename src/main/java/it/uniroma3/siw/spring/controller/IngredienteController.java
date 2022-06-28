package it.uniroma3.siw.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siw.spring.service.IngredienteService;

@Controller
public class IngredienteController {
	
	@Autowired
	private IngredienteService ingredienteService;
	
	@GetMapping("ingrediente/{ingredienteId}")
	public String getIngrediente(@PathVariable("ingredienteId") Long id, Model model) {
		model.addAttribute("ingrediente", ingredienteService.findById(id));
		return "ingrediente.html";
	}
}
