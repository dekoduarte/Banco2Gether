package br.com.banco2gether.usuarios;

import java.io.IOException;
import java.util.List;

import br.com.banco2gether.relatorios.IRelatorioDiretoria;
import br.com.banco2gether.usuarios.exception.ErrosLoginException;
import br.com.banco2gether.util.DadosPopulados;
import br.com.banco2gether.util.IOFiles;
import br.com.banco2gether.util.ListaModeloGeral;

public class Diretor extends Funcionario implements IRelatorioDiretoria {

	public Diretor(String nome, String cpf, String senha) {
		this.setNome(nome);
		this.setCpf(cpf);
		this.setSenha(senha);
		this.setCargo(Cargos.Diretor);
	}

	@Override
	public void autenticar(String cpf, String senha) {
		if (!this.getCpf().equals(cpf) || !this.getSenha().equals(senha)) {
			throw new ErrosLoginException("Login ou senha inválidos");
		}
	}

	@Override
	public void relatorioClientesDoBanco() throws IOException {
		DadosPopulados dados = new DadosPopulados();
		
		List<ListaModeloGeral> lista = dados.getLista();
		
		lista.stream()
		.map(c -> "Nome: " + c.nome + ", CPF: " + c.cpf + ", Agencia: " + c.numero_agencia )
		.forEach(System.out::println);
		
		IOFiles.escreveRelatorioClientesDoBanco(Cargos.Diretor);
	}
}
