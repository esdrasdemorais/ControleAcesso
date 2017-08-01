package br.com.proguaru.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Usuario;

import br.com.proguaru.controladora.Logica;

public class VerificaUsuarioServlet implements Logica {
	
	@Override
	public void executa(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		Usuario usuario = new Usuario();
	}

}
