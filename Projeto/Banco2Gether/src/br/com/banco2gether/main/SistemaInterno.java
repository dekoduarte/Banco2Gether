package br.com.banco2gether.main;

import java.io.IOException;
import java.util.Scanner;

import br.com.banco2gether.contas.*;
import br.com.banco2gether.usuarios.*;
import br.com.banco2gether.usuarios.exception.*;
import br.com.banco2gether.util.*;

class SistemaInterno {

	public enum OpcoesMenu { // MENU PARA CLIENTES
		OPERACOES(1), INFORMACOES(2), SAIR(3), SAQUE(4), DEPOSITO(5), TRANSFERENCIA(6), SIMULAR(7), INICIO(0), MENU(9);

	    private final int valor;
	    OpcoesMenu(int valorOpcao){
	        valor = valorOpcao;
	    }
	}	
	
	public static void escolheOpcao(Usuario usuario, OpcoesMenu opcao, TipoContas tipo) 
	{		
			Scanner scan = new Scanner(System.in);			
			if(opcao == OpcoesMenu.INICIO) {
				System.out.println("-- (1) = Entrar como Cliente (Conta Particular)\n-- (2) = Entrar do Menu Banco (Ger�nciar)");
				int op = scan.nextInt();				
				switch (op) {	
					case 1:						
						escolheOpcao(usuario,OpcoesMenu.MENU,tipo);
						break;
					case 2:  						
						if(usuario instanceof Gerente) { 
							Gerente g = (Gerente) usuario;
							try {
								g.relatorioContasPorAgencia(g.getNumeroAgencia());
							} catch (IOException e) {
								//e.printStackTrace();
							}
						}						
						else if(usuario instanceof Diretor) {  
							Diretor d = (Diretor) usuario;
							try {
								d.relatorioClientesDoBanco();
							} catch (IOException e) {
								//e.printStackTrace();
							}
						}						
						else if(usuario instanceof Presidente) { 
							Presidente p = (Presidente) usuario;
							//p.Relatori
						}
						escolheOpcao(usuario,OpcoesMenu.MENU,tipo);
						break;
					default:
				}				
				//usuarioLogado instanceof Funcionario
			}				
			if(opcao == OpcoesMenu.MENU)
			{					  																			
					
					if(usuario.getConta().getTipo_conta()==tipo.POUPANCA) { 
						System.out.println("\n\n\t------ Digite a Opera��o Desejada ------\n\n-- (1) = Saque\t\t\t (5) = Saldo\n-- (2) = Dep�sito\t\t (6) = Ver Rendimento\n-- (3) = Tranfer�ncia\t\t (7) = Simular Rendimento\n-- (4) = Seguro de Vida\n");	}						
					else { System.out.println("\n\n\t------ Digite a Opera��o Desejada ------\n\n-- (1) = Saque\t\t\t (5) = Saldo\n-- (2) = Dep�sito\t\t (6) = Info. Tributa��o\n-- (3) = Tranfer�ncia\n-- (4) = Seguro de Vida\n");	}
					
					int op = scan.nextInt();
					switch (op) {	
						case 1:
							System.out.print("Digite o Valor R$ ");
				            double val_saq = scan.nextInt();							 							
							 try {
								 usuario.getConta().sacar(val_saq);
								 System.out.println("Saque Realizado R$ "+val_saq+"\nRetire o Envelope!");
							 }
							 catch(SaldoInsuficienteException e) 
							 { 
								 System.out.println(e.getMessage()); 
							 }
							 escolheOpcao(usuario,OpcoesMenu.MENU,tipo);
							break;
						case 2:
							System.out.print("Digite o Valor R$ ");
				            double val_dep = scan.nextInt();
							 usuario.getConta().depositar(val_dep);
							 System.out.println("Dep�sito de  R$ "+val_dep+" realizado com sucesso!");
							 escolheOpcao(usuario,OpcoesMenu.MENU,tipo);
							break;
						case 3:
							/*
							System.out.print("Digite o n�mero da conta de destino: ");
				            int cnum = scan.nextInt();
				            System.out.print("Digite o valor da a ser transferido: R$ ");
				            double tval = scan.nextInt();
							System.out.println("Transfer�ncia Realizada R$ "+tval+" para "+destino.getUsuario().getNome());
							 usuario.getConta().transferir(destino, tval);*/
							 escolheOpcao(usuario,OpcoesMenu.MENU,tipo);
							break;
						case 4:
							if(usuario.getSeguroDeVida() == null) {								
								System.out.print("Digite o quantia a ser recebida em caso de falecimento, valor contratado = R$ ");
								int val_seg = scan.nextInt();
								usuario.contrataSeguroDeVida(val_seg);
							}
							else {	System.out.printf("Voc� j� possui um seguro de vida, o valor assegurado � de R$%.2f",usuario.getSeguroDeVida().getValor()); }
							escolheOpcao(usuario,OpcoesMenu.MENU,tipo);				            
							break;
						case 5:													
							System.out.printf("O seu saldo � de R$%.2f ",usuario.getConta().getSaldo());
							escolheOpcao(usuario,OpcoesMenu.MENU,tipo);
		        			break;
						case 6:
							if(usuario.getConta().getTipo_conta()==tipo.POUPANCA) { 
							System.out.printf("O rendimento do seu saldo de R$%.2f em 30 dias � de R$%.2f",usuario.getConta().getSaldo(), usuario.getConta().SimulaRendimento(usuario.getConta().getSaldo(), 30));							
							}
							else { 
								System.out.println(usuario.relatorioTributacaoContaCorrente()); }
							escolheOpcao(usuario,OpcoesMenu.MENU,tipo);
							break;
						case 7:
							System.out.print("Digite o valor que deseja simular R$ ");
		        			double sim_val = scan.nextInt();
		        			System.out.print("Digite o n�mero de Dias para calcular o rendimento: ");
		        			int sim_dia = scan.nextInt();
		        			System.out.printf("\nO Rendimento de R$%.2f em %d dias ser� de R$%.2f",sim_val,sim_dia, usuario.getConta().SimulaRendimento(sim_val, sim_dia));
		        			escolheOpcao(usuario,OpcoesMenu.MENU,tipo);
		        			break;
						default:
							System.out.println("Op��o inv�lida!");	
							escolheOpcao(usuario,OpcoesMenu.MENU,tipo);
				}

				break;
			default:
			}
			// usuarioLogado instanceof Funcionario
		}
		if (opcao == OpcoesMenu.MENU) {

			if (usuario.getConta().getTipo_conta() == tipo.POUPANCA) {
				System.out.println(
						"\n\n\t------ Digite a Opera��o Desejada ------\n\n-- (1) = Saque\t\t\t (5) = Saldo\n-- (2) = Dep�sito\t\t (6) = Ver Rendimento\n-- (3) = Tranfer�ncia\t\t (7) = Simular Rendimento\n-- (4) = Seguro de Vida\n");
			} else {
				System.out.println(
						"\n\n\t------ Digite a Opera��o Desejada ------\n\n-- (1) = Saque\t\t\t (5) = Saldo\n-- (2) = Dep�sito\t\t (6) = Info. Tributa��o\n-- (3) = Tranfer�ncia\n-- (4) = Seguro de Vida\n");
			}

			int op = scan.nextInt();
			switch (op) {
			case 1:
				System.out.print("Digite o Valor R$ ");
				double val_saq = scan.nextInt();
				try {
					usuario.getConta().sacar(val_saq);
					System.out.println("Saque Realizado R$ " + val_saq + "\nRetire o Envelope!");
				} catch (SaldoInsuficienteException e) {
					System.out.println(e.getMessage());
				}
				escolheOpcao(usuario, OpcoesMenu.MENU, tipo);
				break;
			case 2:
				System.out.print("Digite o Valor R$ ");
				double val_dep = scan.nextInt();
				usuario.getConta().depositar(val_dep);
				System.out.println("Dep�sito de  R$ " + val_dep + " realizado com sucesso!");
				escolheOpcao(usuario, OpcoesMenu.MENU, tipo);
				break;
			case 3:
				/*
				 * System.out.print("Digite o n�mero da conta de destino: "); int cnum =
				 * scan.nextInt(); System.out.print("Digite o valor da a ser transferido: R$ ");
				 * double tval = scan.nextInt();
				 * System.out.println("Transfer�ncia Realizada R$ "+tval+" para "+destino.
				 * getUsuario().getNome()); usuario.getConta().transferir(destino, tval);
				 */
				escolheOpcao(usuario, OpcoesMenu.MENU, tipo);
				break;
			case 4:
				if (usuario.getSeguroDeVida() == null) {
					System.out.print("Digite o quantia a ser recebida em caso de falecimento, valor contratado = R$ ");
					int val_seg = scan.nextInt();
					usuario.contrataSeguroDeVida(val_seg);
				} else {
					System.out.printf("Voc� j� possui um seguro de vida, o valor assegurado � de R$%.2f",
							usuario.getSeguroDeVida().getValor());
				}
				escolheOpcao(usuario, OpcoesMenu.MENU, tipo);
				break;
			case 5:
				System.out.printf("O seu saldo � de R$%.2f ", usuario.getConta().getSaldo());
				escolheOpcao(usuario, OpcoesMenu.MENU, tipo);
				break;
			case 6:
				if (usuario.getConta().getTipo_conta() == tipo.POUPANCA) {
					System.out.printf("O rendimento do seu saldo de R$%.2f em 30 dias � de R$%.2f",
							usuario.getConta().getSaldo(),
							usuario.getConta().SimulaRendimento(usuario.getConta().getSaldo(), 30));
				} else {
					System.out.println(usuario.relatorioTributacaoContaCorrente());
				}
				escolheOpcao(usuario, OpcoesMenu.MENU, tipo);
				break;
			case 7:
				System.out.print("Digite o valor que deseja simular R$ ");
				double sim_val = scan.nextInt();
				System.out.print("Digite o n�mero de Dias para calcular o rendimento: ");
				int sim_dia = scan.nextInt();
				System.out.printf("\nO Rendimento de R$%.2f em %d dias ser� de R$%.2f", sim_val, sim_dia,
						usuario.getConta().SimulaRendimento(sim_val, sim_dia));
				escolheOpcao(usuario, OpcoesMenu.MENU, tipo);
				break;
			default:
				System.out.println("Op��o inv�lida!");
				escolheOpcao(usuario, OpcoesMenu.MENU, tipo);
			}
		}
	}

	public static void main(String[] args) {
		DadosPopulados dados = new DadosPopulados();
       
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Digite seu CPF: ");
		String cpf = scan.nextLine().trim();
		System.out.print("Digite sua Senha: ");
		String senha = scan.nextLine().trim();
		Usuario usuarioLogado = null;

		try {
			usuarioLogado = Sistema.login(cpf, senha);
		} catch (ErrosLoginException e) {
			System.out.println(e.getMessage());
		}

		if (usuarioLogado != null) {
			if (usuarioLogado instanceof Cliente) {
				Cliente cliente = (Cliente) usuarioLogado;
				System.out.println("\nTITULAR: " + cliente.getNome().toUpperCase() + " | CONTA "
						+ cliente.getConta().getTipo_conta() + " - N�MERO: " + cliente.getConta().getNumConta()
						+ " - AG�NCIA: " + cliente.getConta().getAgencia() + " |");
				escolheOpcao(cliente, OpcoesMenu.MENU, cliente.getConta().getTipo_conta());

			}
			if (usuarioLogado instanceof Funcionario) {
				Usuario func = (Funcionario) usuarioLogado;					
					escolheOpcao(func, OpcoesMenu.INICIO,func.getConta().getTipo_conta());
			}			
		}
		else { System.out.println("CPF e/ou senha inv�lido(s)!"); }
	}
}