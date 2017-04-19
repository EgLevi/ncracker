<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
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




<div class="page-header">
    <div class="container">

        <div class="icon">
            <img src="resources/images/logo.jpg">
        </div><!-- /icon-->

    </div>
</div>

<div class="bg-overlay"></div>

<div class="container-fluid">
    <div class="row">

        <div class="col-md-2 col-sm-12">

            <div class="sidebar-menu">



                <div class="menu-wrapper">

                    <ul class="menu">

                        <li><a class="show-1" href="userpage.jsp"><i class="fa fa-user"> Главная</i></a></li><br>
                        <li><a class="show-2" href="messages.jsp."><i class="fa fa-envelope"> Мои Сообщения</i></a></li><br>
                        <li><a class="show-3" href="images.html"><i class="fa fa-camera-retro"> Мои фото</i></a></li> <br>
                        <li><a class="show-4" href="Settings.html"><i class="fa fa-cogs"> Настройки</i></a></li>

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

        <div class="col-md-6 col-sm-12">


            <div class="info-wrapper">

                <h1>${uname.name} ${uname.surname}</h1>
                <hr>

                Возраст: ${uname.age} <br>
                Город: ${uname.city}<br>
                Место обучение:<br>


            </div>


        </div>





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
