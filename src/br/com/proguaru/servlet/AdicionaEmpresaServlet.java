package br.com.proguaru.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmpresaDAO;
import dao.PessoaDAO;
import dao.VisitaDAO;

import modelo.Empresa;

import br.com.proguaru.controladora.Logica;

public class AdicionaEmpresaServlet implements Logica {

	@Override
	public void executa(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		Empresa empresa = new Empresa();
		String nomeEmpresa = request.getParameter("nome_empresa");
		empresa.setNome(nomeEmpresa);
		
		// cadastra a empresa 
		EmpresaDAO empresaDAO = new EmpresaDAO();
		empresaDAO.adiciona(empresa);		
		
		request.setAttribute("empresa", empresa);
		
		RequestDispatcher rd = request.getRequestDispatcher("/frmCadEmpresa.jsp");
		rd.forward(request,response);
	}
	
}