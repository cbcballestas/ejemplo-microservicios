package com.sistema.usuario.feign.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sistema.usuario.rtemplate.model.Carro;

@FeignClient(name = "carro-service", url = "http://localhost:8082")
@RequestMapping("/carro")
public interface CarroClient {

	@PostMapping
	Carro save(@RequestBody Carro carro);
	
	@GetMapping("/usuario/{usuarioId}")
	List<Carro> obtenerCarrosByUsuarioId(@PathVariable(name = "usuarioId") long usuarioId);

}
