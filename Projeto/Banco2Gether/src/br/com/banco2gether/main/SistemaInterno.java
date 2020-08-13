package br.com.banco2gether.main;

import br.com.banco2gether.usuarios.*;
import br.com.banco2gether.util.DadosPopulados;
import br.com.banco2gether.util.Sistema;

class SistemaInterno {

	public static void main(String[] args) {
		DadosPopulados dados = new DadosPopulados();

        
		Usuario usuarioLogado = Sistema.login("1111", "1234");

		if (usuarioLogado != null)
			if (usuarioLogado instanceof Cliente) {
				Cliente cliente = (Cliente) usuarioLogado;
				
				cliente.getConta().sacar(20);
				cliente.getConta().depositar(100);
		
			}else if(usuarioLogado instanceof Gerente)
			{

			}
	}

}
