package org.generation.blogPessoal.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.generation.blogPessoal.DTO.PostsDTO;
import org.generation.blogPessoal.DTO.UsuarioLogin;
import org.generation.blogPessoal.model.Posts;
import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.repository.UsuarioRepository;
import org.generation.blogPessoal.service.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/api/v1/usuario")

public class UsuarioController {
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired 
	private UsuarioServices services;
	
	@GetMapping ("/buscar")
	public ResponseEntity<List<Usuario>> buscarTodos(){
		List<Usuario> listaUsuario = repository.findAll();
		if (!listaUsuario.isEmpty()) {
			return ResponseEntity.status(200).body(listaUsuario);
			
		} else {
			return ResponseEntity.status(204).build();
		}
	}
		
		@GetMapping ("/id/{id_Usuario}")
		public ResponseEntity<Usuario> pegarId(@PathVariable (value = "id_Usuario") Long idUsuario){
			return repository.findById(idUsuario)
					.map(usuarioExistente ->ResponseEntity.status(200).body(usuarioExistente))
					.orElse(ResponseEntity.status(204).build());
		}
		
		@PostMapping ("/cadastrar")
		public ResponseEntity<Usuario> cadastrarUsuario (@Valid @RequestBody Usuario usuario){
			return services.cadastrarUsuario(usuario)
					.map(usuarioCadastrado -> ResponseEntity.status(201).body(usuarioCadastrado))
					.orElse(ResponseEntity.status(400).build());
		}
		
		@PostMapping("/login")
		public ResponseEntity<UsuarioLogin> loginUsuario (@RequestBody Optional<UsuarioLogin> usuarioLogin){
			return services.loginUsuario(usuarioLogin)
				.map(usuarioAutorizado ->ResponseEntity.status(200).body(usuarioAutorizado))
				.orElse(ResponseEntity.status(401).build());
			
		}
		@PutMapping("{idUsuario}/Editar")
		public ResponseEntity<Usuario> editarUsuario(@Valid @PathVariable Long idUsuario, @Valid @RequestBody UsuarioLogin usuario) {
			return services.editUsuario(idUsuario, usuario)
					.map(usuarioEditado -> ResponseEntity.status(201).body(repository.save(usuarioEditado))).orElseGet(() -> {
						return ResponseEntity.badRequest().build();
					});
		}
		@DeleteMapping("/{idUsuario}/Delete")
		public void deleteUsuario(@PathVariable Long idUsuario) {
			repository.deleteById(idUsuario);
		}
		
}
