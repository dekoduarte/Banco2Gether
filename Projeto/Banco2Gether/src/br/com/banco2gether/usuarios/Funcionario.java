package br.com.banco2gether.usuarios;

import br.com.banco2gether.operacoes.OperacaoBancaria;

public abstract class Funcionario extends Usuario implements Autenticavel {
	private String cargo;
}
