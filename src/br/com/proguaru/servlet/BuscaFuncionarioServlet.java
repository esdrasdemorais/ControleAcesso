
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.proguaru.controladora.Logica;
import br.com.proguaru.jdbc.ConnectionFactory;

import dao.ContatoDAO;
import dao.FuncionarioDAO;
import dao.PessoaDAO;

import modelo.Contato;
import modelo.Funcionario;
import modelo.Pessoa;

public class BuscaFuncionarioServlet implements Logica {

	@Override
	public void executa(HttpServletRequest request, HttpServletResponse response)
			throws Exception {				
			    
	    Funcionario funcionario = new Funcionario();
	    FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
	    
	    if ( request.getParameter("drt") != "")  {
	    	Integer drt = Integer.parseInt(request.getParameter("drt"));
	    	funcionarioDAO.getFuncionarioByDRT(drt); 
		    funcionario = funcionarioDAO.getFuncionarioByDRT(drt);
	    }
	    	    
	    if ((request.getParameter("cpf") != null) && (request.getParameter("cpf") != "")) {
	    	String cpf = request.getParameter("cpf");
	    	funcionarioDAO.getFuncionarioByCPF(cpf); 
		    funcionario = funcionarioDAO.getFuncionarioByCPF(cpf);
	    }
	    	    		
	    request.setAttribute("funcionario", funcionario); 	   
	    
		if (funcionario.getDrt() == null ) {	    	        
			if (funcionario.getCpf() == null) {
		//		RequestDispatcher rd = request.getRequestDispatcher("/cadastro_pessoa.jsp");
				RequestDispatcher rd = request.getRequestDispatcher("/admin/frmInicial.jsp");
				rd.forward(request,response);				
				
			} else {	
						
				//RequestDispatcher rd = request.getRequestDispatcher("/resultado.jsp");
				RequestDispatcher rd = request.getRequestDispatcher("/admin/frmInicial.jsp");
				rd.forward(request,response);
			}
		} else {	
			
			//RequestDispatcher rd = request.getRequestDispatcher("/resultado.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("/admin/frmInicial.jsp");
			rd.forward(request,response);
		}	
	}
}
	