<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Регистрация</title>
</head>
<body>
<h1>Сервис создания тестов</h1><hr>
<h3>Регистрация</h3>

<form action="/registration" method="post">
    Логин: <input type="text" name="username"><br><br>
    Пароль: <input type="password" name="password"><br><br>

    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <#if errorMessage??>
        <p style="color: red">${errorMessage}</p>
    </#if>
    <input type="submit" value="Зарегистрироваться"/>
</form>
</body>
</html>