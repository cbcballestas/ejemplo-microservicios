package com.sistema.usuario.rtemplate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Carro {
	private String marca;
	private String modelo;
	private long usuarioId;
}
