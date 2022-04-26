package com.sistema.moto.service;

import java.util.List;

import com.sistema.moto.model.Moto;

public interface IMotoService {
	List<Moto> listar();

	Moto listarPorId(long id);

	List<Moto> findByUsuarioId(long usuarioId);

	Moto registrar(Moto moto);
}
