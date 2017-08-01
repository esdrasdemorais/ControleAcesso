package br.com.proguaru.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Visita;

import dao.VisitaDAO;

import br.com.proguaru.controladora.Logica;

public class AlteraVisitaServlet implements Logica {

	@Override
	public void executa(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		Integer idVisita = Integer.parseInt(request.getParameter("registro"));
		
		// seta o id da visita
		Visita visita = new Visita();
		visita.setId(idVisita);
						
		VisitaDAO visdao = new VisitaDAO();
		visdao.altera(visita);
		
		RequestDispatcher rd = request.getRequestDispatcher("/admin/frmInicial.jsp");
		rd.forward(request,response);	
		

	}

}
