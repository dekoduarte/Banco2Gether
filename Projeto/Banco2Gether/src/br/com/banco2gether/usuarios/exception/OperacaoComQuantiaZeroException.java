package br.com.banco2gether.usuarios.exception;

public class OperacaoComQuantiaZeroException extends RuntimeException{
	public OperacaoComQuantiaZeroException(String mensagem)
	{
		super(mensagem);
	}
}
