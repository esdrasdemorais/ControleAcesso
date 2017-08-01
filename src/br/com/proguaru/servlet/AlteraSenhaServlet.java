package br.com.proguaru.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Funcionario;
import modelo.Usuario;
import modelo.Visita;

import dao.FuncionarioDAO;
import dao.SetorDAO;
import dao.UsuarioDAO;
import dao.VisitaDAO;

import br.com.proguaru.controladora.Logica;

public class AlteraSenhaServlet implements Logica {

	@Override
	public void executa(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		String senha = request.getParameter("senha");
		Integer drt = Integer.parseInt(request.getParameter("drt"));
		
		// seta o id da visita
		Usuario usuario = new Usuario();
		usuario.setSenha(senha);
		
		Funcionario funcionario = new Funcionario();
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		funcionarioDAO.getDRT(drt);
		funcionario = funcionarioDAO.getDRT(drt);
		usuario.setFuncionario(funcionario);						
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.alteraSenha(usuario);
		
		request.setAttribute("usuario", usuario);
		
		RequestDispatcher rd = request.getRequestDispatcher("/frmAlteraSenha.jsp");
		rd.forward(request,response);	
			

	}

}
