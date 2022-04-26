package com.sistema.usuario.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistema.usuario.model.Usuario;

@Repository
public interface IUsuarioRepo extends JpaRepository<Usuario, Long> {

}
