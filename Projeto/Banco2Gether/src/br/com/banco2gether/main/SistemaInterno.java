package br.com.banco2gether.main;

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
				System.out.println("-- (1) = Entrar como Cliente (Conta Particular)\n-- (2) = Entrar do Menu Banco (Gerênciar)");
				int op = scan.nextInt();				
				switch (op) {	
					case 1:						
						escolheOpcao(usuario,OpcoesMenu.MENU,tipo);
						break;
					case 2:  
						
						if(usuario instanceof Gerente) { 
							Gerente g = (Gerente) usuario;
							//g.relatorioContasPorAgencia(g.getNumeroAgencia());
						}						
						else if(usuario instanceof Diretor) { 
							Diretor d = (Diretor) usuario;
							//d.relatorioClientesDoBanco();
						}						
						else if(usuario instanceof Presidente) { 
							Presidente p = (Presidente) usuario;
							//p.Relatori
						}
						
						break;
					default:
				}				
				//usuarioLogado instanceof Funcionario
			}				
			if(opcao == OpcoesMenu.MENU)
			{					  																			
					
					if(usuario.getConta().getTipo_conta()==tipo.POUPANCA) { 
						System.out.println("\n\n\t------ Digite a Operação Desejada ------\n\n-- (1) = Saque\t\t\t (5) = Saldo\n-- (2) = Depósito\t\t (6) = Ver Rendimento\n-- (3) = Tranferência\t\t (7) = Simular Rendimento\n-- (4) = Seguro de Vida\n");	}						
					else { System.out.println("\n\n\t------ Digite a Operação Desejada ------\n\n-- (1) = Saque\t\t\t (5) = Saldo\n-- (2) = Depósito\t\t (6) = Info. Tributação\n-- (3) = Tranferência\n-- (4) = Seguro de Vida\n");	}
					
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
							 System.out.println("Depósito de  R$ "+val_dep+" realizado com sucesso!");
							 escolheOpcao(usuario,OpcoesMenu.MENU,tipo);
							break;
						case 3:
							/*
							System.out.print("Digite o número da conta de destino: ");
				            int cnum = scan.nextInt();
				            System.out.print("Digite o valor da a ser transferido: R$ ");
				            double tval = scan.nextInt();
							System.out.println("Transferência Realizada R$ "+tval+" para "+destino.getUsuario().getNome());
							 usuario.getConta().transferir(destino, tval);*/
							 escolheOpcao(usuario,OpcoesMenu.MENU,tipo);
							break;
						case 4:
							if(usuario.getSeguroDeVida() == null) {								
								System.out.print("Digite o quantia a ser recebida em caso de falecimento, valor contratado = R$ ");
								int val_seg = scan.nextInt();
								usuario.contrataSeguroDeVida(val_seg);
							}
							else {	System.out.printf("Você já possui um seguro de vida, o valor assegurado é de R$%.2f",usuario.getSeguroDeVida().getValor()); }
							escolheOpcao(usuario,OpcoesMenu.MENU,tipo);				            
							break;
						case 5:													
							System.out.printf("O seu saldo é de R$%.2f ",usuario.getConta().getSaldo());
							escolheOpcao(usuario,OpcoesMenu.MENU,tipo);
		        			break;
						case 6:
							if(usuario.getConta().getTipo_conta()==tipo.POUPANCA) { 
							System.out.printf("O rendimento do seu saldo de R$%.2f em 30 dias é de R$%.2f",usuario.getConta().getSaldo(), usuario.getConta().SimulaRendimento(usuario.getConta().getSaldo(), 30));							
							}
							else { 
								System.out.println(usuario.relatorioTributacaoContaCorrente()); }
							escolheOpcao(usuario,OpcoesMenu.MENU,tipo);
							break;
						case 7:
							System.out.print("Digite o valor que deseja simular R$ ");
		        			double sim_val = scan.nextInt();
		        			System.out.print("Digite o número de Dias para calcular o rendimento: ");
		        			int sim_dia = scan.nextInt();
		        			System.out.printf("\nO Rendimento de R$%.2f em %d dias será de R$%.2f",sim_val,sim_dia, usuario.getConta().SimulaRendimento(sim_val, sim_dia));
		        			escolheOpcao(usuario,OpcoesMenu.MENU,tipo);
		        			break;
						default:
							System.out.println("Opção inválida!");	
							escolheOpcao(usuario,OpcoesMenu.MENU,tipo);
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
     
		Usuario usuarioLogado = Sistema.login(cpf, senha);
		
		if (usuarioLogado != null) {
			if (usuarioLogado instanceof Cliente) {
				Cliente cliente = (Cliente) usuarioLogado;										
					System.out.println("\nTITULAR: "+cliente.getNome().toUpperCase()+" | CONTA "+cliente.getConta().getTipo_conta()+" - NÚMERO: "+cliente.getConta().getNumConta()+" - AGÊNCIA: "+cliente.getConta().getAgencia()+" |");
					escolheOpcao(cliente, OpcoesMenu.MENU, cliente.getConta().getTipo_conta());
					
			}
			if (usuarioLogado instanceof Funcionario) {
				Usuario func = (Funcionario) usuarioLogado;					
					escolheOpcao(func, OpcoesMenu.INICIO,func.getConta().getTipo_conta());
			}
			
		}	
		
		/*	
		if (usuarioLogado != null)
			if (usuarioLogado instanceof Cliente) {
				Cliente cliente = (Cliente) usuarioLogado;
				
				if(cliente.getConta().getTipo_conta()==TipoContas.POUPANCA) {
					System.out.println("\nTITULAR: "+cliente.getNome()+" | CONTA "+cliente.getConta().getTipo_conta()+" - NÚMERO: "+cliente.getConta().getConta()+" - AGÊNCIA: "+cliente.getConta().getAgencia()+" |");
					System.out.println("\t------ Digite a Operação Desejada ------\n\n-- (1) = Operações Bancárias\n-- (2) = Informações da Conta\n-- (3) = Sair\n");
				
					int op = scan.nextInt();
					switch (op) {	
						case 1:
							escolheOpcao(OpcoesMenuCliente.OPERACOES);
							break;
						case 2:
							escolheOpcao(OpcoesMenuCliente.INFORMACOES);
						break;
						default:
							escolheOpcao(OpcoesMenuCliente.INVALIDA);
				}
			
				/*cliente.getConta().sacar(20);
				cliente.getConta().depositar(100);

				System.out.println(cliente.relatorioTributacaoContaCorrente());
		
			}else if(usuarioLogado instanceof Gerente)
			{
				Gerente gerente = (Gerente) usuarioLogado;
				
				System.out.println(gerente.relatorioContasPorAgencia(gerente.getNumeroAgencia()));
			}
			}*/
	}

}