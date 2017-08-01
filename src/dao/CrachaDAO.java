package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Cracha;
import modelo.Local;
import modelo.Setor;
import modelo.TipoCracha;
import br.com.proguaru.jdbc.ConnectionFactory;

public class CrachaDAO {
	// a conexão com o banco de dados
	private Connection connection;
	
	public CrachaDAO() throws Exception {
		this.connection = new ConnectionFactory().getConnection();
	}

	public List<Cracha> getListaFunc() {
		  try {
			  List<Cracha> crachas = new ArrayList<Cracha>();
			  PreparedStatement stmt = this.connection.prepareStatement("SELECT" +
																  		" cr_id, cr_numero, cr_tipo" +
																  		" FROM [ControleAcesso].[dbo].[tb_cracha]" +
																  		" WHERE cr_id BETWEEN 1 AND 50"); 
	
			  
			  ResultSet rs = stmt.executeQuery();		  
		  	  
			  while (rs.next()) {
						
				// criando o objeto Cracha  
				  Cracha cracha = new Cracha();
				  cracha.setId(rs.getLong("cr_id"));
				  cracha.setNumeroCracha(rs.getLong("cr_numero"));
				  cracha.setTipoCracha(rs.getLong("cr_tipo"));				  				  
		  
				  // adicionando o objeto à lista
				  crachas.add(cracha);
			  } 

			  rs.close(); 	  
			  stmt.close();
			  return crachas;
		
		  } catch (SQLException e) {
			  throw new RuntimeException(e);
		  }	
	}	
	
	public List<Cracha> getListaVisit() {
		  try {
			  List<Cracha> crachas = new ArrayList<Cracha>();
			  PreparedStatement stmt = this.connection.prepareStatement("SELECT" +
																  		" cr_id, cr_numero, cr_tipo" +
																  		" FROM [ControleAcesso].[dbo].[tb_cracha]" +
																  		" WHERE cr_id BETWEEN 51 AND 100"); 
	
			  
			  ResultSet rs = stmt.executeQuery();		  
		  	  
			  while (rs.next()) {
						
				// criando o objeto Cracha  
				  Cracha cracha = new Cracha();
				  cracha.setId(rs.getLong("cr_id"));
				  cracha.setNumeroCracha(rs.getLong("cr_numero"));
				  cracha.setTipoCracha(rs.getLong("cr_tipo"));				  				  
		  
				  // adicionando o objeto à lista
				  crachas.add(cracha);
			  } 

			  rs.close(); 	  
			  stmt.close();
			  return crachas;
		
		  } catch (SQLException e) {
			  throw new RuntimeException(e);
		  }	
	}	
	
	public Cracha getCrachaById(Long id) throws Exception
	{
		
		Cracha cracha = new Cracha();
		
		String strSql = "SELECT cr_id, cr_numero, cr_tipo" +
		" FROM [ControleAcesso].[dbo].[tb_cracha]" +								
		" WHERE cr_id = ?";
		
		try {
			PreparedStatement stmt = this.connection.prepareStatement(strSql);
			
			/* stmt = select acima, porém ao invés da
			   interrogação, pegando o valor passado
			   pela AdicionaVisitaServlet */
			stmt.setLong(1, id);
			
			/* Faz a atribuição do select para a 
			   			variável rs */
			ResultSet rs = stmt.executeQuery();		
						
			/* executa o select p/ ver se há no banco de 
			   dados, tal registro*/	
			
			if (rs.next()) {			
				cracha.setId(rs.getLong("cr_id"));
			//	cracha.setNumeroCracha(rs.getLong("cr_numero"));
			//	cracha.setTipoCracha(rs.getLong("cr_tipo"));
			}
			else {
				throw new SQLException("Não foi possível obter o código da pessoa.");
			}
		
			rs.close();
			stmt.close();
		
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		/* Retorna o número do crachá específico 
	     para a servlet AdicionaVisitaServlet */
		return cracha;
	}
	
	public Cracha getCracha(Cracha cracha) throws Exception
	{
		
		String strSql = "SELECT cr_id, cr_numero, cr_tipo" +
		" FROM [ControleAcesso].[dbo].[tb_cracha]" +								
		" WHERE cr_id = ?";
		
		try {
			PreparedStatement stmt = this.connection.prepareStatement(strSql);
			
			/* stmt = select acima, porém ao invés da
			   interrogação, pegando o valor passado
			   pela AdicionaVisitaServlet */
			stmt.setLong(1, cracha.getId());
			
			/* Faz a atribuição do select para a 
			   			variável rs */
			ResultSet rs = stmt.executeQuery();		
						
			/* executa o select p/ ver se há no banco de 
			   dados, tal registro*/	
			
			if (rs.next()) {			
				cracha.setId(rs.getLong("cr_id"));
				cracha.setNumeroCracha(rs.getLong("cr_numero"));
				cracha.setTipoCracha(rs.getLong("cr_tipo"));
			}
			else {
				throw new SQLException("Não foi possível obter o número do crachá da pessoa.");
			}
		
			rs.close();
			stmt.close();
		
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		/* Retorna o número do crachá específico 
	     para a servlet AdicionaVisitaServlet */
		return cracha;
	}
	
	
	public Cracha getCrachaByTipo(Cracha tipocracha) throws Exception
	{
		String strSql = " SELECT tipoCracha.tp_id_cracha, SELECT tipoCracha.tp_nome_cracha " +
				" FROM [ControleAcesso].[dbo].[tb_tipo_cracha] as tipoCracha " +
				" INNER JOIN " +
				" [ControleAcesso].[dbo].[tb_cracha] " +
				" ON tipoCracha.tp_id_cracha =  [ControleAcesso].[dbo].[tb_cracha].[tp_id_cracha] " +		
				" WHERE tipoCracha.tp_id_cracha =? ";                                                                      	
				                                                                     
        try {
			PreparedStatement stmt = this.connection.prepareStatement(strSql);
			
			/* stmt = select acima, porém ao invés da
			   interrogação, pegando o valor passado
			   pela AdicionaPessoaServlet */
			stmt.setLong(1, tipocracha.getId());
			
			/* Faz a atribuição do select para a 
			   			variável rs */
			ResultSet rs = stmt.executeQuery();		
						
			/* executa o select p/ ver se há no banco de 
			   dados, tal registro*/	
			TipoCracha tpCracha = new TipoCracha();
			
			if (rs.next()) {			
				tpCracha.setTipo(rs.getString("tp_nome_cracha"));
			//	tipocracha.setTipocracha(tpCracha);				
			}
			else {
				throw new SQLException("Não foi possível obter o código da pessoa.");
			}
			rs.close();
			stmt.close();
		
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		/* Retorna a pessoa com o RG específico 
	     para a servlet AdicionaPessoaServlet */
		return tipocracha;
	}
	
	
	public Cracha adiciona(Cracha cracha) {
		String sql = "insert into [ControleAcesso].[dbo].[tb_cracha] (cr_numero) values (?)";
		try {
			
			// prepared statement para inserção
		    PreparedStatement stmt = connection.prepareStatement(sql);
		    
			// seta os valores
			stmt.setLong(1, cracha.getNumeroCracha());
			
			// executa
			stmt.execute();
			stmt.close();		
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return cracha;
	}	
	
}
