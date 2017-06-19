<%--
  Created by IntelliJ IDEA.
  User: Kamil
  Date: 21.05.2017
  Time: 17:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Miejsca na parkingu</title>
</head>
<body>
<form action="start" method="post">
    Miejsce (1 - 5) <input type="number" value="1" name="miejsce">
    <br>
    Strefa(1 -3) <input type="number" value=1 name="strefa">
    <br>
    <input type="radio" name="action" value="przyjazd" checked="checked"> Przyjazd <br>
    <input type="radio" name="action" value="odjazd">  Odjazd<br>
    <input type="submit" value="Wyslij">
</form>
</body>
</html>