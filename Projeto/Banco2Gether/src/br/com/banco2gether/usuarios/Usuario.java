package br.com.banco2gether.usuarios;

import java.text.DecimalFormat;

import br.com.banco2gether.contas.Conta;
import br.com.banco2gether.contas.ContaCorrente;
import br.com.banco2gether.seguros.SeguroDeVida;

public abstract class Usuario implements Autenticavel {
	private String nome;
	private String cpf;
	private String senha;
	private Conta conta;
	private SeguroDeVida seguroDeVida;
	
	public final String retornoSaldo = "Saldo em conta: R$";
	public final String retornoTotalTributadoNaConta = "Valores tributados em sua conta até o presente momento: R$";
	
	public String relatorioSaldo() {
		return  retornoSaldo + this.getConta().getSaldo();
	}

	public String relatorioTributacaoContaCorrente() {
		ContaCorrente cc = (ContaCorrente) this.getConta();
		StringBuilder texto = new StringBuilder();
		DecimalFormat df = new DecimalFormat("0.00");
		
		texto.append(retornoTotalTributadoNaConta + cc.getValorImposto());
		texto.append("\n----------------------------------------------------------");
		texto.append("\n-----------------Tributacao Bancaria----------------------");
		texto.append("\nSaques: R$" + df.format(cc.getTributoSaque()));
		texto.append("\nDepositos: R$" + df.format(cc.getTributoDeposito()));
		texto.append("\nTransferencias: R$" + df.format(cc.getTributoTransferencia()));
		
		if(this.seguroDeVida != null)
			texto.append("\nSeguro de vida:" +  df.format(this.seguroDeVida.getTributoSeguroDeVida()) + "%");
		
		return texto.toString();
		
	}
	
	public void contrataSeguroDeVida(double quantia) {
		this.seguroDeVida = new SeguroDeVida(quantia);
	}

	public String relatorioRendimentoPoupanca() {
		return "";
	}
	
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
	
	public SeguroDeVida getSeguroDeVida() {
		return seguroDeVida;
	}

	
	
	
}
