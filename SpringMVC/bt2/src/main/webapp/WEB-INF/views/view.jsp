<%--
  Created by IntelliJ IDEA.
  User: 233linhnam
  Date: 6/11/2021
  Time: 2:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="get">
    <div>
        <h3>Word</h3>
        <input type="text" name="word">
    </div>
    <div>
        <h3>Vietnamese</h3>
        <input readonly name="result" value="${result}">
    </div><br>
    <div><button type="submit">Trans</button> </div>
</form>
</body>
</html>
