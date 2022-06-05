<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Авторизация</title>
</head>
<body>
	<h1>Сервис создания тестов</h1><hr>
	<h3>АВТОРИЗАЦИЯ</h3>

	<form action="/login" method="post">
    	Логин: <input type="text" name="username"><br><br>
    	Пароль: <input type="password" name="password"><br><br>
     	<input type="hidden" name="_csrf" value="${_csrf.token}">
   		<input type="submit" value="Вход"/>
	</form><br>

	<a href="/registration">Регистрация</a>
</body>
</html>