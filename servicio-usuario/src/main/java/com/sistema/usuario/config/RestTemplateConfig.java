package com.sistema.usuario.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration // Para colocar la clase en el IOC
public class RestTemplateConfig { // Clase que va a ayudar a conectar los servicios

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
