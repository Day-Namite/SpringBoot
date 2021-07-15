package org.generation.blogPessoal.controller;

import java.util.List;

import javax.validation.Valid;

import org.generation.blogPessoal.DTO.TemaDTO;
import org.generation.blogPessoal.model.Tema;
import org.generation.blogPessoal.repository.TemaRepository;
import org.generation.blogPessoal.service.TemaServices;
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
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/tema")
public class TemaController {

	@Autowired
	private TemaRepository repositoryT;

	@Autowired
	private TemaServices services;

	@GetMapping("/buscarTodos")
	public ResponseEntity<List<Tema>> getAll() {
		return ResponseEntity.ok(repositoryT.findAll());
	}

	@GetMapping("/{idTema}/buscarId")
	public ResponseEntity<Tema> getById(@PathVariable Long idTema) {
		return repositoryT.findById(idTema).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/{descricao}/Buscar/Descricao")
	public ResponseEntity<List<Tema>> getByDescricao(@PathVariable String descricao) {
		return ResponseEntity.ok(repositoryT.findAllByDescricaoContainingIgnoreCase(descricao));

	}

	@PostMapping("/Novo/Tema")
	public ResponseEntity<Tema> novoTema(@RequestBody Tema tema) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repositoryT.save(tema));
	}

	@PutMapping("{idTema}/Editar")
	public ResponseEntity<Tema> editarTema(@Valid @PathVariable Long idTema, @Valid @RequestBody TemaDTO tema) {
		return services.editarTema(idTema, tema)
				.map(temaEditado -> ResponseEntity.status(201).body(repositoryT.save(temaEditado))).orElseGet(() -> {
					return ResponseEntity.badRequest().build();
				});
	}

	@DeleteMapping("/{idTema}/Delete")
	public void deleteTema(@PathVariable Long idTema) {
		repositoryT.deleteById(idTema);
	}

}
