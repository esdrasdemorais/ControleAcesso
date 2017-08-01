package dao;

import java.sql.Connection;

import br.com.proguaru.jdbc.ConnectionFactory;

public class SituacaoDAO {

private Connection connection;
	
	public SituacaoDAO() throws Exception {
		this.connection = new ConnectionFactory().getConnection();
	}
	
	
	
	
	
	
}
