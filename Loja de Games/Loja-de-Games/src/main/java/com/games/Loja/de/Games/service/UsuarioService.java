package com.games.Loja.de.Games.service;

import java.nio.charset.Charset;
import java.util.Optional;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.games.Loja.de.Games.dto.UsuarioLoginDTO;
import com.games.Loja.de.Games.model.Usuario;
import com.games.Loja.de.Games.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
	
	public Optional<Usuario> cadastrarUsuario (Usuario usuario) {
		Optional<Usuario> usuarioExistente = repository.findByEmail(usuario.getEmail());
		if (usuarioExistente.isPresent())
		{
			return Optional.empty();
		}else {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String senhaCriptografada = encoder.encode(usuario.getSenha());
			usuario.setSenha(senhaCriptografada);
			return Optional.ofNullable(repository.save(usuario));
		}
		}
		public Optional <UsuarioLoginDTO> logarUsuario (Optional<UsuarioLoginDTO> logarUsuario){
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			Optional<Usuario> usuarioExistente = repository.findByEmail(logarUsuario.get().getEmail());
			if (usuarioExistente.isPresent()) {
				if (encoder.matches(logarUsuario.get().getSenha(), usuarioExistente.get().getSenha())) {
					String auth = logarUsuario.get().getEmail() + ":" + logarUsuario.get().getSenha();
					byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
					String authHeader = "Basic " + new String(encodedAuth);

					logarUsuario.get().setToken(authHeader);
					logarUsuario.get().setEmail(usuarioExistente.get().getEmail());
					logarUsuario.get().setSenha(usuarioExistente.get().getSenha());

					return logarUsuario;
				}
			}
			return null;
		}
		
	}
