<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Пользователь</title>
</head>
<body>
<h1>Сервис создания тестов</h1><hr>
<h2>Имя пользователя: ${user.username}</h2>
<h3>Список тестов пользователя: <br></h3>
<br>
<#foreach survey in user.surveys>
    <a href="/survey/${survey.id}">${survey.title}<br><br></a>
</#foreach>
<br>
<br>
<form action="/user/rename/${user.id}" method="get">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Переименовать пользователя"/>
</form>
<br>
<form action="/delete/user/${user.id}" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Удалить пользователя"/>
</form>
<br>
<form action="/users" method="get">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Назад"/>
</form>
</body>
</html>