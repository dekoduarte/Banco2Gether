package br.com.banco2gether.contas;

import br.com.banco2gether.agencias.Agencia;
import br.com.banco2gether.operacoes.OperacaoBancaria;
import br.com.banco2gether.usuarios.Usuario;

public abstract class Conta implements OperacaoBancaria {
	Usuario usuario;
	Agencia agencia;
	double saldo;
	TipoContas tipo_conta;
}
