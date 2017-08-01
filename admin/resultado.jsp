<!doctype html public "-//w3c//dtd html 4.0 transitional//en">

<%@page import="modelo.Pessoa"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
Date dataEmDate = new Date();
SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss"); 
%>

<html>
<title>Dados da pessoa</title>

<style type="text/css"> 

p.titulo_tabela {
	font-family: Arial;
	font-size: 110%;
	font-weight:bolder;
	text-indent: 50px;

</style>
 
<body style = "background-image:url(verde_claro.jpg)">
<!-- Contato ${param.nome} adicionado com sucesso -->
<center>
 
<img  src="logo_proguaru.gif">
	<p>	
		<!-- Funcionário ou Visitante -->
		<c:choose>
			<c:when test = "${!empty funcionario.drt or !empty funcionario.cpf}">
	  			<table border="0">	
		  			<tr>
		  			  <td align = "left">
		  				<img  src="Fotos_JPG/${funcionario.foto}-00.jpg" width="100" />  
					  </td>
					  	<td> <br> 
					  		<b> <c:out value=" Nome: ${funcionario.nome}"/></b> <br>			  
			  			  	<b> <c:out value=" DRT: ${funcionario.drt}"/></b> <br>
						   	<b> <c:out value=" CPF: ${funcionario.cpf}"/> 
				 			<!--    <input type = "hidden" name = "id" value="${pessoa.id}"> -->
			 		  	</td>
			 		</tr>
		 		</table> 
			</c:when>
			<c:otherwise>
				<b> <c:out value =" Nome: ${visitante.nome}"/> </b><br>
				<b> <c:out value =" RG: ${visitante.rg}"/> </b><br>
				<b> <c:out value =" Telefone: ${visitante.telefone}"/> </b><br><br><br>				
  		<!-- 	<input type = "hidden" name = "id" value="${visitante.id}"><p> --> 
			</c:otherwise>
		</c:choose>

<br><br>

<form action = "controladora" method = "POST">		
<c:choose>
	<c:when test = "${!empty funcionario.drt or !empty funcionario.cpf}">
		<input type = "hidden" name = "id" value="${funcionario.id}"> 
	</c:when>
	<c:otherwise>
		<input type = "hidden" name = "id" value="${visitante.id}">	
	</c:otherwise>
</c:choose>		


	&nbsp;&nbsp;&nbsp;&nbsp; Horário de Entrada: <input type="text" name="dtEntrada" value="<%=formato.format(dataEmDate)%>" readonly="readonly"><p>
	<!-- &nbsp;&nbsp;&nbsp;&nbsp; Data de saída: <input type="text" name="dtSaida"><p>-->

	<!-- Funcionário ou Visitante -->
		 	&nbsp;&nbsp;&nbsp;&nbsp; Tipo de crachá:
		<c:choose>
			<c:when test = "${!empty funcionario.drt}">
				<input type = "text" name = "tpCracha" value = "Serviço" readonly = "readonly" id = "funcionario"><p> 
			<input type = "hidden" name = "id" value="${funcionario.id}"> 
			</c:when>
			<c:otherwise>
				<input type = "text" name = "tpCracha" value = "Visitante" readonly = "readonly" id = "visitante"><p>	
	  	<input type = "hidden" name = "id" value="${visitante.id}"> 
			</c:otherwise>
		</c:choose> 
		
		<!-- Verifica se há empresa associada ao visitante.
		Se tiver, mostra o nome da empresa -->
		<c:choose>
			<c:when test = "${!empty visitante.empresa.nome}">
				&nbsp;&nbsp;&nbsp;&nbsp; Empresa: 
				<input type = "text" name = "empresa" size = "50" value = "${visitante.empresa.nome}" readonly = "readonly"><br><br>
		<!-- 	<input type = "text" name = "empresaid" size = "50" value = "${visitante.empresa.id}" readonly = "readonly"><br><br>   -->
			</c:when>			
		</c:choose> 	
 	
 			<!-- cria o combo Crachá -->
		<!--<jsp:useBean id="tpCrachaDAO" class="dao.tipoCrachaDAO" />
			<select name="cracha">  
	        	<option value="0">Selecione o crachá</option>  
	      			<c:forEach var="cracha" items="${tpCrachaDAO.lista}">  
	          			<option value="${cracha.id}">${cracha.tipo}</option>  
	      			</c:forEach>  
	    	</select>  <p>   -->
 

	&nbsp;&nbsp;&nbsp;&nbsp; Setor: <!-- cria o combo Empresa -->
		<jsp:useBean id="setDAO" class="dao.SetorDAO" />
			<select name="setor">  
	        	<option value="0">Selecione o setor</option>  
	      			<c:forEach var="setor" items="${setDAO.lista}">  
	          			<option value="${setor.id}">${setor.nome}</option>  
	      			</c:forEach>  
	    	</select>  <p> 
	    	
	&nbsp;&nbsp;&nbsp;&nbsp; Local: <!-- cria o combo Local -->
		<jsp:useBean id = "locDAO" class = "dao.LocalDAO" />
			<select name = "local">  
	        	<option value="0">Selecione o local</option>  
	      			<c:forEach var = "local" items = "${locDAO.lista}">  
	          			<option value = "${local.id}">${local.nome}</option>  
	      			</c:forEach>  
	    	</select> <p>
	    		
		<input type= "hidden" name = "logica" value = "AdicionaVisitaServlet"/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	   	<input type = "submit" value = "Cadastrar Visita"/>

</form>
</center>
</body>
</html>


