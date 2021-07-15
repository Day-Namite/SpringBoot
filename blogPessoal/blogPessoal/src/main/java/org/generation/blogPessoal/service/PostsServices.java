package org.generation.blogPessoal.service;

import java.util.Optional;

import org.generation.blogPessoal.DTO.PostsDTO;
import org.generation.blogPessoal.model.Posts;
import org.generation.blogPessoal.repository.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostsServices {
	
	@Autowired
	private PostsRepository repositoryP;
	
	public Optional<Object> publicar (Posts newPost){
		return repositoryP.findByTitulo(newPost.getTitulo()).map(Existente -> {
			return Optional.empty();
		}).orElseGet(() -> {
			return Optional.ofNullable(repositoryP.save(newPost));
		});
	}
	
	public Optional<Posts> editPost(Long idPosts, PostsDTO post){
		return repositoryP.findById(idPosts).map(postEditado -> {
			postEditado.setTexto(post.getTexto());
			return Optional.ofNullable(repositoryP.save(postEditado));
		}).orElseGet(() -> {
			return Optional.empty();
		});
	}
	
	

}
