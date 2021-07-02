package com.drugstore.farmacia.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.drugstore.farmacia.model.Produtos;

public interface ProdutosRepository extends JpaRepository<Produtos, Long>{
	
	List<Produtos> findByIdProduto(Long idProduto);
	List<Produtos> findAllByItemContaining(String item);
	List<Produtos> findAllByDescricaoContaining(String descricao);
	Optional<Produtos> findByItem(String item);


}
