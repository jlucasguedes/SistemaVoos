package br.com.jlucasguedes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jlucasguedes.domain.Cidade;

public interface CidadesRepository extends JpaRepository<Cidade, Long> {

	
}
