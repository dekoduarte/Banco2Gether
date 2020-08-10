package br.com.banco2gether.usuarios;

import br.com.banco2gether.agencias.Agencia;
import br.com.banco2gether.contas.Conta;
import br.com.banco2gether.relatorios.*;
import br.com.banco2gether.usuarios.exception.ErrosLoginException;

public class Gerente extends Funcionario{

	Agencia agencia;

	public Gerente(Agencia agencia) {
		this.agencia = agencia;
		this.cargo = Cargos.Gerente;
	}
	
	// Relatorio de número de contas na agencia,
	// atravez do metodo getNumeroContas que vem de agencia.
	public int getTotalContasNaAgencia()
	{
		return this.agencia.getNumeroContas();
	}

	@Override
	public void autenticar(String cpf, String senha) {
		if (!this.getCpf().equals(cpf) || !this.getSenha().equals(senha)) {
			throw new ErrosLoginException("Login ou senha inválidos");
		}
	}
}
