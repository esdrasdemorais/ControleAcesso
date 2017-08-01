package br.com.proguaru.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmpresaDAO;
import dao.FuncionarioDAO;
import dao.PessoaDAO;
import dao.SetorDAO;
import dao.UsuarioDAO;
import dao.VisitaDAO;

import modelo.Empresa;
import modelo.Funcionario;
import modelo.Usuario;

import br.com.proguaru.controladora.Logica;

public class AdicionaUsuarioServlet implements Logica {

	@Override
	public void executa(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		Funcionario funcionario = new Funcionario();
		
		Usuario usuario = new Usuario();
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Integer drt = Integer.parseInt(request.getParameter("drt"));
		String nomeUsuario = request.getParameter("nome");
		String senhaUsuario = request.getParameter("senha");
		
		
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		funcionarioDAO.getFuncionarioByDRT(drt);
		funcionario = funcionarioDAO.getFuncionarioByDRT(drt);
		usuario.setFuncionario(funcionario);
		
		usuario.setNomeUsuario(nomeUsuario);
		usuario.setSenha(senhaUsuario);
		
		usuarioDAO.adiciona(usuario);		

		request.setAttribute("usuario", usuario);
		
		RequestDispatcher rd = request.getRequestDispatcher("/admin/frmInicial.jsp");
		rd.forward(request,response);
	}

}
