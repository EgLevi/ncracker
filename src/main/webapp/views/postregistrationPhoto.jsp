<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<script src="resources/js/jquery-1.4.2.min.js" type="text/javascript" ></script>
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
                  <div class="photo-wrapper" style="margin: 0 auto;">
                        <h1 class="logo">
                             <img id="avatar" src="resources/images/logo4.png">
                        </h1>
                   </div>
                    <form method="get" action="userpage" enctype="multipart/form-data">
	                    <div class="form-group">
	                      <div class="inputFile">
	                      	<label style="margin-bottom:7px;">
		                      	<span class="btn btn-primary fileinput-button" id="IFBtn" style="width:0px;">
		                    		<span>Выбрать фото</span>
		                        	<input id="selectPhoto" name="photo_ava" type="file" multiple/>
		                		</span>
	                		</label>
                        	<label id="photoError" class="label label-danger"></label>
	                      </div>
	                    </div>
                     	<div class="form-group" id="btnSubmit">
                        	<button type="submit" class="btn btn-primary btn-block">Сохранить</button>
                      	</div>
                    </form>
                  </div>
                </div>

                </div> <!-- /.col-md-2 col-sm-12-->
                </div>
    <style>
	    .inputFile input[type="file"]{

			display: none;/* скрываем input file */

		}
	</style>
                
	<script type="text/javascript">
		var relativePath="none";
		
		document.getElementById('IFBtn').style.width=document.getElementById('btnSubmit').clientWidth+"px";
		
		function sendPhoto()
		{
			var formData = new FormData($('form')[0]);
	        
	        $.ajax({
	            type : 'POST',
	            url : "postregistrationPhoto", // url записан в параметре action формы
	            data : formData,
	            contentType: false,
	            processData: false,
	            success: function(res) {
	            	if(res.indexOf("fail")>-1)
	            	{
	            		photoError.innerHTML=res;
	            		relativePath = "none"
	            	}
	            	else
	            	{
		              	var img = document.getElementById("avatar");
				      	img.src = res;
				      	relativePath = res;
	            	}
	            }
	          });
		}
		
		selectPhoto.addEventListener("change",sendPhoto);
		
		
		$(function() {
		    $('form').submit(function() {
		    	if(relativePath.indexOf("none")>-1)
		    	{
					window.location = "/ru.ncteam.levelchat/userpage";
					return false; // отменяем отправку формы, т.е. перезагрузку страницы
		    	}
		    	$.ajax({
		            type : 'POST',
		            url : "postregistrationPhoto/save", // url записан в параметре action формы
		            data : relativePath,
	                contentType: 'application/json',
		            success: function(res) {
		            }
		          });
				window.location = "/ru.ncteam.levelchat/userpage";
				return false; // отменяем отправку формы, т.е. перезагрузку страницы
		    });
		  });
	</script>

<!-- <div id="error"><img src="https://dl.dropboxusercontent.com/u/23299152/Delete-icon.png" /> Your caps-lock is on.</div> -->

  <script src='http://codepen.io/assets/libs/fullpage/jquery_and_jqueryui.js'></script>
    </body>
</html>

