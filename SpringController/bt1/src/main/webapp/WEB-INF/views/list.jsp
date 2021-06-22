<%--
  Created by IntelliJ IDEA.
  User: 233linhnam
  Date: 6/14/2021
  Time: 3:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List</title>
</head>
<body>
<h3>Sandwich Condiments</h3>
<c:forEach var="condiment" items="${condiments}">
    <c:out value="${condiment}"/>
</c:forEach>
<a href="/">Back</a>
</body>
</html>
