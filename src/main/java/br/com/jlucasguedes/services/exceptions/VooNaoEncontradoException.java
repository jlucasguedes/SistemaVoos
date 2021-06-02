package br.com.jlucasguedes.services.exceptions;

public class VooNaoEncontradoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8186733101098096414L;

	public VooNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public VooNaoEncontradoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
