package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import modelo.Contato;
import modelo.Funcionario;
import modelo.Pessoa;
//import java.util.Date;

import br.com.proguaru.jdbc.ConnectionFactory;


public class ContatoDAO {
	// a conexão com o banco de dados
	private Connection connection;
	
	public ContatoDAO() throws Exception {
		this.connection = new ConnectionFactory().getConnection();
	}

	public Pessoa busca(Pessoa pessRecebe) {
		
		try {			 			 		  
			Connection con = new ConnectionFactory().getConnection();
			
			String strSQL = "SELECT ps_id" + 
			  " ps_rg, ps_nome, ps_data_nasc" +
			  " FROM ControleAcesso.dbo.tb_pessoa" +
			  " WHERE ps_rg =?";
						
			// stmt = select acima com a interrogação
			PreparedStatement stmt = this.connection.prepareStatement(strSQL);
				
			/* stmt = select acima, porém ao invés da
			   interrogação, pegando o valor passado
			   pela BuscaPessoaServlet */
			stmt.setString(1, pessRecebe.getRg());
			
			/* Faz a atribuição do select para a 
			   			variável rs */
			ResultSet rs = stmt.executeQuery();		
						
			/* executa o select p/ ver se há no banco de 
			   dados, tal registro*/			
			if (rs.next()) {			
				pessRecebe.setNome(rs.getString("ps_nome"));
				pessRecebe.setRg(rs.getString("ps_rg"));
				pessRecebe.setId(rs.getLong(1));							
			}		 
			 
			stmt.close();
			con.close();
			
		  } catch (SQLException e) {
			  throw new RuntimeException(e);
		  }
		  
		  /* Passo 3: retorna a pessoa com o RG específico 
		     para a servlet BuscaPessoaServlet */
		return pessRecebe;
	 }
		  
	public List<Contato> getListaContato() {
		  try {
			  List<Contato> contatos = new ArrayList<Contato>();
			  PreparedStatement stmt = this.connection.prepareStatement("select * from contatos");
			  ResultSet rs = stmt.executeQuery();		  
		  		
			  rs.close(); 	  
			  stmt.close();
			  return contatos;
		
		  } catch (SQLException e) {
			  throw new RuntimeException(e);
		  }
	}
	
	public List<Pessoa> getListaRobert() {
		  try {
			  List<Pessoa> pessoas = new ArrayList<Pessoa>();
			  PreparedStatement stmt = this.connection.prepareStatement("select * from [ControleAcesso].[dbo].[tb_pessoa]");
			  ResultSet rs = stmt.executeQuery();		  
		  	  
			  while (rs.next()) {
				  // criando o objeto Pessoa
				  Pessoa pessoa = new Pessoa();
				  pessoa.setRg(rs.getString("ps_rg"));
				  pessoa.setNome(rs.getString("ps_nome"));
				  
				// adicionando o objeto à lista
				  pessoas.add(pessoa);
			  } 
			  rs.close(); 	  
			  stmt.close();
			  return pessoas;
		
		  } catch (SQLException e) {
			  throw new RuntimeException(e);
		  }		
	}
	
	public Pessoa adiciona(Pessoa pessoa) {

		String sql = "insert into [ControleAcesso].[dbo].[tb_pessoa] (ps_rg,ps_nome) values (?,?)";
		try {
			// prepared statement para inserção
			PreparedStatement stmt = connection.prepareStatement(sql);
			// seta os valores
			stmt.setString(1,pessoa.getRg());
			stmt.setString(2,pessoa.getNome());			
			//stmt.setString(3,contato.getEndereco());
			
			// executa
			if(stmt.execute(sql, Statement.RETURN_GENERATED_KEYS))
			{
				ResultSet rs = stmt.getGeneratedKeys();
			}
			else
			{
				throw new SQLException("Não foi possível obter o código da pessoa.");
			}
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return pessoa;

	}
	
	
	
	
}	