package com.games.Loja.de.Games.dto;

import javax.validation.constraints.NotEmpty;

public class ProdutosDTO {
	
	@NotEmpty
	private String nomeProduto;
	
	@NotEmpty
	private String descricao;

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	

}
