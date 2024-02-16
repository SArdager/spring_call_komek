<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE HTML>
<html>
<head>
  <meta charset="UTF-8">
  <title>Главная</title>
    <link rel="stylesheet" type="text/css" href="resources/css/style.css">
  <script>
    <script type="text/javascript" src="resources/js/jquery-3.6.0.min.js"></script>
  </script>

</head>
<body>
    <section>
    <div class="container">
        <h1>БАЗА ДАННЫХ "КОМЕК"</h1>
        <h4 style="margin-left: 10%;">${pageContext.request.userPrincipal.name}</h4>
        <sec:authorize access="!isAuthenticated()">
            <p>Вход только для авторизованных пользователей</p>
            <h5><a href="login">Войти</a></h5>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
            <h5><a href="worker">На главную</a></h5>
            <h5><a href="change-password">Сменить пароль</a></h5>
            <h5><a href="logout">Выйти</a></h5>
        </sec:authorize>
        <sec:authorize access="hasRole('ADMIN')">
            <a href="admin">Администрирование</a></h5>
        </sec:authorize>
    </div>
    </section>
</body>
</html>
