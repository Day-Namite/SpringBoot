package com.games.Loja.de.Games.dto;

import javax.validation.constraints.NotEmpty;

public class CategoriaDTO {
		
		@NotEmpty
		private String tipo;

		public String getTipo() {
			return tipo;
		}

		public void setTipo(String tipo) {
			this.tipo = tipo;
		}

}
