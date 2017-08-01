<!doctype html public "-//w3c//dtd html 4.0 transitional//en">

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page import = "java.util.*, modelo.Pessoa, modelo.Empresa, dao.ContatoDAO, dao.VisitaDAO, dao.EmpresaDAO, modelo.Visita, modelo.Visitante" %>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>

<html>
	<head>  
		<title>SISTEMA DE CONTROLE DE ACESSO</title>

<!--  Início do código JavaScript -->		

<script language = "javascript">

function atualizarComboEmpresa()
{
    var empresa = document.controller.nome_empresa.value;
    var idEmpresa = document.controller.num_empresa.value;
	window.opener.refreshCombo(empresa,idEmpresa);
	window.close();
	return false;
    
}	

</script>

<!--  Fim do código JavaScript -->


<!--  Início do código CSS -->
<style type="text/css"> 

.imagem {	
	float:left;
	padding-left:0px;
}

.cabecalho {	
	margin-left: 40px;
}

h1 {
	font-style: italic;
	font-family: Arial;
	font-size: 150%;
	font-weight:bolder;
	text-decoration:underline;
	color: #003300
}

.texto {
	margin-left: 40px;
	font-family: Times;
	font-size: 110%;
	font-weight:bolder;	
	color: #0000CD;	
}

.cabecalho {
	margin-top: 0;
	/*/margin-left: 455px;*/
	margin-bottom: 0px;
}
	
</style>
<!-- Fim do CSS  -->

	
	</head>  
	<body bgcolor = "#BFEFFF">
		<br>		 		 
		
		<c:choose>	
			<c:when test = "${!empty usuario.senha}">
				<center>
					<div class = "texto"> A senha foi alterada com sucesso!!! </div> <p>
					<input type = "button" onclick = "javascript:window.close()" value = "Fechar" />
				</center>
			</c:when>
		  	<c:otherwise>
			   <form action = "controladora" name = "controller" method = "POST">  	    		
			
			        <table class = "texto">
			        	<tr align = "center">
				        	<td colspan = "2"><b><u> Deseja cadastrar uma nova senha? </u></b></td>
				        </tr>	
				        <tr>
				        	<td colspan = "2"> &nbsp; </td>
				        <tr>
				        	<td><b> Por favor, digite seu DRT: </b></td>
				        	<td><input type = "text" name = "drt" size = "20" /> </td>        	
						</tr>
						<tr>
				        	<td colspan = "2"> &nbsp; </td>
				        <tr>
					        <td><b> Digite a nova senha: </b></td>
					        <td><input type = "password" name = "senha" size = "20" /> </td>
				   		</tr>
				   		<tr>
				        	<td colspan = "2"> <input type= "hidden" name = "logica" value = "AlteraSenhaServlet"/> </td>
				        </tr>
				   		<tr>   		
					   		<td colspan = "2"> &nbsp; </td>
			   			</tr>
			   			<tr>   		
					   		<td colspan = "2" align = "center"> <input type = "submit" value = "Alterar Senha" /> </td>
			   			</tr>
			   		</table>				   				    		      	    	  
			    </form> 
			</c:otherwise>    
		</c:choose>	 		      
	</body>  
</html>  