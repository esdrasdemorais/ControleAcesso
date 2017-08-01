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

public class tipoCrachaDAO {
	// a conexão com o banco de dados
	private Connection connection;
	
	public tipoCrachaDAO() throws Exception {
		this.connection = new ConnectionFactory().getConnection();
	}

	public List<TipoCracha> getLista() {
		  try {
			  List<TipoCracha> tipoCracha = new ArrayList<TipoCracha>();
			  PreparedStatement stmt = this.connection.prepareStatement("SELECT " +
			  		" tipoCracha.tp_id_cracha, tipoCracha.tp_nome_cracha, " +
			  		" cracha.tp_id_cracha " +
			  		" FROM [ControleAcesso].[dbo].[tb_tipo_cracha] as tipoCracha" +
			  		" INNER JOIN [ControleAcesso].[dbo].[tb_cracha] as cracha" +
			  		" ON tipoCracha.tp_id_cracha = cracha.tp_id_cracha"); 
	
			  
			  ResultSet rs = stmt.executeQuery();		  
		  	  
			  while (rs.next()) {
								  				  
				// criando o objeto tipocracha
				  
				  TipoCracha tpCracha = new TipoCracha();
				  tpCracha.setId(rs.getLong("tp_id_cracha"));
				  tpCracha.setTipo(rs.getString("tp_nome_cracha"));			  
				  			  
				  
				  // adicionando o objeto à lista
				  tipoCracha.add(tpCracha);
			  } 

			  rs.close(); 	  
			  stmt.close();
			  return tipoCracha;
		
		  } catch (SQLException e) {
			  throw new RuntimeException(e);
		  }	
	}	
	
	public Cracha getCrachaById(Cracha craRecebe) throws Exception
	{
		String strSql = "SELECT cracha.cr_id, cracha.tp_id_cracha" +
		" FROM [ControleAcesso].[dbo].[tb_cracha] AS cracha " +
		//" INNER JOIN [ControleAcesso].[dbo].[tb_visitante] " +
		//" ON empresa.emp_id = [ControleAcesso].[dbo].[tb_visitante].[emp_id] " +
		" WHERE cracha.cr_id = ?";
		
		try {
			PreparedStatement stmt = this.connection.prepareStatement(strSql);
			
			/* stmt = select acima, porém ao invés da
			   interrogação, pegando o valor passado
			   pela AdicionaPessoaServlet */
			stmt.setLong(1, craRecebe.getId());
			
			/* Faz a atribuição do select para a 
			   			variável rs */
			ResultSet rs = stmt.executeQuery();		
						
			/* executa o select p/ ver se há no banco de 
			   dados, tal registro*/	
			
			if (rs.next()) {			
				craRecebe.setId(rs.getLong(1));
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
		return craRecebe;
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
	
	
}
