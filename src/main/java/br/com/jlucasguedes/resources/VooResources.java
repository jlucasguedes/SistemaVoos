package br.com.jlucasguedes.resources;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.jlucasguedes.domain.Voo;
import br.com.jlucasguedes.services.VoosService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/voos")
public class VooResources {

	@Autowired
	private VoosService voosService;

	@GetMapping
	public ResponseEntity<List<Voo>> listar() {
		return ResponseEntity.ok(voosService.listar());
	}

	@PostMapping
	public ResponseEntity<Void> salvar(@Valid @RequestBody Voo voo) {
		voo = voosService.salvar(voo);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(voo.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Optional<Voo>> buscar(@PathVariable Long id) {
		Optional<Voo> voo = voosService.buscar(id);
		return ResponseEntity.ok(voo);
	}

	@GetMapping(value = "/{origemId}/{destinoId}")
	public ResponseEntity<List<Voo>> buscarPorOrigemDestino(@PathVariable Long origemId, @PathVariable Long destinoId) {
		return ResponseEntity.ok(voosService.buscarPorOrigemEDestino(origemId, destinoId));
	}

	@GetMapping(value = "/{origemId}/{destinoId}/{dataPartida}")
	public ResponseEntity<List<Voo>> buscarPorOrigemDestinoDataPartida(@PathVariable Long origemId,
			@PathVariable Long destinoId, @PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate dataPartida) {
		return ResponseEntity.ok(voosService.buscarPorOrigemDestinoDataPartida(origemId, destinoId, dataPartida));
	}
	
	@GetMapping(value = "/{origemId}/{destinoId}/{dataPartida}/{preco1}/{preco2}")
	public ResponseEntity<List<Voo>> buscarPorOrigemDestinoDataPartidaFaixaPreco(@PathVariable Long origemId,
			@PathVariable Long destinoId, @PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate dataPartida, @PathVariable BigDecimal preco1,
			@PathVariable BigDecimal preco2) {

		return ResponseEntity
				.ok(voosService.buscarPorOrigemDestinoDataPartidaFaixaPreco(origemId, destinoId,
						dataPartida, preco1, preco2));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		voosService.deletar(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> atualizar(@RequestBody Voo voo, @PathVariable Long id) {
		voo.setId(id);
		voosService.atualizar(voo);
		return ResponseEntity.noContent().build();
	}
}
