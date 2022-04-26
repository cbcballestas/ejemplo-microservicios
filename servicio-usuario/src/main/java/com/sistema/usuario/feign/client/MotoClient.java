package com.sistema.usuario.feign.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sistema.usuario.rtemplate.model.Moto;

@FeignClient(name = "moto-service", url = "http://localhost:8083")
@RequestMapping("/moto")
public interface MotoClient {

	@PostMapping
	Moto save(Moto moto);

	@GetMapping("/usuario/{usuarioId}")
	List<Moto> obtenerMotosByUsuarioId(@PathVariable(name = "usuarioId") long usuarioId);

}
