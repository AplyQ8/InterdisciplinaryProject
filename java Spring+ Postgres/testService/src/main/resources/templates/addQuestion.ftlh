<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Тесты</title>
    <link rel="stylesheet" href="resources/css/styles.css"> <!--подключение файлов css-->
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script>
        function add_input(){
            const inputs = $('.inputs input[type="text"]');
            const new_id = inputs.length + 1;
            $('.inputs').append('<label for="id-'+new_id+'"></label><input id="id-'+new_id+'" name="answers" type="text" placeholder="Вариант ответа"/><br>');
        }
    </script>
</head>
<body>
<h1>Сервис создания тестов</h1><hr>
<h2>Новый тест</h2>
<h3>Добавление вопросов</h3>
<br>
<form action="/add/${id}" method="post">
    Введите вопрос:
        <div class="inputs">
            <label>
                <input type="text" name="title" placeholder="Введите...">
            </label>
            <br>
        Варианты ответа:
         <br>
            <label for="id-1"></label><input id="id-1" type="text" name="answers" placeholder="Вариант ответа">
        <br>
        </div>
        <button type="button" onclick="add_input()">Добавить вариант ответа</button>
    <br>
    <br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Добавить вопрос"/>
</form>
<br>
<br>
<form action="/surveys" method="get">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Завершить создание теста"/>
</form>
</body>
</html>