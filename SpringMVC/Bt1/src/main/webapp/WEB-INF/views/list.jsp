<%--
  Created by IntelliJ IDEA.
  User: 233linhnam
  Date: 6/11/2021
  Time: 12:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Change</title>
</head>
<body>
<form>
    <div>
        <h3>USD</h3>
        <input name="n1" value="0" type="number">
    </div>
    <div>
        <h3>VND</h3>
        <input readonly name="n2" value="${n2}" type="number">
    </div><br>
    <div><button type="submit">Change</button> </div>
</form>
</body>
</html>
