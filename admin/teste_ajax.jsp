<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">

<html lang="pt">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Exemplo de abrir/fechar elemento usando Javascript e CSS - IvoGomes.com</title>
	<meta name="generator" content="TextMate http://macromates.com/">
	<meta name="author" content="Ivo Gomes">
	<!-- Este CSS serve apenas para estilizar esta página e não está relacionado com os exemplos aqui demonstrados. O CSS dos exemplos será incluído na própria página. -->
					
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() { // apenas quando o documento estiver carregado
			$("#maisinfo").before('<a href="#" id="show">Clique aqui!</a>'); // cria o link caso o javasript estaja activo. coloca o link imediatamente antes do elemento #maisinfo, com a função $().before();
			
			$("#maisinfo").hide(); // esconde a div que contém a informação, sem animação nenhuma
			// vamos agora alterar o funcionamento predefinido do link com a id show, para que, ao clicar, ele execute a função togle(), que altera o estado da div #maisinfo.
			$("#show").bind("click",function(){
				$("#maisinfo").slideToggle("slow");
			    return false;
			  });
		});
	</script>
</head>
<body>
	
	<div id="maisinfo" style="display:none"><p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
	</div>
	