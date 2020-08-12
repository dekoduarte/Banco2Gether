package br.com.banco2gether.util;

public class ListaModeloGeral {
	String nome;
	String cpf;
	String senha;
	String tipo_usuario;
	String tipo_conta;
	int numero_conta;
	double saldo_conta;
	int numero_agencia;
	
	public ListaModeloGeral(String nome, String cpf, String senha, String tipo_usuario, String tipo_conta,
			int numero_conta, double saldo_conta, int numero_agencia) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.senha = senha;
		this.tipo_usuario = tipo_usuario;
		this.tipo_conta = tipo_conta;
		this.numero_conta = numero_conta;
		this.saldo_conta = saldo_conta;
		this.numero_agencia = numero_agencia;
	}
}
