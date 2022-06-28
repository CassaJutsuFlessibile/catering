package it.uniroma3.siw.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siw.spring.service.PiattoService;

@Controller
public class PiattoController {
	
	@Autowired
	private PiattoService piattoService;
	
	@GetMapping("piatto/{piattoId}")
	public String getPiatto(@PathVariable("piattoId") Long id, Model model) {
		model.addAttribute("piatto", this.piattoService.findById(id));
		return "piatto.html";
	}
}
