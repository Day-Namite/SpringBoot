package org.generation.blogPessoal.service;

import java.util.Optional;

import org.generation.blogPessoal.DTO.TemaDTO;
import org.generation.blogPessoal.model.Tema;
import org.generation.blogPessoal.repository.TemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TemaServices {
	
	@Autowired
	private TemaRepository repositoryT;
	
	public Optional<Object> novoTema (Tema novoTema){
		return repositoryT.findByDescricao(novoTema.getDescricao()).map(Existente -> {
			return Optional.empty();
		}).orElseGet(() -> {
			return Optional.ofNullable(repositoryT.save(novoTema));
		});
	}
	
	public Optional<Tema> editarTema(Long idTema, TemaDTO tema){
		return repositoryT.findById(idTema).map(temaEditado -> {
			temaEditado.setDescricao(tema.getDescricao());
			return Optional.ofNullable(repositoryT.save(temaEditado));
		}).orElseGet(() -> {
			return Optional.empty();
		});
	}


}
