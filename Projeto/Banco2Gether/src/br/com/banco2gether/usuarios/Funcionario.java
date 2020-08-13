package br.com.banco2gether.usuarios;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

import br.com.banco2gether.util.DadosPopulados;
import br.com.banco2gether.util.IOFiles;
import br.com.banco2gether.util.ListaModeloGeral;

public abstract class Funcionario extends Usuario implements Autenticavel {

	protected Cargos cargo;
	int numero_agencia;

	public void relatorioContasPorAgencia(int agencia) throws IOException {
		DadosPopulados dados = new DadosPopulados();
		
		Predicate<ListaModeloGeral> contas = x -> x.numero_agencia == agencia;

		AtomicInteger total_agencia = new AtomicInteger(0);

		dados.getLista().stream().filter(contas).forEach(c -> total_agencia.incrementAndGet());
		
		System.out.println("Existem atualmente " + total_agencia.intValue() + " clientes na sua agencia. \n");
		
		IOFiles.escreveRelatorioContasPorAgencia(total_agencia.intValue(), Cargos.Gerente);
	}

	public Cargos getCargo() {
		return cargo;
	}

	public void setCargo(Cargos cargo) {
		this.cargo = cargo;
	}

	public int getNumeroAgencia() {
		return this.numero_agencia;
	}

}
