package br.com.banco2gether.util;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import br.com.banco2gether.contas.Conta;
import br.com.banco2gether.contas.ContaCorrente;
import br.com.banco2gether.contas.ContaPoupanca;
import br.com.banco2gether.contas.TipoContas;
import br.com.banco2gether.seguros.Tributavel;
import br.com.banco2gether.usuarios.Autenticavel;
import br.com.banco2gether.usuarios.Cliente;
import br.com.banco2gether.usuarios.Diretor;
import br.com.banco2gether.usuarios.Gerente;
import br.com.banco2gether.usuarios.Presidente;
import br.com.banco2gether.usuarios.Usuario;
import br.com.banco2gether.usuarios.exception.ErrosLoginException;

public final class Sistema {

	private final static String CONTA_CORRENTE = "cc";
	private final static String CONTA_POUPANCA = "cp";

	public static List<Usuario> tabelaUsuario = new ArrayList<>();

	public static Usuario login(String cpf, String senha) {

		Usuario usuario = buscaUsuario(cpf);

		if (usuario != null)
			usuario.autenticar(cpf, senha);
		else
			throw new ErrosLoginException("Conta inexistente");

		return usuario;
	}

	public static Usuario buscaUsuario(String cpf) {
		return (Usuario) tabelaUsuario.stream().filter(u -> cpf.equals(u.getCpf())).findFirst().orElse(null);
	}
	
	public static Usuario buscaUsuario(int numero_conta) {
		return (Usuario) tabelaUsuario.stream().filter(u -> numero_conta == u.getConta().getNumConta()).findFirst().orElse(null);
	}

	public static Usuario buscaUsuario(int numero_conta) {
		return (Usuario) tabelaUsuario.stream().filter(u -> numero_conta == u.getConta().getNumConta()).findFirst().orElse(null);
	}

	public static double relatorioDeTributacao(Tributavel obj) {
		return obj.getValorImposto();
	}

	public static double relatorioSaldo(Conta conta) {
		return conta.getSaldo();
	}

	public static List<ListaModeloGeral> recuperaUsuariosDoSistema() {

		DadosPopulados dados = new DadosPopulados();

		List<ListaModeloGeral> lista = new ArrayList<>();

		try {
			lista = dados.getLista();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lista;
	}

	public static void construtorUsuarios() {

		List<ListaModeloGeral> usuarios = recuperaUsuariosDoSistema();

		for (ListaModeloGeral u : usuarios) {
			if (u.tipo_usuario.equalsIgnoreCase("Cliente")) {

				Cliente cliente = new Cliente(u.nome, u.cpf, u.senha);
				Conta conta;

				if (u.tipo_conta.equalsIgnoreCase(CONTA_CORRENTE)) {
					ContaCorrente cc = new ContaCorrente();
					cc.setTipo_conta(TipoContas.CORRENTE);
					cc.setAgencia(u.numero_agencia);
					cc.setSaldo(u.saldo_conta);
					cc.setNumConta(u.numero_conta);
					cliente.setConta(cc);
					conta = cc;
				} else {
					ContaPoupanca cp = new ContaPoupanca();
					cp.setTipo_conta(TipoContas.POUPANCA);
					cp.setAgencia(u.numero_agencia);
					cp.setSaldo(u.saldo_conta);
					cp.setNumConta(u.numero_conta);
					cliente.setConta(cp);
					conta = cp;
				}

				conta.setUsuario(cliente);
				conta.setAgencia(u.numero_agencia);

				tabelaUsuario.add(cliente);

			} else if (u.tipo_usuario.equalsIgnoreCase("Gerente")) {

				Gerente gerente = new Gerente(u.nome, u.cpf, u.senha, u.numero_agencia);
				Conta conta;

				if (u.tipo_conta.equalsIgnoreCase(CONTA_CORRENTE)) {
					ContaCorrente cc = new ContaCorrente();
					cc.setTipo_conta(TipoContas.CORRENTE);
					cc.setAgencia(u.numero_agencia);
					cc.setSaldo(u.saldo_conta);
					cc.setNumConta(u.numero_conta);
					gerente.setConta(cc);
					conta = cc;
				} else {
					ContaPoupanca cp = new ContaPoupanca();
					cp.setTipo_conta(TipoContas.POUPANCA);
					cp.setAgencia(u.numero_agencia);
					cp.setSaldo(u.saldo_conta);
					cp.setNumConta(u.numero_conta);
					gerente.setConta(cp);
					conta = cp;
				}

				conta.setUsuario(gerente);
				conta.setAgencia(u.numero_agencia);

				tabelaUsuario.add(gerente);

			} else if (u.tipo_usuario.equalsIgnoreCase("Diretor")) {

				Diretor diretor = new Diretor(u.nome, u.cpf, u.senha);
				Conta conta;

				if (u.tipo_conta.equalsIgnoreCase(CONTA_CORRENTE)) {
					ContaCorrente cc = new ContaCorrente();
					cc.setTipo_conta(TipoContas.CORRENTE);
					cc.setAgencia(u.numero_agencia);
					cc.setSaldo(u.saldo_conta);
					cc.setNumConta(u.numero_conta);
					diretor.setConta(cc);
					conta = cc;
				} else {
					ContaPoupanca cp = new ContaPoupanca();
					cp.setTipo_conta(TipoContas.POUPANCA);
					cp.setAgencia(u.numero_agencia);
					cp.setSaldo(u.saldo_conta);
					cp.setNumConta(u.numero_conta);
					diretor.setConta(cp);
					conta = cp;
				}

				conta.setUsuario(diretor);
				conta.setAgencia(u.numero_agencia);

				tabelaUsuario.add(diretor);

			} else {
				Presidente presidente = new Presidente(u.nome, u.cpf, u.senha);
				Conta conta;

				if (u.tipo_conta.equalsIgnoreCase(CONTA_CORRENTE)) {
					ContaCorrente cc = new ContaCorrente();
					cc.setTipo_conta(TipoContas.CORRENTE);
					cc.setAgencia(u.numero_agencia);
					cc.setSaldo(u.saldo_conta);
					cc.setNumConta(u.numero_conta);
					presidente.setConta(cc);
					conta = cc;
				} else {
					ContaPoupanca cp = new ContaPoupanca();
					cp.setTipo_conta(TipoContas.POUPANCA);
					cp.setAgencia(u.numero_agencia);
					cp.setSaldo(u.saldo_conta);
					cp.setNumConta(u.numero_conta);
					presidente.setConta(cp);
					conta = cp;
				}

				conta.setUsuario(presidente);
				conta.setAgencia(u.numero_agencia);

				tabelaUsuario.add(presidente);
			}
		}
	}

}
