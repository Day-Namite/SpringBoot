package org.generation.blogPessoal.controller;

import java.util.List;

import javax.validation.Valid;

import org.generation.blogPessoal.model.Posts;
import org.generation.blogPessoal.repository.PostsRepository;
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

@RestController
@RequestMapping("/posts")
@CrossOrigin("*")
public class PostsController {

	@Autowired
	private PostsRepository repository;

	@GetMapping
	public ResponseEntity<List<Posts>> GetAll() {
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Posts> GetById(@PathVariable Long id) {
		return repository.findById(id).
			map(resp -> ResponseEntity.ok(resp)).
			orElse(ResponseEntity.notFound().build());
	}
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Posts>> GetByTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
		
	}
	@PostMapping
	public ResponseEntity<Posts> post (@RequestBody Posts posts){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(posts));
		
	}
	@PutMapping
	public ResponseEntity<Posts> put (@RequestBody Posts posts){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(posts));
	}
	@DeleteMapping("/{id}")
	public void delete (@PathVariable long id) {
		repository.deleteById(id);
	}
}
