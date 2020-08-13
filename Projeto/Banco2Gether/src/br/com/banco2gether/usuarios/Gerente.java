package br.com.banco2gether.usuarios;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

import br.com.banco2gether.contas.Conta;
import br.com.banco2gether.relatorios.*;
import br.com.banco2gether.usuarios.exception.ErrosLoginException;
import br.com.banco2gether.util.DadosPopulados;
import br.com.banco2gether.util.ListaModeloGeral;

public class Gerente extends Funcionario {

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
