package br.com.banco2gether.main;

import java.util.ArrayList;
import java.util.List;

import br.com.banco2gether.agencias.Agencia;
import br.com.banco2gether.contas.Conta;
import br.com.banco2gether.contas.ContaCorrente;
import br.com.banco2gether.seguros.SeguroDeVida;
import br.com.banco2gether.usuarios.*;
import br.com.banco2gether.util.Sistema;

public class SistemaInterno {
	public static void main(String[] args) {

		List<Conta> contas = new ArrayList<Conta>();
		
		contas.add(new ContaCorrente());
		contas.add(new ContaCorrente());
		contas.add(new ContaCorrente()); 

		
		Agencia agencia = new Agencia( 1, contas);
		Gerente gerente1 = new Gerente(agencia);
		
		gerente1.setSenha("123");
		gerente1.setCpf("41404123423");
		Sistema.login(gerente1, "41404123423", "12345");
		
		System.out.println(gerente1.getTotalContasNaAgencia());
		System.out.println(gerente1.getCargo());
		
		Cliente cliente1 = new Cliente();
		SeguroDeVida seguro = new SeguroDeVida(300.50);
		cliente1.setSeguroDeVida(seguro);
		
		cliente1.getSeguroDeVida().getValorImposto();
	}

}
