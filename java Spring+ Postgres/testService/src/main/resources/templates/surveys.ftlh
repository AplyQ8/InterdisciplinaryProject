<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Тесты</title>
</head>
<body>
<h1>Сервис создания тестов</h1><hr>
<h2>Список тестов пользователя: ${user.username}</h2>
<br>
<form action="/surveys" method="get">
</form><br>
<#foreach survey in surveys>
    <a href="/survey/${survey.id}">${survey.title}<br><br></a>
</#foreach>
<tr>
</tr>
<br>
<form action="/createSurvey" method="get">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Создать новый тест"/>
</form>
<form action="/home" method="get">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="На главную"/>
</form>
</body>
</html>