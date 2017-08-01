package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Empresa;
import modelo.Setor;
import modelo.Usuario;
import modelo.Visitante;

import br.com.proguaru.jdbc.ConnectionFactory;

public class UsuarioDAO {
	
	// a conexão com o banco de dados
		private Connection connection;
		
		public UsuarioDAO() throws Exception {
			this.connection = new ConnectionFactory().getConnection();
		}
		
		public Usuario getVisitanteByUserName(String username) throws Exception
		{	                  
				Usuario usuario = new Usuario();   
			
				String strSql = "SELECT " +
				" usuario.ps_id, usuario.us_nome, usuario.us_senha, " +
				" usuario.us_admin " +				
				" FROM " +
				" [ControleAcesso].[dbo].[tb_usuario] AS usuario " +				
				" WHERE usuario.us_nome = ?";			
			
			try {	 
				PreparedStatement stmt = this.connection.prepareStatement(strSql);
			
				/* stmt = select acima, porém ao invés da
				   interrogação, pegando o valor passado
				   pela VisitaDAO */
				stmt.setString(1, username);
				
				/* Faz a atribuição do select para a 
				   			variável rs */
				ResultSet rs = stmt.executeQuery();		
				
				
				/* executa o select p/ ver se há no banco de 
				   dados, tal registro*/			
				if (rs.next()) {			
					
					usuario.setNomeUsuario(rs.getString("us_nome"));
					usuario.setAdmin(rs.getString("us_admin"));
					
					rs.close();
					stmt.close();
				}
				
			} catch (SQLException e) {
			throw new RuntimeException(e);
			}		
			return usuario;
		}	
		
		public Usuario adiciona(Usuario usuario) {
			String sql = "insert into [ControleAcesso].[dbo].[tb_usuario] (ps_id, us_nome, us_senha, us_admin) values (?,?,?,?)";
			try {
				
				// prepared statement para inserção
			    PreparedStatement stmt = connection.prepareStatement(sql);

				// seta os valores
				stmt.setLong(1, usuario.getFuncionario().getId());
				stmt.setString(2, usuario.getNomeUsuario());
				stmt.setString(3, usuario.getSenha());
				stmt.setString(4, "N" );
				
				// executa
				stmt.execute();
				stmt.close();		
				
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			return usuario;
		}
		
		public Usuario alteraSenha(Usuario usuario) {
			
			String sql = "UPDATE [ControleAcesso].[dbo].[tb_usuario] " +					
					" SET [ControleAcesso].[dbo].[tb_usuario].[us_senha] = ?" +
					" FROM [ControleAcesso].[dbo].[tb_usuario] AS usuario" +
					" INNER JOIN " +
					" [ControleAcesso].[dbo].[tb_funcionario] AS funcionario" +
					" ON usuario.ps_id = funcionario.ps_id" +
					" WHERE funcionario.fc_drt = ?";			
			try {
					
				// prepared statement para inserção
			    PreparedStatement stmt = connection.prepareStatement(sql);

				// seta os valores				
				stmt.setString(1, usuario.getSenha());				
				stmt.setInt(2, usuario.getFuncionario().getDrt());
				
				// executa
				stmt.execute();
				stmt.close();		
				
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			return usuario;
		}
		

		public Usuario existeUsuario(Usuario usuario) {			  
			
			String strSql = "SELECT " +
			" usuario.ps_id, usuario.us_nome, usuario.us_senha, " +
			" usuario.us_admin " +				
			" FROM " +
			" [ControleAcesso].[dbo].[tb_usuario] AS usuario " +				
			" WHERE usuario.us_nome = ? AND usuario.us_senha = ?";			
		
		try {	 
			PreparedStatement stmt = this.connection.prepareStatement(strSql);
					
			stmt.setString(1, usuario.getNomeUsuario());
			stmt.setString(2, usuario.getSenha());
			
			/* Faz a atribuição do select para a 
			   			variável rs */
			ResultSet rs = stmt.executeQuery();		
						
			/* executa o select p/ ver se há no banco de 
			   dados, tal registro*/			
			if (rs.next()) {			
				
				usuario.setNomeUsuario(rs.getString("us_nome"));
				usuario.setSenha(rs.getString("us_senha"));
				usuario.setAdmin(rs.getString("us_admin"));
				rs.close();
				stmt.close();
				return usuario;
			}
			else {
				return null;
			}
		} catch (SQLException e) {
		throw new RuntimeException(e);
		}		
		
	}			

}
