package it.uniroma3.siw.spring.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.spring.model.Buffet;
import it.uniroma3.siw.spring.service.BuffetService;

@Component
public class BuffetValidator implements Validator{

	@Autowired
	private BuffetService buffetService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Buffet.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(this.buffetService.alreadyExists((Buffet)target)) {
			errors.reject("buffet.duplicato");
		}
	}

}
