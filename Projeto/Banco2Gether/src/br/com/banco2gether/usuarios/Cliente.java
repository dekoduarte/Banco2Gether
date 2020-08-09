package br.com.banco2gether.usuarios;

import br.com.banco2gether.usuarios.exception.ErrosLoginException;

public class Cliente extends Usuario implements Autenticavel {

	@Override
	public void autenticar(String senha, String cpf) {
		if (!this.getCpf().equals(cpf) || !this.getSenha().equals(senha)) {
			throw new ErrosLoginException("Login ou senha inválidos");
		}
	}
}
