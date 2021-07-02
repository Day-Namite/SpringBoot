package com.drugstore.farmacia.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.drugstore.farmacia.dto.ProdutoDto;
import com.drugstore.farmacia.model.Produtos;
import com.drugstore.farmacia.repository.ProdutosRepository;
import com.drugstore.farmacia.services.ProdutoServices;

@RestController
@RequestMapping("/Farmacia")
public class ProdutosController {
	
	@Autowired
	private ProdutosRepository produtoRepository;

	@Autowired
	private ProdutoServices produtoServices;

	@PostMapping("/Cadastrar")
	public ResponseEntity<Object> Cadastrar(@Valid @RequestBody Produtos novoProduto) {
		Optional<Object> produto = produtoServices.cadastrar(novoProduto);

		if (produto.isEmpty()) {
			return ResponseEntity.status(200).body("Produto Existente");
		} else {
			return ResponseEntity.status(201).body("Produto Criado");
		}
	}

	@GetMapping("/BuscarTodos")
	public ResponseEntity<List<Produtos>> BuscarTodosProdutos() {
		List<Produtos> produto = produtoRepository.findAll();

		if (produto.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.status(200).body(produto);
		}
	}

	@GetMapping("/BuscarItem")
	public ResponseEntity<List<Produtos>> BuscandoPeloNome(@RequestParam(defaultValue = "") String item) {
		return ResponseEntity.status(200).body(produtoRepository.findAllByItemContaining(item));
	}

	@GetMapping("/BuscarPorDescricao")
	public ResponseEntity<List<Produtos>> BuscandoPelaDescricao(@RequestParam(defaultValue = "") String descricao) {
		return ResponseEntity.status(200).body(produtoRepository.findAllByDescricaoContaining(descricao));
	}

	@GetMapping("/{idProduto}/BuscarPorId")
	public ResponseEntity<Produtos> buscandoPeloID(@PathVariable long idProduto) {
		return produtoRepository.findById(idProduto).map(buscando -> ResponseEntity.ok(buscando))
				.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{idProduto}/Deletar")
	public void Remover(@PathVariable long idProduto) {
		produtoRepository.deleteById(idProduto);

	}
	@PutMapping("/{idProduto}/Alterar")
	public ResponseEntity<Produtos> Alterar (@Valid @PathVariable long idUsuario,
			@Valid @RequestBody ProdutoDto novoProduto){
		return produtoServices.Atualizar(idUsuario, novoProduto).map(att -> ResponseEntity.status(201).body(att))
				.orElse(ResponseEntity.badRequest().build());
	}

}
