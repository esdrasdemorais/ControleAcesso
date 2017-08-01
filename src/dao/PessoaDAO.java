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
import modelo.Setor;
//import java.util.Date;

import br.com.proguaru.jdbc.ConnectionFactory;

public class PessoaDAO {
	// a conexão com o banco de dados
	private Connection connection;
	
	public PessoaDAO() throws Exception {
		this.connection = new ConnectionFactory().getConnection();
	}

	public Pessoa busca(Pessoa pess) {
		
		try {
			
			Connection con = new ConnectionFactory().getConnection();
			
			String strSql = "SELECT ps_id" + 
			  " ps_rg, ps_nome, ps_data_nasc" +
			  " FROM [ControleAcesso].[dbo].[tb_pessoa]" +
			  " WHERE ps_rg = ?";
			
		 
				PreparedStatement stmt = this.connection.prepareStatement(strSql);
				
				/* stmt = select acima, porém ao invés da
				   interrogação, pegando o valor passado
				   pela AdicionaPessoaServlet */
				stmt.setString(1, pess.getRg());
				
				/* Faz a atribuição do select para a 
				   			variável rs */
				ResultSet rs = stmt.executeQuery();			
						
				if (rs.next()) {			
					pess.setId(rs.getLong("ps_id"));
					pess.setNome(rs.getString("ps_nome"));
					pess.setRg(rs.getString("ps_rg"));											
				}		 
			 
			stmt.close();
			con.close();
			
		  } catch (SQLException e) {
			  throw new RuntimeException(e);
		  }
		  
		  /* Passo 3: retorna a pessoa com o RG específico 
		     para a servlet BuscaPessoaServlet */
		return pess;
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
		String sql = "insert into [ControleAcesso].[dbo].[tb_pessoa] (ps_rg,ps_nome,ps_data_nasc) values (?,?,?)";
		try {
			
			// prepared statement para inserção
		    PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		    
			// seta os valores
			stmt.setString(1,pessoa.getRg());
			stmt.setString(2,pessoa.getNome());			
			stmt.setDate(3, new Date(pessoa.getDataNasc().getTimeInMillis()));
			
			// executa	
			stmt.executeUpdate();
		    ResultSet rs = stmt.getGeneratedKeys();			
			
			if(rs.next()) {
				pessoa.setId(rs.getLong(1));
			}			
			else
			{
				throw new SQLException("Não foi possível obter o código da pessoa.");
			}
			
				rs.close();
				stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return pessoa;
	}
	
	public Pessoa getPessoaById(Pessoa pesRecebe) throws Exception
	{
		String strSql = "SELECT pessoa.ps_id, pessoa.ps_nome " +
		" FROM [ControleAcesso].[dbo].[tb_pessoa] AS pessoa " +
		//" INNER JOIN [ControleAcesso].[dbo].[tb_visitante] " +
		//" ON empresa.emp_id = [ControleAcesso].[dbo].[tb_visitante].[emp_id] " +
		" WHERE pessoa.st_id = ?";
		
		try {
			PreparedStatement stmt = this.connection.prepareStatement(strSql);
			
			/* stmt = select acima, porém ao invés da
			   interrogação, pegando o valor passado
			   pela AdicionaPessoaServlet */
			stmt.setLong(1, pesRecebe.getId());
			
			/* Faz a atribuição do select para a 
			   			variável rs */
			ResultSet rs = stmt.executeQuery();		
						
			/* executa o select p/ ver se há no banco de 
			   dados, tal registro*/	
			
			if (rs.next()) {			
				pesRecebe.setId(rs.getLong(1));
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
		return pesRecebe;
	}	
	
    public Pessoa getNomeByRg(String rg){  
        try {             
            
        	Pessoa pessoa  = new Pessoa();            
            String sql = "SELECT * 	FROM [ControleAcesso].[dbo].[tb_pessoa] WHERE ps_rg=?";  
            PreparedStatement stmt = connection.prepareStatement(sql);             
            stmt.setString(1, rg);  
            ResultSet rs = stmt.executeQuery();  
            
            if(rs.next()) {
            	
	            pessoa.setId(rs.getLong("ps_id"));
	            pessoa.setNome(rs.getString("ps_nome"));
	            pessoa.setRg(rs.getString("ps_rg"));
	            
	            Calendar data = Calendar.getInstance();  
	            data.setTime(rs.getDate("ps_data_nasc"));  
	            pessoa.setDataNasc(data);              
            }  
            
            rs.close();  
            stmt.close();  
            return pessoa;  
        } catch (SQLException e){  
            throw new RuntimeException(e);  
        }  
    }  
	
    public Pessoa getPessoaByDRT(Integer drt){  
        try {             
            
        	Pessoa pessoa  = new Pessoa();            
            String sql = "SELECT pessoa.ps_id, pessoa.ps_rg, pessoa.ps_nome, pessoa.ps_data_nasc" +
            		" funcionario.fc_drt, funcionario.fc_cpf " +
            		" * FROM [ControleAcesso].[dbo].[tb_pessoa] " +
            		" AS pessoa," +            		            		
            		" INNER JOIN [ControleAcesso].[dbo].[tb_funcionario]" +            		
            		" AS funcionario " +
            		" ON pessoa.ps_id, funcionario.ps_id" +
            		" WHERE funcionario.fc_drt = ?"; 
            
            PreparedStatement stmt = connection.prepareStatement(sql);             
            stmt.setInt(1, drt);  
            ResultSet rs = stmt.executeQuery();  
            
            if(rs.next()) {
            	
            		            
            	
            	pessoa.setNome(rs.getString("nome"));
	            pessoa.setCargo(rs.getLong("cargo"));
	            pessoa.setDrt(rs.getInt("drt"));
	            pessoa.setCpf(rs.getString("cpf"));
	            
	            Calendar dataNasc = Calendar.getInstance();  
	            dataNasc.setTime(rs.getDate("datnasc"));  
	            pessoa.setDataNasc(dataNasc);              
            
	            Calendar dataAdm = Calendar.getInstance();  
	            dataAdm.setTime(rs.getDate("datadm"));  
	            pessoa.setDataNasc(dataAdm);
            
            }  
            
            rs.close();  
            stmt.close();  
            return pessoa;  
        } catch (SQLException e){  
            throw new RuntimeException(e);  
        }  
    }
    
    public Pessoa getPessoaByCPF(String cpf){  
        try {             
            
        	Pessoa pessoa  = new Pessoa();            
            String sql = "SELECT * FROM [db_selecao].[dbo].[tb_funcionarios] WHERE cpf=?";  
            PreparedStatement stmt = connection.prepareStatement(sql);             
            stmt.setString(1, cpf);  
            ResultSet rs = stmt.executeQuery();  
            
            if(rs.next()) {
            	
            	pessoa.setId(rs.getLong("ps_id"));
            	pessoa.setNome(rs.getString("nome"));
	            pessoa.setCargo(rs.getLong("cargo"));
	            pessoa.setDrt(rs.getInt("drt"));
	            pessoa.setCpf(rs.getString("cpf"));
	            
	            Calendar dataNasc = Calendar.getInstance();  
	            dataNasc.setTime(rs.getDate("datnasc"));  
	            pessoa.setDataNasc(dataNasc);              
            
	            Calendar dataAdm = Calendar.getInstance();  
	            dataAdm.setTime(rs.getDate("datadm"));  
	            pessoa.setDataNasc(dataAdm);
            
            }  
            
            rs.close();  
            stmt.close();  
            return pessoa;  
        } catch (SQLException e){  
            throw new RuntimeException(e);  
        }  
    }	
}	