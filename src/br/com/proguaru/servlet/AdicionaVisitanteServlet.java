package br.com.proguaru.servlet;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import captura.CaptureWebCamApplet;

import modelo.Empresa;
import modelo.Pessoa;
import modelo.Visitante;
import modelo.pessoaFoto;
import dao.EmpresaDAO;
import dao.PessoaDAO;
import dao.SetorDAO;
import dao.VisitanteDAO;

import br.com.proguaru.controladora.Logica;

public class AdicionaVisitanteServlet implements Logica {
	
	public void executa(HttpServletRequest request, HttpServletResponse response) 
				throws Exception {

				PrintWriter out = response.getWriter();
				
				String RG = request.getParameter("rg");		
				String nome = request.getParameter("nome");		
				Long telefone = Long.parseLong(request.getParameter("telefone"));
				//String strEmpresa = request.getParameter("empresa");	
				Long idEmpresa = Long.parseLong(request.getParameter("empresa"));
		//		String pathImagem = "C:/Users/roberttorres/workspace/ControleAcesso/WebContent/Fotos_JPG/" + RG + ".jpg";
				String pathImagem = "//servdados/C$/Arquivos de programas/apache-tomcat-7.0.14/webapps/ControleAcesso/ControleAcessoImagem/" + "/" + RG + ".jpg";
				
				String tipoPessoaFoto = "Visitante";
				
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

				// monta um objeto pessoaFoto
				pessoaFoto pessoafoto = new pessoaFoto();
				
				// monta um objeto empresa
				Empresa empresa = new Empresa();
				EmpresaDAO empresaDAO = new EmpresaDAO();
				
				//pega o id da pessoa que é o mesmo do visitante
				visitante.setId(pessoa.getId());		       
		        
		        //pega o telefone do visitante
		        visitante.setTelefone((long)telefone);
		        
		        //pega o nome da pessoa que é o mesmo do visitante
				visitante.setNome(pessoa.getNome());
				
				//pega o RG da pessoa que é o mesmo do visitante
				visitante.setRg(pessoa.getRg());
				
				//pega o  caminho da imagem
				//pessoafoto.setPathImagem(pathImagem);
				
				//pega o tipo da imagem
			//	pessoafoto.setTipo(tipoPessoaFoto);
				
				//pega a instância pessoafoto;
				//visitante.setPessoafoto(pessoafoto);
		               
				try {
					
					//Copiando a foto para a pasta do Eclipse
			//		 String origem = "C:/Users/roberttorres/workspace/ControleAcesso/WebContent/FotoTemp/nomeFoto.jpg";
			//		 String destino = "C:/Users/roberttorres/workspace/ControleAcesso/WebContent/FOTOS_JPG/" + visitante.getRg() + ".jpg";
					
					//String origem = "//172.16.80.250/C$/Users/roberttorres/workspace/ControleAcesso/WebContent/FotoTemp/nomeFoto.jpg";
//					String origem = "C:/Arquivos de programas/apache-tomcat-7.0.14/webapps/ControleAcesso/FotoTemp/nomeFoto.jpg";
	//				String destino = "C:/Arquivos de programas/apache-tomcat-7.0.14/webapps/ControleAcesso/ControleAcessoImagem/" + "/" + visitante.getRg() + ".jpg";
					
//					String nomeFoto = "172.16.80.181/C$/Program Files/apache-tomcat-7.0.14/webapps/ControleAcesso/FotoTemp/nomeFoto.jpg";
					String origem = "//172.16.80.181/C$/Program Files/apache-tomcat-7.0.14/webapps/ControleAcesso/FotoTemp/nomeFoto.jpg";
					String destino = "//172.16.80.181/C$/Program Files/apache-tomcat-7.0.14/webapps/ControleAcesso/ControleAcessoImagem/" + "/" + visitante.getRg() + ".jpg";
					
					//String origem = "//servdados/C$/Arquivos de programas/apache-tomcat-7.0.14/webapps/ControleAcesso/FotoTemp/nomeFoto.jpg";
				//	String destino = "//servdados/C$/Arquivos de programas/apache-tomcat-7.0.14/webapps/ControleAcesso/ControleAcessoImagem/" + "/" + visitante.getRg() + ".jpg";
					
					  FileInputStream fisOrigem = new FileInputStream(origem);  
					  FileOutputStream fisDestino = new FileOutputStream(destino);  
					  FileChannel fcOrigem = fisOrigem.getChannel();    
					  FileChannel fcDestino = fisDestino.getChannel();    
					  fcOrigem.transferTo(0, fcOrigem.size(), fcDestino);    
					  fisOrigem.close();    
					  fisDestino.close(); 
					  
					//Copiando a foto para a pasta do TomCat
					  //String origem2 = "//servdados/C$/Arquivos de programas/apache-tomcat-7.0.14/webapps/ControleAcesso/FotoTemp/nomeFoto.jpg";
					 // String destino2 = "//servdados/C$/Arquivos de programas/apache-tomcat-7.0.14/webapps/ControleAcesso/ControleAcessoImagem/" + "/" + visitante.getRg() + ".jpg";					  
					 
					//  String origem2 = "C:/Arquivos de programas/apache-tomcat-7.0.14/webapps/ControleAcesso/FotoTemp/nomeFoto.jpg";
 					//  String destino2 = "C:/Arquivos de programas/apache-tomcat-7.0.14/webapps/ControleAcesso/ControleAcessoImagem/" + "/" + visitante.getRg() + ".jpg";

					  String origem2 = "//172.16.80.181/C$/Program Files/apache-tomcat-7.0.14/webapps/ControleAcesso/FotoTemp/nomeFoto.jpg";
	 				  String destino2 = "//172.16.80.181/C$/Program Files/apache-tomcat-7.0.14/webapps/ControleAcesso/ControleAcessoImagem/" + "/" + visitante.getRg() + ".jpg";
					  
					  FileInputStream fisOrigem2 = new FileInputStream(origem2);  
					  FileOutputStream fisDestino2 = new FileOutputStream(destino2);  
					  FileChannel fcOrigem2 = fisOrigem2.getChannel();    
					  FileChannel fcDestino2 = fisDestino2.getChannel();    
					  fcOrigem2.transferTo(0, fcOrigem2.size(), fcDestino2);    
					  fisOrigem2.close();    
					  fisDestino2.close(); 
					  
					  //Apagando a imagem da pasta FotoTemp
					 // File arq = new File("//servdados/C$/Arquivos de programas/apache-tomcat-7.0.14/webapps/ControleAcesso/FotoTemp/nomeFoto.jpg");
					//  File arq = new File("C:/Arquivos de programas/apache-tomcat-7.0.14/webapps/ControleAcesso/FotoTemp/nomeFoto.jpg");
					  File arq = new File("//172.16.80.181/C$/Program Files/apache-tomcat-7.0.14/webapps/ControleAcesso/FotoTemp/nomeFoto.jpg");
					  arq.delete(); 	

				} catch (IOException ioex) {
					out.println("Erro de Gravação de Imagem");
				}
				
				
				
		      if (idEmpresa != 0) {
		        //pega o id da empresa		        
		    	empresaDAO.getNomeEmpresaById(idEmpresa);
		    	empresa = empresaDAO.getNomeEmpresaById(idEmpresa);
		        visitante.setEmpresa(empresa);      
		      } 
		      else {
		    	  empresa.setId(idEmpresa);
		    	  visitante.setEmpresa(empresa);
		      }
		        
				// salva o visitante
				VisitanteDAO visDAO = new VisitanteDAO();
				visDAO.adiciona(visitante);	
						
				request.setAttribute("visitante", visitante);
				
				RequestDispatcher rd = request.getRequestDispatcher("/admin/frmInicial.jsp");
				rd.forward(request,response);					
				
	}
}