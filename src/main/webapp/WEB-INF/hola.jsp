<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<c:set target="${auteur}" property="firstName" value="${auteur.firstName}"/>
	<p>
		Bonjour cette application est réalisé par
		<c:out value="${auteur.firstName}" />
	</p>
	<c:forEach var="i" begin="0" end="10" step="1">
		<p>
			Hola estamos en la linea
			<c:out value="${i+1}" />
		</p>
	</c:forEach>

	<c:forEach items="${titres}" var="titre" begin="0" end="1"
		varStatus="status">
		<p>
			el numero
			<c:out value="${status.count}" />
			: es la pellicula es
			<c:out value="${titre}" />
		</p>
	</c:forEach>

	<c:choose>
		<c:when test="${ !empty form.resultat }">
			 ${ form.resultat }
        </c:when>
		<c:otherwise>
			Hola 
        </c:otherwise>
	</c:choose>
<!-- 
	<form method="post" action="bonjour">
		<p>
			<label for="login">login:</label> <input type="text" name="login"
				id="login" />
		</p>
		<p>
			<label for="pass">contraseña :</label> <input type="password"
				name="pass" id="pass" />
		</p>
		<input type="submit" />

	</form> -->

	<p>Hola vosotros</p>


	<p>
		<c:out value="${variable}" default="where i am"></c:out>
	</p>
	
	<p>
		Bonjour nos sommes actuellement le :
		<c:out value="${time}" default="time lost"></c:out>

	</p>
	
	<c:set var="pseudo" value="BenDcard" scope="page" />
	<p>
		<c:out value="${pseudo}" />
	</p>
	<c:remove var="pseudo" scope="page" />

	<c:forTokens var="morceau" items="lesbronzées/fontdu/code" delims="/">
		<p>${morceau}</p>
	</c:forTokens>
	
	    <c:if test="${ !empty fichier }"><p><c:out value="Le fichier ${ fichier } (${ description }) a été uploadé !" /></p></c:if>
    <form method="post" action="bonjour" enctype="multipart/form-data">
        <p>
            <label for="description">Description du fichier : </label>
            <input type="text" name="description" id="description" />
        </p>
        <p>
            <label for="fichier">Fichier à envoyer : </label>
            <input type="file" name="fichier" id="fichier" />
        </p>
        
        <input type="submit" />
    </form>
	



</body>
</html>