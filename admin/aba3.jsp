<!doctype html public "-//w3c//dtd html 4.0 transitional//en">

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
	<head>
		<title>SISTEMA DE CONTROLE DE ACESSO</title>
	
		<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="js/jquery.init.js"></script>
		<script type="text/javascript" src="js/frmInicial.js" ></script>
		
		<link rel="stylesheet" type="text/css" href="estilo.css" />
	
	</head>	
	<body>
				
		
		 <form action = "controladora" name = "autentica" method = "POST">			
				<table class = "tabelaUsuario">
					<tr> 
			      		<td>		      	      
						     <table>
							    <tr>  
							    	<td>&nbsp;</td>
							    	<td>&nbsp;</td>
							    </tr>
							    <tr>	
							        <td> DRT: </td>  
							        <td><input type = "text" name = "drt" size = "30"></td>  
							    </tr>
							    <tr><td>&nbsp;</td></tr>
							    <tr>	
							        <td> Nome de Usuário: </td>  
							        <td><input type = "text" name = "nome" size = "30"></td>  
							    </tr>  
							    <tr><td>&nbsp;</td></tr>
							    <tr>  
							        <td><label> Senha: </label></td>  
							        <td><input type = "password" name = "senha"  size = "30"></td>  
							    </tr>      
							    <tr>
							        <td colspan="2" align="center" height="75">	    																		
										<input type= "hidden" name = "logica" value = "AdicionaUsuarioServlet"/>												   										   		  
									    <input type="submit" class="gaia le button" name="signIn" id="signIn" value="Cadastrar Usuário">
								  	</td>
								</tr>					  
							 </table>	 		      								  
				    	</td>
				    </tr>	
		    	</table> 
  	  	  </form>
	</body>
</html>