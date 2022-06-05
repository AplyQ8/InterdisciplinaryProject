<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Сервис создания тестов</title>
</head>
<body>
<h1>Сервис создания тестов</h1><hr>
<nav class="menu">
    <ul>
        <li><a href="home">Главная</a></li>
        <li><a href="profile">Профиль</a></li>
        <#if isAdmin>
         <li><a href="users">Список всех пользователей</a> </li>
        </#if>
    </ul>
</nav><br><br>

<form action="/logout" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Выйти"/>
</form><br>

</body>
</html>
