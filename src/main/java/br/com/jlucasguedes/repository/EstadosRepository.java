package br.com.jlucasguedes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jlucasguedes.domain.Estado;

public interface EstadosRepository extends JpaRepository<Estado, Long> {

}
