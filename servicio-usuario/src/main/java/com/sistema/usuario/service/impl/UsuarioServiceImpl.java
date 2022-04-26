package com.sistema.usuario.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sistema.usuario.dto.VehiculoResumenDTO;
import com.sistema.usuario.exception.ResourceNotFoundException;
import com.sistema.usuario.feign.client.CarroClient;
import com.sistema.usuario.feign.client.MotoClient;
import com.sistema.usuario.model.Usuario;
import com.sistema.usuario.repo.IUsuarioRepo;
import com.sistema.usuario.rtemplate.model.Carro;
import com.sistema.usuario.rtemplate.model.Moto;
import com.sistema.usuario.service.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

	@Autowired
	private IUsuarioRepo repo;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private CarroClient carroFeignClient;

	@Autowired
	private MotoClient motoFeignClient;

	@Override
	public List<Usuario> listarUsuarios() {
		return repo.findAll();
	}

	@Override
	public Usuario listarPorId(long id) {
		Usuario usuarioBuscar = repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("NO SE ENCONTRÓ REGISTRO CON ID: " + id));
		return usuarioBuscar;
	}

	@Override
	public Usuario crearUsuario(Usuario usuario) {
		return repo.save(usuario);
	}

	@Override
	public Usuario actualizarUsuario(long id, Usuario usuario) {

		// Se consulta si existe registro con el ID específicado
		Usuario usuarioBuscar = repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("NO SE ENCONTRÓ REGISTRO CON ID: " + id));

		usuarioBuscar.setNombre(usuario.getNombre());
		usuarioBuscar.setEmail(usuario.getEmail());

		Usuario updatedUser = repo.save(usuarioBuscar);

		return updatedUser;
	}

	@Override
	public Carro saveCarro(long usuarioId, Carro carro) {
		// Se consulta si existe registro con el ID específicado
		Usuario usuarioBuscar = repo.findById(usuarioId)
				.orElseThrow(() -> new ResourceNotFoundException("NO SE ENCONTRÓ USUARIO CON ID: " + usuarioId));

		carro.setUsuarioId(usuarioId);

		Carro nuevoCarro = carroFeignClient.save(carro);

		return nuevoCarro;
	}

	@Override
	public Moto saveMoto(long usuarioId, Moto moto) {

		// Se consulta si existe registro con el ID específicado
		Usuario usuarioBuscar = repo.findById(usuarioId)
				.orElseThrow(() -> new ResourceNotFoundException("NO SE ENCONTRÓ USUARIO CON ID: " + usuarioId));

		moto.setUsuarioId(usuarioId);

		Moto nuevaMoto = motoFeignClient.save(moto);

		return nuevaMoto;
	}

	@Override
	public VehiculoResumenDTO obtenerVehiculoResumen(long usuarioId) {

		// Se consulta si existe registro con el ID específicado
		Usuario usuarioBuscar = repo.findById(usuarioId)
				.orElseThrow(() -> new ResourceNotFoundException("NO SE ENCONTRÓ USUARIO CON ID: " + usuarioId));

		// Se obtiene el listado de motos para el usuario consultado
		List<Moto> motos = motoFeignClient.obtenerMotosByUsuarioId(usuarioId);

		// Se obtiene el listado de carros para el usuario consultado
		List<Carro> carros = carroFeignClient.obtenerCarrosByUsuarioId(usuarioId);

		VehiculoResumenDTO resumen = new VehiculoResumenDTO();
		resumen.setCarros(carros);
		resumen.setMotos(motos);

		return resumen;
	}

	@Override
	public void eliminar(long id) {
		// Se consulta si existe registro con el ID específicado
		Usuario usuarioBuscar = repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("NO SE ENCONTRÓ REGISTRO CON ID: " + id));

		repo.delete(usuarioBuscar);

	}

	@Override
	public List<Carro> listarCarros(long usuarioId) {

		Usuario usuarioBuscar = repo.findById(usuarioId)
				.orElseThrow(() -> new ResourceNotFoundException("NO SE ENCONTRÓ USUARIO CON ID: " + usuarioId));

		List<Carro> carros = restTemplate.getForObject("http://localhost:8082/carro/usuario/" + usuarioId, List.class);

		if (carros == null || carros.isEmpty()) {
			throw new ResourceNotFoundException("NO SE ENCONTRARON CARROS ASOCIADOS AL USUARIO ESPECIFICADO");
		}

		return carros;
	}

	@Override
	public List<Moto> listarMotos(long usuarioId) {

		Usuario usuarioBuscar = repo.findById(usuarioId)
				.orElseThrow(() -> new ResourceNotFoundException("NO SE ENCONTRÓ USUARIO CON ID: " + usuarioId));

		List<Moto> motos = restTemplate.getForObject("http://localhost:8083/moto/usuario/" + usuarioId, List.class);

		if (motos == null || motos.isEmpty()) {
			throw new ResourceNotFoundException("NO SE ENCONTRARON MOTOS ASOCIADOS AL USUARIO ESPECIFICADO");
		}

		return motos;
	}

}
