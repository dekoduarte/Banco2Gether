package br.com.banco2gether.contas;

public class ContaPoupanca extends Conta {
	
	private double jurosDiario = 0.00004125;
	
	@Override
	public double getRendimento() {
		this.rendimento = this.saldo * (jurosDiario * 30);
		return super.rendimento;
	}

	@Override
	public double SimulaRendimento(double quantia, int dias) {
		this.simula_rendimento = quantia * (jurosDiario * dias); 
		return super.simula_rendimento;
	}
}
