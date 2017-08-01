
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
import dao.PessoaDAO;

import modelo.Contato;
import modelo.Pessoa;

public class BuscaPessoaServlet implements Logica {

	@Override
	public void executa(HttpServletRequest request, HttpServletResponse response)
			throws Exception {				
		 
		Pessoa pessoa = new Pessoa();  
	    PessoaDAO pessoaDAO = new PessoaDAO();  
	      
	    if ((request.getParameter("drt") != ""))  {
	    	Integer drt = Integer.parseInt(request.getParameter("drt"));
	    	pessoaDAO.getPessoaByDRT(drt); 
		    pessoa = pessoaDAO.getPessoaByDRT(drt);
	    }
	    
	    if ((request.getParameter("cpf") != null) && (request.getParameter("cpf") != "")) {
	    	String cpf = request.getParameter("cpf");
	    	pessoaDAO.getPessoaByCPF(cpf); 
		    pessoa = pessoaDAO.getPessoaByCPF(cpf);
	    }
	
	    
		if (pessoa.getDrt() == null ) {	    	        
			if (pessoa.getCpf() == null) {
				RequestDispatcher rd = request.getRequestDispatcher("/cadastro_pessoa.jsp");
				rd.forward(request,response);				
				
			} else {	
						
				RequestDispatcher rd = request.getRequestDispatcher("/resultado.jsp");
				rd.forward(request,response);
			}
		} else {	
			
			RequestDispatcher rd = request.getRequestDispatcher("/resultado.jsp");
			rd.forward(request,response);
		}	
	}		
}



/*
			out.println("<html>");		
			out.println("<body background ='backgr1.JPG'>");
			out.println("<img src='logo_proguaru.gif' width='300'><br><br><br><br>");
			//out.println(" RG: " + pesPesq.getId() + " <br> Nome: " + pesPesq.getNome() + " <br> Pessoa localizada na base de dados");
			out.println(" RG: " + rge + "  <br> Nome: " + pesPesq.getNome() + " <br> Pessoa localizada na base de dados");
			out.println("<p>");
			out.println("Data de entrada: <input type =text name =dtEntrada><p>");
			out.println("Número do crachá: <input type =text name =nrCracha><p>");
			out.println("Local: <input type =text name =local><p>");
			out.println("Setor: <input type =text name =setor><p>");
			out.println("<jsp:useBean id=empDAO class=dao.EmpresaDAO />" +
				" Empresa: " +
				" <select name=empresa> " +  
		        "	<option value=0>Selecione a empresa</option> " +  
		      		"	<c:forEach var=empresa items=${empDAO.lista}> " +  
		          	"	<option value=${empresa.id}>${empresa.nome}</option>" +  
		     " </c:forEach> " +  
		   " </select> ");			
			
			out.println("<p>");
			out.println("<p>");
			out.println("</body>");
			out.println("</html>");
			
		}	*/
			
	
	   // RequestDispatcher rd = request.getRequestDispatcher("/resultado.jsp");
	  //  rd.forward(request, response);
		//System.out.println("Alterando contato ..." + pesPesq.getNome());
	
	



