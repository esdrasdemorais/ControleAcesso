package br.com.proguaru.servlet;

import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import dao.ContatoDAO;
import dao.EmpresaDAO;
import dao.PessoaDAO;
import dao.VisitanteDAO;

import modelo.Empresa;
import modelo.Pessoa;
import modelo.Visitante;

import br.com.proguaru.controladora.Logica;

public class AdicionaPessoaServlet implements Logica {

	@Override
	public void executa(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PrintWriter out = response.getWriter();
				
		String RG = request.getParameter("rg");		
		String nome = request.getParameter("nome");		
		Long telefone = Long.parseLong(request.getParameter("telefone"));
		//String strEmpresa = request.getParameter("empresa");	
		Long idEmpresa = Long.parseLong(request.getParameter("empresa"));
		
		Calendar dataNasc = null;
		
		try {
			String dataEmTexto = request.getParameter("data_nasc");
			Date dataEmDate = new SimpleDateFormat("dd/MM/yyyy").parse(dataEmTexto);
			dataNasc = Calendar.getInstance();
			dataNasc.setTime(dataEmDate);
		} catch (ParseException e) {
			out.println("Erro de conversão da data");
		}	
		
		// monta um objeto pessoa
		Pessoa pessoa = new Pessoa();
		
		pessoa.setRg(RG);
		pessoa.setNome(nome);
		pessoa.setDataNasc(dataNasc);
		
		// salva a pessoa e já obtem o id da pessoa
		PessoaDAO dao = new PessoaDAO();
		pessoa = dao.adiciona(pessoa);			
		
		// monta um objeto visitante
		Visitante visitante = new Visitante();		
		
		// monta um objeto empresa
		Empresa empresa = new Empresa();
		
		//pega o id da pessoa que é o mesmo do visitante
		visitante.setId(pessoa.getId());		       
        
        //pega o telefone do visitante
        visitante.setTelefone((long)telefone);           
                   
      //pega o id da empresa
        empresa.setId(idEmpresa);
        visitante.setEmpresa(empresa);         
        
       
        
		// salva o visitante
		VisitanteDAO visDAO = new VisitanteDAO();
		visDAO.adiciona(visitante);				
		
		out.println("<html>");		
		out.println("<body background ='backgr1.JPG'>");
		out.println("<img src='logo_proguaru.gif' width='300'><br><br><br><br>");
		
		out.println(" RG: " + RG + "  <br> Nome: " + nome + 
				" <br> Data de Nascimento: " + visitante.getDataNasc() +
				" <br> Telefone: " + telefone + "  <br> Empresa: " + empresa); 
		out.println("</body>");
		out.println("</html>");		
		
		// salva a visita		
		
		
		
		
		
		
		//RequestDispatcher rd = request.getRequestDispatcher("/frmInicial.jsp");
		//rd.forward(request,response);			
		
	}	
}


