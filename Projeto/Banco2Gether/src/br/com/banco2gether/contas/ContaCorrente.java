package br.com.banco2gether.contas;

public class ContaCorrente extends Conta{

	@Override
	public void sacar(double quantia) {
		this.saldo -= quantia + 0.10;
		
	}

	@Override
	public void depositar(double quantia) {
		this.saldo += quantia - 0.10;
		
	}

	@Override
	public void transferir(Conta conta, double quantia) {
		this.saldo -= quantia + 0.20;
        conta.saldo += quantia;
		
	}

}
