package org.generation.blogPessoal.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUsuario;

	@Email
	@NotEmpty(message = "Esse campo não pode estar vazio!")
	@Size(min = 2, max = 100)
	private String email;

	@NotEmpty(message = "Esse campo não pode estar vazio!")
	@Size(min = 5, max = 100)
	private String usuario;

	@NotEmpty(message = "Esse campo não pode estar vazio!")
	@Size(min = 8, max = 100)
	private String senha;
	
	private String foto;
	
	private String tipoUsuario;
	

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("usuario")
	private List<Posts> posts;

	public Usuario() {
		super();
	}

	public Usuario(
			@NotEmpty(message = "Esse campo não pode estar vazio!") @Email(message = "Este não é um e-mail válido") String email,
			@NotEmpty(message = "Esse campo não pode estar vazio!") String senha,
			@NotEmpty(message = "Esse campo não pode estar vazio!") String usuario) {
		super();
		this.email = email;
		this.usuario = usuario;
		this.senha = senha;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
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
	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public List<Posts> getPosts() {
		return posts;
	}

	public void setPosts(List<Posts> posts) {
		this.posts = posts;
	}

}
