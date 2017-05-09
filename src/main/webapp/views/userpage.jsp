<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <script src="resources/js/jquery-1.4.2.min.js" type="text/javascript" ></script>
    <script src="resources/js/jquery.timers.js" type="text/javascript" ></script>
    <script src="resources/js/main.js" type="text/javascript" ></script>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <sec:csrfMetaTags />
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

        <div id="editUserInfoTable" class="col-md-3 col-sm-12" style="width:430px; margin:auto; display:none;">
            <div class="panel panel-primary" style="background-color:#e5e8ed">
                <div class="panel-heading" style="background-color:#e5e8ed">
                    <img src="resources/images/logo4.png" alt="Logo" height='40' align='top'>
                    <div id="closeEditUserInfoBtn" class="btn remBtn" style="position:absolute; right:40px; color:#aaaaaa;"><span class="glyphicon glyphicon-remove"></span></div>
                </div>
                <div class="panel-body">
                    <form id="edituserform" method="post" action="edituserinfo">
                        <div class="form-group">
                            <label for="email">Email</label>
                            <input type="text" class="form-control" name="email" style="margin-bottom:7px;">
                            <label id="emailError" class="label label-danger">${emailError}</label>
                            <form:errors name="email" cssClass="error"/>
                        </div>
                        <div class="form-group">
                            <label for="country">Страна</label>
                            <input type="text" class="form-control" name="country" style="margin-bottom:7px;">
                            <label id="countryError" class="label label-danger">${countryError}</label>
                            <form:errors name="country" cssClass="error"/>
                        </div>
                        <div class="form-group">
                            <label for="city">Город</label>
                            <input type="text" class="form-control" name="city" style="margin-bottom:7px;">
                            <label id="cityError" class="label label-danger">${cityError}</label>
                            <form:errors name="city" cssClass="error"/>
                        </div>
                        <div class="form-group">
                            <label for="name">Имя</label>
                            <input type="text" class="form-control" name="name" style="margin-bottom:7px;">
                            <label id="nameError" class="label label-danger">${nameError}</label>
                            <form:errors name="name" cssClass="error"/>
                        </div>
                        <div class="form-group">
                            <label for="surname">Фамилия</label>
                            <input type="text" class="form-control" name="surname" style="margin-bottom:7px;">
                            <label id="surnameError" class="label label-danger">${surnameError}</label>
                            <form:errors name="surname" cssClass="error"/>
                        </div>
                        <div class="form-group">
                            <label for="age">Возраст</label>
                            <input type="text" class="form-control" name="age" style="margin-bottom:7px;">
                            <label id="ageError" class="label label-danger">${ageError}</label>
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
                            <button id="saveUserInfoBtn" type="button" class="btn btn-primary btn-block">Сохранить</button>
                        </div>
                    </form>
                </div>
            </div>

        </div> <!-- /.col-md-2 col-sm-12-->

        <div id="userAvaTable" class="col-md-3 col-sm-12" style="">

            <div class="photo-wrapper">

                <h1 class="logo">
                    <img id="avaOnPage" src="${userInfo.photo_ava}">
                    <button type="button" id="editBtn" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#modalPhoto">Редактировать фото</button>

                </h1>


            </div> <!-- /.photo-wrapper -->

        </div>

        <div class="modal fade" id="modalPhoto" role="dialog" style="position:absolute; top:30px;">
            <div class="modal-dialog my-modal-dialog" style="width:450px; margin:auto">
                <div class="panel panel-primary" style="background-color:#e5e8ed; width:450px">
                    <div class="panel-heading" style="background-color:#e5e8ed">
                        <img src="resources/images/logo4.png" alt="Logo" height='40' align='top'>
                        <button id="closeModal" type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>
                    <div class="panel-body">
                        <div class="photo-wrapper" style="margin: 0 auto;">
                            <h1 class="logo">
                                <img id="avatar" src="${userInfo.photo_ava}">
                            </h1>
                        </div>
                        <form id="photoeditform" method="get" action="userpage" enctype="multipart/form-data">
                            <div class="form-group">
                                <div class="inputFile">
                                    <label style="margin-bottom:7px;">
                                                    <span class="btn btn-primary fileinput-button" id="IFBtn" style="width:417px;">
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
            </div>
        </div>
        <div id="userinfotable" class="col-md-6 col-sm-12" style="">


            <div class="info-wrapper">

                <h1>${userInfo.name} ${userInfo.surname}</h1>
                <hr>

                <label>Возраст: ${userInfo.age}</label><br>
                <label>Город: ${userInfo.city}</label><br>
                <label>Пол: ${userInfo.sex}</label><br>

                <hr>
                <button id="edituserinfoBtn" type="button" class="btn btn-link">Редактировать</button>


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



<style>
    .inputFile input[type="file"]{

        display: none;/* скрываем input file */

    }
</style>


<script type="text/javascript">

    var csrfToken = $("meta[name='_csrf']").attr("content");
    var relativePath="none";

    function showEditUserInfo()
    {
        userinfotable.style.display = "none";
        userAvaTable.style.display = "none";
        editUserInfoTable.style.display = "";
    }

    function closeEditUserInfo()
    {
        userinfotable.style.display = "";
        userAvaTable.style.display = "";
        editUserInfoTable.style.display = "none";
    }

    closeEditUserInfoBtn.addEventListener("click",closeEditUserInfo);

    edituserinfoBtn.addEventListener("click",showEditUserInfo);

    function sendPhoto()
    {
        var formData = new FormData($('#photoeditform')[0]);

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
                    avaOnPage.src = res;
                    relativePath = res;
                }
            }
        });
    }

    selectPhoto.addEventListener("change",sendPhoto);

    function sendUserInfo()
    {
        var formData = {"name":document.getElementsByName("name")[0].value,
            "surname":document.getElementsByName("surname")[0].value,
            "sex":document.getElementsByName("sex")[0].value,
            "age":document.getElementsByName("age")[0].value,
            "country":document.getElementsByName("country")[0].value,
            "city":document.getElementsByName("city")[0].value,
            "email":document.getElementsByName("email")[0].value};
        $.ajax({
            type : 'POST',
            url : "edituserinfo", // url записан в параметре action формы
            headers:{'X-CSRF-TOKEN':csrfToken},
            data : JSON.stringify(formData),
            contentType: 'application/json',
            success: function(res) {
                var result;
                try {
                    result = JSON.parse(res);
                } catch (e) {
                    result = res;
                }
                if(result == "")
                {
                    var listLabel = userinfotable.getElementsByTagName("label");
                    listLabel[0].innerHTML = "Возраст: " + document.getElementsByName("age")[0].value;
                    listLabel[1].innerHTML = "Город: " + document.getElementsByName("city")[0].value;
                    listLabel[2].innerHTML = "Пол: " + document.getElementsByName("sex")[0].value;
                    var username = userinfotable.getElementsByTagName("h1");
                    username[0].innerHTML = document.getElementsByName("name")[0].value + document.getElementsByName("surname")[0].value;
                    resetErrorLabels();
                    closeEditUserInfoBtn.click();
                    return;
                }
                for (var field in result)
                {
                    document.getElementById(field).innerHTML = result[field];
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                resetErrorLabels();
                closeEditUserInfoBtn.click();
            }
        });
        formData=undefined;
    }

    function resetErrorLabels()
    {
        document.getElementById("emailError").innerHTML = "";
        document.getElementById("countryError").innerHTML = "";
        document.getElementById("cityError").innerHTML = "";
        document.getElementById("nameError").innerHTML = "";
        document.getElementById("surnameError").innerHTML = "";
        document.getElementById("ageError").innerHTML = "";
    }

    saveUserInfoBtn.addEventListener("click",sendUserInfo);

    $(function() {
        $('#photoeditform').submit(function() {
            if(relativePath.indexOf("none")>-1)
            {
                closeModal.click();
                return false; // отменяем отправку формы, т.е. перезагрузку страницы
            }
            $.ajax({
                type : 'POST',
                url : "postregistrationPhoto/save", // url записан в параметре action формы
                headers:{'X-CSRF-TOKEN':csrfToken},
                data : relativePath,
                contentType: 'application/json',
                success: function(res) {
                    closeModal.click();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    closeModal.click();
                }
            });
            return false; // отменяем отправку формы, т.е. перезагрузку страницы
        });
    });
</script>
</body>
</html>