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

import br.com.banco2gether.usuarios.Cargos;
import br.com.banco2gether.usuarios.exception.IOFilesException;

public final class IOFiles {

	public static final String PATH_RELATORIOS = "../Banco2Gether/src/br/com/banco2gether/arquivos/";

	public static final String FILE_EXTENSION = System.getProperty("os.name").indexOf("win") >= 0 ? ".txt" : ".text";

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
		File file = new File(PATH_RELATORIOS + cargo.toString().trim() + formatter.format(date).trim() + FILE_EXTENSION);

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

	public static void writeFile(String path) throws IOException {
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
		String linha = "";
		Scanner in = new Scanner(System.in);
		System.out.println("Escreva algo: ");
		linha = in.nextLine();
		buffWrite.append(linha + "\n");
		buffWrite.close();
	}

}
