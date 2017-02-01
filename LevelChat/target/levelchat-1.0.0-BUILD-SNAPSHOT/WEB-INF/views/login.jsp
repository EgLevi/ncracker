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
    </head>
    <body style="background-attachment:fixed" topmargin="10">
        <header>
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                      <!-- header Nav Start -->
                        <nav class="navbar navbar-default"> <!-- navbar-default navbar-static-top-->
                            <div class="container-fluid">
                              <!-- Brand and toggle get grouped for better mobile display -->
                                <div class="navbar-header">
                                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                                        <span class="sr-only">Toggle navigation</span>
                                        <span class="icon-bar"></span>
                                        <span class="icon-bar"></span>
                                        <span class="icon-bar"></span>
                                    </button>
                                    <a class="navbar-brand" href="index.html">
                                      <img src="resources/images/logo4.png" alt="Logo" height='40' align='top'>
                                    </a>
                                </div>
                                <!-- Collect the nav links, forms, and other content for toggling -->
                                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                                    <ul class="nav navbar-nav navbar-right">
                                      <li><a href="index.html">Главная</a></li>
                                      <li><a href="#">Поддержка</a></li>
                                      <li><a href="#">О нас</a></li>
                                      <li><a href="contact.html">Контакты</a></li>
                                    </ul>
                                </div><!-- /.navbar-collapse -->
                              </div><!-- /.container-fluid -->
                          </nav>
                      </div>
                  </div>
              </div>
        </header><!-- header close -->     
        <div class="login-card">
            <h1>Войти</h1><br>
            <form:form method="post" action="j_spring_security_check">
                 <input type="text" name="j_username" placeholder="Имя пользователя" puth="login">
                 <input type="password" name="j_password" placeholder="Пароль" puth="password">
                 <input type="submit" name="button_login" class="login login-submit" value="Войти">
            </form:form>
            <div class="login-help">
                <a href="#">Регистрация</a> 
                <a href="adminpage">Войти как админ</a>
            </div>
        </div>

<!-- <div id="error"><img src="https://dl.dropboxusercontent.com/u/23299152/Delete-icon.png" /> Your caps-lock is on.</div> -->

  <script src='http://codepen.io/assets/libs/fullpage/jquery_and_jqueryui.js'></script>
    </body>
</html>
