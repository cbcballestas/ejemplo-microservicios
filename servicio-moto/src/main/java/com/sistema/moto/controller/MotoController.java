package com.sistema.moto.controller;

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

import com.sistema.moto.model.Moto;
import com.sistema.moto.service.IMotoService;

@RestController
@RequestMapping("/moto")
public class MotoController {

	@Autowired
	private IMotoService service;
	
	@GetMapping
	public ResponseEntity<List<Moto>> listarMotos() {
		return ResponseEntity.ok(service.listar());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Moto> listarPorId(@PathVariable(name = "id") long id) {
		return new ResponseEntity<Moto>(service.listarPorId(id), HttpStatus.OK);
	}

	@GetMapping("/usuario/{usuarioId}")
	public ResponseEntity<List<Moto>> listarMotosPorUsuario(@PathVariable(name = "usuarioId") long usuarioId) {
		return new ResponseEntity<List<Moto>>(service.findByUsuarioId(usuarioId), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Moto> crearMoto(@RequestBody Moto moto) {
		return new ResponseEntity<Moto>(service.registrar(moto), HttpStatus.CREATED);
	}
	
}
