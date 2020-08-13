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
	public static final String FILE_EXTENSION = "csv";

	public static String abrirCaminhoDoArquivo() {

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

	public static String criarArquivo(Cargos cargo) throws IOException {

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date date = new Date();
		File file = new File(cargo.toString().trim() + formatter.format(date).trim() + FILE_EXTENSION);

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

		String path = PATH_RELATORIOS + "OperacaoBancaria.csv";
		BufferedReader buffRead = new BufferedReader(new FileReader(path));

		String row;

		double total = 0;

		buffRead.readLine();

		while ((row = buffRead.readLine()) != null) {
			String[] data = row.split(",");
			total += Double.parseDouble(data[3]);

		}

		return total;

	}

	public static void escreveArquivoOperacaoBancaria(String titular, double quantia_operacao, double quantia_imposto,
			TipoOperacao operacao) throws IOException {

		String path = PATH_RELATORIOS + "OperacaoBancaria.csv";

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

	public static List<ListaModeloGeral> ListaDados() throws IOException {

		String linha;
		String path = PATH_RELATORIOS + "Usuario.csv";
		List<ListaModeloGeral> lista = new ArrayList<>();
		BufferedReader csvReader = new BufferedReader(new FileReader(path));
		linha = csvReader.readLine();

		while ((linha = csvReader.readLine()) != null) {
			String[] data = linha.split(",");

			lista.add(new ListaModeloGeral(data[0], data[1], data[2], data[3], data[4], Integer.parseInt(data[5]),
					Double.parseDouble(data[6]), Integer.parseInt(data[7])));

		}

		csvReader.close();

		return lista;

	}

}
