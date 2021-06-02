package br.com.jlucasguedes.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.jlucasguedes.domain.Cidade;
import br.com.jlucasguedes.domain.Voo;
import br.com.jlucasguedes.repository.VoosRepository;
import br.com.jlucasguedes.services.exceptions.CidadeNaoEncontradaException;
import br.com.jlucasguedes.services.exceptions.VooNaoEncontradoException;

@Service
public class VoosService {

	@Autowired
	private VoosRepository voosRepository;
	
	@Autowired
	private CidadeService cidadeService;

	public List<Voo> listar() {
		return voosRepository.findAll();
	}

	public Optional<Voo> buscar(Long id) {
		Optional<Voo> voo = voosRepository.findById(id);

		if (voo.isEmpty()) {
			throw new VooNaoEncontradoException("A reserva de voo não foi encontrada.");
		}

		return voo;
	}

	public Voo salvar(Voo voo) {
		voo.setId(null);
//		
//		voo.setOrigem(cidadeService.buscar(voo.getOrigem().getId()));
//		voo.setDestino(cidadeService.buscar(voo.getDestino().getId()));
//		
		return voosRepository.save(voo);
	}

	public void deletar(Long id) {
		try {
			voosRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new VooNaoEncontradoException("A reserva de voo não foi encontrada.");
		}
	}

	public void atualizar(Voo voo) {
		verificarExistencia(voo);
		voosRepository.save(voo);
	}

	public List<Voo> buscarPorOrigemEDestino(Long origemId, Long destinoId) {
		Cidade origem = cidadeService.buscar(origemId);
		Cidade destino = cidadeService.buscar(destinoId);

		if (origem == null) {
			throw new CidadeNaoEncontradaException("A Origem não pode ser encontrada.");
		}

		if (destino == null) {
			throw new CidadeNaoEncontradaException("O destino não pode ser encontrado.");
		}

		List<Voo> voo = voosRepository.findByOrigemAndDestino(origem, destino);

		if (voo.isEmpty()) {
			throw new VooNaoEncontradoException("A reserva de voo não foi encontrada.");
		}
		return voo;

	}

	public List<Voo> buscarPorOrigemDestinoDataPartida(Long origemId, Long destinoId, LocalDate dataPartida) {
		Cidade origem = cidadeService.buscar(origemId);
		Cidade destino = cidadeService.buscar(destinoId);

		if (origem == null) {
			throw new CidadeNaoEncontradaException("A Origem não pode ser encontrada.");
		}

		if (destino == null) {
			throw new CidadeNaoEncontradaException("O destino não pode ser encontrado.");
		}

		List<Voo> voo = voosRepository.findByOrigemAndDestinoAndDataPartida(origem, destino, dataPartida);

		if (voo.isEmpty()) {
			throw new VooNaoEncontradoException("A reserva de voo não foi encontrada.");
		}
		return voo;

	}

	public List<Voo> buscarPorOrigemDestinoDataPartidaFaixaPreco(Long origemId, Long destinoId, LocalDate dataPartida,
			BigDecimal preco1, BigDecimal preco2) {
		
		Cidade origem = cidadeService.buscar(origemId);
		Cidade destino = cidadeService.buscar(destinoId);
		
		List<Voo> voo = voosRepository.findByOrigemAndDestinoAndDataPartidaBetweenPrecos(origem, destino,
				dataPartida, preco1, preco2);

		if (voo.isEmpty()) {
			throw new VooNaoEncontradoException("A reserva de voo não foi encontrada.");
		}

		return voo;

	}

	private void verificarExistencia(Voo voo) {
		buscar(voo.getId());
	}

}
