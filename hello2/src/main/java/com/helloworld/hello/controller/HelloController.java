package com.helloworld.hello.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/hello")
public class HelloController {
	
	@GetMapping
	public String hello () {
		return "Objetivos de aprendizagem:"
				+ "\n Nesta semana, quer absorver melhor o conteúdo "
				+ "e conseguir me aplicar melhor nas atividades. ";
		
		
	}

}
