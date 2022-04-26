package com.sistema.usuario.controller;

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

import com.sistema.usuario.dto.VehiculoResumenDTO;
import com.sistema.usuario.model.Usuario;
import com.sistema.usuario.rtemplate.model.Carro;
import com.sistema.usuario.rtemplate.model.Moto;
import com.sistema.usuario.service.IUsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private IUsuarioService service;

	@GetMapping
	public ResponseEntity<List<Usuario>> listarUsuarios() {
		return ResponseEntity.ok(service.listarUsuarios());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> obtenerUsuario(@PathVariable(name = "id") long id) {
		return new ResponseEntity<Usuario>(service.listarPorId(id), HttpStatus.OK);
	}

	@GetMapping("/carros/{usuarioId}")
	public ResponseEntity<List<Carro>> obtenerCarros(@PathVariable(name = "usuarioId") long usuarioId) {
		return new ResponseEntity<>(service.listarCarros(usuarioId), HttpStatus.OK);
	}

	@GetMapping("/motos/{usuarioId}")
	public ResponseEntity<List<Moto>> obtenerMotos(@PathVariable(name = "usuarioId") long usuarioId) {
		return new ResponseEntity<>(service.listarMotos(usuarioId), HttpStatus.OK);
	}

	@GetMapping("/resumenVehiculo/{usuarioId}")
	public ResponseEntity<VehiculoResumenDTO> obtenerResumenVehiculos(
			@PathVariable(name = "usuarioId") long usuarioId) {
		return new ResponseEntity<>(service.obtenerVehiculoResumen(usuarioId), HttpStatus.OK);
	}

	@PostMapping("/carro/{usuarioId}")
	public ResponseEntity<Carro> guardarCarro(@PathVariable(name = "usuarioId") long usuarioId,
			@RequestBody Carro carro) {
		return new ResponseEntity<>(service.saveCarro(usuarioId, carro), HttpStatus.CREATED);
	}

	@PostMapping("/moto/{usuarioId}")
	public ResponseEntity<Moto> guardarMoto(@PathVariable(name = "usuarioId") long usuarioId, @RequestBody Moto moto) {
		return new ResponseEntity<>(service.saveMoto(usuarioId, moto), HttpStatus.CREATED);
	}

	@PostMapping
	public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
		return new ResponseEntity<Usuario>(service.crearUsuario(usuario), HttpStatus.CREATED);
	}

}
