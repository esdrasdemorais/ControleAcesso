<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@page import="modelo.Usuario"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page import = "java.util.*, modelo.Pessoa, modelo.Empresa, dao.ContatoDAO, dao.VisitaDAO, dao.EmpresaDAO, modelo.Visita, modelo.Visitante" %>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>


<html>
	<head>
		<title>SISTEMA DE CONTROLE DE ACESSO</title>

	<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="js/jquery.init.js"></script>
	<script type="text/javascript" src="js/frmInicial.js" ></script>
	
	<link rel="stylesheet" type="text/css" href="estilo.css" />

	</head>
	
	<body bgcolor="#BFEFFF" onLoad="tipoPessoa()"> <center>
	
	<div id="container">

		<div id="header" title="Controle de Acesso">
			<c:import url="cabecalho.jsp"/>	 	
		</div>
		
		<div class = "sair">		
			<a href = "javascript:document.logout.submit();">Sair</a>
		</div>
		
			<ol id = "abas">
		
			    <li><span><a href="#abaContent1">Visitante/Funcionário</a></span></li>			
		
				<c:if test="${usuario.admin != 'N'}">
					<li><span><a href="#abaContent2">Relatórios</a></span></li>	 
				</c:if>	
				
				<c:if test="${usuario.admin != 'N'}">
					<li><span><a href="#abaContent3">Novo Usuário</a></span></li>	 
				</c:if>	
				
			</ol>
					
			<!--  **************************************** 1ª Aba ****************************************  -->
		
			<div class = "contents" id = "abaContent1">
				<c:import url = "aba1.jsp"/>	
			</div>		
    												
		
			<!--  **************************************** 2ª Aba ****************************************  -->
			
			<div class = "contents" id = "abaContent2">	
				<c:import url = "aba2.jsp"/> 	 		
			</div>					
				
				
			<!--  **************************************** 3ª Aba ****************************************  -->
			
			<div class = "contents" id = "abaContent3">	
				<c:import url = "aba3.jsp"/> 	 		
			</div>		
			
				
	</div>  <!-- Fecha a aba que representa Tudo -->
	
	
	
			<!--  **************************************** Rodapé ****************************************  -->
	
			<div id="footer">© 2011 - Desenvolvido e Mantido pela Equipe de Desenvolvimento da Proguaru</div>  

	
	
	<!--  ******** Código JavaScript ******** -->

	<script src = "js/activatables.js" type = "text/javascript"></script>

	<script type="text/javascript">
		activatables('page', ['abaContent1', 'abaContent2', 'abaContent3']);
	</script>

</center></body>
</html>
