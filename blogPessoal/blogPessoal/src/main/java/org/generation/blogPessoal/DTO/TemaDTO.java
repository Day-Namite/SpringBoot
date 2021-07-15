package org.generation.blogPessoal.DTO;

import javax.validation.constraints.NotEmpty;

public class TemaDTO {
	
	@NotEmpty (message= "A Descrição não pode estar vazia!")
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	

}
