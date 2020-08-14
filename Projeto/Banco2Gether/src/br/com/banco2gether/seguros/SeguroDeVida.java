package br.com.banco2gether.seguros;

public class SeguroDeVida implements Tributavel {

	private double valor;
	private final double tributoSeguroDeVida = 0.2;
	
	public SeguroDeVida(double valor) {	
		this.valor = valor;		
	}
	
	@Override
	public double getValorImposto() {
		double total = this.valor * this.tributoSeguroDeVida;
		return total;
	}
	
	public double getTributoSeguroDeVida()
	{
		return this.tributoSeguroDeVida;
	}
	
	public double getValor() {
		return this.valor;
	}

}
