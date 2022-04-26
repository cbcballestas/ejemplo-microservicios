package com.sistema.carro.service;

import java.util.List;

import com.sistema.carro.model.Carro;

public interface ICarroService {
	List<Carro> listar();

	List<Carro> findByUsuarioId(long usuarioId);

	Carro listarPorId(long id);

	Carro registrar(Carro carro);
}
