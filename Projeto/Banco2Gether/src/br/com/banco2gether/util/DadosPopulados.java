package br.com.banco2gether.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.banco2gether.contas.*;
import br.com.banco2gether.usuarios.*;
import br.com.banco2gether.usuarios.Usuario;

public class DadosPopulados {

	private List<ListaModeloGeral> lista = new ArrayList<ListaModeloGeral>();

	public List<ListaModeloGeral> getLista() {
		return lista;
	}

	public DadosPopulados() {
		this.lista = listaDeUsuarios();
	}

	private List<ListaModeloGeral> listaDeUsuarios() {
		List<ListaModeloGeral> lista = new ArrayList<>();

		ListaModeloGeral usuario1 = new ListaModeloGeral("Joao", "1111", "1234", "cliente", "cc", 1, 100.00, 1);
		ListaModeloGeral usuario2 = new ListaModeloGeral("Ana", "2222", "1234", "gerente", "cp", 1, 100.00, 1);
		ListaModeloGeral usuario3 = new ListaModeloGeral("Maria", "3333", "1234", "diretor", "cc", 1, 100.00, 1);
		ListaModeloGeral usuario4 = new ListaModeloGeral("Antonio", "4444", "1234", "presidente", "cc", 1, 100.00, 1);

		lista.add(usuario1);
		lista.add(usuario2);
		lista.add(usuario3);
		lista.add(usuario4);

		return lista;
	}

	private List<Conta> listaDeContas() {
		Conta contaCorrente = new ContaCorrente();
		Conta contaPoupanca = new ContaPoupanca();

		List<Conta> lista = Arrays.asList(contaCorrente, contaPoupanca);

		return lista;
	}

}
