package com.games.Loja.de.Games.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idUsuario;

	@NotEmpty
	@Size(min = 2, max = 100)
	private String email;

	@NotEmpty
	@Size(min = 5, max = 100)
	private String usuario;

	@NotEmpty
	@Size(min = 8, max = 100)
	private String senha;
	
public Usuario() {}
	
	public Usuario(Long idUsuario, String email, String usuario, String senha) {
		this.idUsuario=idUsuario;
		this.email=email;
		this.usuario=usuario;
		this.senha=senha;
	}

	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	

}
