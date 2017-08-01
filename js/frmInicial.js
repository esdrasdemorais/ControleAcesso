//Funções JavaScript do Sistema de Controle de Acesso

function tipoPessoa() {
	document.getElementById('tiau').style.display = "none";
	
	if ((!document.getElementById('funcionario').checked) && (!document.getElementById('visitante').checked) && (!document.getElementById('cracha_p').checked)) {	   
		document.getElementById('pesquisaFuncionario').style.display = "none";
		document.getElementById('pesquisaVisitante').style.display = "none"; 
		document.getElementById('pesquisaCr').style.display = "none"; 
	 }
	else {
		if (document.getElementById('funcionario').checked) {
			document.getElementById('pesquisaFuncionario').style.display = "";
			document.getElementById('pesquisaVisitante').style.display = "none";
			document.getElementById('pesquisaCr').style.display = "none";
		}
		else {
			if (document.getElementById('visitante').checked) {				
				document.getElementById('pesquisaVisitante').style.display = "";
				document.getElementById('pesquisaFuncionario').style.display = "none";
				document.getElementById('pesquisaCr').style.display = "none";
			}	
		}
	}		
}


function escolherCracha() {
	document.getElementById('pesquisaCr').style.display = "";
	document.getElementById('pesquisaFuncionario').style.display = "none";
	document.getElementById('pesquisaVisitante').style.display = "none";	
	document.getElementById('texto_pesq').style.display = "block";
	document.getElementById('botao_pesq').style.display = "block";
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

function addOptionCombo(Text,Value)
{
	// Create an Option object        
    var opt = document.createElement("option");

    // Add an Option object to Drop Down/List Box
    document.getElementById("selecEmp").options.add(opt,2); 
  
 // Assign text and value to Option object
    opt.text = Text;
    opt.value = Value;        
}

function showBIRTReport() {
	
	  var Dataini = document.getElementById('periodo_inicial').value;
	  var Datafim = document.getElementById('periodo_final').value;
	  
	  if (document.getElementById('visit').checked) {	   		  
		 // window.open("http://servdados:8080/birt-viewer/run?__report=Report\\RelVisit.rptdesign&data_inicial="+Dataini+"&data_final="+Datafim, '_blank');	 
		  window.open("http://localhost:8090/birt-viewer/run?__report=Report\\RelVisit.rptdesign&data_inicial="+Dataini+"&data_final="+Datafim, '_blank');
	  }
		else {
			if (document.getElementById('funcion').checked) {
			//	window.open("http://servdados:8080/birt-viewer/run?__report=Report\\RelFunc.rptdesign&data_inicial="+Dataini+"&data_final="+Datafim, '_blank');
				window.open("http://localhost:8090/birt-viewer/run?__report=Report\\RelFunc.rptdesign&data_inicial="+Dataini+"&data_final="+Datafim, '_blank');	
				
			}
			else {
				if (document.getElementById('todos').checked) {
			//		window.open("http://servdados:8080/birt-viewer/run?__report=Report\\RelTodos.rptdesign&data_inicial="+Dataini+"&data_final="+Datafim, '_blank');
					window.open("http://localhost:8090/birt-viewer/run?__report=Report\\RelTodos.rptdesign&data_inicial="+Dataini+"&data_final="+Datafim, '_blank');
				}		
			}
		}	  	  
}