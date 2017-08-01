<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>AJAX and Java - veerasundar.com</title>
</head>
<body>
	<form method="post">
	Enter City :
		<input id="cityName" name="cityName" size="30" type="text" />
		<input id="getWeatherReport" name="getWeatherReport" type="button" value="Get Weather" />
	</form>
	<div id="weatherReport" class="outputTextArea"></div>
	
	<script type="text/javascript" src="jquery-1.4.4.min.js"></script>
	<script type="text/javascript">
	$(document).ready(function() {
		$("#getWeatherReport").click(function(){
			$cityName = document.getElementById("cityName").value;
			$.post("WeatherServlet", {cityName:$cityName}, function(data) {
				alert(data);
				$("#weatherReport").html(data);
			});
		});
	});
	</script>
</body>
</html>