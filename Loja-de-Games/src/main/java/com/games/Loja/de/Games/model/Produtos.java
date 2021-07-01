package com.games.Loja.de.Games.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "produto")
@Component
public class Produtos {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull(message = "Campo não pode ser nulo")
	@Size(min = 5, max = 100)
	private String nomeProduto;

	@NotNull(message = "Campo não pode ser nulo")
	@Size(min = 5, max = 500)
	private String descricao;

	@NotNull(message = "Campo não pode ser nulo")
	private String preco;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getProduto() {
		return nomeProduto;
	}

	public void setProduto(String produto) {
		this.nomeProduto = produto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getPreco() {
		return preco;
	}

	public void setPreco(String preco) {
		this.preco = preco;
	}

}
