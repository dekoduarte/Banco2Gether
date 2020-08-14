package br.com.banco2gether.usuarios;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

import br.com.banco2gether.contas.Conta;
import br.com.banco2gether.contas.ContaCorrente;
import br.com.banco2gether.contas.ContaPoupanca;
import br.com.banco2gether.contas.TipoContas;
import br.com.banco2gether.relatorios.*;
import br.com.banco2gether.usuarios.exception.ErrosLoginException;
import br.com.banco2gether.util.DadosPopulados;
import br.com.banco2gether.util.ListaModeloGeral;
import br.com.banco2gether.util.Sistema;

public class Gerente extends Funcionario {

	public Gerente(String nome, String cpf, String senha, int numero_agencia) {
		this.numero_agencia = numero_agencia;
		this.setNome(nome);
		this.setCpf(cpf);
		this.setSenha(senha);
		this.cargo = Cargos.GERENTE;
	}

	@Override
	public void autenticar(String cpf, String senha) {
		if (!this.getCpf().equals(cpf) || !this.getSenha().equals(senha)) {
			throw new ErrosLoginException("Login ou senha inválidos");
		}
	}
	
	public void CriaConta(String nome, String cpf, String senha, int num_conta, TipoContas tipo_conta, int agencia) {
		if (Sistema.buscaUsuario(cpf) != null) {
			throw new RuntimeException("CPF já Existe!");
		}
		if (Sistema.buscaUsuario(num_conta) != null) {
			throw new RuntimeException("Essa conta já Existe!");
		}
		Cliente c = new Cliente(nome, cpf, senha);
		if (tipo_conta == TipoContas.CORRENTE) {
			ContaCorrente cc = new ContaCorrente();
			cc.setTipo_conta(tipo_conta);
			cc.setNumConta(num_conta);
			cc.setAgencia(agencia);
			c.setConta(cc);
		} else {
			ContaPoupanca cp = new ContaPoupanca();
			cp.setTipo_conta(tipo_conta);
			cp.setNumConta(num_conta);
			cp.setAgencia(agencia);
			c.setConta(cp);
		}
		Sistema.tabelaUsuario.add(c);
	}

	
}
