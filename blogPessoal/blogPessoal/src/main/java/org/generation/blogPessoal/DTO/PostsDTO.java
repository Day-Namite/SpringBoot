package org.generation.blogPessoal.DTO;

import javax.validation.constraints.NotEmpty;

public class PostsDTO {
	
	@NotEmpty(message = "O Texto precisa ser preenchido!")
	private String texto;

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	

}
