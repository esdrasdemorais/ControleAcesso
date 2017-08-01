<!doctype html public "-//w3c//dtd html 4.0 transitional//en">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
	<head>
		<title>SISTEMA DE CONTROLE DE ACESSO</title>

<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/jquery.init.js"></script>

<link rel="stylesheet" type="text/css" href="estilo.css" />
     

<!-- Início do Código JavaScript  -->
		
<script language="JavaScript" type="text/JavaScript">

function Validate() {
	var user = document.autentica.nome;
	var pass = document.autentica.senha; 

	if ((user.value == null) || (user.value == "")){
		alert("Por favor, digite seu Nome de Usuário");
		user.focus();
		return false;
	}
	if ((pass.value == null) || (pass.value == "")){
		alert("Por favor, digite sua Senha");
		pass.focus();
		return false;
	}
		return true;
}

function load() {
	//Script courtesy of http://www.web-source.net - Your Guide to Professional Web Site Design and Development
	var load = window.open('frmAlteraSenha.jsp','Pagina','width=430,height=225,resizable=no,scrollbars=no,menubar=no,toolbar=no,status=yes,location=no,left=500,top=280');
	}


</script>
</script>
<!--  Fim do código JavaScript -->

<style type = "text/css">

a {
	color: #0000FF;
}

</style>

</head>	

<body> 
<center>
<div id = "container">
	<div id = "header">
		<c:import url="admin/cabecalho.jsp" />
	</div>

	<c:if test = "${!empty usuario.nomeUsuario}">
		<script language="JavaScript" type="text/JavaScript">													
			alert("Usuário inválido. Por favor, digite corretamente o nome de usuário e/ou senha!!!");
		</script>		
	</c:if>
	
			
		  <form action = "controladora" name = "autentica" method = "POST" onSubmit = "return Validate()" >			
				<table>
					<tr> 
			      		<td>		      	      
						     <table class = "tabelaLogin">
							    <tr>  
							    	<td>&nbsp;</td>
							    	<td>&nbsp;</td>
							    </tr>
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
										<input type= "hidden" name = "logica" value = "LoginPessoaServlet"/>												   										   		  
									    <input type="submit"  name="signIn" id="signIn" value="Login">
								  	</td>
								</tr>
								<tr>
									<td class = "gaia le fpwd" valign="bottom" height="33.0" align="center" colspan="2">
										<a href = "javascript:load()"> Esqueceu a senha? </a>
									</td>	
								</tr>					  
							 </table>	 		      								  
				    	</td>
				    </tr>	
		    	</table> 
  	  	  </form>
</div>	
</center>

	<script language="JavaScript" type="text/JavaScript">
		document.autentica.nome.focus();
	</script>	

</body>

</html>