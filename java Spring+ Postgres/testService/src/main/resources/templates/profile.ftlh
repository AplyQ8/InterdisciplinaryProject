<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Личный кабинет</title>
</head>
<body>
<h1>Сервис создания тестов</h1><hr>
<h2>Личный кабинет</h2>
<h3>Имя пользователя: ${user.username}</h3>
<a href="/surveys">Список тестов<br></a>
<br>
<form action="/home" method="get">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="На главную"/>
</form>
</div>
</body>
</html>
