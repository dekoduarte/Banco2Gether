package br.com.banco2gether.util;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import br.com.banco2gether.contas.Conta;
import br.com.banco2gether.operacoes.TipoOperacao;
import br.com.banco2gether.usuarios.Cargos;
import br.com.banco2gether.usuarios.Usuario;
import br.com.banco2gether.usuarios.exception.IOFilesException;

public final class IOFiles {

	public static final String PATH_RELATORIOS = "../Banco2Gether/src/br/com/banco2gether/arquivos/";
	public static final String CABECALHO_OPERACOES_BANCARIAS = "OPERACAO,TITULAR,VALOR,IMPOSTO,DATA";
	public static final String FILE_EXTENSION_CSV = ".csv";
	public static final String FILE_EXTENSION_TXT = System.getProperty("os.name").indexOf("win") >= 0 ? ".txt" : ".text";

	public static String abrirCaminhoDoArquivo() {

		try {

			JFileChooser file = new JFileChooser();

			file.setFileFilter(new FileNameExtensionFilter("*" + FILE_EXTENSION_CSV, FILE_EXTENSION_CSV));
			file.setMultiSelectionEnabled(false);
			file.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			file.setFileHidingEnabled(false);

			if (file.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				java.io.File f = file.getSelectedFile();
				return f.getAbsolutePath();
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}

		return "";
	}

	public static String criarArquivo(Cargos cargo) throws IOException {

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date date = new Date();
		File file = new File(cargo.toString().trim() + formatter.format(date).trim() + FILE_EXTENSION_TXT);

		if (file.createNewFile()) {
			return file.getName();
		} else
			throw new IOFilesException("Erro ao criar o arquivo.");
	}

	public static void leituraArquivo(String path) throws IOException {

		BufferedReader buffRead = new BufferedReader(new FileReader(path));
		String linha = "";

		while (true) {
			if (linha != null) {
				System.out.println(linha);

			} else
				break;
			linha = buffRead.readLine();
		}
		buffRead.close();
	}

	public static double leituraDeCapitalDoBanco() throws IOException {

		String path = PATH_RELATORIOS + "OperacaoBancaria" + FILE_EXTENSION_CSV;
		
		File f = new File(path);
		if (!f.exists()) {
			f.createNewFile();
		}  
		
		BufferedReader buffRead = new BufferedReader(new FileReader(path));

		String row;

		double total = 0;

		buffRead.readLine();

		while ((row = buffRead.readLine()) != null) {
			String[] data = row.split(",");
			total += Double.parseDouble(data[3]);

		}
		
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(PATH_RELATORIOS + criarArquivo(Cargos.Presidente)));

		buffWrite.append("Atualmente o capital do banco2Gether é: " + total);
		buffWrite.close();

		return total;

	}

	public static void escreveRelatorioContasPorAgencia(int total, Cargos cargo) throws IOException {
		String path = PATH_RELATORIOS + criarArquivo(cargo);
		
		File f = new File(path);
		if (!f.exists()) {
			f.createNewFile();
		}  
		
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
		StringBuilder linha = new StringBuilder();

		buffWrite.append("Existem atualmente " + total + " clientes na sua agencia.");
		buffWrite.close();
	}
	
	public static void escreveRelatorioClientesDoBanco(Cargos cargo) throws IOException {
		
		
		String path = PATH_RELATORIOS + criarArquivo(cargo);
		
		File f = new File(path);
		if (!f.exists()) {
			f.createNewFile();
		}  
		
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
		StringBuilder linha = new StringBuilder();

		
		
		for(Usuario c : Sistema.tabelaUsuario)
		{
			buffWrite.append("Nome: " + c.getNome() + ", CPF: " + c.getCpf() + ", Agencia: " + c.getConta().getAgencia() );
		}

		buffWrite.close();
	}

	public static void escreveArquivoOperacaoBancaria(String titular, double quantia_operacao, double quantia_imposto,
			TipoOperacao operacao) throws IOException {

		String path = PATH_RELATORIOS + "OperacaoBancaria" + FILE_EXTENSION_CSV;

		File f = new File(path);
		if (!f.exists()) {
			f.createNewFile();
		}

		BufferedReader buffRead = new BufferedReader(new FileReader(path));
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path, true));

		StringBuilder linha = new StringBuilder();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date date = new Date();

		if (buffRead.readLine() == null) {
			buffWrite.write(CABECALHO_OPERACOES_BANCARIAS);
			buffWrite.newLine();
		}

		buffRead.close();

		linha.append(operacao.toString().trim());
		linha.append("," + titular);
		linha.append("," + quantia_operacao);
		linha.append("," + quantia_imposto);
		linha.append("," + formatter.format(date));

		buffWrite.append(linha);
		buffWrite.newLine();
		buffWrite.flush();
		buffWrite.close();
	}

	

}
