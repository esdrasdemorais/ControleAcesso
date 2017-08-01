<!doctype html public "-//w3c//dtd html 4.0 transitional//en">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
		<title>Registro incluído</title>
	</head>
	<body background = "verde_claro.jpg">
		<center><img src = "logo_proguaru.gif" width = "300"></center><br><br><br><br>
				
		<center><b> O registro foi incluído com sucesso!!! </b></center><p>
		<center><b> Nome:  ${visitante.nome}  <br> RG: ${visitante.rg} <br>    
				    Telefone:  ${visitante.telefone} </b></center>	<p>
				
				<center><b> 
					<form id = "form1" name = "form1" method = "post">
						<input type = "submit" name = "button" id = "button" value = "Voltar" 
			    		onclick = "form.action='frmInicial.jsp'; form.submit()"/>
			    	</form>
			    </b></center>
	</body>
</html>