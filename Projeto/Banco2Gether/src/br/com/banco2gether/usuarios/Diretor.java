package br.com.banco2gether.usuarios;

import java.util.List;

import br.com.banco2gether.relatorios.IRelatorioDiretoria;
import br.com.banco2gether.usuarios.exception.ErrosLoginException;

public class Diretor extends Funcionario implements IRelatorioDiretoria {

	public Diretor(String nome, String cpf, String senha) {
		this.setNome(nome);
		this.setCpf(cpf);
		this.setSenha(senha);
		this.setCargo(Cargos.Diretor);
	}

	@Override
	public void autenticar(String senha, String cpf) {
		if (!this.getCpf().equals(cpf) || !this.getSenha().equals(senha)) {
			throw new ErrosLoginException("Login ou senha inválidos");
		}

	}

	@Override
	public void relatorioClientesDoBanco(List<Cliente> contas) {
		// TODO Auto-generated method stub

	}
}
