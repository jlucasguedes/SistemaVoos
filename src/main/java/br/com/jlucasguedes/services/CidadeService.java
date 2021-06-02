package br.com.jlucasguedes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.jlucasguedes.domain.Cidade;
import br.com.jlucasguedes.repository.CidadesRepository;
import br.com.jlucasguedes.services.exceptions.CidadeNaoEncontradaException;

@Service
public class CidadeService {

	@Autowired
	private CidadesRepository cidadesRepository;

	public List<Cidade> listar() {
		return cidadesRepository.findAll();
	}

	public Cidade buscar(Long id) {
		Optional<Cidade> cidade = cidadesRepository.findById(id);

		if (cidade.isEmpty()) {
			throw new CidadeNaoEncontradaException("A cidade de origem ou destino não pode ser encontrada.");
		}

		return cidade.get();
	}

	public Cidade salvar(Cidade cidade) {
		cidade.setId(null);
		return cidadesRepository.save(cidade);
	}

	public void deletar(Long id) {
		try {
			cidadesRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new CidadeNaoEncontradaException("A cidade de origem ou destino não pode ser encontrada.");
		}
	}

	public void atualizar(Cidade cidade) {
		verificarExistencia(cidade);
		cidadesRepository.save(cidade);
	}

	private void verificarExistencia(Cidade cidade) {
		buscar(cidade.getId());
	}

}
