package br.com.proguaru.servlet;

import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.Usuario;
import br.com.proguaru.controladora.Logica;

import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result; 

import dao.UsuarioDAO;

@WebServlet("/logout")
public class LogoutPessoaServlet implements Logica {

	private Usuario usuario;
	
	@Override
	public void executa(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		HttpSession session = request.getSession(false);
		session.invalidate();		
		
		System.out.print("TESTE LOGOUT!");
	//	session.setAttribute ("LoginStatus", "false"); 
		RequestDispatcher rd = request.getRequestDispatcher("/frmLogin.jsp");
		rd.forward(request,response);
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}		
	
}
