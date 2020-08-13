package br.com.banco2gether.seguros;

import br.com.banco2gether.usuarios.Usuario;

public class SeguroDeVida implements Tributavel {

	private double valor;
	private final double tributoSeguroDeVida = 0.2;
	
	public SeguroDeVida(double valor) {
		this.valor = valor;
		
	}
	
	@Override
	public double getValorImposto() {
		return this.valor * this.tributoSeguroDeVida;
	}
	
	public double getTributoSeguroDeVida()
	{
		return this.tributoSeguroDeVida;
	}

}
