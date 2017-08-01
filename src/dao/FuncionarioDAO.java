package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import br.com.proguaru.jdbc.ConnectionFactory;

import modelo.Situacao;
import modelo.Funcionario;
import modelo.Local;
import modelo.Pessoa;

public class FuncionarioDAO {

	private Connection connection;
	
	public FuncionarioDAO() throws Exception {
		this.connection = new ConnectionFactory().getConnection();
	}

	
	public Funcionario getFuncionarioByDRT(Integer drt) throws Exception{  
        try {             
            
        	Funcionario funcionario = new Funcionario();            
            
        	
        	String sql = "SELECT pessoa.ps_id, pessoa.ps_rg, pessoa.ps_nome, pessoa.ps_data_nasc, " +
    		" funcionario.fc_drt, funcionario.fc_cpf, " +
    		" afastamento.ps_id, afastamento.af_situacao " +
    		" FROM [ControleAcesso].[dbo].[tb_pessoa] " +
    		" AS pessoa" +            		            		
    		" INNER JOIN [ControleAcesso].[dbo].[tb_funcionario]" +            		
    		" AS funcionario " +
    		" ON pessoa.ps_id = funcionario.ps_id" +
    		" LEFT JOIN [ControleAcesso].[dbo].[tb_afastamento]" +            		
    		" AS afastamento " +
    		" ON funcionario.ps_id = afastamento.ps_id" +
    		" WHERE funcionario.fc_drt = ?";  
        	
            PreparedStatement stmt = connection.prepareStatement(sql);             
            stmt.setInt(1, drt);  
            ResultSet rs = stmt.executeQuery();  
            
            if(rs.next()) {
            	
            	Situacao situacao = new Situacao();
            	situacao.setId(rs.getLong("ps_id"));
            	situacao.setTipoSituacao(rs.getString("af_situacao"));
          //  	String situac = rs.getString("af_situacao");
            	
            	funcionario.setId(rs.getLong("ps_id"));
            	funcionario.setNome(rs.getString("ps_nome"));
            //	funcionario.setCargo(rs.getLong("cargo"));	            
            	funcionario.setDrt(rs.getInt("fc_drt"));
            	funcionario.setCpf(rs.getString("fc_cpf"));            	
	            funcionario.setSituacao(situacao);
	            Long sit = rs.getLong("ps_id");
            	
	            Calendar dataNasc = Calendar.getInstance();  
	            dataNasc.setTime(rs.getDate("ps_data_nasc"));  
	            funcionario.setDataNasc(dataNasc);   	            	              
            	                    
            }   
	        else {
	        
	        	funcionario.setId(null);
	            funcionario.setNome(null);	            
	            funcionario.setDrt(null);
	            funcionario.setCpf(null);
	        }
            
            rs.close();  
            stmt.close();  
            return funcionario;  
        } catch (SQLException e){  
            throw new RuntimeException(e);  
        }  
    }
    
    public Funcionario getFuncionarioByCPF(String cpf) throws Exception{
        try {             
            
        	Funcionario funcionario = new Funcionario();
        	
        	String sql = "SELECT pessoa.ps_id, pessoa.ps_rg, pessoa.ps_nome, pessoa.ps_data_nasc, " +
    		" funcionario.fc_drt, funcionario.fc_cpf, " +
    		" afastamento.ps_id, afastamento.af_situacao " +
    		" FROM [ControleAcesso].[dbo].[tb_pessoa] " +
    		" AS pessoa" +            		            		
    		" LEFT JOIN [ControleAcesso].[dbo].[tb_funcionario]" +            		
    		" AS funcionario " +
    		" ON pessoa.ps_id = funcionario.ps_id" +
    		" LEFT JOIN [ControleAcesso].[dbo].[tb_afastamento]" +            		
    		" AS afastamento " +
    		" ON pessoa.ps_id = afastamento.ps_id" +
    		" WHERE funcionario.fc_cpf = ?"; 

	
		    PreparedStatement stmt = connection.prepareStatement(sql);             
		    stmt.setString(1, cpf);  
		    ResultSet rs = stmt.executeQuery();  
		    
		    if(rs.next()) {
		    	
		    	Situacao situacao = new Situacao();
            	situacao.setTipoSituacao(rs.getString("af_situacao"));
	            String teste = situacao.getTipoSituacao();
            	
	            funcionario.setId(rs.getLong("ps_id"));
	            funcionario.setNome(rs.getString("ps_nome"));	            
	            funcionario.setDrt(rs.getInt("fc_drt"));
	            funcionario.setCpf(rs.getString("fc_cpf"));
	            
	            funcionario.setSituacao(situacao);     
	            
	            Calendar dataNasc = Calendar.getInstance();  
	            dataNasc.setTime(rs.getDate("ps_data_nasc"));  
	            funcionario.setDataNasc(dataNasc);            
            
            }  
            else {
            
            	funcionario.setId(null);
	            funcionario.setNome(null);	            
	            funcionario.setDrt(null);
	            funcionario.setCpf(null);
            }
            
            rs.close();  
            stmt.close();  
            return funcionario;  
        } catch (SQLException e){  
            throw new RuntimeException(e);  
        }  
    }
    
    public Funcionario getFuncionarioById(Long id){  
        try {             
            
        	Funcionario funcionario = new Funcionario();            
            
        	String sql = "SELECT pessoa.ps_id, pessoa.ps_rg, pessoa.ps_nome, pessoa.ps_data_nasc, " +
            		" funcionario.ps_id, funcionario.fc_drt, funcionario.fc_cpf, " +
            		" afastamento.ps_id, afastamento.af_situacao " +
            		" FROM [ControleAcesso].[dbo].[tb_pessoa] " +
            		" AS pessoa" +            		            		
            		" LEFT JOIN [ControleAcesso].[dbo].[tb_funcionario]" +            		
            		" AS funcionario " +
            		" ON pessoa.ps_id = funcionario.ps_id" +
            		" LEFT JOIN [ControleAcesso].[dbo].[tb_afastamento]" +            		
            		" AS afastamento " +
            		" ON funcionario.ps_id = afastamento.ps_id" +
            		" WHERE funcionario.ps_id = ?"; 

        	
            PreparedStatement stmt = connection.prepareStatement(sql);             
            stmt.setLong(1, id);  
            ResultSet rs = stmt.executeQuery();  
            
            if(rs.next()) {
            	
            	Situacao situacao = new Situacao();
            	
            	funcionario.setId(rs.getLong("ps_id"));
            	funcionario.setNome(rs.getString("ps_nome"));
            //	funcionario.setCargo(rs.getLong("cargo"));	            
            	funcionario.setDrt(rs.getInt("fc_drt"));
            	funcionario.setCpf(rs.getString("fc_cpf"));            	
	            situacao.setTipoSituacao(rs.getString("af_situacao"));
            	funcionario.setSituacao(situacao);
            	
	            Calendar dataNasc = Calendar.getInstance();  
	            dataNasc.setTime(rs.getDate("ps_data_nasc"));  
	            funcionario.setDataNasc(dataNasc);              
            	                    
            }  
            
            rs.close();  
            stmt.close();  
            return funcionario;  
        } catch (SQLException e){  
            throw new RuntimeException(e);  
        }  
    }
    
    public Funcionario getFuncionarioByVisitas(Funcionario funcionario) throws Exception {	
		
	String strSql = "SELECT" +
			" visita.pfvs_id, pessoa.ps_id, pessoa.ps_rg, pessoa.ps_nome, pessoa.ps_data_nasc, " +
			" funcionario.ps_id, funcionario.fc_drt, funcionario.fc_cpf, " +
			" afastamento.af_id, afastamento.ps_id, afastamento.af_situacao" +
			" FROM [ControleAcesso].[dbo].[tb_visita] AS visita " +
			" LEFT OUTER JOIN" +
			" [ControleAcesso].[dbo].[tb_pessoa] AS pessoa" +
			" ON visita.pfvs_id = pessoa.ps_id " +
			" LEFT OUTER JOIN " +
			" [ControleAcesso].[dbo].[tb_funcionario] AS funcionario" +
			" ON pessoa.ps_id = funcionario.ps_id " +
			" LEFT OUTER JOIN" +
			" [ControleAcesso].[dbo].[tb_afastamento] AS afastamento" +
			" ON funcionario.ps_id = afastamento.ps_id" +			
			" WHERE visita.pfvs_id = ?";			
		
		try {	 
			PreparedStatement stmt = this.connection.prepareStatement(strSql);
			
			/* stmt = select acima, porém ao invés da
			   interrogação, pegando o valor passado
			   pela VisitaDAO */
			stmt.setLong(1, funcionario.getId());
			
			/* Faz a atribuição do select para a 
			   			variável rs */
			ResultSet rs = stmt.executeQuery();		
						
			/* executa o select p/ ver se há no banco de 
			   dados, tal registro*/			
			if (rs.next()) {			
				
				funcionario.setId(rs.getLong("ps_id"));
				funcionario.setNome(rs.getString("ps_nome"));
				funcionario.setDrt(rs.getInt("fc_drt"));
				funcionario.setCpf(rs.getString("fc_cpf"));
				
				Situacao situacao = new Situacao();
				situacao.setTipoSituacao(rs.getString("af_situacao"));
				funcionario.setSituacao(situacao);						
			
			}
				else {
			 
	        	funcionario.setId(null);
	            funcionario.setNome(null);	            
	            funcionario.setDrt(null);
	            funcionario.setCpf(null);	        
	            funcionario.setSituacao(null);
			}
		
			rs.close();
			stmt.close();
		
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return funcionario;
	}
    
    public Funcionario getDRT(Integer drt) throws Exception{  
        try {             
     	
        	Funcionario funcionario = new Funcionario();
        	
        	String sql = "SELECT funcionario.ps_id, funcionario.fc_drt, " +
    		" usuario.ps_id " +
    		" FROM [ControleAcesso].[dbo].[tb_funcionario] " +
    		" AS funcionario" +            		            		
    		" INNER JOIN [ControleAcesso].[dbo].[tb_usuario]" +            		
    		" AS usuario " +
    		" ON funcionario.ps_id = usuario.ps_id" +    		
    		" WHERE funcionario.fc_drt = ?";  
        	
            PreparedStatement stmt = connection.prepareStatement(sql);             
            stmt.setInt(1, drt);  
            ResultSet rs = stmt.executeQuery();  
            
            if(rs.next()) {
            	funcionario.setDrt(rs.getInt("fc_drt"));                  
            }   
	        else {
	        
	        	throw new SQLException("Não foi encontrado este DRT.");
	        }
            
            rs.close();  
            stmt.close();  
            return funcionario;  
        } catch (SQLException e){  
            throw new RuntimeException(e);  
        }  
    }
    
}
