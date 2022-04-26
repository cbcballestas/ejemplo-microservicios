package com.sistema.usuario.service;

import java.util.List;

import com.sistema.usuario.dto.VehiculoResumenDTO;
import com.sistema.usuario.model.Usuario;
import com.sistema.usuario.rtemplate.model.Carro;
import com.sistema.usuario.rtemplate.model.Moto;

public interface IUsuarioService {
	List<Usuario> listarUsuarios();

	List<Carro> listarCarros(long usuarioId);

	List<Moto> listarMotos(long usuarioId);

	VehiculoResumenDTO obtenerVehiculoResumen(long usuarioId);

	Usuario listarPorId(long id);

	Usuario crearUsuario(Usuario usuario);

	Usuario actualizarUsuario(long id, Usuario usuario);

	Carro saveCarro(long usuarioId, Carro carro);

	Moto saveMoto(long usuarioId, Moto moto);

	void eliminar(long id);
}
