package it.uniroma3.siw.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.spring.controller.validator.CredentialsValidator;
import it.uniroma3.siw.spring.controller.validator.UserValidator;
import it.uniroma3.siw.spring.model.Credentials;
import it.uniroma3.siw.spring.model.User;
import it.uniroma3.siw.spring.service.CredentialsService;
import it.uniroma3.siw.spring.service.UserService;

@Controller
public class AuthenticationController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CredentialsService credentialsService;
	
	@Autowired
	private UserValidator userValidator;
	
	@Autowired
	private CredentialsValidator credentialsValidator;
	
	@GetMapping("/login")
	public String getLoginPage(Model model) {
		return "loginForm.html";
	}
	
	@GetMapping("/logout") 
	public String logout(Model model) {
		return "index.html";
	}
	
	@GetMapping("/register")
	public String getRegisterPage(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("credentials", new Credentials());
		return "registerUser.html";
	}
	
	@PostMapping("/register")
	public String newUser(@Validated @ModelAttribute(value = "credentials") Credentials credentials,
			BindingResult userBindingResult, @Validated @ModelAttribute(value = "user") User user,  
			BindingResult credentialsBindingResult ,Model model) {
		this.userValidator.validate(user, userBindingResult);
		this.credentialsValidator.validate(credentials, credentialsBindingResult);
		if(!userBindingResult.hasErrors() && !credentialsBindingResult.hasErrors()) {
			credentials.setUser(user);
            credentialsService.saveCredentials(credentials);
            return "registrationSuccessful.html";
		}
		return "registerUser.html";
	}
	
    @GetMapping("/default")
    public String defaultAfterLogin(Model model) {
    	UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
    	if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
            return "admin/home.html";
        }
        return "home.html";
    }
    

	@GetMapping("/home")
	public String homePage(Model model) {
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
    	if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
            return "admin/home.html";
        }
        return "home.html";
	}
}
