package br.com.proguaru.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UsuarioDAO;

import modelo.Usuario;

public class FiltroValidacao implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest req, ServletResponse res,

            FilterChain chain) throws IOException, ServletException {

		HttpSession session = ((HttpServletRequest)req).getSession();
		
      	Usuario usuario = (Usuario)session.getAttribute("usuario");
				
		if (usuario == null) {	
			
			session.setAttribute("msg","Você não está logado no sistema!");                 	
	         
	    	RequestDispatcher rd = req.getRequestDispatcher("/frmLogin.jsp");
		    rd.forward(req,res);
			            			
      }else{
    	  
    	  chain.doFilter(req, res);
            

      }

}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}	

}
