<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 233linhnam
  Date: 6/14/2021
  Time: 9:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Calculator</title>
</head>
<body>
<div align="center">
    <form method="get">
        <div>
            <input type="number" name="number1">
            <input type="number" name="number2">
        </div><br><br>
        <div>
            <input type="submit" name="operation" value="+">
            <input type="submit" name="operation" value="-">
            <input type="submit" name="operation" value="*">
            <input type="submit" name="operation" value="/">
        </div>
        <hr>
        <div>
            <h3><c:out value="${message}"/></h3>
            <h3><c:out value="${result}"/></h3>
        </div>
    </form>
</div>
</body>
</html>
