package com.sistema.carro.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistema.carro.model.Carro;

@Repository
public interface ICarroRepo extends JpaRepository<Carro, Long> {

	List<Carro> findByUsuarioId(long usuarioId);
}
