package br.com.banco2gether.contas;

import br.com.banco2gether.seguros.Tributavel;

public class ContaCorrente extends Conta implements Tributavel {

	private double totalTributado;
	
	private double tributoSaque =  0.10;
	private double tributoDeposito =  0.10;
	private double tributoTransferencia =  0.20;
	
	@Override
	public void sacar(double quantia) {
		this.totalTributado += tributoSaque;
		this.saldo -= quantia + tributoSaque;
		
	}

	@Override
	public void depositar(double quantia) {
		this.totalTributado += tributoDeposito;
		this.saldo += quantia - tributoDeposito;
		
	}

	@Override
	public void transferir(Conta conta, double quantia) {
		this.totalTributado += tributoTransferencia;
		this.saldo -= quantia + tributoTransferencia;
		conta.saldo += quantia;
	}

	@Override
	public double getValorImposto() {
		return totalTributado;
	}
	
}
