package br.com.jlucasguedes.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.jlucasguedes.domain.DetalhesErro;
import br.com.jlucasguedes.services.exceptions.CidadeNaoEncontradaException;
import br.com.jlucasguedes.services.exceptions.VooNaoEncontradoException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(VooNaoEncontradoException.class)
	public ResponseEntity<DetalhesErro> handlerVooNaoEncontradoException(VooNaoEncontradoException e,
			HttpServletRequest request) {

		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(404l);
		erro.setTitulo("A reserva de voo não pode ser encontrada");
		erro.setMensagemDesenvolvedor("https://localhost:8080/erros/404");
		erro.setTimestamp(System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}

	@ExceptionHandler(CidadeNaoEncontradaException.class)
	public ResponseEntity<DetalhesErro> handlerCidadeNaoEncontradaException(CidadeNaoEncontradaException e,
			HttpServletRequest request) {

		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(404l);
		erro.setTitulo(e.getMessage());
		erro.setMensagemDesenvolvedor("https://localhost:8080/erros/404");
		erro.setTimestamp(System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
}
