package br.com.jlucasguedes.services.exceptions;

public class CidadeNaoEncontradaException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7013939239288782188L;

	public CidadeNaoEncontradaException(String mensagem) {
		super(mensagem);
	}

	public CidadeNaoEncontradaException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
