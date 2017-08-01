<!doctype html public "-//w3c//dtd html 4.0 transitional//en">


<html>
	<head>
		<title>SISTEMA DE CONTROLE DE ACESSO</title>
	
		<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="js/jquery.init.js"></script>
		<script type="text/javascript" src="js/frmInicial.js" ></script>
		
		<link rel="stylesheet" type="text/css" href="estilo.css" />
	
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
		</script>
				
	</head>	
	<body>
		<center>	
			<br>
			<p class = "aba1"><u> Por favor, digite o período e a opção de verificação desejada: </u> </p>  
		    <br><br>
		    <form name = "relatorio">
				  <p class = "aba1">
					   De: <input type = "text" name = "datNascimento" id = "periodo_inicial" maxlength = "10" onkeypress = "return dateMask(this, event);" /> &nbsp;&nbsp;
					   
					   Até: <input type = "text" name = "datNascimento" id = "periodo_final" maxlength = "10" onkeypress = "return dateMask(this, event);" />
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
	</body>
</html>