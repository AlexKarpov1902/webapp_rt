<%--
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>

</head>
<body>

    <div class="form">

        <h1>Вход в систему</h1><br>
        <form method="post" action="">
            <label>Логин</label><br>
            <input type="text" required  name="login"><br>
            <label>Пароль</label><br>
            <input type="password" required  name="password"><br><br>
            <input class="button" type="submit" value="Войти"><br><br>
             <input type="checkbox" name="memo" checked>
            <label>Запомнить меня</label><br>

        </form>
    </div>
</body>
</html>
