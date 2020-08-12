package br.com.banco2gether.contas;

import br.com.banco2gether.operacoes.OperacaoBancaria;
import br.com.banco2gether.usuarios.Usuario;

public abstract class Conta implements OperacaoBancaria {

	Usuario usuario;
	int numero_agencia;
	double saldo;
	TipoContas tipo_conta;

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
