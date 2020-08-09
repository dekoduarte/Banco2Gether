package br.com.banco2gether.agencias;

import java.util.List;

import br.com.banco2gether.contas.Conta;
import br.com.banco2gether.usuarios.Gerente;

public class Agencia {

	int numero;
	public Gerente gerente;
	private List<Conta> contas;

	public Agencia(int numero, List<Conta> contas) {
		this.numero = numero;
		this.contas = contas;
	}
}
