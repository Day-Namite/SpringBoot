package com.drugstore.farmacia.dto;

import javax.validation.constraints.NotEmpty;

public class CategoriaDto {
	
	@NotEmpty (message= "Campo não pode estar vazio")
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
 
}
