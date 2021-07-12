package com.games.Loja.de.Games.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.games.Loja.de.Games.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	public Optional<Usuario> findByUsuario (String usuario);
	Optional<Usuario> findByEmail (String email);
	List<Usuario> findAllByEmailContaining (String email);

}
