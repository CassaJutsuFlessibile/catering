package it.uniroma3.siw.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.spring.controller.validator.ChefValidator;
import it.uniroma3.siw.spring.model.Chef;
import it.uniroma3.siw.spring.service.ChefService;

@Controller
public class ChefController {

	@Autowired
	private ChefService chefService;

	@Autowired
	private ChefValidator chefValidator;

	@PostMapping("/admin/chef")
	public String addChef(@Validated @ModelAttribute(value="chef") Chef chef, 
			BindingResult bindingResult, Model model) {

		this.chefValidator.validate(chef, bindingResult);

		if (!bindingResult.hasErrors()) {
			this.chefService.save(chef); // salvo un oggetto chef
			model.addAttribute("chef", chef);
			return "chef.html"; 
		}
		else {
			model.addAttribute("chef", chef);
			return "admin/chefForm.html"; 
		}
	}
	
	@GetMapping("/chef/{id}")
	public String getChef(@PathVariable("id")Long id, Model model) {
		Chef chef = chefService.findById(id);
		model.addAttribute("chef", chef);
		return "chef.html";
	}

	@GetMapping("/chefs")
	public String getChefs(Model model) {
		List<Chef> chefs = chefService.findAll();
		model.addAttribute("chefs", chefs);
		return "chefs.html";
	}

	@GetMapping("/admin/chefForm")
	public String chefForm(Model model) {
		model.addAttribute("chef", chefService.createChef());
		return "admin/chefForm.html";
	}
	
	@GetMapping("/admin/chefs/addBuffet") 
	public String addBuffet(Model model) {
		model.addAttribute("chefs", chefService.findAll());
		return "admin/selezionaChef.html";
	}
}

