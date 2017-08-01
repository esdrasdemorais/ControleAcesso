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
	font-size: 210%;
	font-weight:bolder;
	text-decoration:underline;
	color: #003300
}

.registro_cadastros {
	margin-left: 40px;
	font-family: Arial;
	font-size: 100%;
	font-weight:bolder;	
}
	
</style>
<!-- Fim do CSS  -->

	
	</head>  
	<body bgcolor = "#BFEFFF">
		<br>		 		    
	   <form action = "controladora" name = "controller" method = "POST">  	    		
	    	<table border="0" class = "cabecalho">  		      
			    <tr>  
			        <td>
			        	<img src = "/ControleAcesso/logo_proguaru.jpg" class = "imagem">
			        </td>
			    	<td>
			    	 <h1> Controle de acesso </h1>
			    	</td>	
			    </tr>
			</table>        	
        	<p>
        	<div class = "registro_cadastros"> Por favor, digite o nome da empresa que deseja cadastrar:  </div> 				
				
				
				<c:if test = "${empresa.nome != null}">	
				<!--  Aqui está a grande jogada -->
				<!--  Código que busca a última empresa -->
				<%
		   			EmpresaDAO empresaDAO = new EmpresaDAO();
		   			Empresa empresa = new Empresa();
		   			
		   			empresa = empresaDAO.getUltima();		   					   			
		   		%>
		   		<input type = "hidden" name = "num_empresa" size = "80"  value = "<%=empresa.getId()%>">  		
				 <input type = "hidden" name = "ultimo_nome_empresa" size = "80" value = "<%=empresa.getNome()%>">
					<script language = "javascript">						
						var empresa = document.controller.ultimo_nome_empresa.value;
						var idEmpresa = document.controller.num_empresa.value;											
			   			window.opener.addOptionCombo(empresa,idEmpresa);			   			
			   			window.close();			   			
					</script>	
				</c:if>	
				
				 <input type = "text" name = "nome_empresa" size = "80"> <p>								
				
				<input type= "hidden" name = "logica" value = "AdicionaEmpresaServlet"/>
		   			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		   			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;		   				   				   		
		   		<input type = "submit" value = "Cadastrar Empresa" /> <p> 		   		
		   	<!-- 	<input type = "button" value = "Fechar" onclick = "atualizarComboEmpresa()" /> <p>  --> 
	  		    		      	    	  
	    </form>  		      
	</body>  
</html>  