package br.com.proguaru.servlet;

import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Cracha;
import modelo.Empresa;
import modelo.Funcionario;
import modelo.Local;
import modelo.Motivo;
import modelo.Pessoa;
import modelo.Setor;
import modelo.Usuario;
import modelo.Visita;
import modelo.Visitante;

import dao.CrachaDAO;
import dao.EmpresaDAO;
import dao.FuncionarioDAO;
import dao.LocalDAO;
import dao.MotivoDAO;
import dao.PessoaDAO;
import dao.SetorDAO;
import dao.VisitaDAO;
import dao.VisitanteDAO;

import br.com.proguaru.controladora.Logica;

public class AdicionaVisitaServlet implements Logica {

	@Override
	public void executa(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PrintWriter out = response.getWriter();	
		
		Long idPessoa = Long.parseLong(request.getParameter("id"));
		String tipoCracha = request.getParameter("tpCracha");
		Long idSetor = Long.parseLong(request.getParameter("setor"));			
		Long idCracha = Long.parseLong(request.getParameter("cracha"));
		
		// monta um objeto Funcionário
		Funcionario funcionario = new Funcionario();		
		
		// monta um objeto Visitante
		Visitante visitante = new Visitante();
		
		// monta um objeto Cracha
		Cracha cracha = new Cracha();
		
		// monta um objeto Visita
		Visita visita = new Visita();
		Visita aux_visita = new Visita();
		
		// monta um objeto Setor
		Setor setor = new Setor();
		
		// monta um objeto Local
		//Local local = new Local();		
				
		// monta um objeto Empresa
		Empresa empresa = new Empresa();
			
		// monta um objeto Motivo
		Motivo motivo = new Motivo();
				
		/* Mandando a variável para o método
	       getSetorById e adicionando o setor à Visita*/        
		SetorDAO setDAO = new SetorDAO();
		setDAO.getSetorById(idSetor);
		setor = setDAO.getSetorById(idSetor);
		visita.setSetor(setor);																		
				
		/* Verificando se a pessoa é 
		   Visitante ou Funcionário */
		
		String servico = "Serviço";
		String visitant = "Visitante";
		
		if (tipoCracha.equals(servico))  {		  
			
			/* Mandando a variável para o método
		       getFuncionarioById e adicionando o Funcionario à Visita*/        
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarioDAO.getFuncionarioById(idPessoa);
			funcionario = funcionarioDAO.getFuncionarioById(idPessoa);
			
			Long idMotivo = Long.parseLong(request.getParameter("motivo"));
			
			/* Mandando a variável para o método
		       getMotivoById e adicionando o motivo à Visita*/        
			MotivoDAO motDAO = new MotivoDAO();
			motDAO.getMotivoById(idMotivo);
			motivo = motDAO.getMotivoById(idMotivo);
			
			visita.setMotivo(motivo);
			visita.setFuncionario(funcionario);
			aux_visita.setFuncionario(funcionario);	
	    }
		else 				
			if (tipoCracha.equals(visitant))  {				
					
				/* Mandando a variável para o método
				getVisitanteById e adicionando o Visitante à Visita*/        
				VisitanteDAO visitanteDAO = new VisitanteDAO();
				visitanteDAO.getVisitanteById(idPessoa);
				visitante = visitanteDAO.getVisitanteById(idPessoa);
				visita.setVisitante(visitante);
		}	
				 
					
		/* Mandando a variável para o método
	       getCrachaById e adicionando o número do Crachá à Visita*/        
	//	CrachaDAO crachaDAO = new CrachaDAO();
	//	crachaDAO.getCrachaById(idCracha);
	//	cracha = crachaDAO.getCrachaById(idCracha);		
	//	visita.setCracha(cracha);
		
		// Vendo se tal crachá já está sendo utilizado
		VisitaDAO visitaDAO = new VisitaDAO();
		visitaDAO.getCrachaVisitas(idCracha);
		aux_visita = visitaDAO.getCrachaVisitas(idCracha);
		//aux_visita.setCracha(cracha);
		
		// se estiver sendo utilizado
		if (aux_visita != null) 
		{							
			String message = "Atenção! Este número de crachá já está sendo utilizado. " +
						     " Por favor, selecione outro crachá.";
			request.setAttribute("message", message);						
			RequestDispatcher rd = request.getRequestDispatcher("/admin/frmInicial.jsp");
		    rd.forward(request,response);			
		}	
		else		 
		{							
			CrachaDAO crachaDAO = new CrachaDAO();
			crachaDAO.getCrachaById(idCracha);
			cracha = crachaDAO.getCrachaById(idCracha);		
			visita.setCracha(cracha);
			// salva a visita
			visitaDAO.adiciona(visita);		    			
			request.setAttribute("visita", visita);	
			RequestDispatcher rd = request.getRequestDispatcher("/admin/frmInicial.jsp");
			rd.forward(request,response);	
		}							
	}				
}	
