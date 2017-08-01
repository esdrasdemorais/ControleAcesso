package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.proguaru.jdbc.ConnectionFactory;

import modelo.Empresa;
import modelo.Local;
import modelo.Setor;

public class SetorDAO {
	
	// a conexão com o banco de dados
	private Connection connection;
	
	public SetorDAO() throws Exception {
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public List<Setor> getLista() {
		  try {
			  List<Setor> setores = new ArrayList<Setor>();
			  PreparedStatement stmt = this.connection.prepareStatement("select * from [ControleAcesso].[dbo].[tb_setor]");
			  ResultSet rs = stmt.executeQuery();		  
		  	  
			  while (rs.next()) {
				  
				// criando o objeto Pessoa
				  Setor setor = new Setor();
				  setor.setId(rs.getLong("st_id"));
				  setor.setNome(rs.getString("st_nome"));
				  				  
				  // adicionando o objeto à lista
				  setores.add(setor);
			  } 
			  rs.close(); 	  
			  stmt.close();
			  return setores;
		
		  } catch (SQLException e) {
			  throw new RuntimeException(e);
		  }		
	}	
	
	
	public Setor getSetorById(Long id) throws Exception
	{
		Setor setor = new Setor();
		
		String strSql = "SELECT setor.st_id, setor.st_nome " +
		" FROM [ControleAcesso].[dbo].[tb_setor] AS setor " +
		//" INNER JOIN [ControleAcesso].[dbo].[tb_visitante] " +
		//" ON empresa.emp_id = [ControleAcesso].[dbo].[tb_visitante].[emp_id] " +
		" WHERE setor.st_id = ?";
		
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
				setor.setId(rs.getLong("st_id"));
				setor.setNome(rs.getString("st_nome"));			
			}
			else {
				throw new SQLException("Não foi possível obter o código da pessoa.");
			}
		
			rs.close();
			stmt.close();
		
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return setor;
	}
	
public Setor getSetorByNome(Setor nomeSetor) throws Exception {	
		
		String strSql = "SELECT Setor.st_id, Setor.st_nome" +    
				" FROM [ControleAcesso].[dbo].[tb_setor] AS Setor" +
				" INNER JOIN" +
				" [ControleAcesso].[dbo].[tb_visita]" +
				" ON Setor.st_id = [ControleAcesso].[dbo].[tb_visita].[st_id]" +
				" WHERE [ControleAcesso].[dbo].[tb_visita].[st_id] = ?";		
		
		try {	 
			PreparedStatement stmt = this.connection.prepareStatement(strSql);
			
			/* stmt = select acima, porém ao invés da
			   interrogação, pegando o valor passado
			   pela VisitaDAO */
			stmt.setLong(1, nomeSetor.getId());
			
			/* Faz a atribuição do select para a 
			   			variável rs */
			ResultSet rs = stmt.executeQuery();		
						
			/* executa o select p/ ver se há no banco de 
			   dados, tal registro*/			
			if (rs.next()) {			
				nomeSetor.setNome(rs.getString("st_nome"));					
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
		return nomeSetor;
	}
	
public Setor adiciona(Setor setor) {
	String sql = "insert into [ControleAcesso].[dbo].[tb_setor] (st_nome) values (?)";
	try {
		
		// prepared statement para inserção
	    PreparedStatement stmt = connection.prepareStatement(sql);
	    
		// seta os valores
		stmt.setString(1, setor.getNome());
		
		// executa
		stmt.execute();
		stmt.close();		
		
	} catch (SQLException e) {
		throw new RuntimeException(e);
	}
	return setor;
}	
}
