package br.com.banco2gether.usuarios;

import br.com.banco2gether.seguros.SeguroDeVida;
import br.com.banco2gether.usuarios.exception.ErrosLoginException;

public class Cliente extends Usuario implements Autenticavel {

	private SeguroDeVida seguroDeVida;
	
	public Cliente(String nome, String cpf, String senha)
	{
		this.setNome(nome);
		this.setCpf(cpf);
		this.setSenha(senha);
	}
	
	@Override
	public void autenticar(String cpf, String senha) {
		if (!this.getCpf().equals(cpf) || !this.getSenha().equals(senha)) {
			throw new ErrosLoginException("Login ou senha inválidos");
		}
	}

	public SeguroDeVida getSeguroDeVida() {
		return seguroDeVida;
	}

	public void setSeguroDeVida(SeguroDeVida seguroDeVida) {
		this.seguroDeVida = seguroDeVida;
	}
		
}
