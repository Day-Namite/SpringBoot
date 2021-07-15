package org.generation.blogPessoal.controller;

import java.util.List;

import javax.validation.Valid;

import org.generation.blogPessoal.DTO.PostsDTO;
import org.generation.blogPessoal.model.Posts;
import org.generation.blogPessoal.repository.PostsRepository;
import org.generation.blogPessoal.service.PostsServices;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/posts")
@CrossOrigin("*")
public class PostsController {

	@Autowired
	private PostsRepository repositoryP;

	@Autowired
	private PostsServices service;

	@GetMapping("/BuscarTudo")
	public ResponseEntity<List<Posts>> buscarTudo() {
		List<Posts> post = repositoryP.findAll();

		if (post.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.status(200).body(post);
		}
	}

	@GetMapping("/{idPosts}/BuscarId")
	public ResponseEntity<Posts> buscarId(@PathVariable Long idPosts) {
		return repositoryP.findById(idPosts).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/BuscarTitulo")
	public ResponseEntity<List<Posts>> buscarTitulo(@RequestParam(defaultValue = "") String titulo) {
		return ResponseEntity.status(200).body(repositoryP.findAllByTituloContaining(titulo));

	}

	@GetMapping("/BuscarTexto")
	public ResponseEntity<List<Posts>> buscarTexto(@RequestParam(defaultValue = "") String texto) {
		return ResponseEntity.status(200).body(repositoryP.findAllByTextoContaining(texto));
	}

	@PostMapping("/NovoPost")
	public ResponseEntity<Posts> Novopost(@RequestBody Posts newPost) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repositoryP.save(newPost));

	}

	@PutMapping("{idPosts}/Editar")
	public ResponseEntity<Posts> editarPost(@Valid @PathVariable Long idPosts, @Valid @RequestBody PostsDTO post) {
		return service.editPost(idPosts, post)
				.map(editado -> ResponseEntity.status(201).body(repositoryP.save(editado))).orElseGet(() -> {
					return ResponseEntity.badRequest().build();
				});
	}

	@DeleteMapping("/{idPosts}/Delete")
	public void delete(@PathVariable Long idPosts) {
		repositoryP.deleteById(idPosts);
	}
}
