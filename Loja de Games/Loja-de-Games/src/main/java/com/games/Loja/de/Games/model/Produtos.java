package com.games.Loja.de.Games.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "produto")
@Component
public class Produtos {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;

	@NotEmpty(message = "Campo não pode ser nulo")
	@Size(min = 5, max = 100)
	private String nomeProduto;

	@NotEmpty(message = "Campo não pode ser nulo")
	@Size(min = 5, max = 500)
	private String descricao;

	@NotEmpty(message = "Campo não pode ser nulo")
	private String preco;
	
	@ManyToOne
	@JsonIgnoreProperties("produtos")
	private Categoria categoria;
	

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
