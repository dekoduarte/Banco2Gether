package br.com.banco2gether.usuarios.exception;

public class SaldoInsuficienteException extends RuntimeException {
	
	public SaldoInsuficienteException(String mensagem)
	{
		super(mensagem);
	}

}
