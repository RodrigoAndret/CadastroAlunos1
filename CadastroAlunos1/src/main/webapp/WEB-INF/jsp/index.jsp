<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h2> Informe seu usuário e senha: </h2>
<form action="autenticar" method="post">
	Login: <input type="text" name="usuario">
	<br><br>
	
	
	Senha: <input type="password" name="senha">
	<br><br>
	
	<input type="submit" name="Efetuar Login">
	<br><br>

</form>

<%
	String erro = (String) request.getAttribute("error");
	if (erro != null ) {
		if (erro.equals("1")) {
			out.println("<p> Usuário ou senha invalida </p>");
		}
		if (erro.equals("2")) {
			out.println("<p> Sessão Encerrada, faça login novamente </p>");
		}
	}
%>
	
</body>
</html>