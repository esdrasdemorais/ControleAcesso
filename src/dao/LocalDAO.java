package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.proguaru.jdbc.ConnectionFactory;

import modelo.Local;
import modelo.Setor;
import modelo.Visitante;

public class LocalDAO {

	// a conexão com o banco de dados
	private Connection connection;
	
	public LocalDAO() throws Exception {
		this.connection = new ConnectionFactory().getConnection();
	}

	public List<Local> getLista() {
		  try {
			  List<Local> locais = new ArrayList<Local>();
			  PreparedStatement stmt = this.connection.prepareStatement("select * from [ControleAcesso].[dbo].[tb_local]");
			  ResultSet rs = stmt.executeQuery();		  
		  	  
			  while (rs.next()) {
								  				  
				// criando o objeto Local
				  Local local = new Local();
				  local.setId(rs.getLong("loc_id"));
				  local.setNome(rs.getString("loc_nome"));
				  
				  
				  // adicionando o objeto à lista
				  locais.add(local);
			  } 

			  rs.close(); 	  
			  stmt.close();
			  return locais;
		
		  } catch (SQLException e) {
			  throw new RuntimeException(e);
		  }		
	}	
	
	public Local getLocalById(Long id) throws Exception
	{
		
		Local local = new Local();
		
		String strSql = "SELECT local.loc_id, local.loc_nome " +
		" FROM [ControleAcesso].[dbo].[tb_local] AS local " +
		//" INNER JOIN [ControleAcesso].[dbo].[tb_visitante] " +
		//" ON empresa.emp_id = [ControleAcesso].[dbo].[tb_visitante].[emp_id] " +
		" WHERE local.loc_id = ?";
		
		try {
			PreparedStatement stmt = this.connection.prepareStatement(strSql);
			
			/* stmt = select acima, porém ao invés da
			   interrogação, pegando o valor passado
			   pela AdicionaPessoaServlet */
			stmt.setLong(1, id);
			
			/* Faz a atribuição do select para a 
			   			variável rs */
			ResultSet rs = stmt.executeQuery();		
						
			/* executa o select p/ ver se há no banco de 
			   dados, tal registro*/	
			
			if (rs.next()) {			
				local.setId(rs.getLong("loc_id"));
				local.setNome(rs.getString("loc_nome"));
			}
			else {
				throw new SQLException("Não foi possível obter o código da pessoa.");
			}
		
			rs.close();
			stmt.close();
		
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
			
		return local;
	}
	
public Local getLocalByNome(Local nomeLocal) throws Exception {	
		
		String strSql = "SELECT Local.loc_id, Local.loc_nome" +    
				" FROM [ControleAcesso].[dbo].[tb_local] AS Local" +
				" INNER JOIN" +
				" [ControleAcesso].[dbo].[tb_visita]" +
				" ON Local.loc_id = [ControleAcesso].[dbo].[tb_visita].[loc_id]" +
				" WHERE [ControleAcesso].[dbo].[tb_visita].[loc_id] = ?";		
		
		try {	 
			PreparedStatement stmt = this.connection.prepareStatement(strSql);
			
			/* stmt = select acima, porém ao invés da
			   interrogação, pegando o valor passado
			   pela VisitaDAO */
			stmt.setLong(1, nomeLocal.getId());
			
			/* Faz a atribuição do select para a 
			   			variável rs */
			ResultSet rs = stmt.executeQuery();		
						
			/* executa o select p/ ver se há no banco de 
			   dados, tal registro*/			
			if (rs.next()) {			
				nomeLocal.setNome(rs.getString("loc_nome"));					
			}
			else {
				throw new SQLException("Não foi possível obter o nome da pessoa.");
			}
		
			rs.close();
			stmt.close();
		
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		/* Retorna a pessoa com o RG específico 
	     para a servlet VisitaDAO */
		return nomeLocal;
	}
	
}
