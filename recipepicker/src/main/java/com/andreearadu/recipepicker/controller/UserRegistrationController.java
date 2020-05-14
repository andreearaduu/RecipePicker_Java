package com.andreearadu.recipepicker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.andreearadu.recipepicker.dto.UserDto;
import com.andreearadu.recipepicker.service.UserService;
import com.andreearadu.recipepicker.validator.UserValidator;

@Controller
public class UserRegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;
    
    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
    	model.addAttribute("user", new UserDto());
    	
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUserAccount(@ModelAttribute("user") UserDto userDto, BindingResult result) {
    	
    	userValidator.validate(userDto, result);
    	
        UserDto existing = userService.getUserByEmail(userDto.getEmail());
        
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }

        if (result.hasErrors()) {
            return "registration";
        }

        userService.save(userDto);
        
        return "redirect:/registration?success";
    }
    
    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }
	
	@GetMapping({"/home","/"})
	public String homeApp() {
		return "/home";
	}
	
	@GetMapping({"/addRecipe"})
	public String addRecipe() {
		return "/addRecipe";
	}
	
	@GetMapping({"/admin"})
	public String adminSection() {
		return "/admin";
	}
	
}