package br.com.banco2gether.contas;

import java.io.IOException;

import br.com.banco2gether.operacoes.TipoOperacao;
import br.com.banco2gether.usuarios.Usuario;
import br.com.banco2gether.usuarios.exception.IOFilesException;
import br.com.banco2gether.usuarios.exception.OperacaoComQuantiaZeroException;
import br.com.banco2gether.usuarios.exception.SaldoInsuficienteException;
import br.com.banco2gether.util.IOFiles;

public abstract class Conta {

	private Usuario usuario;
	private int numero_agencia;
	double saldo;
	private TipoContas tipo_conta;

	public void sacar(double quantia) {
		if (quantia > this.saldo)
			throw new SaldoInsuficienteException("Saldo insuficiente");

		if (quantia <= 0)
			throw new OperacaoComQuantiaZeroException("Valor selecionado é negativo ou zero");

		this.saldo -= quantia;

		try {
			IOFiles.escreveArquivoOperacaoBancaria(this.getUsuario().getNome(), quantia, 0,
					TipoOperacao.Saque);
		} catch (IOFilesException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public void depositar(double quantia) {
		if (quantia <= 0)
			throw new OperacaoComQuantiaZeroException("Valor selecionado é negativo ou zero");

		this.saldo += quantia;
		
		try {
			IOFiles.escreveArquivoOperacaoBancaria(this.getUsuario().getNome(), quantia, 0,
					TipoOperacao.Deposito);
		} catch (IOFilesException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public void transferir(Conta conta, double quantia) {
		
		if (quantia > this.saldo)
			throw new SaldoInsuficienteException("Saldo insuficiente");

		if (quantia <= 0)
			throw new OperacaoComQuantiaZeroException("Valor selecionado é negativo ou zero");
		
		this.saldo -= quantia;
		conta.saldo += quantia;
		
		try {
			IOFiles.escreveArquivoOperacaoBancaria(this.getUsuario().getNome(), quantia, 0,
					TipoOperacao.Deposito);
		} catch (IOFilesException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public int getAgencia() {
		return numero_agencia;
	}

	public void setAgencia(int numero_agencia) {
		this.numero_agencia = numero_agencia;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double quantia) {
		this.saldo = quantia;
	}

	public TipoContas getTipo_conta() {
		return tipo_conta;
	}

	public void setTipo_conta(TipoContas tipo_conta) {
		this.tipo_conta = tipo_conta;
	}

}
