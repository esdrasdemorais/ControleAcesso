
package br.com.proguaru.servlet;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.proguaru.controladora.Logica;
import br.com.proguaru.jdbc.ConnectionFactory;

import dao.ContatoDAO;
import dao.VisitanteDAO;

import modelo.Contato;
import modelo.Visitante;

public class BuscaVisitanteServlet implements Logica {

	@Override
	public void executa(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		 
		Visitante visitante = new Visitante();  
		
	    VisitanteDAO visitanteDAO = new VisitanteDAO();  	    	   
	      	     
	    
	   // if ((request.getParameter("rg") != ""))  {
	    	String rg = request.getParameter("rg");
	    	visitanteDAO.getVisitanteByRg(rg);
	    	visitante = visitanteDAO.getVisitanteByRg(rg);
	    	    	    
	    	if (visitante.getRg().length() < 8) {
	    		RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
				rd.forward(request,response);
	    	}
	    	
	    request.setAttribute("visitante", visitante);
	    
	   // request.setParameter("visitante", visitante);  
	
		if (visitante.getRg() == null ) {	    	        
			RequestDispatcher rd = request.getRequestDispatcher("/admin/frmInicial.jsp");
			rd.forward(request,response);
	
	
		} else {			
		
			RequestDispatcher rd = request.getRequestDispatcher("/admin/frmInicial.jsp");
			rd.forward(request,response);
		}	
	}

}

