package com.games.Loja.de.Games.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.games.Loja.de.Games.model.Categoria;
import com.games.Loja.de.Games.repository.CategoriaRepository;
import com.games.Loja.de.Games.service.CategoriaServices;

@RestController
@RequestMapping("/categoria")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController {
	
	@Autowired
	private CategoriaRepository repositoryC;
	
	@Autowired
	private CategoriaServices services;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> GetAll (){
		return ResponseEntity.ok(repositoryC.findAll());
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<Categoria> GetById(@PathVariable Long id) 
    {
        return repositoryC.findById(id)
                .map(resp -> ResponseEntity.ok(resp))
                .orElse(ResponseEntity.notFound().build());
    }
	@GetMapping("/PegarTodos")
	public ResponseEntity<List<Categoria>> pegarTodos() {
		return ResponseEntity.status(200).body(repositoryC.findAll());
	}
	
	@PostMapping
	public ResponseEntity<Categoria> postCategoria(@RequestBody Categoria categoria){
		return ResponseEntity.status(HttpStatus.CREATED).body(repositoryC.save(categoria));
	}
	
	@PutMapping
	public ResponseEntity<Categoria> putCategoria(@RequestBody Categoria categoria){
		return ResponseEntity.status(HttpStatus.OK).body(repositoryC.save(categoria));
	
	}
	
	@DeleteMapping("/{id}")
	public void deleteCategoria(@PathVariable long id) {
		repositoryC.deleteById(id);
	}
	
	@PostMapping("/Cadastar")
	public ResponseEntity<Object> cadastar(@RequestBody @Valid Categoria novaCategoria) {
		Optional<Object> novo = services.Cadastar(novaCategoria);

		if (novo.isEmpty()) {
			return ResponseEntity.status(200).body("Categoria Existente");
		} else {
			return ResponseEntity.status(201).body("Categoria Criada");
		}
	

}
}
