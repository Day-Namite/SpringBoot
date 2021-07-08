package org.generation.blogPessoal.security;

import java.util.Optional;

import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImplements implements UserDetailsService{

	private @Autowired UsuarioRepository repositoryU;
	
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
