package br.com.banco2gether.usuarios.exception;

public class ErrosLoginException extends RuntimeException {
	
	public ErrosLoginException(String mensagem)
	{
		super(mensagem);
	}

}
