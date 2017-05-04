<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="resources/js/jquery-1.4.2.min.js" type="text/javascript" ></script>
<script src="resources/js/jquery.timers.js" type="text/javascript" ></script>
<script src="resources/js/main.js" type="text/javascript" ></script>


    <meta charset="utf-8">

    <title>Level Chat</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width">

    <link rel="stylesheet" href="resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="resources/css/normalize.min.css">
    <link rel="stylesheet" href="resources/css/font-awesome.min.css">
    <link rel="stylesheet" href="resources/css/animate.css">
    <link rel="stylesheet" href="resources/css/templatemo_misc.css">
    <link rel="stylesheet" href="resources/css/LCstyle.css">
    <link rel="stylesheet" href="resources/css/styles.css">

    <link rel="shortcut icon" href="resources/images/favicon.png" type="image/png">


    <script src="js/vendor/modernizr-2.6.2.min.js"></script>







</head>
<body>




<div class="navbar navbar-default"> <!-- navbar-default navbar-static-top-->
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
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
                <li><a href="/userpage">Главная</a></li>
                <li><a href="http://localhost:8081/ru.ncteam.levelchat/postregistration#">Поддержка</a></li>
                <li><a href="http://localhost:8081/ru.ncteam.levelchat/postregistration#">О нас</a></li>
                <li><a href="http://localhost:8081/ru.ncteam.levelchat/contact.html">Контакты</a></li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</div>



<div class="container-fluid">
    <div class="row">

        <div class="col-md-2 col-sm-12">

            <div class="sidebar-menu">



                <div class="menu-wrapper">

                    <ul class="menu">

                        <li><a class="show-1" href="/userpage"><i class="fa fa-user"> Главная</i></a></li><br>
                        <li><a class="show-2" href="/messages"><i class="fa fa-envelope"> Мои Сообщения</i></a></li><br>
                        <li><a class="show-3" href="/gallery"><i class="fa fa-camera-retro"> Мои фото</i></a></li> <br>
                        <li><a class="show-4" href="/settings"><i class="fa fa-cogs"> Настройки</i></a></li>

                    </ul> <!-- /.menu -->


                </div> <!-- /.menu-wrapper -->


            </div> <!-- /.sidebar-menu -->

        </div> <!-- /.col-md-2 col-sm-12-->


        <div class="col-md-3 col-sm-12">

            <div class="photo-wrapper">

                <h1 class="logo">
                    <img src="resources/images/ava.jpg">
                    <button type="button" class="btn btn-primary btn-sm">Редактировать фото</button>

                </h1>


            </div> <!-- /.photo-wrapper -->

        </div>
<form:form method="post" action="edituserinfo">
        <div class="col-md-6 col-sm-12">


            <div class="info-wrapper">

                <h1>${uname.name} ${uname.surname}</h1>
                <hr>

                Возраст: ${uname.age} <br>
                Город: ${uname.city} <br>
                Пол: ${uname.sex} <br>

                <hr>
                <a href="/edituserinfo">Редакировать</a>

            </div>


        </div>
</form:form>





    </div>
</div>
</div>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-12 footer">
            <p id="footer-text">

                <a href="#">Netcracker Team</a>

            </p>
        </div><!-- /.footer -->
    </div>
</div> <!-- /.container-fluid -->





</body>
</html>
