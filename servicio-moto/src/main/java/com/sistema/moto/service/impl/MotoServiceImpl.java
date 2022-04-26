package com.sistema.moto.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.moto.exception.ResourceNotFoundException;
import com.sistema.moto.model.Moto;
import com.sistema.moto.repo.IMotoRepo;
import com.sistema.moto.service.IMotoService;

@Service
public class MotoServiceImpl implements IMotoService {

	@Autowired
	private IMotoRepo repo;

	@Override
	public List<Moto> listar() {
		return repo.findAll();
	}

	@Override
	public Moto listarPorId(long id) {

		Moto moto = repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("REGISTRO CON ID: " + id + ", NO HA SIDO ENCONTRADO"));

		return moto;
	}

	@Override
	public List<Moto> findByUsuarioId(long usuarioId) {

		List<Moto> motos = repo.findByUsuarioId(usuarioId);

		if (motos == null || motos.isEmpty()) {
			throw new ResourceNotFoundException("EL USUARIO CONSULTADO NO TIENE MOTOS ASOCIADAS");
		}

		return motos;
	}

	@Override
	public Moto registrar(Moto moto) {

		Moto nuevaMoto = repo.save(moto);

		return nuevaMoto;
	}

}
