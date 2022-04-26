package com.sistema.carro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.carro.model.Carro;
import com.sistema.carro.service.ICarroService;

@RestController
@RequestMapping("/carro")
public class CarroController {

	@Autowired
	private ICarroService service;

	@GetMapping
	public ResponseEntity<List<Carro>> listarCarros() {
		return ResponseEntity.ok(service.listar());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Carro> obtenerCarro(@PathVariable(name = "id") long id) {
		return new ResponseEntity<Carro>(service.listarPorId(id), HttpStatus.OK);
	}

	@GetMapping("/usuario/{usuarioId}")
	public ResponseEntity<List<Carro>> listarCarrosPorUsuario(@PathVariable(name = "usuarioId") long usuarioId) {
		return new ResponseEntity<List<Carro>>(service.findByUsuarioId(usuarioId), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Carro> crearCarro(@RequestBody Carro carro) {
		return new ResponseEntity<Carro>(service.registrar(carro), HttpStatus.CREATED);
	}

}
