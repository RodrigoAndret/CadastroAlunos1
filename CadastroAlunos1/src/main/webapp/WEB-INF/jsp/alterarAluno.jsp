<%@page import="com.senac.model.Aluno" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	String usuario = (String) session.getAttribute("usuario");
	if (usuario == null) {
		response.sendRedirect("/erroLogin?error=2");	
		}
%>

<h2> Alterar Aluno:</h2>

<%  Aluno aluno = (Aluno) request.getAttribute("aluno"); %>

<form action="/confirmarAlteracao" method="post">
		
		<input type="hidden" name="id"  value="<%= aluno.getId()%>" >
		<input type="hidden" name="matricula"  value="<%= aluno.getMatricula()%>" >
		
		Matricula: <%=aluno.getMatricula()%>
		<br><br>		

		Nome: 
		<input type="text"  name="nome"  value="<%= aluno.getNome()%>" >
		<br><br>
		
		Idade:
		<input type="number" name="idade" value="<%= aluno.getIdade()%>" >
		<br><br>
		
		Semestre:		
        <select name="semestre">
            <option value="Primeiro" <%= aluno.getSemestre().equals("Primeiro") ? "selected" : "" %>	>Primeiro</option>
            <option value="Segundo"	<%= aluno.getSemestre().equals("Segundo") ? "selected" : "" %>		>Segundo	</option>          
        </select>
        <br><br>
		
		Gênero:
		<input type="radio" name="genero" value="Masculino" <%= aluno.getGenero().equals("Masculino") ? "checked" : "" %>  > Masculino
		<input type="radio" name="genero" value="Feminino" <%= aluno.getGenero().equals("Feminino") ? "checked" : "" %>> Feminino
		<br><br>
				
		<input type="submit" value="Confirmar Alteração">        
		<input type="button"  onclick="javascript:location.href='/listarAlunos'"  value="Voltar">

</form> 
</body>
</html>