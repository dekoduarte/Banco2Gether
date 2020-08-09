package br.com.banco2gether.usuarios;

import br.com.banco2gether.agencias.Agencia;
import br.com.banco2gether.contas.Conta;
import br.com.banco2gether.usuarios.exception.ErrosLoginException;

public class Gerente extends Funcionario {
	
	Agencia agencia;
	
	@Override
	public void autenticar(String senha, String cpf) {
		if (!this.getCpf().equals(cpf) || !this.getSenha().equals(senha)) {
			throw new ErrosLoginException("Login ou senha inválidos");
		}

	}

}
