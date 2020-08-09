package br.com.banco2gether.util;

import br.com.banco2gether.usuarios.Autenticavel;
import br.com.banco2gether.usuarios.exception.ErrosLoginException;

public final class Sistema {
	
	public static void login(Autenticavel usuario, String cpf, String senha )
	{
		try {
			usuario.autenticar(senha, cpf);
		} catch (ErrosLoginException e) {
			System.out.println(e.getMessage());
		}
		
	}
}
