package br.com.jlucasguedes.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@SequenceGenerator(name = "voo_seq_gen", sequenceName = "voo_id_seq", allocationSize = 1)
public class Voo {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "voo_seq_gen")
	private Long id;

	@NotNull(message = "A origem deve ser preenchida")
	@JsonInclude(Include.NON_NULL)
	@ManyToOne
	@JoinColumn(name = "origem_id")
	private Cidade origem;

	@NotNull(message = "O destino deve ser preenchido")
	@JsonInclude(Include.NON_NULL)
	@ManyToOne
	@JoinColumn(name = "destino_id")
	private Cidade destino;

	@NotNull(message = "O data de partida deve ser preenchida")
	@JsonInclude(Include.NON_NULL)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataPartida;

	@NotNull(message = "O preco deve ser preenchido")
	@JsonInclude(Include.NON_NULL)
	private BigDecimal preco;

	public Voo() {
	}

	public Voo(Cidade origem, Cidade destino, LocalDate dataPartida, BigDecimal preco) {
		this.origem = origem;
		this.destino = destino;
		this.dataPartida = dataPartida;
		this.preco = preco;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cidade getOrigem() {
		return origem;
	}

	public void setOrigem(Cidade origem) {
		this.origem = origem;
	}

	public Cidade getDestino() {
		return destino;
	}

	public void setDestino(Cidade destino) {
		this.destino = destino;
	}

	public LocalDate getDataPartida() {
		return dataPartida;
	}

	public void setDataPartida(LocalDate dataPartida) {
		this.dataPartida = dataPartida;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\", \"origem\":\"" + origem + "\", \"destino\":\"" + destino
				+ "\", \"dataPartida\":\"" + dataPartida + "\", \"preco\":\"" + preco + "\"}";
	}

}
