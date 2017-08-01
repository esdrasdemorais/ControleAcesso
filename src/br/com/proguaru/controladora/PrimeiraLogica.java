package br.com.proguaru.controladora;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import modelo.Pessoa;

import dao.ContatoDAO;

public class PrimeiraLogica implements Logica {

	@Override
	public void executa(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		//System.out.println("Executando a lógica e redirecionando...");
		ContatoDAO cdao = new ContatoDAO();
		//java.util.List<Pessoa> lstPessoas = cdao.getLista();
		RequestDispatcher rd = req.getRequestDispatcher("/primeira-logica.jsp");
		rd.forward(req, res);
	}

}
