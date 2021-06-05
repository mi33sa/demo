package com.example.demo.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.repository.Coffeerepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class HomeController {
	
	private final ApplicationContext appContext;
	
	@GetMapping("/")
	public String showList(Model model) {
		Coffeerepository repository = (Coffeerepository)appContext.getBean("coffeerepository");
		model.addAttribute("toString", this.toString());
		model.addAttribute("allCoffee", repository.findAll());
		
		return "index";
	}

}
