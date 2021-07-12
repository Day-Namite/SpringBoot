package com.games.Loja.de.Games.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.games.Loja.de.Games.dto.UsuarioLoginDTO;
import com.games.Loja.de.Games.model.Usuario;
import com.games.Loja.de.Games.repository.UsuarioRepository;
import com.games.Loja.de.Games.service.UsuarioService;

@RestController
@RequestMapping("/v1/usuario")

public class UsuarioController {
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private UsuarioService service;
	
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
	public ResponseEntity<Usuario> pegarId(@PathVariable (value = "id_Usuario") long idUsuario){
		return repository.findById(idUsuario)
				.map(usuarioExistente ->ResponseEntity.status(200).body(usuarioExistente))
				.orElse(ResponseEntity.status(204).build());
	}
	
	@PostMapping ("/cadastrar")
	public ResponseEntity<Usuario> novoUsuario (@Valid @RequestBody Usuario usuario){
		return service.cadastrarUsuario(usuario)
				.map(usuarioCadastrado -> ResponseEntity.status(201).body(usuarioCadastrado))
				.orElse(ResponseEntity.status(400).build());
	}
	
	@PostMapping("/logar")
	public ResponseEntity<UsuarioLoginDTO> logarUsuario (@RequestBody Optional<UsuarioLoginDTO> usuarioLogin){
		return service.logarUsuario(usuarioLogin)
			.map(usuarioAutorizado ->ResponseEntity.status(200).body(usuarioAutorizado))
			.orElse(ResponseEntity.status(401).build());
		
	}

}
