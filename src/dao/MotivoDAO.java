package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.proguaru.jdbc.ConnectionFactory;

import modelo.Local;
import modelo.Motivo;
import modelo.Setor;
import modelo.Visitante;

public class MotivoDAO {

	// a conexão com o banco de dados
	private Connection connection;
	
	public MotivoDAO() throws Exception {
		this.connection = new ConnectionFactory().getConnection();
	}

	public List<Motivo> getLista() {
		  try {
			  List<Motivo> motivos = new ArrayList<Motivo>();
			  PreparedStatement stmt = this.connection.prepareStatement("select * from [ControleAcesso].[dbo].[tb_motivo]");
			  ResultSet rs = stmt.executeQuery();		  
		  	  
			  while (rs.next()) {
								  				  
				// criando o objeto Local
				  Motivo motivo = new Motivo();
				  motivo.setId(rs.getLong("mt_id"));
				  motivo.setTipo(rs.getString("mt_tipo"));
				  
				  
				  // adicionando o objeto à lista
				  motivos.add(motivo);
			  } 

			  rs.close(); 	  
			  stmt.close();
			  return motivos;
		
		  } catch (SQLException e) {
			  throw new RuntimeException(e);
		  }		
	}			
	
	public Motivo getMotivoById(Long id) throws Exception
	{
		Motivo motivo = new Motivo();
		
		String strSql = "SELECT motivo.mt_id, motivo.mt_tipo " +
		" FROM [ControleAcesso].[dbo].[tb_motivo] AS motivo " +		
		" WHERE motivo.mt_id = ?";
		
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
				motivo.setId(rs.getLong("mt_id"));
				motivo.setTipo(rs.getString("mt_tipo"));			
			}
			else {
				throw new SQLException("Não foi possível obter o código do motivo.");
			}
		
			rs.close();
			stmt.close();
		
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return motivo;
	}
	
public Motivo getMotivoByNome(Motivo tipoMotivo) throws Exception {	
		
		String strSql = "SELECT motivo.mt_id, motivo.mt_tipo" +    
				" FROM [ControleAcesso].[dbo].[tb_motivo] AS motivo" +
				" INNER JOIN" +
				" [ControleAcesso].[dbo].[tb_visita]" +
				" ON motivo.mt_id = [ControleAcesso].[dbo].[tb_visita].[mt_id]" +
				" WHERE [ControleAcesso].[dbo].[tb_visita].[mt_id] = ?";		
		
		try {	 
			PreparedStatement stmt = this.connection.prepareStatement(strSql);
			
			/* stmt = select acima, porém ao invés da
			   interrogação, pegando o valor passado
			   pela VisitaDAO */
			stmt.setLong(1, tipoMotivo.getId());
			
			/* Faz a atribuição do select para a 
			   			variável rs */
			ResultSet rs = stmt.executeQuery();		
						
			/* executa o select p/ ver se há no banco de 
			   dados, tal registro*/			
			if (rs.next()) {			
				tipoMotivo.setTipo(rs.getString("mt_tipo"));					
			}
			else {
				tipoMotivo.setTipo(null);
			}
		
			rs.close();
			stmt.close();
		
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		/* Retorna a pessoa com o RG específico 
	     para a servlet VisitaDAO */
		return tipoMotivo;
	}
}
