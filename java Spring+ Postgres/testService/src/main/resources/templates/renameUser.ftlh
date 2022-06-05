<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Переименование пользователя</title>
</head>
<body>
<h1>Сервис создания тестов</h1><hr>
<h2>Переименование пользователя</h2>

<form action="/rename/${user.id}" method="post">
    <br>
    <br>
    <p>Текущее имя пользователя: ${user.username}</p>
    <br>
    <br>
    <p>Введите новое имя пользователя:</p>
    <label>
        <input type="text" name="username" placeholder="Новое имя пользователя">
    </label>

    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <#if errorMessage??>
        <p style="color: red">${errorMessage}</p>
    </#if>
    <input type="submit" value="Переименовать"/>
</form>

<br>
<form action="/user/${user.id}" method="get">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Отмена"/>
</form>
</div>
</body>
</html>