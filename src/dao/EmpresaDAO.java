package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.proguaru.jdbc.ConnectionFactory;

import modelo.Empresa;
import modelo.Pessoa;
import modelo.Setor;
import modelo.Visita;
import modelo.Visitante;

public class EmpresaDAO {

	// a conexão com o banco de dados
	private Connection connection;
	
	public EmpresaDAO() throws Exception {
		this.connection = new ConnectionFactory().getConnection();
		
	}
	

	/*
	public Empresa getNomeEmpresaByIdVisitante(Empresa nomeEmpresa) throws Exception {	
		
		String strSql = "SELECT Pessoa.ps_id, Pessoa.ps_nome, Pessoa.ps_rg" +
				" FROM [ControleAcesso].[dbo].[tb_pessoa] AS Pessoa" +
				" INNER JOIN" +
				" [ControleAcesso].[dbo].[tb_visitante] AS Visitante" +
				" ON Pessoa.ps_id = Visitante.ps_id " +
				" INNER JOIN" +
				" [ControleAcesso].[dbo].[tb_visita] AS Visita" +
				" ON Visitante.ps_id = Visita.pfvs_id" +
				" WHERE Visitante.ps_id = ?";
		
		try {	 
			PreparedStatement stmt = this.connection.prepareStatement(strSql);
			
			/* stmt = select acima, porém ao invés da
			   interrogação, pegando o valor passado
			   pela VisitaDAO */
			/*stmt.setLong(1, nomeEmpresa.getId());
			
			/* Faz a atribuição do select para a 
			   			variável rs */
		/*	ResultSet rs = stmt.executeQuery();		
						
			/* executa o select p/ ver se há no banco de 
			   dados, tal registro*/			
		/*	if (rs.next()) {			
				nomeEmpresa.setNome(rs.getString("ps_nome"));
				//nomeVisitante.setRg(rs.getString("ps_rg"));
						
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
	     para a servlet AdicionaPessoaServlet */
		/*return nomeEmpresa;
	}
	*/
	
public Empresa getNomeEmpresaByIdVisitante(Long id) throws Exception 
{
		Empresa empresa = new Empresa();
	
		String strSql = " SELECT " +
					" empresa.emp_id, empresa.emp_nome, " +
					" visitante.ps_id, visitante.emp_id " +
					" FROM [ControleAcesso].[dbo].[tb_empresa] AS empresa " +
					" INNER JOIN " +
					" [ControleAcesso].[dbo].[tb_visitante] AS visitante" +
					" ON " +
					" empresa.emp_id = visitante.emp_id" +					
					" WHERE empresa.emp_id = ?";
		
		try {	 
			PreparedStatement stmt = this.connection.prepareStatement(strSql);
			
			/* stmt = select acima, porém ao invés da
			   interrogação, pegando o valor passado
			   pela VisitaDAO */
			stmt.setLong(1, id);
			
			/* Faz a atribuição do select para a 
			   			variável rs */
			ResultSet rs = stmt.executeQuery();		
						
			/* executa o select p/ ver se há no banco de 
			   dados, tal registro*/			
			if (rs.next()) {			
				empresa.setId(rs.getLong("emp_id"));
				empresa.setNome(rs.getString("emp_nome"));			
						
			}
			else {
				throw new SQLException("Não foi possível obter o nome da Empresa.");
			}
		
			rs.close();
			stmt.close();
		
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
				
		return empresa;
	}

public Empresa getNomeEmpresaById(Long id) throws Exception 
{
		Empresa empresa = new Empresa();
	
		String strSql = " SELECT " +
					" empresa.emp_id, empresa.emp_nome " +
		//			" visitante.ps_id, visitante.emp_id " +
					" FROM [ControleAcesso].[dbo].[tb_empresa] AS empresa " +
		//			" INNER JOIN " +
		//			" [ControleAcesso].[dbo].[tb_visitante] AS visitante" +
		//			" ON " +
		//			" empresa.emp_id = visitante.emp_id" +					
					" WHERE empresa.emp_id = ?";
		
		try {	 
			PreparedStatement stmt = this.connection.prepareStatement(strSql);
			
			/* stmt = select acima, porém ao invés da
			   interrogação, pegando o valor passado
			   pela VisitaDAO */
			stmt.setLong(1, id);
			
			/* Faz a atribuição do select para a 
			   			variável rs */
			ResultSet rs = stmt.executeQuery();		
						
			/* executa o select p/ ver se há no banco de 
			   dados, tal registro*/			
			if (rs.next()) {			
				empresa.setId(rs.getLong("emp_id"));
				empresa.setNome(rs.getString("emp_nome"));			
						
			}
			else {
				throw new SQLException("Não foi possível obter o nome da Empresa.");
			}
		
			rs.close();
			stmt.close();
		
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
				
		return empresa;
	}
	

public Empresa getUltima() throws Exception 
{
		Empresa empresa = new Empresa();
		String strSql = " SELECT TOP 1" +
					" emp_id, emp_nome" +
					" FROM [ControleAcesso].[dbo].[tb_empresa]" +
					" ORDER BY emp_id DESC";
					
		
		try {	 
			PreparedStatement stmt = this.connection.prepareStatement(strSql);
		
			/* Faz a atribuição do select para a 
			   			variável rs */
			ResultSet rs = stmt.executeQuery();		
						
			/* executa o select p/ ver se há no banco de 
			   dados, tal registro*/			
			if (rs.next()) {			
				empresa.setId(rs.getLong("emp_id"));
				empresa.setNome(rs.getString("emp_nome"));			
						
			}
			else {
				throw new SQLException("Não foi possível obter o nome da Empresa.");
			}
		
			rs.close();
			stmt.close();
		
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
				
		return empresa;
}


	public List<Empresa> getLista() {
		  try {
			  List<Empresa> empresas = new ArrayList<Empresa>();
			  PreparedStatement stmt = this.connection.prepareStatement("select * from [ControleAcesso].[dbo].[tb_empresa] order by emp_id desc");
			  ResultSet rs = stmt.executeQuery();		  
		  	  
			  while (rs.next()) {
				  // criando o objeto Pessoa
				  Empresa empresa = new Empresa();
				  empresa.setId(rs.getLong("emp_id"));
				  empresa.setNome(rs.getString("emp_nome"));				  
				  
				// adicionando o objeto à lista
				  empresas.add(empresa);
			  } 
			  rs.close(); 	  
			  stmt.close();
			  return empresas;
		
		  } catch (SQLException e) {
			  throw new RuntimeException(e);
		  }		
	}	  
	
	public Empresa getEmpresa(Empresa empresa) throws Exception
	{		
		
		String strSql = "SELECT empresa.emp_id, empresa.emp_nome, " +
		" visitante.emp_id " +
		" FROM [ControleAcesso].[dbo].[tb_empresa] AS empresa " +
		" INNER JOIN [ControleAcesso].[dbo].[tb_visitante] as visitante " +
		" ON empresa.emp_id = visitante.emp_id " +
		" WHERE empresa.emp_id = ?";
		
		try {
			PreparedStatement stmt = this.connection.prepareStatement(strSql);
			
			/* stmt = select acima, porém ao invés da
			   interrogação, pegando o valor passado
			   pela AdicionaPessoaServlet */
			stmt.setLong(1, empresa.getId());
			
			/* Faz a atribuição do select para a 
			   			variável rs */
			ResultSet rs = stmt.executeQuery();		
						
			/* executa o select p/ ver se há no banco de 
			   dados, tal registro*/	
			
			if (rs.next()) {			
				empresa.setId(rs.getLong("emp_id"));
				empresa.setNome(rs.getString("emp_nome"));	
				String nome = empresa.getNome();
				Long id = empresa.getId();
			}			
		
			rs.close();
			stmt.close();
		
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return empresa;
	}
	
	public Empresa adiciona(Empresa empresa) {
		String sql = "insert into [ControleAcesso].[dbo].[tb_empresa] (emp_nome) values (?)";
		try {
			
			// prepared statement para inserção
		    PreparedStatement stmt = connection.prepareStatement(sql);
		    
			// seta os valores
			stmt.setString(1, empresa.getNome());
			
			// executa
			stmt.execute();
			stmt.close();		
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return empresa;
	}	
}

