package br.com.jlucasguedes.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.jlucasguedes.domain.Cidade;
import br.com.jlucasguedes.domain.Voo;

public interface VoosRepository extends JpaRepository<Voo, Long> {

	public List<Voo> findByOrigemAndDestino(Cidade Origem, Cidade destino);

	public List<Voo> findByOrigemAndDestinoAndDataPartida(Cidade Origem, Cidade destino, LocalDate dataPartida);

	@Query("SELECT v FROM Voo v WHERE v.origem = ?1 AND v.destino = ?2 AND v.dataPartida = ?3 AND v.preco BETWEEN ?4 AND ?5")
	public List<Voo> findByOrigemAndDestinoAndDataPartidaBetweenPrecos(Cidade origem, Cidade destino,
			LocalDate dataPartida, BigDecimal preco1, BigDecimal preco2);
}
