package com.drugstore.farmacia.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.drugstore.farmacia.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

	List<Categoria> findAllByItemContaining(String item);

	List<Categoria> findAllByDescricaoContaining(String descricao);
	
	Optional<Categoria> findByItem(String item);

}
