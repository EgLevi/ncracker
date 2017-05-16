<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="resources/css/affablebean.css">
    <link rel="stylesheet" type="text/css" href="resources/css/bootstrap_min.css">
    <link rel="stylesheet" type="text/css" href="resources/css/LCstyle.css">
</head>
<body>
<div class="navbar navbar-default"> <!-- navbar-default navbar-static-top-->
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="http://localhost:8081/ru.ncteam.levelchat/index.html">
                <img src="resources/images/logo4.png" alt="Logo" height="40" align="top">
            </a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="http://localhost:8081/ru.ncteam.levelchat/index.html">Главная</a></li>
                <li><a href="http://localhost:8081/ru.ncteam.levelchat/postregistration#">Поддержка</a></li>
                <li><a href="http://localhost:8081/ru.ncteam.levelchat/postregistration#">О нас</a></li>
                <li><a href="http://localhost:8081/ru.ncteam.levelchat/contact.html">Контакты</a></li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</div>
<div class="container-fluid" style="width:730px;">
    <div class="row col-md-12" style="width:430px; margin:auto;">
        <div class="panel panel-primary" style="background-color:#e5e8ed">
            <div class="panel-heading" style="background-color:#e5e8ed">
                <img src="resources/images/logo4.png" alt="Logo" height='40' align='top'>
            </div>
            <div class="panel-body">
                <form:form method="post" action="postregistration">
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input type="text" class="form-control" name="email" style="margin-bottom:7px;">
                        <label class="label label-danger">${emailError}</label>
                        <form:errors name="email" cssClass="error"/>
                    </div>
                    <div class="form-group">
                        <label for="country">Страна</label>
                        <input type="text" class="form-control" name="country" style="margin-bottom:7px;">
                        <label class="label label-danger">${countryError}</label>
                        <form:errors name="country" cssClass="error"/>
                    </div>
                    <div class="form-group">
                        <label for="city">Город</label>
                        <input type="text" class="form-control" name="city" style="margin-bottom:7px;">
                        <label class="label label-danger">${cityError}</label>
                        <form:errors name="city" cssClass="error"/>
                    </div>
                    <div class="form-group">
                        <label for="name">Имя</label>
                        <input type="text" class="form-control" name="name" style="margin-bottom:7px;">
                        <label class="label label-danger">${nameError}</label>
                        <form:errors name="name" cssClass="error"/>
                    </div>
                    <div class="form-group">
                        <label for="surname">Фамилия</label>
                        <input type="text" class="form-control" name="surname" style="margin-bottom:7px;">
                        <label class="label label-danger">${surnameError}</label>
                        <form:errors name="surname" cssClass="error"/>
                    </div>
                    <div class="form-group">
                        <label for="age">Возраст</label>
                        <input type="text" class="form-control" name="age" style="margin-bottom:7px;">
                        <label class="label label-danger">${ageError}</label>
                        <form:errors name="age" cssClass="error"/>
                    </div>
                    <div class="form-group">
                        <label for="sex">Пол</label>
                        <select class="form-control" name="sex" style="margin-bottom:7px;">
                            <option>м</option>
                            <option>ж</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary btn-block">Сохранить</button>
                    </div>
                </form:form>
            </div>
        </div>

    </div> <!-- /.col-md-2 col-sm-12-->
</div>
</div>

<script src='http://codepen.io/assets/libs/fullpage/jquery_and_jqueryui.js'></script>
</body>
</html>

