<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Тесты</title>
</head>
<body>
<h1>Сервис создания тестов</h1><hr>
<h2>Новый тест</h2>
<br>
<form action="/create" method="post">
    Название:
    <label>
        <input type="text" name="title" placeholder="Введите название теста...">
    </label>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Далее"/>
</form>
</body>
</html>