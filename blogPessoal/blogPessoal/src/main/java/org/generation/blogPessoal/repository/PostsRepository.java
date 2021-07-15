package org.generation.blogPessoal.repository;

import java.util.List;
import java.util.Optional;

import org.generation.blogPessoal.model.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostsRepository extends JpaRepository<Posts, Long>{
	public List <Posts> findAllByTituloContaining(String titulo);
	List<Posts> findAllByTextoContaining(String texto);
	Optional<Posts> findByTitulo (String titulo);

}
