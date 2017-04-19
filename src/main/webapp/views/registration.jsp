<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
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
                    <form:form method="post" action="registration">
                      <div class="form-group">
                      	<label for="login">Логин</label>
                        <div class="input-group">
                        <input id="login" type="text" class="form-control" name="login" placeholder="Имя пользователя" style="margin-bottom:7px;">
                        <div class="input-group-btn">
                        	<button id="checkBtn" type="button" data-title="Проверить существоание логина" class="btn btn-default" style="margin-bottom:7px;"><span id="okIcon" class="glyphicon glyphicon-ok" style="color:#999999; margin-bottom:6px;"></span></button>
                        </div>
						</div>
                        <label class="label label-danger">${loginError}</label>
                        <form:errors name="login" cssClass="error"/>
                      </div>
                      <div class="form-group">
                      	<label for="password">Пароль</label>
                        <input type="password" class="form-control" name="password" placeholder="Пароль" style="margin-bottom:7px;"/>
                        <label class="label label-danger">${passwordError}</label>
                        <form:errors name="password" cssClass="error"/>
                      </div>
                      <div class="form-group">
                        <button type="submit" class="btn btn-primary btn-block">Регистрация</button>
                      </div>
                      <div class="form-group">
                        <input id="remember_me" name="remeber_me_parameter" type="checkbox"/>
						<label for="remember_me" class="inline">Запомнить</label>
                      </div>
                    </form:form>
                  </div>
                </div>

                </div> <!-- /.col-md-2 col-sm-12-->
                </div>
                </div>
	<style>
		
			
		.btn-default:hover::after { 
				content: attr(data-title); /* Выводим текст */
			    position: absolute; /* Абсолютное позиционирование */
			    left: 20%; top: 30%; /* Положение подсказки */
			    z-index: 1; /* Отображаем подсказку поверх других элементов */
			    background: rgba(255,255,230,0.9); /* Полупрозрачный цвет фона */
			    font-family: Arial, sans-serif; /* Гарнитура шрифта */
			    font-size: 11px; /* Размер текста подсказки */
			    padding: 5px 10px; /* Поля */
			    border: 1px solid #333; /* Параметры рамки */ 
			}
	</style>   

<!-- <div id="error"><img src="https://dl.dropboxusercontent.com/u/23299152/Delete-icon.png" /> Your caps-lock is on.</div> -->

  <script src='http://codepen.io/assets/libs/fullpage/jquery_and_jqueryui.js'></script>
  
  <script type="text/javascript">

		function clickOnCheck(e)
		{
			var formData = new FormData($('form')[0]);
			
			$.ajax({
		            type : 'POST',
		            url : "registration/check", // url записан в параметре action формы
		            data : formData,
		            contentType: false,
		            processData: false,
		            success: function(res) {
		              if (res.indexOf("success")>-1)
		              {
		              	okIcon.style.color="#00ff00";
		              }
		              else
		              {
		              	okIcon.style.color="#ff0000";
		              }
		            }
		          });
		}

		checkBtn.addEventListener("click",clickOnCheck);
	</script>
  
    </body>
</html>

