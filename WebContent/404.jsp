<!doctype html public "-//w3c//dtd html 4.0 transitional//en">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
	<head>
		<title>ERRO!</title>
	
		<style type = "text/css">
			
		.msgErro {
			font-weight:bolder;	
			text-align: center;
		}
			
		</style>

		<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="js/jquery.init.js"></script>
		<script type="text/javascript" src="js/frmInicial.js" ></script>
		
		<link rel="stylesheet" type="text/css" href="estilo.css" />

	</head>
	
	<body bgcolor="#BFEFFF">

		<div id="container">
	
			<div id="header" title="Controle de Acesso">
				<c:import url = "admin/cabecalho.jsp"/>	 	
			</div>
				
				<br><br>
				<div class = "msgErro">
					A página que você tentou acessar não existe!!!
				</div>
				<p>
				<center> <a href="javascript:history.back(1);">Voltar</a> </center>  
		</div>
	</body>
</html>