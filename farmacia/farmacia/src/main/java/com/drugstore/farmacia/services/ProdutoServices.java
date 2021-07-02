package com.drugstore.farmacia.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drugstore.farmacia.dto.ProdutoDto;
import com.drugstore.farmacia.model.Produtos;
import com.drugstore.farmacia.repository.ProdutosRepository;

@Service
public class ProdutoServices {
	
	@Autowired
	private ProdutosRepository repository;


	public Optional<Object> cadastrar(Produtos novoProduto) {
		return repository.findByItem(novoProduto.getItem()).map(usuarioExistente -> {
			return Optional.empty();
		}).orElseGet(() -> {
			return Optional.ofNullable(repository.save(novoProduto));
		});
	}

	
	public Optional<Produtos> Atualizar(long idProduto, ProdutoDto produtoAtt) {
		return repository.findById(idProduto).map(ProdutoAtualizado -> {
			ProdutoAtualizado.setDescricao(produtoAtt.getDescricao());
			ProdutoAtualizado.setPreco(produtoAtt.getPreco());
			return Optional.ofNullable(repository.save(ProdutoAtualizado));
		}).orElseGet(() -> {
			return Optional.empty();
		});
	}

}
