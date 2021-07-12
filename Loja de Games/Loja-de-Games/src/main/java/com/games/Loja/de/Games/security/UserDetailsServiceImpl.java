package com.games.Loja.de.Games.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.games.Loja.de.Games.model.Usuario;
import com.games.Loja.de.Games.repository.UsuarioRepository;

public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository repositoryU;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> usuarioExistente =repositoryU.findByEmail(username);
		if (usuarioExistente.isPresent()) {
			return new UserDetailImplements(usuarioExistente.get());
		} else {
			throw new UsernameNotFoundException(username + "Usuário Inexistente.");
		}
	}

}
