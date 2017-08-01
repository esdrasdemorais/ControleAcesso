<!doctype html public "-//w3c//dtd html 4.0 transitional//en">

<%@page import="modelo.Usuario"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page import = "java.util.*, modelo.Pessoa, modelo.Funcionario, modelo.Empresa, dao.ContatoDAO, dao.VisitaDAO, dao.EmpresaDAO, dao.MotivoDAO, modelo.Visita, modelo.Visitante, modelo.Cracha" %>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>

<%Visita visita = new Visita(); %>

<html>
	<head>
		<title>SISTEMA DE CONTROLE DE ACESSO</title>

		<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="js/jquery.init.js"></script>
		<script type="text/javascript" src="js/frmInicial.js" ></script>
		
		<link rel="stylesheet" type="text/css" href="estilo.css" />

		<style type = "text/css">		
			a{color: #c75f3e;}						
			
			a#cr{color:black;}						
		</style>	

		<SCRIPT type="text/javascript" language = "javascript">
	
			function dateMask(inputData, e){   
			//Créditos ao site http://maozinhadaweb.blogspot.com/2007/05/mscara-de-data-com-javascript_14.html - usuário korbendallas e saulo	
		
				var tecla;
			
				if(document.all) // Internet Explorer
					tecla = event.keyCode;
				else //Outros Browsers
					tecla = e.which;
			
				if(tecla >= 47 && tecla < 58){ // numeros de 0 a 9 e '/'
					var data = inputData.value;
			
				//se for um numero coloca no input
				if(tecla > 47 && tecla < 58){
					if (data.length == 2 || data.length == 5){
						data += '/';
					}
				} else if(tecla == 47){ //se for a barra, so deixa colocar se estiver na posicao certa
					if (data.length != 2 && data.length != 5){
							return false;
					}
				}
				//atualiza o input da data
				inputData.value = data;
				return true;
			
				}else if(tecla == 8 || tecla == 0) // Backspace, Delete e setas direcionais(para mover o cursor, apenas para FF)
					return true;
				else
					return false;
			}
			
			function formataCpf(campo, teclapres)
			{
				var tecla = teclapres.keyCode;
				var vr = new String(campo.value);
				vr = vr.replace(".", "");
				vr = vr.replace("/", "");
				vr = vr.replace("-", "");
				tam = vr.length + 1;
				if (tecla != 14)
				{
					if (tam == 4)
						campo.value = vr.substr(0, 3) + '.';
					if (tam == 7)
						campo.value = vr.substr(0, 3) + '.' + vr.substr(3, 6) + '.';
					if (tam == 11)
						campo.value = vr.substr(0, 3) + '.' + vr.substr(3, 3) + '.' + vr.substr(7, 3) + '-' + vr.substr(11, 2);
				}
			}
			
			function doDigits(pStr)
			{
				if (reDigits.test(pStr)) {
					alert(pStr + " contém apenas dígitos.");
				} else if (pStr != null && pStr != "") {
					alert(pStr + " NÃO contém apenas dígitos.");
				}
			}

		function pesquisaCracha() {

			cracha_pesq = document.parametro_cracha.cracha_pesq.value;
			document.parametro_cracha.cracha_pesq.value = "";
			document.parametro_cracha.cracha_pesq.focus();
	
			this.location = "#" + cracha_pesq; 
			
			document.getElementById("cell_cracha_"+cracha_pesq).style.fontSize='14px';
			document.getElementById("cell_cracha_"+cracha_pesq).style.color='#FF0000';
			document.getElementById("row_cracha_"+cracha_pesq).bgColor='#e0e9f3';			
							
		}
					              				
	</script>  
	
	</head>
	<body onLoad="tipoPessoa()">
	
	<fieldset>
	<!-- 	<legend><h3>Busca de Funcionário / Visitante</h3></legend>  -->			
		
			<br><br>				
		 						
		<!--  Foi pesquisado um Funcionário ou um Visitante ou Crachá-->
		<c:choose>	
			<c:when test = "${!empty funcionario.nome}">
				<center>
					<form name = "radios">
						<div class = "pessoa_escolhida">					
							<label for="funcionario">Funcionário </label><input type="radio" name="escolherPessoa" value="0" checked = "checked"  id = "funcionario"  onClick = "tipoPessoa()">
							<label for="visitante"> Visitante </label><input type="radio" name="escolherPessoa"  value="1"   id = "visitante"  onClick = "tipoPessoa()">
							<label for="cracha_pesquisa"> Crachá </label><input type="radio" name="escolherPessoa"  value="2"   id = "cracha_pesquisa"  onClick = "escolherCracha()">
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
							<label for="cracha_pesquisa"> Crachá </label><input type="radio" name="escolherPessoa"  value="2"   id = "cracha_pesquisa"  onClick = "escolherCracha()">
						</div>		
					</form>
					<br>
				</center>
			</c:when>
			<c:when test = "${funcionario.drt == 0}">
				<center><b><c:out value =" Não há esse funcionário na base de dados. Por favor, tente novamente."/> </b></center><p>
				<center>
					<form name = "radios">
						<div class = "pessoa_escolhida">					
							<label for="funcionario">Funcionário </label><input type="radio" name="escolherPessoa" value="0"   id = "funcionario" onClick = "tipoPessoa()">
							<label for="visitante"> Visitante </label><input type="radio" name="escolherPessoa"  value="1"  id = "visitante"  onClick = "tipoPessoa()">
							<label for="cracha_pesquisa"> Crachá </label><input type="radio" name="escolherPessoa"  value="2"   id = "cracha_pesquisa"  onClick = "escolherCracha()">
						</div>		
					</form>
					<br>
				</center>
			</c:when>
				<c:otherwise>
				<center>
					<form name = "radios">
						<div class = "pessoa_escolhida">					
							<label for="funcionario">Funcionário </label><input type="radio" name="escolherPessoa" value="0"   id = "funcionario"  onClick = "tipoPessoa()">
							<label for="visitante"> Visitante </label><input type="radio" name="escolherPessoa"  value="1"  id = "visitante"  onClick = "tipoPessoa()">
							<label for="cracha_pesquisa"> Crachá </label><input type="radio" name="escolherPessoa"  value="2"   id = "cracha_pesquisa"  onClick = "escolherCracha()">
						</div>		
					</form>
					<br>
				</center>
				</c:otherwise>		
		</c:choose>											
	
		<!-- ******************************** Início da label de Funcionário ******************************** -->	
		
		<label id = "pesquisaFuncionario">				
				
				<form action = "controladora" method = "POST" align = "center">		
				 	<p class = "aba1">  
						<label>Pesquisar pelo DRT: </label>
						<input name = "drt" type = "text">&nbsp;&nbsp;
						<label>Pesquisar pelo CPF: </label>
						<input name = "cpf" type = "text" maxlength="14" onkeyup="formataCpf(this,event)">
				  		<br>		
					</p>
						<input type = "hidden" name = "logica" value = "BuscaFuncionarioServlet"/>
						<input type = "submit" value = "Pesquisar"/>
						<!--  Se for feita a busca de um Funcionário  -->
						
				</form>				
				<c:if test = "${!empty funcionario.nome}">			
					<br>				
					<table border="0" class = "tabFuncionarios">	
				  			<tr>
				  				<td valign = "top">
				  					<!-- <img  src = "file://///servdados/C$/Arquivos de programas/apache-tomcat-7.0.14/webapps/ControleAcesso/ControleAcessoImagem//${funcionario.foto}-00.bmp" width="100" height = "120" /> -->  					
							 <!-- 	<img src = "http://localhost:8090/ControleAcesso/ControleAcessoImagem/${funcionario.foto}-00.bmp" width="100" height = "120" /> -->
							 		<img src = "http://172.16.80.181:8090/ControleAcesso/ControleAcessoImagem/${funcionario.foto}-00.bmp" width="100" height = "120" /> 
							 		
							 	</td>
							 	
							 	<td valign = "top">
	
									<div class = "dadosFuncionarios">					
										 <b> <c:out value=" Nome: ${funcionario.nome}"/></b> <br><br>			  
										 <b> <c:out value=" DRT: ${funcionario.drt}"/></b> <br><br>									 
											 <c:set var="cpf" value="${funcionario.cpf}"/>
										 <b> <c:out value=" CPF: ${fn:substring(cpf,0,3)}.${fn:substring(cpf,3,6)}.${fn:substring(cpf,6,9)}-${fn:substring(cpf,9,11)}"/></b><br><br>
													           	 
					           	<!-- <b> <c:out value=" CPF: ${funcionario.cpf}"/> </b> <br><br>
								
									 <b> <c:out value=" Situação: ${funcionario.situacao.tipoSituacao}"/> </b> <br><br>  -->											
									
										<jsp:useBean id="agora" class="java.util.Date"/>		
											 <b> <c:out value = " Horário de Entrada: "/>   
											<fmt:formatDate value="${agora}" pattern="dd/MM/yyyy HH:mm:ss"/> </b> 																   										
									</div>																												 																				
							 </td>
							 <td>
							 	<div class = "dadosFuncionarios">	
									<form action = "controladora" method = "POST">													
										
										<!-- Funcionário ou Visitante --> 
										<c:choose>
											<c:when test = "${funcionario.situacao.tipoSituacao != '1-Ativo' and !empty funcionario.situacao.tipoSituacao}">
												 <b> <c:out value = " Tipo de crachá: Visitante"/> 
												    <input type= "hidden" name = "tpCracha" value = "Serviço"/>	
									  				<input type = "hidden" name = "id" value = "${funcionario.id}"> </b> <br><br> 												
											</c:when>
											<c:otherwise>
												 <b> <c:out value = " Tipo de crachá: Serviço"/> 
													<input type = "hidden" name = "tpCracha" value = "Serviço"/>
													<input type = "hidden" name = "id" value = "${funcionario.id}"> </b> <br><br>
											</c:otherwise>	
										</c:choose>																					
																																								
											<b> <c:out value = " Número do Crachá: "/> </b>  <!-- cria o combo Crachá -->
										
												<jsp:useBean id = "crachaDAO" class = "dao.CrachaDAO" />
													<select name = "cracha" style="width: 180px">  
											        	<option value="0">Selecione o nº do crachá:</option>  
											      			<c:forEach var = "cracha" items = "${crachaDAO.listaFunc}">  
											          			<option value = "${cracha.id}">${cracha.numeroCracha}</option>
											      			</c:forEach>  
											    	</select> 	
											<br><br>
											
											<b> <c:out value = " Motivo: "/> </b>  <!-- cria o combo Motivo -->									
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;						
											<jsp:useBean id = "motDAO" class = "dao.MotivoDAO" />
												<select name = "motivo" style="width: 180px">  
										        	<option value="0">Selecione:</option>  
										      			<c:forEach var = "motivo" items = "${motDAO.lista}">  
										          			<option value = "${motivo.id}">${motivo.tipo}</option>  
										      			</c:forEach>  
									    		</select>
											<br><br>
												
											 <h3> <c:out value = " Setor: "/> </h3>  <!-- cria o combo Setor -->
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<jsp:useBean id="setDAO" class="dao.SetorDAO" />
													<select name="setor" style="width: 180px">  
											        	<option value="0">Selecione o setor:</option>  
											      			<c:forEach var="setor" items="${setDAO.lista}">  
											          			<option value="${setor.id}">${setor.nome}</option>  
											      			</c:forEach>  
											    	</select>   									    
										
												<br>
										
											<!--  
											 <b> <c:out value = " Local: "/> </b>-->  <!-- cria o combo Local -->
											<!-- 
												<jsp:useBean id = "locDAO" class = "dao.LocalDAO" />
													<select name = "local" style="width: 160px">  
											        	<option value="0">Selecione o local</option>  
											      			<c:forEach var = "local" items = "${locDAO.lista}">  
											          			<option value = "${local.id}">${local.nome}</option>  
											      			</c:forEach>  
											    	</select>
											-->    	
											    	 	
											    <br><br>
											 <p align = "left">   										
												<input type= "hidden" name = "logica" value = "AdicionaVisitaServlet"/>					   				
									   			<input type = "submit" value = "Cadastrar Visita" onClick = "crachaJaExistente()">									   											   																														
											</p>
									</form>
								</div>	 	
							</td>
						</tr>	    										  					 					
					</table>					
				<c:if test = "${!empty message}">			
					<SCRIPT LANGUAGE="JavaScript" TYPE="text/javascript">
						//	alert("Atenção! Este crachá já está sendo utilizado. Por favor, selecione outro crachá!")												  
						  var msg = '<c:out value="${message}"/>';  
						  alert(msg);  
					</script> 
				</c:if>														 																										    		   																								  												 	    													
			</c:if>																	  
		</label>
		
	<!-- ******************************** Fim da label de Funcionário ******************************** -->
		
		
	<!-- ******************************** Início da label de Visitante ******************************** -->
			
		
		<label id = "pesquisaVisitante" style = "display:none">
				
				<c:if test = "${empty visitante.rg}">				
					<form action = "controladora" method = "POST"  name = "controller" style = "text-align:center">		
						<center>Digite o RG:	<input name = "rg" type = "text">			
							<input type = "hidden" name = "logica" value = "BuscaVisitanteServlet"/>
							<input type = "submit" value = "Pesquisar"/> &nbsp;
						</center>	
							<font color = "red" size = "2" >
								*Campo RG deve conter no mínimo 8 dígitos.
							</font>						
					</form>	
				</c:if>
				
					<c:if test = "${!empty visitante.rg}">
						<form action = "controladora" method = "POST"  name = "controller" style = "text-align:center">		
							<center>Digite o RG:	<input name = "rg" type = "text">			
								<input type = "hidden" name = "logica" value = "BuscaVisitanteServlet"/>
								<input type = "submit" value = "Pesquisar"/> &nbsp;
							</center>												
						</form>	
					</c:if>
					
				<c:if test = "${!empty visitante.rg and empty visitante.nome}">										
												
					<form action = "controladora" method = "POST" name = "cadVisit">
						<table border="0"	class = "tabVisitNEnc">	
							<tr style = "text-align:left">
								<td valign = "top">
									<input type = "hidden" name = "id" value="${visitante.id}">
								<br>														
																			
									<!-- Id: --> 
									<input type = "hidden" name = "id">
									
									<font face = " Times">
										Não foi localizada essa pessoa na base <br> 
										de dados. Por favor, selecione: <br><br>  
										Munícipe <input type = "radio"  name = "escolherPessoa" value = "0"  id = "municipe" checked = "checked"  onClick = "MunicForn()">&nbsp;&nbsp;
										Fornecedor	<input type = "radio"  name = "escolherPessoa"  value = "1" id = "fornecedor"  onClick = "MunicForn()"><br><br>		
									
									<table border = "0">
										<tr>
											<td> Nome: </td>
											<td> <input type = "text" name = "nome" size = "48"> </td> 								
										</tr>
										<tr>
											<td> RG: </td>
											<td> <input type = "text" name = "rg" value = "${param.rg}" size = "17"> </td>  										
										</tr>
										<tr>
											<td> Data de Nascimento: </td>
											<td> <input type = "text" name = "data_nasc" size = "17" maxlength = "10" onkeypress = "return dateMask(this, event);"> </td> 										
										</tr>	
										<tr>
											<td> Telefone: </td>
											<td> <input type = "text" name = "telefone" size = "17"> </td>	
										</tr>
									</table>		
																																					
									<!-- label Empresa -->
									<label id = "empresa" style = "display:none">
								
									     	<!-- cria o combo Empresa -->
												
								   		 <jsp:useBean id="empDAO" class="dao.EmpresaDAO" />

										Empresa:    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;																																	
										<select name = "empresa" id = "selecEmp" onchange = "if(this.options[this.selectedIndex].value == 'a'){window.open('frmCadEmpresa.jsp','Pagina','width=530,height=225,resizable=no,scrollbars=no,menubar=no,toolbar=no,status=yes,location=no,left=300,top=280')}"  onfocus = "${empDAO.lista}" >									
								        		<option value="0">Selecione a empresa</option>  
								      			<option value="a"> Nova empresa </option>
								      			<c:forEach var="empresa" items="${empDAO.lista}">  
								          			<option value="${empresa.id}">${empresa.nome}</option>									          			
								      			</c:forEach> 								      																				      	  										      	
								   		 </select> 									   			 	
									</label>	
							</font>		
								<br>					
								</td>								
								<td>									
									<center>
										<APPLET CODE="captura/CaptureWebCamApplet" ARCHIVE="CaptureWebCamAppletAssigned.jar" WIDTH = "150" HEIGHT = "230">					    															 
										</APPLET>
									</center>
								</td>						
							</tr>
						</table>
						<p class = "aba1">	
							<input type= "hidden" name = "logica" value = "AdicionaVisitanteServlet"/>
							<input type = "submit" name = "cadastro" value = "Cadastrar"> &nbsp;
							<a href='javascript:history.back(2)'> Voltar </a>																																
						</p>
					</form>												
				</c:if>
		
				<c:if test = "${!empty visitante.nome}">	
					<form action = "controladora" method = "POST">			
						<table border="0" class = "tabVisitantes">		
			  				<tr>
			  			 	 	<td>
			  			  			<img src = "http://172.16.80.181:8090/ControleAcesso/ControleAcessoImagem/${visitante.rg}.jpg" width="90" height = "120"/>							  							  									 		
						 		</td>  
						  		<td>						  	
									<br>
									<div class = "dadosVisitantes">
										 <b> <c:out value=" Nome: ${visitante.nome}"/></b> <br><br>			  
										 <b> <c:out value=" RG: ${visitante.rg}"/></b> <br><br>
										 <b> <c:out value=" Telefone: ${visitante.telefone}"/> </b> <br><br>
								
																											
										<!-- Data Atual -->
										<jsp:useBean id="dataAtual" class="java.util.Date"/>		
											 <b> <c:out value = "Horário de Entrada: "/>
												<fmt:formatDate value="${dataAtual}" pattern="dd/MM/yyyy HH:mm:ss"/> </b> <br><br>
						  			</div>
						  		</td>
						 		<td>
						 			<br><br><br><br>
						 			
						 			<div class = "dadosVisitantes">		
										<!-- Funcionário ou Visitante --> 
											<c:choose>
												<c:when test = "${!empty visitante.rg}">												 
												 <b> <c:out value = " Tipo de crachá: Visitante"/> 
												    <input type= "hidden" name = "tpCracha" value = "Visitante"/>	
									  				<input type = "hidden" name = "id" value="${visitante.id}"> </b> <br><br> 												
											</c:when>
												<c:otherwise>												 
												 <b> <c:out value = " Tipo de crachá: Serviço"/> 
													<input type= "hidden" name = "tpCracha" value = "Serviço"/>
													<input type = "hidden" name = "id" value="${funcionario.id}"> </b> <br><br>
											</c:otherwise>	
										</c:choose> 
																					
										
										<!-- Verifica se há empresa associada ao visitante.
										Se tiver, mostra o nome da empresa -->
									
											<c:if test = "${visitante.empresa.id != 0}">
												 <b> Empresa: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												 	 <c:out value =
												 		"${visitante.empresa.nome}"
												 	 />  
												 </b> 
												 <br><br>
											</c:if>	
													
										<!-- cria o combo nº do Crachá -->
										    <b> <c:out value = " Número do Crachá: "/> </b>  <!-- cria o combo Crachá -->
											<jsp:useBean id = "crachDAO" class = "dao.CrachaDAO" />
												<select name = "cracha" style="width: 180px">  
										        	<option value="0">Selecione o nº do crachá:</option>  
										      			<c:forEach var = "cracha" items = "${crachDAO.listaVisit}">  
										          			<option value = "${cracha.id}">${cracha.numeroCracha}</option>  
										      			</c:forEach>  
										    	</select> 	
										    <br><br>			 	
								
										<!-- cria o combo Setor -->
										 <h5> Setor:  </h5> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<jsp:useBean id="setorDAO" class="dao.SetorDAO" />
											<select name="setor" style="width: 180px">  
									        	<option value="0">Selecione o setor:</option>  
									      			<c:forEach var="setor" items="${setorDAO.lista}">  
									          			<option value="${setor.id}">${setor.nome}</option>  
									      			</c:forEach>  
									    	</select>   
										<br><br>
										
										<!-- cria o combo Local 
										 <b> <c:out value = " Local:"/>  </b> 
										<jsp:useBean id = "localDAO" class = "dao.LocalDAO" />
											<select name = "local" style="width: 276px">  
									        	<option value="0">Selecione o local</option>  
									      			<c:forEach var = "local" items = "${localDAO.lista}">  
									          			<option value = "${local.id}">${local.nome}</option>  
									      			</c:forEach>  
									    	</select>
									    -->	 
									    <br><br>	
									</div>
								</td>	    									      
							</tr>
						</table>
								<p class = "aba1">		    						 					
									<input type= "hidden" name = "logica" value = "AdicionaVisitaServlet"/>												   				
							   		<input type = "submit" value = "Cadastrar Visita"/>										 	
								</p>
					</form>													    			   								
				</c:if>			  	
		</label>
		
		<br>		
	
	<!-- ******************************** Fim da label de Visitante ******************************** -->
	
	<!-- ******************************** Início da label de Crachá ******************************** -->	
		
		<label id = "pesquisaCr"  style = "display:none">		
			<form onsubmit = "return false" name = "parametro_cracha" > 
				 <table border = 0 align = "center">	
					<tr> 
						<td>
							<input type = "text" id = "texto_pesq" name = "cracha_pesq" size = "6" style = "display:none"/>			
						</td>
						<td>
							<input type = "submit" value = "Pesquisar Crachá" id = "botao_pesq" style = "display:none" onClick = "pesquisaCracha()"/> 											 				
						</td>
					</tr>
				</table>		
			</form>		
		</label>
			
		<!-- ******************************** Fim da label de Crachá ******************************** -->
	
	
	<c:choose>
		<c:when test = "${!empty visitante.rg and empty visitante.nome}">	
			<!-- Não exibe nada -->
		</c:when>
		<c:otherwise>							
						
			<p class="titulo_tabela" style="text-align:center">Pessoas Presentes</p> 										
											
				<!-- cria a lista -->
			<jsp:useBean id = "visDao" class = "dao.VisitaDAO" />
			<div id = "sublinhado">						
			<table id = "mytable">							
				<thead>
					<tr>																
						<th> Nº do Crachá </th>
						<th> Tipo do Crachá </th>
						<th> Tipo </th>
						<th> Nome </th>
						<th> DRT/RG </th>
						<th> Data de Entrada </th>
			<!-- 		<th> Local </th>  -->
						<th> Setor </th>
						<th> Empresa </th>
						<th> Motivo </th>
						<th> Saída </th>
					</tr>
				</thead>						
		 										
				<!-- for -->				
				<c:forEach var = "visita" items = "${visDao.lista}" varStatus="id">									
					
					<tr bgcolor="#${id.count % 2 == 0 ? 'E0FFFF' : 'FFFFFF'}" id="row_cracha_${visita.cracha.numeroCracha}">						
		
						<!-- Número do Crachá -->							
						
						<td id="cell_cracha_${visita.cracha.numeroCracha}"><center>
						    <a name = "${visita.cracha.numeroCracha}" id = "cr">
								<c:if test = "${visita.funcionario.id != null}">										
									${visita.cracha.numeroCracha}								
								</c:if>	
								<c:if test = "${visita.funcionario.id == null}">											
									${visita.cracha.numeroCracha}							
								</c:if>								
							</a>
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
								
						<!-- Local -->
				<!-- 		<td><center>
							<c:if test = "${visita.funcionario.id != null}">			
								${visita.local.nome}
							</c:if>	
							<c:if test = "${visita.funcionario.id == null}">			
								${visita.local.nome}
							</c:if>	
						</center></td>  -->
						
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
										
						<!-- Motivo -->
						<td><center>
							<c:if test = "${visita.funcionario.id != null}">			
								${visita.motivo.tipo}
							</c:if>	
							<c:if test = "${visita.funcionario.id == null}">			
								${visita.motivo.tipo}
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
			</div>																		
		</c:otherwise>
	</c:choose>	
	</fieldset>
	</body>	
</html>