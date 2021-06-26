package com.helloworld.hello.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/hello")
public class HelloController {
	
	@GetMapping
	public String hello () {
		return "Habilidade e mentalidades utilizadas nessa aprendizagem foram:"
				+ "\n Persistência e Mentalidade de Crescimento ";
		
		
	}

}
