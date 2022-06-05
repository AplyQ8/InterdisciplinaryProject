<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Тесты</title>
</head>
<body>
<h1>Сервис создания тестов</h1><hr>
<h2>Тест: ${survey.title}</h2>
<h3>Составитель: ${user.username}</h3>
<br>
<br>
<form>
    <#foreach question in survey.questionList>
        <td>${question.question}<br></td>
        Варианты ответа:<br>
        <#foreach answer in question.answers>
            <td> ◯ ${answer.answer}<br></td>
        </#foreach>
        <br>
        <br>
    </#foreach>
</form>
<a href="/survey/${survey.id}/export/pdf">Скачать в PDF</a>
<#if isAdmin || isOwner>
    <form action="/delete/survey/${survey.id}" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Удалить тест"/>
    </form>
</#if>
<br>
<br>

<form action="/home" method="get">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="На главную"/>
</form>
</body>
</html>