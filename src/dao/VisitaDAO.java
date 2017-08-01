package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.proguaru.jdbc.ConnectionFactory;

import modelo.Cracha;
import modelo.Empresa;
import modelo.Funcionario;
import modelo.Local;
import modelo.Motivo;
import modelo.Pessoa;
import modelo.Portaria;
import modelo.Setor;
import modelo.Unidade;
import modelo.Usuario;
import modelo.Visita;
import modelo.Visitante;
import modelo.TipoCracha;

public class VisitaDAO {

	// a conexão com o banco de dados
	private Connection connection;
	
	public VisitaDAO() throws Exception {
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public List<Visita> getVisitasByDay() throws Exception
	{
	    List<Visita> visitas = new ArrayList<Visita>();
	    
	    String strSql = "set dateformat dmY;" +
	    			"select Visita.vs_id, Visita.vs_data, Visita.vs_data_saida, " +
	    			 "	Visitante.vs_telefone, Visitante.emp_id " +
	    			 "from [ControleAcesso].[dbo].tb_visita as Visita " +
	    			 "inner join [ControleAcesso].[dbo].tb_visitante AS Visitante " +
	    			 "	on Visita.pfvs_id = Visitante.ps_id " +
	    			 "inner join [ControleAcesso].[dbo].[tb_pessoa] AS Pessoa " +
	    			 "	on Visita.pfvs_id = Pessoa.ps_id " +
	    			 " where Visita.vs_data = getDate()";
	    			
	    System.out.print(strSql);
	    
	    PreparedStatement stmt = this.connection.prepareStatement(strSql);
	    ResultSet rs = stmt.executeQuery();		  
  
	    while (rs.next()) {
	    	// criando o objeto Pessoa
	    	Visita visita = new Visita();
	    	
	    	visita.setId(rs.getInt("vs_id"));
	    	
	    	//visita.setVisitante(new VisitanteDAO().getVisitanteById(visita.getId());
	    	VisitanteDAO visitanteDAO = new VisitanteDAO();
	    //	visita.setVisitante(visitanteDAO.getVisitanteById(visita.getId()));
	    	
	    	
	    	// adicionando o objeto à lista
	    	visitas.add(visita);
	    } 	
	    rs.close(); 	  
	    stmt.close();
	    
	    return visitas;
	}
	
	public List<Visita> getLista() throws Exception {
		  try {
			  List<Visita> visitas = new ArrayList<Visita>();
			  PreparedStatement stmt = this.connection.prepareStatement("SELECT * " +					  
					  " FROM [ControleAcesso].[dbo].[tb_visita] AS visita " +					  
					  " WHERE visita.vs_status = 'A' ");			  	
			  
			  ResultSet rs = stmt.executeQuery();		  
			  
			  while (rs.next()) {
	      
				 Visita visita = new Visita();				 
				 visita.setId(rs.getInt("vs_id"));
				 
				 // setando o Setor
				 Setor setor = new Setor();
				 setor.setId(rs.getLong("st_id"));
							        
				SetorDAO setorDAO = new SetorDAO();
				setorDAO.getSetorByNome(setor);
				setor = setorDAO.getSetorByNome(setor);
					
				// setando o Local
				/* Local local = new Local();
				 local.setId(rs.getLong("loc_id"));
				 
				 LocalDAO localDAO = new LocalDAO();
				 localDAO.getLocalByNome(local);
				 local = localDAO.getLocalByNome(local);		*/		 
				 
				// setando o Cracha
				 Cracha cracha = new Cracha();
				 cracha.setId(rs.getLong("cr_id"));
				 
				 CrachaDAO crachaDAO = new CrachaDAO();
				 crachaDAO.getCracha(cracha);
				 cracha =  crachaDAO.getCracha(cracha);
				 
				// setando o Motivo
				 Motivo motivo = new Motivo();
				 motivo.setId(rs.getLong("mt_id"));
				 
				 MotivoDAO motivoDAO = new MotivoDAO();
				 motivoDAO.getMotivoByNome(motivo);
				 motivo = motivoDAO.getMotivoByNome(motivo);
				 
				// setando a data			 							
				 
				// Date dataEntrada = new Date(0);
				// SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss"); 
				 	
				 // ***********************************************				 
				 
				try {
					SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
					Date dataVisita =  rs.getDate("vs_data");
					Time horaVisita = rs.getTime("vs_data");
					
					visita.setDataEntrada(dataVisita);
					visita.setHoraEntrada(horaVisita);
				} catch (Exception e) {
					System.out.println("Erro de conversão da data");
					 //para a execução do método
				}
												 
							 
				 if (cracha.getTipoCracha() == 1)  {	
				 
					 Funcionario funcionario = new Funcionario();					 
					 funcionario.setId(rs.getLong("pfvs_id"));
					 	
					 FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
					 funcionarioDAO.getFuncionarioByVisitas(funcionario);
					 funcionario = funcionarioDAO.getFuncionarioByVisitas(funcionario);
					 visita.setFuncionario(funcionario);
					 
				 }
				 else {
						
					 Visitante visitante = new Visitante();					 
					 visitante.setId(rs.getLong("pfvs_id"));					 	
					 
					 VisitanteDAO visitanteDAO = new VisitanteDAO();
					 visitanteDAO.getVisitanteByVisitas(visitante);
					 visitante = visitanteDAO.getVisitanteByVisitas(visitante);
					 visita.setVisitante(visitante);

				}

				 visita.setSetor(setor);
			//	 visita.setLocal(local);	
				 visita.setCracha(cracha);	 
				 visita.setMotivo(motivo);
				 
			     // adicionando o objeto à lista
				  visitas.add(visita);
			  } 
			  rs.close(); 	  
			  stmt.close();
			  return visitas;
		
		  } catch (SQLException e) {
			  throw new RuntimeException(e);
		  }		
	}	
	
	public Visita getCrachaVisitas(Long idCracha) throws Exception {
		  try {
			  Visita aux_visita = new Visita();
			  
			  String sql = "SELECT * " +					  
					  " FROM [ControleAcesso].[dbo].[tb_visita] AS visita " +					  
					  " WHERE visita.cr_id = ? AND " +
					  " visita.vs_status = 'A'";
			  
			  PreparedStatement stmt = this.connection.prepareStatement(sql);
			  stmt.setLong(1,idCracha);
			  ResultSet rs = stmt.executeQuery();			  			  	            
	            
			  if (rs.next()) {	      

				  Cracha cracha = new Cracha();
				  cracha.setId(rs.getLong("cr_id"));				  
				  aux_visita.setCracha(cracha);
			   } 
			  else
			  {
				  aux_visita = null;				  
			  }
			  	  			  	  
			  	  rs.close(); 	  
				  stmt.close();				  
				  return aux_visita;
				  
		  } catch (SQLException e) {
			  throw new RuntimeException(e);
		  }		
	}
	
	public Visita adiciona(Visita visita) {
		
		String sql = "insert into [ControleAcesso].[dbo].[tb_visita] (pfvs_id, cr_id, st_id, mt_id, vs_status) values (?,?,?,?,?)";
		try {
						
			Funcionario funcionario = new Funcionario();
			// prepared statement para inserção
		    PreparedStatement stmt = connection.prepareStatement(sql);				    

		    if (visita.getFuncionario() != null) {
		    	// seta os valores	
			    stmt.setLong(1, visita.getFuncionario().getId());
			    stmt.setLong(2, visita.getCracha().getId());
			    stmt.setLong(3, visita.getSetor().getId());
			    stmt.setLong(4, visita.getMotivo().getId());
			 //   stmt.setLong(4, visita.getLocal().getId());			    
				stmt.setString(5, "A");
		    }	
		    else { 
		    	   	// seta os valores	
				    stmt.setLong(1, visita.getVisitante().getId());
				    stmt.setLong(2,visita.getCracha().getId());
				    stmt.setLong(3, visita.getSetor().getId());
				    stmt.setString(4, null);
				    //	    stmt.setLong(4, visita.getLocal().getId());	
					stmt.setString(5, "A");
		    }
			
		    // executa
			stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
			}
			return visita;
	}
	
	public Visita altera(Visita visita) {
		 String strSQL = "UPDATE [ControleAcesso].[dbo].[tb_visita] SET vs_status = 'E', vs_data_saida = GETDATE() where vs_id=?";
		
		 try {
			 PreparedStatement stmt = connection.prepareStatement(strSQL);
			 stmt.setLong(1, visita.getId());			 
			 stmt.execute();
			 stmt.close();
		 } catch (SQLException e) {
		 throw new RuntimeException(e);
		 }
		 return visita;	
	}
	
	public Visita deleta(Visita visita) {
		try {
			 PreparedStatement stmt = connection.prepareStatement("delete from [ControleAcesso].[dbo].[tb_visita] where pfvs_id=?");
			 
			 stmt.setLong(1, visita.getId());
			 
			 //deleta o registro
			 stmt.execute();
			 stmt.close();
			 
			 } catch (SQLException e) {
				 throw new RuntimeException(e);
		     }
			 return visita;
		}
			
}	

