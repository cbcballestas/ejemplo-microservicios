package com.sistema.moto.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistema.moto.model.Moto;

@Repository
public interface IMotoRepo extends JpaRepository<Moto, Long> {

	List<Moto> findByUsuarioId(long usuarioId);

}
