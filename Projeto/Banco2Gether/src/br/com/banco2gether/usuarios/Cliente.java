package br.com.banco2gether.usuarios;

import br.com.banco2gether.contas.ContaCorrente;
import br.com.banco2gether.relatorios.IRelatorioGeral;
import br.com.banco2gether.seguros.SeguroDeVida;
import br.com.banco2gether.usuarios.exception.ErrosLoginException;

public class Cliente extends Usuario implements Autenticavel, IRelatorioGeral {

	private SeguroDeVida seguroDeVida;
	
	public Cliente(String nome, String cpf, String senha)
	{
		this.setNome(nome);
		this.setCpf(cpf);
		this.setSenha(senha);
	}
	
	@Override
	public void autenticar(String cpf, String senha) {
		if (!this.getCpf().equals(cpf) || !this.getSenha().equals(senha)) {
			throw new ErrosLoginException("Login ou senha inválidos");
		}
	}

	public SeguroDeVida getSeguroDeVida() {
		return seguroDeVida;
	}

	public void setSeguroDeVida(SeguroDeVida seguroDeVida) {
		this.seguroDeVida = seguroDeVida;
	}

	@Override
	public String relatorioSaldo() {
		return  retornoSaldo + this.getConta().getSaldo();
		
	}

	@Override
	public String relatorioTributacaoContaCorrente() {
		ContaCorrente cc = (ContaCorrente) this.getConta();
		StringBuilder texto = new StringBuilder();
		
		texto.append(retornoTotalTributadoNaConta + cc.getValorImposto());
		texto.append("\n----------------------------------------------------------");
		texto.append("\n-----------------Tributacao Bancaria----------------------");
		texto.append("\nSaques: R$" + cc.getTributoSaque());
		texto.append("\nDepositos: R$" + cc.getTributoDeposito());
		texto.append("\nTransferencias: R$" + cc.getTributoTransferencia());
		
		return texto.toString();
		
	}

	@Override
	public String relatorioRendimentoPoupanca() {
		return "";
		
	}
		
}
