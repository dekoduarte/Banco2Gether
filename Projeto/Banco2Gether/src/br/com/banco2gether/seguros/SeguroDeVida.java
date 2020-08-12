package br.com.banco2gether.seguros;

import br.com.banco2gether.usuarios.Usuario;

public class SeguroDeVida implements Tributavel {

	Usuario usuario;
	double valor;
	int numeroApolice;
	
	public SeguroDeVida(double valor) {
		this.valor = valor;
		
	}
	
	@Override
	public double getValorImposto() {
		return this.valor * 0.2;
	}

}
