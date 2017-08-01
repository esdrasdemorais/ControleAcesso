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

import br.com.proguaru.jdbc.ConnectionFactory;
import br.com.proguaru.servlet.BuscaVisitanteServlet;

import modelo.Empresa;
import modelo.Funcionario;
import modelo.Pessoa;
import modelo.Setor;
import modelo.Situacao;
import modelo.Visita;
import modelo.Visitante;

public class VisitanteDAO {

	// a conexão com o banco de dados
	private Connection connection;
	
	public VisitanteDAO() throws Exception {
		this.connection = new ConnectionFactory().getConnection();
		
	}
	
	
	
	public Visitante adiciona(Visitante visitante) {
		
		if (visitante.getEmpresa().getId() != 0) {		
			String sql = "insert into [ControleAcesso].[dbo].[tb_visitante] (ps_id,vs_telefone, emp_id) values (?,?,?)";
		
			try {
				
				// prepared statement para inserção
			    PreparedStatement stmt = connection.prepareStatement(sql);			
			    
				// seta os valores
				stmt.setLong(1, visitante.getId());
				stmt.setLong(2, visitante.getTelefone());			
				stmt.setLong(3, visitante.getEmpresa().getId());				
				
				
				// executa
				stmt.execute();
				stmt.close();
				
			} catch (SQLException e) {
				throw new RuntimeException(e);
				}				
		}
		
		else {
			String sql = "insert into [ControleAcesso].[dbo].[tb_visitante] (ps_id,vs_telefone) values (?,?)";
			try {
				
				// prepared statement para inserção
			    PreparedStatement stmt = connection.prepareStatement(sql);			
			    
				// seta os valores
				stmt.setLong(1, visitante.getId());
				stmt.setLong(2, visitante.getTelefone());		
											
				// executa
				stmt.execute();
				stmt.close();
				
			} catch (SQLException e) {
				throw new RuntimeException(e);
				}
		}	
		return visitante;
	}
	
public Visitante adicionaFoto(Visitante visitante) {
				
			String sql = "insert into [ControleAcesso].[dbo].[tb_pessoa_foto] (ps_id, pft_imagem, ft_tipo) values (?,?,?)";
		
			try {
				
				// prepared statement para inserção
			    PreparedStatement stmt = connection.prepareStatement(sql);			
			    
				// seta os valores
				stmt.setLong(1, visitante.getId());
				stmt.setString(2, visitante.getPessoafoto().getPathImagem());			
				stmt.setString(3, visitante.getPessoafoto().getTipo());
				
				//stmt.setLong(3, visitante.getEmpresa().getId());				
				
				
				// executa
				stmt.execute();
				stmt.close();
				
			} catch (SQLException e) {
				throw new RuntimeException(e);
				
				}					
		return visitante;
	}
	
public Visitante getVisitanteByVisitas(Visitante visitante) throws Exception {	
		
			String strSql = "SELECT" +
						" visita.pfvs_id, pessoa.ps_id, pessoa.ps_rg, pessoa.ps_nome, pessoa.ps_data_nasc, " +
						" visitante.ps_id, visitante.vs_telefone, visitante.emp_id,  " +
						" afastamento.af_id, afastamento.ps_id, afastamento.af_situacao" +
						" FROM [ControleAcesso].[dbo].[tb_visita] AS visita " +
						" LEFT OUTER JOIN" +
						" [ControleAcesso].[dbo].[tb_pessoa] AS pessoa" +
						" ON visita.pfvs_id = pessoa.ps_id " +
						" LEFT OUTER JOIN " +
						" [ControleAcesso].[dbo].[tb_visitante] AS visitante" +
						" ON pessoa.ps_id = visitante.ps_id " +
						" LEFT OUTER JOIN" +
						" [ControleAcesso].[dbo].[tb_afastamento] AS afastamento" +
						" ON visitante.ps_id = afastamento.ps_id" +			
						" WHERE visita.pfvs_id = ?";	
		
		try {	 
			PreparedStatement stmt = this.connection.prepareStatement(strSql);
			
			/* stmt = select acima, porém ao invés da
			   interrogação, pegando o valor passado
			   pela VisitaDAO */
			stmt.setLong(1, visitante.getId());
			
			/* Faz a atribuição do select para a 
			   			variável rs */
			ResultSet rs = stmt.executeQuery();		

			
			/* executa o select p/ ver se há no banco de 
			   dados, tal registro*/			
			if (rs.next()) {			
				
				Empresa empresa = new Empresa();				
				empresa.setId(rs.getLong("emp_id"));
				
				EmpresaDAO empresaDAO = new EmpresaDAO();
				empresa = empresaDAO.getEmpresa(empresa);
				
				
				visitante.setEmpresa(empresa);
				visitante.setNome(rs.getString("ps_nome"));
				visitante.setRg(rs.getString("ps_rg"));	
			}
			else {
				visitante.setEmpresa(null);
				visitante.setNome(null);
				visitante.setRg(null);
				visitante.setSituacao(null);
			}
			
			
			
			rs.close();
			stmt.close();
		
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return visitante;
	}

	public Visitante getVisitanteByRg(String rg) throws Exception
	{	                  
	        Visitante visitante = new Visitante();
			
			String strSql = "SELECT " +
			" pessoa.ps_id, pessoa.ps_nome, pessoa.ps_rg, " +
			" visita.pfvs_id, " +
			" visitante.vs_telefone, visitante.ps_id, visitante.emp_id " +
			" FROM " +
			" [ControleAcesso].[dbo].[tb_visitante] AS visitante " +
			" INNER JOIN " +
			" [ControleAcesso].[dbo].[tb_pessoa] AS pessoa " +
			" ON " +	
			" visitante.ps_id = pessoa.ps_id " +
			" LEFT JOIN " +
			" [ControleAcesso].[dbo].[tb_visita] AS visita " +
			" ON " +
			" visitante.ps_id = visita.pfvs_id " +
			" WHERE pessoa.ps_rg = ?";			
		
		try {	 
			PreparedStatement stmt = this.connection.prepareStatement(strSql);
		
			/* stmt = select acima, porém ao invés da
			   interrogação, pegando o valor passado
			   pela VisitaDAO */
			stmt.setString(1, rg);
			
			/* Faz a atribuição do select para a 
			   			variável rs */
			ResultSet rs = stmt.executeQuery();		
			
			
			/* executa o select p/ ver se há no banco de 
			   dados, tal registro*/			
			if (rs.next()) {			
				
				Empresa empresa = new Empresa();				
				empresa.setId(rs.getLong("emp_id"));
				
				EmpresaDAO empresaDAO = new EmpresaDAO();
				empresa = empresaDAO.getEmpresa(empresa);
				
				visitante.setEmpresa(empresa);
				visitante.setId(rs.getLong("ps_id"));
				visitante.setNome(rs.getString("ps_nome"));
				visitante.setRg(rs.getString("ps_rg"));	
				visitante.setTelefone(rs.getLong("vs_telefone"));
				
			}
			else {
				visitante.setRg(rg);
				String rge = visitante.getRg();
				String rgw = rge;
			}
				rs.close();
				stmt.close();
		
			} catch (SQLException e) {
			throw new RuntimeException(e);
			}
		
		return visitante;
	}

	public Visitante getVisitanteByNomeAndRG(Visitante nomeVisitante) throws Exception {	
		
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
			stmt.setLong(1, nomeVisitante.getId());
			
			/* Faz a atribuição do select para a 
			   			variável rs */
			ResultSet rs = stmt.executeQuery();		
						
			/* executa o select p/ ver se há no banco de 
			   dados, tal registro*/			
			if (rs.next()) {			
				nomeVisitante.setId(rs.getLong("ps_id"));
				nomeVisitante.setNome(rs.getString("ps_nome"));
				nomeVisitante.setRg(rs.getString("ps_rg"));
						
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
		return nomeVisitante;
	}
	
	

	 public Visitante getVisitanteByRg2(String rg)
	 {	                  
	            
	        Visitante visitante = new Visitante();
	        Empresa empresa = new Empresa();
	        
        	String sql = "SELECT " +
        		" pessoa.ps_id, pessoa.ps_rg, pessoa.ps_nome, pessoa.ps_data_nasc," +
        		" visitante.ps_id, visitante.vs_telefone, visitante.emp_id, " +
        		" empresa.emp_nome, empresa.emp_id " +
        		" FROM [ControleAcesso].[dbo].[tb_visitante] AS visitante " +
        		" INNER JOIN " +
                " [ControleAcesso].[dbo].[tb_pessoa] AS pessoa " +
                " ON visitante.ps_id = pessoa.ps_id " +
                " LEFT JOIN " +
                " [ControleAcesso].[dbo].[tb_empresa] AS empresa " +
                " ON visitante.emp_id = empresa.emp_id " +
                " WHERE pessoa.ps_rg=?";
	        		        	
	        try {	
	        	PreparedStatement stmt = connection.prepareStatement(sql);             
	            stmt.setString(1, rg);  
	            ResultSet rs = stmt.executeQuery();  
	            
	            if(rs.next()) {            	
	            	
	            	
			            visitante.setId(rs.getLong("ps_id"));
			            visitante.setTelefone(rs.getLong("vs_telefone"));
			            visitante.setNome(rs.getString("ps_nome"));
			            visitante.setRg(rs.getString("ps_rg"));
			            
			            empresa.setId(rs.getLong("emp_id"));
			            empresa.setNome(rs.getString("emp_nome"));
			            visitante.setEmpresa(empresa);
			            visitante.getEmpresa().getNome();
			            visitante.getEmpresa().getId();
			            
			            Calendar data = Calendar.getInstance();  
			            data.setTime(rs.getDate("ps_data_nasc"));  
			            visitante.setDataNasc(data);           
	            }
	            	rs.close();  
	            	stmt.close();  
	            	return visitante;  
	            } catch (SQLException e){  
	            throw new RuntimeException(e);  
	        }  
	    }  
	
public Visitante getIdEmpresaByIdVisitante(Visitante nomeVisitante) throws Exception {	
		
		String strSql = "SELECT Visitante.ps_id, Visitante.emp_id" +
			" FROM [ControleAcesso].[dbo].[tb_visitante] AS Visitante" +			
			" WHERE Visitante.ps_id = ?";
		
		try {	 
			PreparedStatement stmt = this.connection.prepareStatement(strSql);
			
			/* stmt = select acima, porém ao invés da
			   interrogação, pegando o valor passado
			   pela VisitaDAO */
			stmt.setLong(1, nomeVisitante.getId());
			
			/* Faz a atribuição do select para a 
			   			variável rs */
			ResultSet rs = stmt.executeQuery();		
						
			/* executa o select p/ ver se há no banco de 
			   dados, tal registro*/			
			if (rs.next()) {			
				Empresa empresa = new Empresa(); 
				nomeVisitante.setEmpresa(empresa);
						
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
		return nomeVisitante;
	}

	public Visitante getVisitanteById(Long id){  
	    try {             
	        
	    	Visitante visitante = new Visitante();            
	        
	    	String sql = "SELECT pessoa.ps_id, pessoa.ps_rg, pessoa.ps_nome, pessoa.ps_data_nasc, " +
	        		" visitante.ps_id " +
	        		" FROM [ControleAcesso].[dbo].[tb_pessoa] " +
	        		" AS pessoa" +            		            		
	        		" INNER JOIN [ControleAcesso].[dbo].[tb_visitante]" +            		
	        		" AS visitante " +
	        		" ON pessoa.ps_id = visitante.ps_id" +
	        		" WHERE visitante.ps_id = ?"; 
	
	    	
	        PreparedStatement stmt = connection.prepareStatement(sql);             
	        stmt.setLong(1, id);  
	        ResultSet rs = stmt.executeQuery();  
	        
	        if(rs.next()) {
	        	
	        	visitante.setId(rs.getLong("ps_id"));
	        	visitante.setNome(rs.getString("ps_nome"));
	        //	funcionario.setCargo(rs.getLong("cargo"));	            
	        	visitante.setRg(rs.getString("ps_rg"));
	        	            	
	            
	            Calendar dataNasc = Calendar.getInstance();  
	            dataNasc.setTime(rs.getDate("ps_data_nasc"));  
	            visitante.setDataNasc(dataNasc);	               
	        	                    
	        }  
	        
	        rs.close();  
	        stmt.close();  
	        return visitante;  
	    } catch (SQLException e){  
	        throw new RuntimeException(e);  
	    }  
	}
	
}

