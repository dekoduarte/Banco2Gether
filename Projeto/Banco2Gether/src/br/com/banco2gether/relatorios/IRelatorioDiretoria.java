package br.com.banco2gether.relatorios;

import java.util.List;

import br.com.banco2gether.usuarios.Cliente;

public interface IRelatorioDiretoria {
	public void relatorioClientesDoBanco(List<Cliente> contas);
}
