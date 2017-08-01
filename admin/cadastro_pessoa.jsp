<!doctype html public "-//w3c//dtd html 4.0 transitional//en">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>		
<body background ="backgr1.JPG">
<head>
<title>Digite os dados da pessoa</title>

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.3/jquery.min.js" type="text/javascript">

<!-- CSS -->
<style type="text/css"> 

p.caixa1 {
	
	text-indent: 420 px;
}

p.caixa2 {
	
	margin-left: 100 px;
}


p.caixa3 {
	
	margin-left: 60 px;
}

p.caixa {
	text-indent: 290px;
}


</style>
<!-- Fim do CSS -->


<!-- Funcão JavaScript  -->
<script language = "javascript">

function tipoPessoa() {
	  if (document.getElementById('municipe').checked) {
	   document.getElementById('empresa').style.display = "none";    
	 }
	else {
		document.getElementById('empresa').style.display = ""; 
	}
}
</script>

<script language = "javascript">

$(document).ready(function() {
	$("input[type=submit]").click(function(event) {
		$("#resultado").load('visitante_incluido.jsp');
	});
});
</script>

</head>
<body style = "background-image:url(verde_claro.jpg)">

	<center>
	<img src='logo_proguaru.gif' width='300'><br><br><br><br>
</center>	
		
		<c:choose>
			<c:when test = "${!empty visitante.rg or !empty funcionario.cpf}">
				<center><b><c:out value =" Não há esse funcionário na base de dados. Por favor, tente novamente."/> </b></center><p>
				<center><b> 
				<form id = 'form1' name = 'form1' method = 'post'> 
				<input type = 'button' name = 'button' id = 'button' value = 'Voltar'  
			    onclick = "javascript:history.back(); "/></b></center> 
			    </form>	
	<!-- 	    <input type = "hidden" name = "id" value="${pessoa.id}">  -->		    	
				
			</c:when>
			<c:otherwise>
				<center><b><c:out value =" Não há essa pessoa na base de dados. Por favor, preencha os dados do Visitante."/></b></center>				
				<input type = "hidden" name = "id" value="${visitante.id}"><p>
					
				<center>				
				<form action = "controladora" method = "POST">

					<!-- Id: --> 
					<input type = "hidden" name = "id"><p>
			
					Selecione:<br>  
					Munícipe <input type = "radio"  name = "escolherPessoa" value = "0"  id = "municipe" checked = "checksed"  onClick = "tipoPessoa()">&nbsp;&nbsp;
					Fornecedor	<input type = "radio"  name = "escolherPessoa"  value = "1" id = "fornecedor"  onClick = "tipoPessoa()"><br><br>	
	
		
					Nome: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type = "text" name = "nome"><p>
					
					RG: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type = "text" name = "rg" value = "${param.rg}" ><p>
						
					Data de Nascimento: 
					<input type = "text" name = "data_nasc"><p>	
					
					Telefone: 
					<input type = "text" name = "telefone"><p>	
	

					<label id = "empresa" style = "display:none">
  
					     	<!-- cria o combo Empresa -->
						<jsp:useBean id="empDAO" class="dao.EmpresaDAO" />
							Empresa:
							<select name="empresa">  
					        	<option value="0">Selecione a empresa</option>  
					      			<c:forEach var="empresa" items="${empDAO.lista}">  
					          			<option value="${empresa.id}">${empresa.nome}</option>  
					      			</c:forEach>  
					   		 </select> 
				
			   			 <p>	
					</label>
				<!-- 	<p><input type="button" value="Carregar conteúdo" /></p>   -->
					
					<input type= "hidden" name = "logica" value = "AdicionaVisitanteServlet"/>
					<input type = "submit" name = "cadastro" value = "Cadastrar">		
					<div id = "resultado"></div>

				</form>
				</center>	
					
					
			</c:otherwise>
		</c:choose>
<p>
	
</body>
</html>	