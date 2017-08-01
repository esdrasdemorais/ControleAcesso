<!doctype html public "-//w3c//dtd html 4.0 transitional//en">

<%@page import="modelo.Usuario"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page import = "java.util.*, modelo.Pessoa, modelo.Empresa, dao.ContatoDAO, dao.VisitaDAO, dao.EmpresaDAO, modelo.Visita, modelo.Visitante" %>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>


<%
Date dataEmDate = new Date();
SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss"); 
%>

<%
Visita visita = new Visita();
HttpSession sessio = request.getSession();
String teste = sessio.getAttribute("usuario").toString();
int num = 2;
int nu = 3;
%>

<html>
<head>

<title>SISTEMA DE CONTROLE DE ACESSO</title>

<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/jquery.init.js"></script>
<script type="text/javascript" src="js/blixt.js" ></script>

<link rel="stylesheet" type="text/css" href="estilo.css" />
     
<!-- Funcão JavaScript  -->
<script language="JavaScript" type="text/JavaScript">

function tipoPessoa() {
	document.getElementById('tiau').style.display = "none";
	
	if ((!document.getElementById('funcionario').checked) && (!document.getElementById('visitante').checked)) {	   
		document.getElementById('pesquisaFuncionario').style.display = "";
		document.getElementById('pesquisaVisitante').style.display = "none"; 
	 }
	else {
		if (document.getElementById('funcionario').checked) {
			document.getElementById('pesquisaFuncionario').style.display = "";
			document.getElementById('pesquisaVisitante').style.display = "none";			
		}
		else {
			document.getElementById('pesquisaVisitante').style.display = "";
			document.getElementById('pesquisaFuncionario').style.display = "none";
		}
	}		
}

function MunicForn() {
	  if (document.getElementById('municipe').checked) {
	   document.getElementById('empresa').style.display = "none";    
	 }
	else {
		document.getElementById('empresa').style.display = ""; 
	}
}

function abrefecha(obj) {

	switch (obj.id) {
	
	case 'primeiro':

	document.getElementById('cad_empresa').style.display="block";
		document.getElementById('cad_setor').style.display="none";
		break;
		
	case 'segundo':

		document.getElementById('cad_empresa').style.display="none";
		document.getElementById('cad_setor').style.display="block";
		break;
		
	}
}


function tipoRelatorio(obj) {

	switch (obj.id) {
	case 'func':

		document.getElementById('rel_func').style.display="block";
		document.getElementById('rel_visit').style.display="none";
		break;
	
	case 'visit':

		document.getElementById('rel_func').style.display="none";
		document.getElementById('rel_visit').style.display="block";
		break;
	
	}
}

function refreshCombo(Text,Value)
{
	// Create an Option object        
    var opt = document.createElement("option");

    // Add an Option object to Drop Down/List Box
    document.getElementById("selecEmp").options.add(opt);

 // Assign text and value to Option object
    opt.text = Text;
    opt.value = Value;
    
}

function showBIRTReport() {
	
	  var Dataini = document.getElementById('periodo_inicial').value;
	  var Datafim = document.getElementById('periodo_final').value;
	  
	  if (document.getElementById('visit').checked) {	   		  
		  window.open("http://localhost:8090/birt-viewer/run?__report=Report\\RelVisit.rptdesign&data_inicial="+Dataini+"&data_final="+Datafim, '_blank');	 
	  }
		else {
			if (document.getElementById('funcion').checked) {
				window.open("http://localhost:8090/birt-viewer/run?__report=Report\\RelFunc.rptdesign&data_inicial="+Dataini+"&data_final="+Datafim, '_blank');
		
			}
			else {
				if (document.getElementById('todos').checked) {
					window.open("http://localhost:8090/birt-viewer/run?__report=Report\\RelTodos.rptdesign&data_inicial="+Dataini+"&data_final="+Datafim, '_blank');
				}		
			}
		}
	  
}
</script>
<!--  Fim do código JavaScript -->

<!--  Início do código css -->
<style type = "text/css">


a {
	color: #c75f3e;
}

#mytable {
	padding: 0;
	margin-left: 10px;
	border-top: 1px solid #C1DAD7;
	border-left: 1px solid #C1DAD7;
	border-right: 1px solid #C1DAD7;
	border-bottom: 1px solid #C1DAD7;
}

caption {
	padding: 0 0 5px 0;
	font: italic 11px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
	text-align: right;
}

th {
	font: bold 11px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
	color: #4f6b72;
	border-right: 1px solid #C1DAD7;
	border-bottom: 1px solid #C1DAD7;
	border-top: 1px solid #C1DAD7;
	letter-spacing: 2px;
	text-transform: uppercase;
	text-align: center;
	padding: 6px 6px 6px 12px;
	background: #CAE8EA url(images/bg_header.jpg) no-repeat;
}

th.nobg {
	border-top: 0;
	border-left: 0;
	border-right: 1px solid #C1DAD7;
	background: none;
}

th.spec {
	border-left: 1px solid #C1DAD7;
	border-top: 0;
	background: #fff url(images/bullet1.gif) no-repeat;
	font: bold 10px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
}

th.specalt {
	border-left: 1px solid #C1DAD7;
	border-top: 0;
	background: #f5fafa url(images/bullet2.gif) no-repeat;
	font: bold 10px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
	color: #797268;
}

#mytable td {
	font: 14px Times;
	color: #000000;	
	letter-spacing: 2px;	
	text-align: center;
	padding: 6px 6px 6px 12px;	
}

ol#abas {
    height: 2em;
    list-style: none;
    margin-top: 40px;
    padding: 0;
}

ol#abas li {
    float: left;
    margin: 0 1px 0 0;
}

ol#abas a {
    background: #bdf url(tabs.gif);
    color: #008;
    display: block;
    float: left;
    height: 2em;
    padding-left: 10px;
    text-decoration: none;
}

ol#abas a:hover {
    background-color: #3af;
    background-position: 0 -120px;
}

ol#abas a:hover span {
    background-position: 100% -120px;
}

ol#abas li.current a {
    background-color: #48f;
    background-position: 0 -60px;
    color: #fff;
    font-weight: bold;
}

ol#abas li.current span {
    background-position: 100% -60px;
}

ol#abas span {
    background: url(tabs.gif) 100% 0;
    display: block;
    line-height: 2em;
    padding-right: 10px;
}

div.contents { padding:5px 10px 10px; float:left; border:5px solid #fff;
	background:#fff; box-shadow:0 0 5px #999; -moz-box-shadow:0 0 5px #999;
	-webkit-box-shadow:0 0 5px #999; 
}

</style>
<!--  Fim do código css -->

</head>

<body bgcolor="#BFEFFF" onLoad="tipoPessoa()">

<div id="container">

	<div id="header" title="Controle de Acesso">
		<c:import url="cabecalho.jsp"/>	 	
	</div>
	
	<div id="contents">
		<ol id ="abas">
		
		    <li class="current"><span><a href="#abaContent1">Visitante/Funcionário</a></span></li>			
		
			<c:if test="${usuario.admin != 'N'}">
				<li><span><a href="#abaContent2">Relatório</a></span></li>	 
			</c:if>	
		</ol>
	
			
					<!--  **************************************** 1ª Aba ****************************************  -->
			
			<div class="contents" id = "abaContent1">
				
				<fieldset>
					<legend><h3>Busca de Funcionário / Visitante</h3></legend>
					
						<br><br>
					
					<!--  Foi pesquisado um Funcionário ou um Visitante -->
					<c:choose>	
						<c:when test = "${!empty funcionario.nome}">
							<center>
								<form name = "radios">
									<div class = "pessoa_escolhida">					
										<label for="funcionario">Funcionário </label><input type="radio" name="escolherPessoa" value="0" checked = "checked"  id = "funcionario"  onClick = "tipoPessoa()">
										<label for="visitante"> Visitante </label><input type="radio" name="escolherPessoa"  value="1"   id = "visitante"  onClick = "tipoPessoa()">
									</div>
								</form>
							</center>
						</c:when>
						<c:when test = "${!empty visitante.rg}">
							<center>
								<form name = "radios">
									<div class = "pessoa_escolhida">					
										<label for="funcionario">Funcionário </label><input type="radio" name="escolherPessoa" value="0"   id = "funcionario"  onClick = "tipoPessoa()">
										<label for="visitante"> Visitante </label><input type="radio" name="escolherPessoa"  value="1" checked ="checked" id = "visitante"  onClick = "tipoPessoa()">
									</div>		
								</form>
								<br><br>
							</center>
						</c:when>
						<c:when test = "${funcionario.drt == 0}">
							<center><b><c:out value =" Não há esse funcionário na base de dados. Por favor, tente novamente."/> </b></center><p>
							<center>
								<form name = "radios">
									<div class = "pessoa_escolhida">					
										<label for="funcionario">Funcionário </label><input type="radio" name="escolherPessoa" value="0"   id = "funcionario" onClick = "tipoPessoa()">
										<label for="visitante"> Visitante </label><input type="radio" name="escolherPessoa"  value="1"  id = "visitante"  onClick = "tipoPessoa()">
									</div>		
								</form>
								<br><br>
							</center>
						</c:when>
							<c:otherwise>
							<center>
								<form name = "radios">
									<div class = "pessoa_escolhida">					
										<label for="funcionario">Funcionário </label><input type="radio" name="escolherPessoa" value="0"   id = "funcionario"  onClick = "tipoPessoa()">
										<label for="visitante"> Visitante </label><input type="radio" name="escolherPessoa"  value="1"  id = "visitante"  onClick = "tipoPessoa()">
									</div>		
								</form>
								<br><br>
							</center>
							</c:otherwise>		
					</c:choose>
					
					<!-- ******************************** Início da label de Funcionário ******************************** -->	
					<label id = "pesquisaFuncionario">		
						<form action = "controladora" method = "POST" style = "text-align:center">		
							<p class = "aba1">
								<label>Pesquisar pelo DRT: </label>
								<input name = "drt" type = "text">&nbsp;&nbsp;
								<label>Pesquisar pelo CPF: </label>
								<input name = "cpf" type = "text">
								<br>
								<br>			
							</p>
								<input type = "hidden" name = "logica" value = "BuscaFuncionarioServlet"/>
								<input type = "submit" value = "Pesquisar"/>
							<!--  Se for feita a busca de um Funcionário  -->	
						</form>	
					
					<c:if test = "${!empty funcionario.nome}">					
							<table border="0" class = "tabFuncionarios">	
						  			<tr>
						  			  <td align = "left">
						  				<img  src="IMAGEM/${funcionario.foto}-00.bmp" width="100" height = "120" />  
								<!--  	  \\serverdb\database\DPessoal\IMAGEM  -->
									  </td>
									  	<td>
											<br>					
											&nbsp; <b> <c:out value=" Nome: ${funcionario.nome}"/></b> <br><br>			  
											&nbsp; <b> <c:out value=" DRT: ${funcionario.drt}"/></b> <br><br>
											&nbsp; <b> <c:out value=" CPF: ${funcionario.cpf}"/> </b> <br><br>
											&nbsp; <b> <c:out value=" Situação: ${funcionario.situacao.tipoSituacao}"/> </b> <br><br>									
				
											<c:if test = "${funcionario.situacao.tipoSituacao == '1-Ativo' and !empty funcionario.nome}">
												&nbsp; <b> <c:out value = " Motivo: Esquecimento do Crachá"/> </b> <br><br>
											</c:if>
											<c:if test = "${empty funcionario.situacao.tipoSituacao and !empty funcionario.nome}">
												 &nbsp; <b> <c:out value = " Motivo: Esquecimento do Crachá"/> </b> <br><br>    
											</c:if>	
										
											<form action = "controladora" method = "POST">				
												
												<c:if test = "${!empty funcionario.nome}">		
												
													<jsp:useBean id="agora" class="java.util.Date"/>		
													&nbsp; <b> <c:out value = " Horário de Entrada: "/>   
													<fmt:formatDate value="${agora}" pattern="dd/MM/yyyy HH:mm:ss"/> </b> <br><br>
																																
													<!-- Funcionário ou Visitante --> 
													<c:choose>
														<c:when test = "${funcionario.situacao.tipoSituacao != '1-Ativo' and !empty funcionario.situacao.tipoSituacao}">
															&nbsp; <b> <c:out value = " Tipo de crachá: Visitante"/> 
															    <input type= "hidden" name = "tpCracha" value = "Serviço"/>	
												  				<input type = "hidden" name = "id" value = "${funcionario.id}"> </b> <br><br> 												
														</c:when>
														<c:otherwise>
															&nbsp; <b> <c:out value = " Tipo de crachá: Serviço"/> 
																<input type = "hidden" name = "tpCracha" value = "Serviço"/>
																<input type = "hidden" name = "id" value = "${funcionario.id}"> </b> <br><br>
														</c:otherwise>	
													</c:choose> 
																
													&nbsp; <b> <c:out value = " Setor: "/> </b>  <!-- cria o combo Setor -->
														<jsp:useBean id="setDAO" class="dao.SetorDAO" />
															<select name="setor">  
													        	<option value="0">Selecione o setor</option>  
													      			<c:forEach var="setor" items="${setDAO.lista}">  
													          			<option value="${setor.id}">${setor.nome}</option>  
													      			</c:forEach>  
													    	</select>   
												    
													<br><br>
												
													&nbsp; <b> <c:out value = " Local: "/> </b>  <!-- cria o combo Local -->
														<jsp:useBean id = "locDAO" class = "dao.LocalDAO" />
															<select name = "local">  
													        	<option value="0">Selecione o local</option>  
													      			<c:forEach var = "local" items = "${locDAO.lista}">  
													          			<option value = "${local.id}">${local.nome}</option>  
													      			</c:forEach>  
													    	</select> 	
													    <br><br>							 					
												
														<input type= "hidden" name = "logica" value = "AdicionaVisitaServlet"/>					
												   			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												   		<input type = "submit" value = "Cadastrar Visita"/>
													<br><br>	 										
												</c:if>
											</form>
										</td>
									</tr>
							</table>			    		   					
					</c:if>			  	
				</label>
				<!-- ******************************** Fim da label de Funcionário ******************************** -->
				
				
				<!-- ******************************** Início da label de Visitante ******************************** -->
					<label id = "pesquisaVisitante" style = "display:none">
						<form action = "controladora" method = "POST"  name = "controller" style = "text-align:center">		
							<center><Label>Digite o RG:	<input name = "rg" type = "text">			
								<input type = "hidden" name = "logica" value = "BuscaVisitanteServlet"/>
								<input type = "submit" value = "Pesquisar"/>
							</font></center>	
						</form>			
						
							<c:if test = "${!empty visitante.rg and empty visitante.nome}">
								<br>
								<center><b><c:out value =" Não há essa pessoa na base de dados. Por favor, preencha os dados do Visitante."/></b></center>				
								<input type = "hidden" name = "id" value="${visitante.id}">
									
												<br>
								<form action = "controladora" method = "POST" name = "cadVisit">
				
									<!-- Id: --> 
									<input type = "hidden" name = "id"><br>
									
									<center><b><font face = "Arial">
										Por favor, selecione: &nbsp;  
										Munícipe <input type = "radio"  name = "escolherPessoa" value = "0"  id = "municipe" checked = "checked"  onClick = "MunicForn()">&nbsp;&nbsp;
										Fornecedor	<input type = "radio"  name = "escolherPessoa"  value = "1" id = "fornecedor"  onClick = "MunicForn()"><br><br>	
									</font></b></center>
									
									<table border="0" class = "tabVisitNEnc">	
										<tr>
											<td>								
												Nome: <input type = "text" name = "nome" size = "48"> &nbsp;&nbsp;	
												RG:   <input type = "text" name = "rg" value = "${param.rg}" size  = "14">  <br><br>
												Data de Nascimento: <input type = "text" name = "data_nasc"> &nbsp;&nbsp;	
												Telefone: 	<input type = "text" name = "telefone" size = "17">	<br><br>
				
												<!-- label Empresa -->
												<label id = "empresa" style = "display:none">
												     	<!-- cria o combo Empresa -->
													
											   		 <jsp:useBean id="empDAO" class="dao.EmpresaDAO" />
													Empresa:    
													<select name = "empresa" id = "selecEmp" onchange = "if(this.options[this.selectedIndex].value == 'a'){window.open('frmCadEmpresa.jsp','Pagina','width=530,height=225,resizable=no,scrollbars=no,menubar=no,toolbar=no,status=yes,location=no,left=500,top=280')}"  onfocus = "${empDAO.lista}">									
											        		<option value="0">Selecione a empresa</option>  
											      			<c:forEach var="empresa" items="${empDAO.lista}">  
											          			<option value="${empresa.id}">${empresa.nome}</option>									          			
											      			</c:forEach> 								      												
											      		<option value="a"> Nova empresa </option>  										      		
											   		 </select> <br><br>									   			 	
												</label>
											</td>
										</tr>
									</table>		
								
									<center>			
										<applet code="captura.CaptureWebCamApplet" ARCHIVE="CaptureWebCamAppletAssigned.jar" width="440" height="410">
										</applet>
									</center>
										
								<center>	
									<input type= "hidden" name = "logica" value = "AdicionaVisitanteServlet"/>
									<input type = "submit" name = "cadastro" value = "Cadastrar">		
								</center>
				
								</form>						
							</c:if>
					
						<c:if test = "${!empty visitante.nome}">	
									
							<table border="0" class = "tabVisitantes">	
					  			<tr>
					  			  <td align = "left">
					  				<img src = "IMAGEM/${visitante.rg}.jpg" width="100" height = "120" />							  							  			
								  </td>
								  	<td>
										<br>
										&nbsp; <b> <c:out value=" Nome: ${visitante.nome}"/></b> <br><br>			  
										&nbsp; <b> <c:out value=" RG: ${visitante.rg}"/></b> <br><br>
										&nbsp; <b> <c:out value=" Telefone: ${visitante.telefone}"/> </b> <br><br>
									
										<form action = "controladora" method = "POST">									
											
											<!-- Data Atual -->
											<jsp:useBean id="dataAtual" class="java.util.Date"/>		
												&nbsp; <b> <c:out value = "Horário de Entrada: "/>
													<fmt:formatDate value="${dataAtual}" pattern="dd/MM/yyyy HH:mm:ss"/> </b> <br><br>
			
											<!-- Funcionário ou Visitante --> 
												<c:choose>
													<c:when test = "${!empty visitante.rg}">
													&nbsp; <b> <c:out value = " Tipo de crachá: Visitante"/> 
													    <input type= "hidden" name = "tpCracha" value = "Visitante"/>	
										  				<input type = "hidden" name = "id" value="${visitante.id}"> </b> <br><br> 												
												</c:when>
												<c:otherwise>
													&nbsp; <b> <c:out value = " Tipo de crachá: Serviço"/> 
														<input type= "hidden" name = "tpCracha" value = "Serviço"/>
														<input type = "hidden" name = "id" value="${funcionario.id}"> </b> <br><br>
												</c:otherwise>	
											</c:choose> 
										
											<!-- Verifica se há empresa associada ao visitante.
											Se tiver, mostra o nome da empresa -->
										
												<c:if test = "${visitante.empresa.id != 0}">
													&nbsp; <b> <c:out value = " Empresa: ${visitante.empresa.nome}"/>  </b> <br><br>
												</c:if>			 	
									
											<!-- cria o combo Setor -->
											&nbsp; <b> Setor:  </b> 
											<jsp:useBean id="setorDAO" class="dao.SetorDAO" />
												<select name="setor">  
										        	<option value="0">Selecione o setor</option>  
										      			<c:forEach var="setor" items="${setorDAO.lista}">  
										          			<option value="${setor.id}">${setor.nome}</option>  
										      			</c:forEach>  
										    	</select>   
											<br><br>
											
											<!-- cria o combo Local -->
											&nbsp; <b> <c:out value = " Local:"/>  </b> 
											<jsp:useBean id = "localDAO" class = "dao.LocalDAO" />
												<select name = "local">  
										        	<option value="0">Selecione o local</option>  
										      			<c:forEach var = "local" items = "${localDAO.lista}">  
										          			<option value = "${local.id}">${local.nome}</option>  
										      			</c:forEach>  
										    	</select> 
										    <br><br>							 					
												<input type= "hidden" name = "logica" value = "AdicionaVisitaServlet"/>					
										   				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										   		<input type = "submit" value = "Cadastrar Visita"/>
											<br><br>	 	
										</form>
									</td>
								</tr>
							</table>			    	
				   								
						</c:if>			  	
					</label>
					
					<br><br><br>
			
					<p class="titulo_tabela" style="text-align:center">Pessoas presentes</p> 
					
					<!-- cria a lista -->
					<jsp:useBean id = "visDao" class = "dao.VisitaDAO" />
						
					<table width="95%" id = "mytable">
						<thead>
							<tr>																
								<th> Nº do Crachá </th>
								<th> Tipo </th>
								<th> Nome </th>
								<th> DRT/RG </th>
								<th> Data de Entrada </th>
								<th> Tipo do Crachá </th>
								<th> Local </th>
								<th> Setor </th>
								<th> Empresa </th>
								<th> Saída </th>
							</tr>
						</thead>						
				 										
						<!-- for -->				
						<c:forEach var = "visita" items = "${visDao.lista}" varStatus="id">									
							
							<tr bgcolor="#${id.count % 2 == 0 ? 'E0FFFF' : 'FFFFFF'}" >						
							
	
											<!-- Número do Crachá -->
											<td><center>
												<c:if test = "${visita.funcionario.id != null}">			
													<!--  -->
												</c:if>	
												<c:if test = "${visita.funcionario.id == null}">			
													<!--  -->
												</c:if>	
											</center></td>
										
											<!-- Funcionário ou Visitante -->
											<td><center>
												<c:if test = "${visita.funcionario.id != null}">			
													Funcionário
												</c:if>	
												<c:if test = "${visita.funcionario.id == null}">
													<c:if test = "${visita.visitante.empresa.nome == null}">															
														Munícipe
													</c:if>
													<c:if test = "${visita.visitante.empresa.nome != null}">															
														Fornecedor
													</c:if>	
												</c:if>	
											</center></td>	
													
											<!-- Nome -->
											<td><center>
												<c:if test = "${visita.funcionario.id != null}">										
												<!-- Deixando as letras em MAIÚSCULAS -->	
													${fn:toUpperCase(visita.funcionario.nome)}								
												</c:if>	
												<c:if test = "${visita.funcionario.id == null}">
												<!-- Deixando as letras em MAIÚSCULAS -->	
													${fn:toUpperCase(visita.visitante.nome)}
												</c:if>	
											</center></td>
											
											<!-- DRT/RG/CPF -->
											<td><center>
												<c:if test = "${visita.funcionario.id != null}">			
													${visita.funcionario.drt}
												</c:if>	
												<c:if test = "${visita.funcionario.id == null}">			
													${visita.visitante.rg}
												</c:if>	
											</center></td>														
												
											<!-- Data de Entrada -->
											<td><center>
												<c:if test = "${visita.funcionario.id != null}">																									
									<!--  Mostra a data  -->	<fmt:formatDate value = "${visita.dataEntrada}" type = "DATE" pattern = "dd/MM/yyyy"/> 
									<!--  Mostra a hora  -->    ${visita.horaEntrada}
												</c:if>	
												<c:if test = "${visita.funcionario.id == null}">			
									<!--  Mostra a data  -->	<fmt:formatDate value = "${visita.dataEntrada}" type = "DATE" pattern = "dd/MM/yyyy"/> 
									<!--  Mostra a hora  -->    ${visita.horaEntrada}							
												</c:if>	
											</center></td>						
											
											<!-- Tipo de Crachá -->
											<td><center>
												<c:choose>								
													<c:when test = "${empty visita.funcionario.situacao.tipoSituacao and !empty visita.funcionario.nome}">
														<c:out value = "Serviço"/> 
													</c:when>
													<c:when test = "${visita.funcionario.situacao.tipoSituacao == '1-Ativo'}">
														<c:out value = "Serviço"/> 
													</c:when>
													<c:otherwise>
														<c:out value = "Visitante"/> 
													</c:otherwise>
												</c:choose>																										
											</center></td>			 
												
															
											<!-- Local -->
											<td><center>
												<c:if test = "${visita.funcionario.id != null}">			
													${visita.local.nome}
												</c:if>	
												<c:if test = "${visita.funcionario.id == null}">			
													${visita.local.nome}
												</c:if>	
											</center></td>
											
											<!-- Setor -->
											<td><center>
												<c:if test = "${visita.funcionario.id != null}">			
													${visita.setor.nome}
												</c:if>	
												<c:if test = "${visita.funcionario.id == null}">			
													${visita.setor.nome}
												</c:if>	
											</center></td>
											
											<!-- Empresa -->
											<td><center>
												<c:if test = "${visita.funcionario.id != null}">			
													<!-- // -->
												</c:if>	
												<c:if test = "${visita.funcionario.id == null}">			
													${visita.visitante.empresa.nome}
												</c:if>	
											</center></td>		
															
											
											<!-- Saída -->
											<td><center>
												<c:if test = "${visita.funcionario.id != null}">			
													<form action = "controladora" method = "GET">	
														<input type= "hidden" name = "logica" value = "ExcluiVisitaServlet"/>
														<a href="controladora?logica=AlteraVisitaServlet&registro=${visita.id}"><img src="saida.jpg" alt="Excluir Visita" width = "25" height = "25"/></a>
													</form>
												</c:if>											
												<c:if test = "${visita.funcionario.id == null}">			
													<form action = "controladora" method = "GET">	
														<input type= "hidden" name = "logica" value = "ExcluiVisitaServlet"/>
														<a href="controladora?logica=AlteraVisitaServlet&registro=${visita.id}"><img src="saida.jpg" alt="Excluir Visita" width = "25" height = "25"/></a>
													</form>
												</c:if>	
											</center></td>							
							</tr>						
						</c:forEach>
					</table>																		
				</fieldset>
			</div>		
	    												
			
				<!--  **************************************** 2ª Aba ****************************************  -->
				
				<div class="contents" id = "abaContent2">	
					<center>	
						<br>
						<p class = "aba1"><u> Por favor, digite o período e a opção de verificação desejada: </u> </p>  
					    <br><br>
					    <form name = "relatorio">
							  <p class = "aba1">
								   De: <input type = "text"  id = "periodo_inicial" /> &nbsp;&nbsp;
								   Até: <input type = "text"  id = "periodo_final" /> 
								   <br><br>			   		   		   
								   Visitante <input type = "radio" name = "pessoa" id = "visit" /> &nbsp;&nbsp;
								   Funcionário <input type = "radio" name = "pessoa" id = "funcion" /> &nbsp;&nbsp;
								   Todos <input type = "radio" name = "pessoa" id = "todos" /> &nbsp;&nbsp; 
							  </p>
							   <br>		
							   		<!--  Arrumar esta chamada ao relatório, pois não está sendo seguido o modelo MVC conforme observação do Esdras -->				 					
									<input type = "button" onclick = " showBIRTReport()" value = "Gerar Relatório" /> <p>
						</form>		
								<form action = "controladora" method = "post"  name = "logout">
									<input type= "hidden" name = "logica" value = "LogoutPessoaServlet"/>					
					   				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;					   					   									
									<input type = "submit" value = "Sair" id = "tiau" />
								</form>						
					</center>	  		
				</div>
		</div>
	</div>

<div id="footer" align = "center">© 2011 - Desenvolvido e Mantido pela Equipe de Desenvolvimento da Proguaru</div>



</body>
</html>
