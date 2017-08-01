package br.com.proguaru.jdbc;
	 
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.PreparedStatement;  
import java.sql.ResultSet;  
import java.sql.SQLException;  
	   
public class ConnectionFactory {   
    //private static final String url = "jdbc:jtds:sqlserver://SERVERSQL/ControleAcesso;instance=PROGUARUSA";  
	private static final String url = "jdbc:jtds:sqlserver://SERVERDB/ControleAcesso;instance=DESENVOLVIMENTO";
	private static final String driver = "net.sourceforge.jtds.jdbc.Driver";  
    private static final String usuario = "ProguaruDB";  
    private static final String senha = "3p1d3rm3";  
      
	       
    public static Connection getConnection() throws SQLException { 
    	System.out.println("Conectando ao banco");
        try {  
            Class.forName(driver);  
            return DriverManager.getConnection(url, usuario, senha);  
        } catch (ClassNotFoundException e) {  
            throw new SQLException(e.getMessage());  
        }  
    }	 
	 
}
