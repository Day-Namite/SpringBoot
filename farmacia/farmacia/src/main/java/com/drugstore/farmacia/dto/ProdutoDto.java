package com.drugstore.farmacia.dto;

import javax.validation.constraints.NotEmpty;

public class ProdutoDto {
	
	@NotEmpty(message= "Campo não pode estar vazio")
	private String descricao;
	
	@NotEmpty(message= "Campo não pode estar vazio")
	private double preco;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}
	

}
