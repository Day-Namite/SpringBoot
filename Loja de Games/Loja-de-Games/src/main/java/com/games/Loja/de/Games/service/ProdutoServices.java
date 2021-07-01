package com.games.Loja.de.Games.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.games.Loja.de.Games.dto.ProdutosDTO;
import com.games.Loja.de.Games.model.Categoria;
import com.games.Loja.de.Games.model.Produtos;
import com.games.Loja.de.Games.repository.CategoriaRepository;
import com.games.Loja.de.Games.repository.ProdutosRepository;

@Service
public class ProdutoServices {


	@Autowired
	private ProdutosRepository repositoryProduto;

	
	public Optional<Object> Cadastrar(Produtos novoProduto) {
		return repositoryProduto.findByNomeProduto(novoProduto.getNomeProduto())
				.map(ProdutoExistente -> {return Optional.empty();}).orElseGet(() -> {
			return Optional.ofNullable(repositoryProduto.save(novoProduto));
		});
	}

	public Optional<Produtos> atualizarProduto(Long id, ProdutosDTO novo) {
		return repositoryProduto.findById(id).map(atualizar -> {
			atualizar.setNomeProduto(novo.getNomeProduto());
			atualizar.setDescricao(novo.getDescricao());
			return Optional.ofNullable(repositoryProduto.save(atualizar));
		}).orElseGet(() -> {
			return Optional.empty();
		});
	}

}
