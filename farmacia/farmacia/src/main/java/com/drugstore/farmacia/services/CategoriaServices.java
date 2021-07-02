package com.drugstore.farmacia.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drugstore.farmacia.dto.CategoriaDto;
import com.drugstore.farmacia.model.Categoria;
import com.drugstore.farmacia.repository.CategoriaRepository;

@Service
public class CategoriaServices {
	
	@Autowired
	private CategoriaRepository repository;

	public Optional<Object> Cadastrar(Categoria novaCategoria) {
		return repository.findByItem(novaCategoria.getItem()).map(CategoriaEx -> {
			return Optional.empty();
		}).orElseGet(() -> {
			return Optional.ofNullable(repository.save(novaCategoria));
		});

	}
	
	public Optional<Categoria> Alterar(long idCategoria, CategoriaDto novaCategoira){
		return repository.findById(idCategoria).map(CategoriaAtt -> {
			CategoriaAtt.setDescricao(novaCategoira.getDescricao());
			return Optional.ofNullable(repository.save(CategoriaAtt));
		}).orElseGet(() -> {
			return Optional.empty();
		});
	}

}
