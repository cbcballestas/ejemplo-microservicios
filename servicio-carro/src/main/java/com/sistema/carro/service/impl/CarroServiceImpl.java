package com.sistema.carro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.carro.exception.ResourceNotFoundException;
import com.sistema.carro.model.Carro;
import com.sistema.carro.repo.ICarroRepo;
import com.sistema.carro.service.ICarroService;

@Service
public class CarroServiceImpl implements ICarroService {

	@Autowired
	private ICarroRepo repo;

	@Override
	public List<Carro> listar() {
		return repo.findAll();
	}

	@Override
	public Carro listarPorId(long id) {
		Carro carro = repo.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("EL REGISTRO CON ID " + id + ", NO HA SIDO ENCONTRADO"));
		return carro;
	}

	@Override
	public List<Carro> findByUsuarioId(long usuarioId) {
		List<Carro> carros = repo.findByUsuarioId(usuarioId);

		if (carros == null || carros.isEmpty()) {
			throw new ResourceNotFoundException("EL USUARIO NO TIENE CARROS ASOCIADOS");
		}

		return carros;
	}

	@Override
	public Carro registrar(Carro carro) {

		Carro nuevoCarro = repo.save(carro);

		return nuevoCarro;
	}

}
