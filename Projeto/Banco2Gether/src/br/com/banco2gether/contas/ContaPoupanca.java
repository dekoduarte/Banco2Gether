package br.com.banco2gether.contas;

public class ContaPoupanca extends Conta {

	@Override
	public void sacar(double quantia) {
		this.saldo -= quantia;
		
	}

	@Override
	public void depositar(double quantia) {
		this.saldo += quantia;
		
	}

	@Override
	public void transferir(Conta conta, double quantia) {
		this.saldo -= quantia;
        conta.saldo += quantia;
	}

}
