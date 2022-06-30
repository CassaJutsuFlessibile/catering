package it.uniroma3.siw.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.spring.controller.validator.PiattoValidator;
import it.uniroma3.siw.spring.model.Piatto;
import it.uniroma3.siw.spring.service.PiattoService;

@Controller
public class PiattoController {
	
	@Autowired
	private PiattoService piattoService;
	@Autowired PiattoValidator piattoValidator;
	
	@GetMapping("piatto/{piattoId}")
	public String getPiatto(@PathVariable("piattoId") Long id, Model model) {
		model.addAttribute("piatto", this.piattoService.findById(id));
		return "piatto.html";
	}
	
	@GetMapping("/admin/piattoForm")
	public String piattoForm(Model model) {
		Piatto p = new Piatto();
		model.addAttribute("piatto", p);
		return "admin/piattoForm.html";
	}
	
	@PostMapping("/admin/piatto")
	public String addPiatto(@Validated @ModelAttribute(value="chef") Piatto piatto, 
			BindingResult bindingResult, Model model) {

		this.piattoValidator.validate(piatto, bindingResult);

		if (!bindingResult.hasErrors()) {
			this.piattoService.save(piatto); // salvo un oggetto piatto
			model.addAttribute("piatto", piatto);
			return "piatto.html"; 
		}
		else {
			model.addAttribute("piatto", piatto);
			return "admin/chefForm.html"; 
		}
	}
}
