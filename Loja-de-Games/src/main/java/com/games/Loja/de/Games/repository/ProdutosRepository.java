package com.games.Loja.de.Games.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.games.Loja.de.Games.model.Produtos;

@Repository
public interface ProdutosRepository extends JpaRepository<Produtos, Long> {
	public List <Produtos> findAllByNomeProdutoContainingIgnoreCase (String NomeProduto);
	

}
