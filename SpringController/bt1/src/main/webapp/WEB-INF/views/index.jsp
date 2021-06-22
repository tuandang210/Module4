<%--
  Created by IntelliJ IDEA.
  User: 233linhnam
  Date: 6/14/2021
  Time: 3:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Title</title>
</head>
<body>
<h3>Sandwich Condiments</h3>
<form method="get">
  <div>
    <input type="checkbox" name="condiment" value="lettuce">Lettuce
    <input type="checkbox" name="condiment" value="tomato">Tomato
    <input type="checkbox" name="condiment" value="mustard">Mustard
    <input type="checkbox" name="condiment" value="sprouts">Sprouts
  <hr>

    <button type="submit">Save</button>

    <c:if test="${condiments!=null}">
      <c:out value=""/>
    </c:if>
    <c:if test="${condiments==null}">
      <c:out value="${message}"/>
    </c:if>

  </div>
</form>
</body>
</html>
