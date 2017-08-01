<!doctype html public "-//w3c//dtd html 4.0 transitional//en">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
	<head>  
		<title>SISTEMA DE CONTROLE DE ACESSO</title>
	</head>
	<body bgcolor = "#BFEFFF">
		<div class="cabecalho">
			<table>
				<tr>
					<td><img src = "logo_proguaru.jpg"></td>
					<td><h1>&nbsp;&nbsp;SISTEMA DE CONTROLE DE ACESSO</h1></td>
				</tr>
			</table>
		</div>
 
	    <c:if test = "${!empty usuario.nomeUsuario}">
			<center><h2><font color = "#003300">  O usuário foi adicionado com sucesso!!! </font></h2></center> 												
		<p> <center> <a href="javascript:history.back(1);">Voltar</a> </center>  
		</c:if>

		<br><br>
	    
	    <form action = "controladora" method = "POST">		
		    <table border = "0" align = "center">  
		      
			    <tr>  
			        <td><b><font size = "5"> DRT: </font></b></td>  
			        <td><input type = "text" name = "drt"></td>  
			    </tr>
			    
			    <tr>  
			        <td><b><font size = "5"> Nome de Usuário: </font></b></td>  
			        <td><input type = "text" name = "nome" size = "40"></td>  
			    </tr>  
			      
			    <tr>  
			        <td><b> <font size = "5"> Senha: </font></b></td>  
			        <td><input type = "password" name = "senha"></td>  
			    </tr>  
			    
			    <tr>   
			        <td>&nbsp;</td>  
			        <td>&nbsp;</td>  
			    </tr> 
	    	</table>
	        
	        <center>
		    	<input type= "hidden" name = "logica" value = "AdicionaUsuarioServlet"/>					
				<input type = "submit" value = "Cadastrar Usuario"/>	      		      
	   		</center>
	   </form>  
	</body>  
</html>  