package org.generation.blogPessoal.service;

import java.nio.charset.Charset;
import java.util.Optional;
import org.apache.commons.codec.binary.Base64;
import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.model.UsuarioLogin;
import org.generation.blogPessoal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServices {

	@Autowired
	private UsuarioRepository repository;

	public Optional<Usuario> cadastrarUsuario(Usuario usuario) {
		Optional<Usuario> usuarioExistente = repository.findByUsuario(usuario.getUsuario());
		if(usuarioExistente.isPresent())
	{
		return Optional.empty();
	}else
	{
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String senhaCriptografada = encoder.encode(usuario.getSenha());
		usuario.setSenha(senhaCriptografada);
		return Optional.ofNullable(repository.save(usuario));
	}
	}

	public Optional<UsuarioLogin> loginUsuario (Optional<UsuarioLogin> loginUsuario){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Usuario> usuarioExistente = repository.findByUsuario(loginUsuario.get().getUsuario());

		if(usuarioExistente.isPresent()) {
			if(encoder.matches(loginUsuario.get().getSenha(), usuarioExistente.get().getSenha())) {
				String auth = loginUsuario.get().getUsuario() + ":" + loginUsuario.get().getSenha();
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);
				
				loginUsuario.get().setToken(authHeader);				
				loginUsuario.get().setEmail(usuarioExistente.get().getEmail());
				loginUsuario.get().setSenha(usuarioExistente.get().getSenha());

				return loginUsuario;
			}
		}
		return null;
	}
}