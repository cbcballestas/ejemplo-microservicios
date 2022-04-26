package com.sistema.usuario.dto;

import java.util.List;

import com.sistema.usuario.rtemplate.model.Carro;
import com.sistema.usuario.rtemplate.model.Moto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehiculoResumenDTO {

	private List<Moto> motos;
	private List<Carro> carros;
}
