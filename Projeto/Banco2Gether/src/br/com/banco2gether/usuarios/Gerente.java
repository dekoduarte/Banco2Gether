package br.com.banco2gether.usuarios;

import br.com.banco2gether.contas.Conta;
import br.com.banco2gether.relatorios.*;
import br.com.banco2gether.usuarios.exception.ErrosLoginException;

public class Gerente extends Funcionario{

	int numero_agencia;

	public Gerente(String nome, String cpf, String senha, int numero_agencia) {
		this.numero_agencia = numero_agencia;
		this.setNome(nome);
		this.setCpf(cpf);
		this.setSenha(senha);
		this.cargo = Cargos.Gerente;
	}
	
	@Override
	public void autenticar(String cpf, String senha) {
		if (!this.getCpf().equals(cpf) || !this.getSenha().equals(senha)) {
			throw new ErrosLoginException("Login ou senha inválidos");
		}
	}
}
