<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link rel="stylesheet" type="text/css" href="resources/css/affablebean.css">
         <link rel="stylesheet" type="text/css" href="resources/css/bootstrap_min.css">
         <link rel="stylesheet" type="text/css" href="resources/css/LCstyle.css">  
    </head>
    <body style="background-attachment:fixed" topmargin="10">
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
                    <form:form method="post" action="j_spring_security_check">
                      <div class="form-group">
                        <input type="text" class="form-control" name="j_username" placeholder="Имя пользователя">
                      </div>
                      <div class="form-group">
                        <input type="password" class="form-control" name="j_password" placeholder="Пароль">
                      </div>
                      <div class="form-group">
                        <button type="submit" class="btn btn-primary btn-block">Войти</button>
                      </div>
                    </form:form>
                    <div class="login-help">
                      <a href="registration" style="color:#777777">Регистрация</a> 
                      <a href="adminpage" style="color:#777777">Войти как админ</a>
                    </div>
                  </div>
                </div>

                </div> <!-- /.col-md-2 col-sm-12-->
                </div>
                </div>   

<!-- <div id="error"><img src="https://dl.dropboxusercontent.com/u/23299152/Delete-icon.png" /> Your caps-lock is on.</div> -->

  <script src='http://codepen.io/assets/libs/fullpage/jquery_and_jqueryui.js'></script>
    </body>
</html>

