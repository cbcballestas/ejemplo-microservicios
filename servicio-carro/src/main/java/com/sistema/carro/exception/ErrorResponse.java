package com.sistema.carro.exception;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
	private LocalDate fecha;
	private String mensaje;
	private String urlRecurso;
}
