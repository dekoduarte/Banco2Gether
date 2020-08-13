package br.com.banco2gether.contas;

import java.io.IOException;

import br.com.banco2gether.operacoes.TipoOperacao;
import br.com.banco2gether.seguros.Tributavel;
import br.com.banco2gether.usuarios.exception.IOFilesException;
import br.com.banco2gether.usuarios.exception.OperacaoComQuantiaZeroException;
import br.com.banco2gether.usuarios.exception.SaldoInsuficienteException;
import br.com.banco2gether.util.IOFiles;

public class ContaCorrente extends Conta implements Tributavel {

	private double totalTributado;

	private double tributoSaque = 0.10;
	private double tributoDeposito = 0.10;
	private double tributoTransferencia = 0.20;

	@Override
	public void sacar(double quantia) {
		if (quantia + tributoSaque > this.saldo)
			throw new SaldoInsuficienteException("Saldo insuficiente");

		if (quantia <= 0)
			throw new OperacaoComQuantiaZeroException("Valor selecionado é negativo ou zero");
		
		this.saldo -= quantia + tributoSaque;
		this.totalTributado += tributoSaque;

		try {
			IOFiles.escreveArquivoOperacaoBancaria(this.getUsuario().getNome(), quantia, tributoSaque,
					TipoOperacao.SAQUE);
		} catch (IOFilesException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	@Override
	public void depositar(double quantia) {
		
		if (quantia <= 0)
			throw new OperacaoComQuantiaZeroException("Valor selecionado é negativo ou zero");
		
		this.saldo += quantia - tributoDeposito;
		this.totalTributado += tributoDeposito;
		
		try {
			IOFiles.escreveArquivoOperacaoBancaria(this.getUsuario().getNome(), quantia, tributoDeposito,
					TipoOperacao.DEPOSITO);
		} catch (IOFilesException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	@Override
	public void transferir(Conta conta, double quantia) {
		
		if (quantia + tributoTransferencia > this.saldo)
			throw new SaldoInsuficienteException("Saldo insuficiente");

		if (quantia <= 0)
			throw new OperacaoComQuantiaZeroException("Valor selecionado é negativo ou zero");
		
		this.saldo -= quantia + tributoTransferencia;
		conta.saldo += quantia;
		
		this.totalTributado += tributoTransferencia;
		
		try {
			IOFiles.escreveArquivoOperacaoBancaria(this.getUsuario().getNome(), quantia, tributoTransferencia,
					TipoOperacao.TRANSFERENCIA);
		} catch (IOFilesException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public double getValorImposto() {
		return totalTributado;
	}

	public double getTributoSaque() {
		return tributoSaque;
	}

	public double getTributoDeposito() {
		return tributoDeposito;
	}

	public double getTributoTransferencia() {
		return tributoTransferencia;
	}

}
