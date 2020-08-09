package br.com.banco2gether.operacoes;

import br.com.banco2gether.contas.Conta;

public interface OperacaoBancaria {
	
	public void sacar(double quantia);
	public void depositar(double quantia);
	public void transferir(Conta conta, double quantia);

}
