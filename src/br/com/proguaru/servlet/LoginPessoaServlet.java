package br.com.proguaru.servlet;

import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.Usuario;
import br.com.proguaru.controladora.Logica;



import dao.UsuarioDAO;

@WebServlet("/logar")
public class LoginPessoaServlet implements Logica {

	private Usuario usuario;
	
	@Override
	public void executa(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = new Usuario();
		
		String nomeUser = request.getParameter("nome");
		String senhaUser = request.getParameter("senha");
		
		usuario.setNomeUsuario(nomeUser);
		usuario.setSenha(senhaUser);
		
		usuario = usuarioDAO.existeUsuario(usuario);
		
		if (usuario != null) {
			
			HttpSession session = request.getSession(true);
			session.setAttribute("usuario", usuario);
			ServletContext context = getServletContext();
			
			RequestDispatcher rd = request.getRequestDispatcher("/admin/frmInicial.jsp");
			rd.forward(request,response);	
		} 
		else 	
		{								
				HttpSession session = request.getSession(false);
				session.setAttribute("msg", "Login ou senha incorretos!");	
				ServletContext context = getServletContext();
				RequestDispatcher rd = request.getRequestDispatcher("/frmLogin.jsp");
				rd.forward(request,response);	
			
		}
	}
	
	private ServletContext getServletContext() {
		// TODO Auto-generated method stub
		return null;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}		
	
}
