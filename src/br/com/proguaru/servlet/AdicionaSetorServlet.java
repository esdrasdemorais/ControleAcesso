package br.com.proguaru.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmpresaDAO;
import dao.PessoaDAO;
import dao.SetorDAO;
import dao.VisitaDAO;

import modelo.Empresa;
import modelo.Setor;

import br.com.proguaru.controladora.Logica;

public class AdicionaSetorServlet implements Logica {

	@Override
	public void executa(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		Setor setor = new Setor();
		String nomeSetor = request.getParameter("nome_setor");
		setor.setNome(nomeSetor);
		
		// cadastra a empresa 
		SetorDAO setordao = new SetorDAO();
		setordao.adiciona(setor);			
		
		request.setAttribute("setor",setor);
		
		RequestDispatcher rd = request.getRequestDispatcher("/admin/frmInicial.jsp");
		rd.forward(request,response);
		
	}

}
