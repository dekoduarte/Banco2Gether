package br.com.banco2gether.util;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import br.com.banco2gether.contas.Conta;
import br.com.banco2gether.operacoes.TipoOperacao;
import br.com.banco2gether.usuarios.Cargos;
import br.com.banco2gether.usuarios.exception.IOFilesException;

public final class IOFiles {

	public static final String PATH_RELATORIOS = "../Banco2Gether/src/br/com/banco2gether/arquivos/";
	public static final String CABECALHO_OPERACOES_BANCARIAS = "OPERACAO,TITULAR,VALOR,IMPOSTO,DATA";
	public static final String FILE_EXTENSION = "csv";

	public static String openFileSystem() {

		try {

			JFileChooser file = new JFileChooser();

			file.setFileFilter(new FileNameExtensionFilter("*" + FILE_EXTENSION, FILE_EXTENSION));
			file.setMultiSelectionEnabled(false);
			file.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			file.setFileHidingEnabled(false);

			if (file.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				java.io.File f = file.getSelectedFile();
				return f.getAbsolutePath();
			}
		} catch (NullPointerException e) {

		} catch (RuntimeException e) {

		}

		return "";
	}

	public static String createTextFile(Cargos cargo) throws IOException {
	
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date date = new Date();
		File file = new File(cargo.toString().trim() + formatter.format(date).trim() + FILE_EXTENSION);
	
		if (file.createNewFile()) {
			return file.getName();
		} else
			throw new IOFilesException("Erro ao criar o arquivo de texto.");
	}

	public static void readFile(String path) throws IOException {

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

	public static void escreveOperacaoBancaria(Conta conta, TipoOperacao operacao) throws IOException {
		
		String path = PATH_RELATORIOS + "OperacaoBancaria.csv";
		
		BufferedReader buffRead = new BufferedReader(new FileReader(path));
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
		
		StringBuilder linha = new StringBuilder();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date date = new Date();
		
		
		if(buffRead.readLine() == null)
		{
			buffWrite.write(CABECALHO_OPERACOES_BANCARIAS);
			buffWrite.newLine();
		}
		
		buffWrite.close();
		
		linha.append(operacao.toString().trim());
		linha.append(",Conta.titular");
		linha.append(",Conta.valor");
		linha.append(",Conta.imposto");
		linha.append("," + formatter.format(date));
		
		buffWrite.append(linha);
		buffWrite.newLine();
		buffWrite.close();
	}

}
