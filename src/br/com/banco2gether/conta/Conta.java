package br.com.banco2gether.conta;

import br.com.banco2gether.agencia.Agencia;
import br.com.banco2gether.usuario.Usuario;

public abstract class Conta {
	Usuario cliente;
	Agencia agencia;
	double saldo;
	TipoConta tipo_conta;
}
