package br.com.banco2gether.relatorios;

public interface IRelatorioGeral {
	
	public String retornoSaldo = "Saldo em conta: R$";
	public String retornoTotalTributadoNaConta = "Valores tributados em sua conta até o presente momento: R$";
	
	public String relatorioSaldo();
	public String relatorioTributacaoContaCorrente();
	public String relatorioRendimentoPoupanca();
}
