package org.generation.blogPessoal.service;

import java.nio.charset.Charset;
import java.util.Optional;
import org.apache.commons.codec.binary.Base64;
import org.generation.blogPessoal.DTO.PostsDTO;
import org.generation.blogPessoal.DTO.UsuarioLogin;
import org.generation.blogPessoal.model.Posts;
import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServices {

	@Autowired
	private UsuarioRepository repository;

	public Optional<Usuario> cadastrarUsuario(Usuario usuario) {
		Optional<Usuario> usuarioExistente = repository.findByEmail(usuario.getEmail());
		if (usuarioExistente.isPresent()) {
			return Optional.empty();
		} else {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String senhaCriptografada = encoder.encode(usuario.getSenha());
			usuario.setSenha(senhaCriptografada);
			return Optional.ofNullable(repository.save(usuario));
		}
	}
	public Optional<Usuario> editUsuario(Long idUsuario, UsuarioLogin usuario){
		return repository.findById(idUsuario).map(usuarioEditado -> {
			usuarioEditado.setUsuario(usuario.getUsuario());
			return Optional.ofNullable(repository.save(usuarioEditado));
		}).orElseGet(() -> {
			return Optional.empty();
		});
	}

	public Optional<UsuarioLogin> loginUsuario(Optional<UsuarioLogin> loginUsuario) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Usuario> usuarioExistente = repository.findByEmail(loginUsuario.get().getEmail());

		if (usuarioExistente.isPresent()) {
			if (encoder.matches(loginUsuario.get().getSenha(), usuarioExistente.get().getSenha())) {
				String auth = loginUsuario.get().getEmail() + ":" + loginUsuario.get().getSenha();
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);

				loginUsuario.get().setToken(authHeader);
				loginUsuario.get().setEmail(usuarioExistente.get().getEmail());
				loginUsuario.get().setSenha(usuarioExistente.get().getSenha());
				loginUsuario.get().setUsuario(usuarioExistente.get().getUsuario());
				loginUsuario.get().setFoto(usuarioExistente.get().getFoto());
				loginUsuario.get().setTipoUsuario(usuarioExistente.get().getTipoUsuario());

				return loginUsuario;
			}
		}
		return null;
	}
}