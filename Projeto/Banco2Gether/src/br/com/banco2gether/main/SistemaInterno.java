package br.com.banco2gether.main;

import br.com.banco2gether.usuarios.*;
import br.com.banco2gether.util.Sistema;

public class SistemaInterno {
	public static void main(String[] args) {

		Gerente gerente1 = new Gerente();
		
		gerente1.setSenha("123");
		gerente1.setCpf("41404123423");
		
		Sistema.login(gerente1, "123", "41404123423");
		
	
	}
}
