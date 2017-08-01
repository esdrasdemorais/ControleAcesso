package br.com.proguaru.relatorios;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.proguaru.jdbc.ConnectionFactory;

import net.sf.jasperreports.engine.JRException;

import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import br.com.proguaru.jdbc.ConnectionFactory;


public class ReportServlet {

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

	    OutputStream out = null;

	    // obt�m o relat�rio compilado
	    InputStream inputStream = getClass().getResourceAsStream( "C:/Program Files/Java/jdk1.6.0_25/lib/relFuncSem.jasper" );

	    // preenche o mapa de par�metros
	    Map<String, Object> parametros = new HashMap<String, Object>();
	    parametros.put( "primeiroNome", "D%" );

	    try {

	        // gera o relat�rio e atribui o OutputStream gerado
	        out = ReportUtils.createPDFReport( inputStream, parametros,
	                ConnectionFactory.getConnection(), response );

	        
	    } catch ( SQLException exc ) {
	        exc.printStackTrace();
	    } catch ( JRException exc ) {
	        exc.printStackTrace();
	    } finally {

	        // se n�o aconteceu nenhum problema, fecha o output stream
	        if ( out != null ) {
	            out.close();
	        }

	    }

	}
}
