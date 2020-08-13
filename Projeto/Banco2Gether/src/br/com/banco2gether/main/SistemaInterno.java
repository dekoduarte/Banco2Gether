package br.com.banco2gether.main;

import java.io.IOException;

import br.com.banco2gether.usuarios.*;
import br.com.banco2gether.util.DadosPopulados;
import br.com.banco2gether.util.IOFiles;
import br.com.banco2gether.util.Sistema;

class SistemaInterno {

	public static void main(String[] args) {
		DadosPopulados dados = new DadosPopulados();

		Usuario usuarioLogado = Sistema.login("2222", "1234");

		if (usuarioLogado != null)
			if (usuarioLogado instanceof Cliente) {
				Cliente cliente = (Cliente) usuarioLogado;

				cliente.getConta().sacar(20);
				cliente.getConta().depositar(100);

				System.out.println(cliente.relatorioTributacaoContaCorrente());

			} else if (usuarioLogado instanceof Gerente) {
				Gerente gerente = (Gerente) usuarioLogado;

				try {
					gerente.relatorioContasPorAgencia(gerente.getNumeroAgencia());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(usuarioLogado instanceof Diretor)
			{
				Diretor diretor = (Diretor) usuarioLogado;
				
				diretor.relatorioClientesDoBanco();
			}
			else if(usuarioLogado instanceof Presidente)
			{
				Presidente presidente = (Presidente) usuarioLogado;
				
				System.out.println(presidente.relatorioTotalCapitalDoBanco());
			}
	}

}
