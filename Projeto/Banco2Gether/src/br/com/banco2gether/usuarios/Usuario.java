package br.com.banco2gether.usuarios;

import br.com.banco2gether.contas.Conta;

public abstract class Usuario {
	private String nome;
	private String cpf;
	private String senha;
	private Conta conta;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public Conta getConta() {
		return this.conta;
	}
	public void setConta(Conta conta) {
		this.conta = conta;
	}
	
	
	
	
}
