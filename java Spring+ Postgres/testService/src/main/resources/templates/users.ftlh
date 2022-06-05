<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Тесты</title>
</head>
<body>
<h1>Сервис создания тестов</h1><hr>
<br>
<form action="/users" method="get">
</form><br>
<#foreach user in users>
    <a href="/user/${user.id}">${user.username}<br><br></a>
</#foreach>
<tr>
</tr>
<br>
<form action="/home" method="get">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="На главную"/>
</form>
</body>
</html>