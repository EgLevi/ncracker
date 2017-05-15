<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <script src="resources/js/jquery-3.2.0.min.js"></script>
    <script src="resources/js/jquery.json.js"></script>
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
        <form action="/logout" id="logout" method="post">
            <!--<input type="hidden" name="${_csrf.parameterName}"
                   value="${_csrf.token}" />-->
        </form>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/userpage">Главная</a></li>
                <li><a href="http://localhost:8081/ru.ncteam.levelchat/postregistration#">Поддержка</a></li>
                <li><a href="http://localhost:8081/ru.ncteam.levelchat/postregistration#">О нас</a></li>
                <li><a href="http://localhost:8081/ru.ncteam.levelchat/contact.html">Контакты</a></li>
                <li><a href="#" onclick="document.getElementById('logout').submit();">Выйти</a></li>
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

                        <li><a id="menuMain" class="show-1" href=""><i class="fa fa-user"> Главная</i></a></li><br>
                        <li><a id="menuSearch" class="show-4" href=""><i class="fa fa-cogs">Поиск</i></a></li>
                        <li><a id="menuMyMessage" class="show-2" href=""><i class="fa fa-envelope"> Мои Сообщения</i></a></li><br>
                        <li><a id="menuPhoto" class="show-3" href=""><i class="fa fa-camera-retro"> Мои фото</i></a></li> <br>
                        <li><a id="menuSettings" class="show-4" href=""><i class="fa fa-cogs"> Настройки</i></a></li>

                    </ul> <!-- /.menu -->


                </div> <!-- /.menu-wrapper -->


            </div> <!-- /.sidebar-menu -->

        </div> <!-- /.col-md-2 col-sm-12-->

        <div id="editUserInfoTable" class="col-md-3 col-sm-12 removableElement" style="width:430px; margin:auto; display:none;">
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

        <div id="userAvaTable" class="col-md-3 col-sm-12 removableElement" style="">

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
        <div id="userinfotable" class="col-md-6 col-sm-12 removableElement" style="">


            <div class="info-wrapper">

                <h1>${userInfo.name} ${userInfo.surname}</h1>
                <hr>

                <label>Возраст: ${userInfo.age}</label><br>
                <label>Пол: ${userInfo.sex}</label><br>
                <label>Email: ${userInfo.email}</label><br>
                <label>Страна: ${userInfo.country}</label><br>
                <label>Город: ${userInfo.city}</label><br>

                <hr style="margin-bottom:0px;">
                <button id="edituserinfoBtn" type="button" class="btn btn-link">Редактировать</button>


            </div>


        </div>

    <div id="chatPanel" class="col-md-8 col-sm-12 removableElement" style="display:none;">
        <div class="panel panel-default">
            <div class="panel-heading">Header (что-нибудь о чате)</div>
            <div id="chatHistory" class="panel-body table-responsive" style="height: 300px;">

            </div>
            <div class="panel-footer" style="position:relative; height: 120px;">
                <div style="position:absolute; top:23px;">
                        <textarea id="message" name="message" class="form-control" rows="3" style="resize: none; width: 500px;"></textarea>
                </div>
                <form id="fileForm" method="post" enctype="multipart/form-data">
                    <div class="btn-group-vertical" style="position:absolute; top:30px; left:530px;">
                        <div class="inputFile" style="height:48px;">
                            <label style="margin-bottom: 0px;">
	                            <span class="btn fileinput-button" style="border-left-width: 0px; padding-left: 0px; padding-right: 0px; border-right-width: 0px;">
	                                <img src="resources/images/fileload2.png" alt="Logo" height="40" align="top">
	                                <input id="file" name="file" type="file" multiple="">
	                            </span>
                            </label>
                        </div>
                        <div id="progressConteiner" class="progress" style="width:40px; height: 7px;">
                            <div id="progressBar" class="progress-bar" role="progressbar" aria-valuenow="10" aria-valuemin="0" aria-valuemax="100" style="width:0%"></div>
                        </div>
                        <div id="successLoad" style="width:13px;margin:auto; display:none"><span class="glyphicon glyphicon-ok" style="color:#00FF00;"></span></div>
                        <div id="badLoad" style="width:13px;margin:auto; display:none"><span class="glyphicon glyphicon-remove" style="color:#FF0000;"></span></div>
                    </div>
                </form>
                <div id="sendMsg" style="position:absolute; top:40px; left:580px;">
                    <button type="button" class="btn btn-primary">Отправить</button>
                </div>
            </div>
        </div>
    </div>

   <div id="chatListPanel" class="col-md-8 col-sm-12 removableElement" style="display:none;">
        <div class="panel panel-default">
            <div class="panel-heading">Список чатов</div>
            <div class="panel-body table-responsive" style="">
                <div id="listChats" class="btn-group-vertical" style="width:100%">
                    <c:forEach items="${chats}" var="chat">
                        <div class="btn btn-default"><label style="display: none;">${chat.chatId}</label>${chat.nameChat}</div>
                    </c:forEach>
                </div>
                <div class="panel-footer" style="">
                </div>
            </div>
        </div>
    </div>

   <div id="searchPanel" class="col-md-8 col-sm-12 removableElement" style="display:none;">
       <div class="panel panel-primary" style="background-color:#e5e8ed; width: 400px; height:483px;">
           <div class="panel-heading" style="background-color:#e5e8ed">
               <div style="width:80%; margin:auto; text-align: center; transform:scale(1.5);">
                   <img src="resources/images/search-title.png" alt="Logo" height="40" align="top">
               </div>
           </div>
           <div class="panel-body table-responsive" style="height: 420px">
               <div style="width:40%; margin-left: auto;margin-right: auto;margin-top: 100px;">
                   <div class="btn" style="transform:scale(2); width:100%; margin:auto;">
                       <!--<button type="button" class="btn btn-primary btn-block btn-lg" ></button>-->
                       <img src="resources/images/search.png" alt="Logo" height="40" align="top">
                   </div>
                   <div style="margin-top: 15px">
                       <div style="width:100%; margin-left: auto; margin-right: auto;text-align:center">
                           <button type="button" class="btn btn-link" data-toggle="modal" data-target="#searchModal">параметры поиска</button>
                           <!-- Modal -->
                           <div class="modal fade" id="searchModal" role="dialog" style="position:absolute; left:-100px;">
                               <div class="modal-dialog my-modal-dialog" style="">
                                   <div class="panel panel-primary" style="background-color:#e5e8ed; width: 400px; height:483px;">
                                       <div class="panel-heading" style="background-color:#e5e8ed">
                                           <button type="button" class="close" data-dismiss="modal">&times;</button>
                                           <img src="resources/images/interest_for_search.png" alt="Logo" height="40" align="top" style="transform: scale(1);">
                                       </div>
                                       <div class="panel-body" style="height: 75%">
                                           <div>
                                               <div style="width:100px;margin:auto;">
                                                   <label class="fontface" style="text-align: center">Выбранные интересы</label>
                                               </div>
                                               <div id="listInterest" style="overflow: auto; height:55px;">
                                                   <div id="flistelement" class="listSelInterest" style="display: none;">
                                                       <label id="flabel" style="display: none;">0</label>
                                                       <span id="fspan" class="label fontface selInterest">Default Label</span>
                                                       <div id="fbtn" class="btn remBtn"><span class="glyphicon glyphicon-remove"></span></div>
                                                   </div>
                                               </div>
                                               <div style="margin-top:20px">
                                                   <label class="fontface">Категория</label>
                                                   <select id="category" class="form-control fontface" style="width:100%">
                                                   </select>

                                                   <div style="margin-top:10px;"><label class="fontface">Интересы</label></div>
                                                   <div class="table-responsive" style="height: 130px;">
                                                       <div id="interests" class="btn-group-vertical table-responsive" style="width:100%;">
                                                           <div id="fdivinterest">
                                                               <label id="flabelinterest" style="display:none">0</label>
                                                               <button id="finterest" type="button" class="btn btn-default fontface" style="display:none; width:100%">Apple</button>
                                                           </div>
                                                       </div>
                                                   </div>
                                               </div>
                                           </div>
                                       </div>
                                       <div class="panel-footer" style="background-color:#e5e8ed;">
                                           <div style="margin-left:auto; width:35%">
                                               <button id="okBtn" type="button" class="btn btn-primary">OK</button>
                                               <button id="cancelBtn" type="button" class="btn btn-primary" data-dismiss="modal">Cancel</button>
                                           </div>
                                       </div>
                                   </div>
                               </div>
                           </div>

                       </div>
                   </div>

               </div>

           </div>
       </div>
   </div>

        <div id="myPhotoPanel" class="col-md-8 col-sm-12 removableElement" style="display:none;">
            <div class="panel panel-primary" style="background-color:#e5e8ed; width: 580px; height:483px;">
                <div class="panel-heading" style="background-color:#e5e8ed">
                    <h3 style="color:#000000">Мои Фото</h3>
                </div>
                <div id="myPhotoContent" class="panel-body table-responsive" style="height: 420px">
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

<style>

    .remBtn
    {
        padding-left: 0px;
        padding-right: 5px;
        padding-top: 0px;
    }
    .selInterest
    {
        color: grey;
        border-style: solid;
        border-top-width: 2px;
        border-bottom-width: 2px;
        border-right-width: 2px;
        border-color: #337ab7;
        border-left-width: 2px;
    }
    .fontface
    {
        font-family: "segoe ui";
    }
    .my-modal-dialog
    {
        top:0px;
        left:15px;
        margin-top:0px;
        margin-bottom:0px;
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
        var formData={};
        if(document.getElementsByName("name")[0].value!="")
        {
            formData.name=document.getElementsByName("name")[0].value;
        }
        if(document.getElementsByName("surname")[0].value!="")
        {
            formData.surname=document.getElementsByName("surname")[0].value;
        }
        if(document.getElementsByName("sex")[0].value!="")
        {
            formData.sex=document.getElementsByName("sex")[0].value;
        }
        if(document.getElementsByName("country")[0].value!="")
        {
            formData.country=document.getElementsByName("country")[0].value;
        }
        if(document.getElementsByName("city")[0].value!="")
        {
            formData.city=document.getElementsByName("city")[0].value;
        }
        if(document.getElementsByName("age")[0].value!="")
        {
            formData.age=document.getElementsByName("age")[0].value;
        }
        if(document.getElementsByName("email")[0].value!="")
        {
            formData.email=document.getElementsByName("email")[0].value;
        }
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
                    if(document.getElementsByName("age")[0].value!="")
                    {
                        listLabel[0].innerHTML = "Возраст: " + document.getElementsByName("age")[0].value;
                    }
                    if(document.getElementsByName("sex")[0].value!="")
                    {
                        listLabel[1].innerHTML = "Пол: " + document.getElementsByName("sex")[0].value;
                    }
                    if(document.getElementsByName("email")[0].value!="")
                    {
                        listLabel[2].innerHTML = "Email: " + document.getElementsByName("email")[0].value;
                    }
                    if(document.getElementsByName("country")[0].value!="")
                    {
                        listLabel[1].innerHTML = "Страна: " + document.getElementsByName("country")[0].value;
                    }
                    if(document.getElementsByName("city")[0].value!="")
                    {
                        listLabel[1].innerHTML = "Город: " + document.getElementsByName("city")[0].value;
                    }
                    var username = userinfotable.getElementsByTagName("h1");
                    var innerStr = username[0].innerHTML;
                    var name = innerStr.substr(0,innerStr.indexOf(" "));
                    var surname = innerStr.substr(innerStr.indexOf(" "),innerStr.length);
                    if(document.getElementsByName("name")[0].value!= "")
                    {
                        username[0].innerHTML = document.getElementsByName("name")[0].value;
                    }
                    else
                    {
                        username[0].innerHTML=name;
                    }
                    if(document.getElementsByName("surname")[0].value!= "")
                    {
                        username[0].innerHTML = username[0].innerHTML +" " +document.getElementsByName("surname")[0].value;
                    }
                    else
                    {
                        username[0].innerHTML = username[0].innerHTML + " " + surname;
                    }
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


<script type="text/javascript">
    var dataLink = null;
    var dataId = -1;
    var chatId = -1;
    function getParent(el){
        return el.parentElement || el.parentNode;
    }

    function clickOnImg()
    {
        var parent = getParent(getParent(this));
        var link = parent.getElementsByTagName("a")[0];
        link.click();
    }

    function getData() {
        $.ajax({
            url: "chats/"+chatId+"/chat",
            type: "GET",
            dataType: "json",
            context: document.body,
            error: function () {
                getData();
            },
            success: function (data) {
                var result;
                try {
                    result = JSON.parse(data);
                } catch (e) {
                    result = data;
                }
                if (result.link != null) {
                    $('#chatHistory').append('<div class="media">'+
                                                '<div class="media-left">'+
                                                    '<img src="'+result.userInfo.photo_ava+'" class="media-object" style="width:60px; height:60px;">'+
                                                '</div>'+
                                                '<div class="media-body">'+
                                                    '<h3 class="media-heading" style="font-weight: 600;">'+result.username+'</h3>'+
                                                    '<p>'+result.textMessage+'</p>'+
                                                '</div>'+
                                                '<div class="media-left">'+
                                                    '<span class="btn"><img class="hrefImg" src = "resources/images/prikreplen.png" alt = "Logo" height = "40" align = "top"></span>'+
                                                    '<a href="' + result.link + '" download style="display:none;"></a>'+
                                                '</div>'+
                                            '</div>');
                    var imgs = document.getElementsByClassName("hrefImg");
                    var lastAddElement = imgs[imgs.length-1];
                    lastAddElement.addEventListener("click",clickOnImg);
                }
                else
                {
                    $('#chatHistory').append('<div class="media">'+
                        '<div class="media-left">'+
                        '<img src="'+result.userInfo.photo_ava+'" class="media-object" style="width:60px; height:60px;">'+
                        '</div>'+
                        '<div class="media-body">'+
                        '<h3 class="media-heading" style="font-weight: 600;">'+result.username+'</h3>'+
                        '<p>'+result.textMessage+'</p>'+
                        '</div>'+
                        '</div>');
                }
                getData();
            }
        });
    }

    function clickOnSendMdgBtn()
    {
        var formData = {message:{textMessage: message.value}, dataId:dataId};
        badLoad.style.display = "none";
        successLoad.style.display = "none";
        progressConteiner.style.display = "";
        progressBar.style.width = 0 + "%";
        $.ajax({
            url: "chats/"+chatId+"/chat",
            contentType:"application/json",
            data: JSON.stringify(formData),
            type: 'POST',
        });
        message.value = '';
        //$('#file').val('');
        dataId = -1;
    }
    sendMsg.addEventListener("click",clickOnSendMdgBtn);

    function enterDown(event)
    {
        if(!event.shiftKey)
        {
            if(event.keyCode == 13)
            {
                sendMsg.click();
                event.preventDefault();
            }
        }
    }

    document.addEventListener("keydown",enterDown);

    function fileUploaded()
    {
        var formData = new FormData($('#fileForm')[0]);

        $.ajax({
            type : 'POST',
            url : "chats/upload",
            data : formData,
            contentType: false,
            processData: false,
            success: function(res) {
                var result;
                try {
                    result = JSON.parse(res);
                } catch (e) {
                    result = res;
                }
                dataId = result.dataId;
                dataLink = result.dataLink;
            },
            error: function(jqXHR, textStatus, errorThrown)
            {
                badLoad.style.display = "";
                progressConteiner.style.display = "none";
            },
            xhr: function(){
                var xhr = $.ajaxSettings.xhr(); // получаем объект XMLHttpRequest
                xhr.upload.addEventListener('progress', function(evt){ // добавляем обработчик события progress (onprogress)
                    if(evt.lengthComputable) { // если известно количество байт
                        // высчитываем процент загруженного
                        var percentComplete = Math.ceil(evt.loaded / evt.total * 100);
                        // устанавливаем значение в атрибут value тега <progress>
                        // и это же значение альтернативным текстом для браузеров, не поддерживающих <progress>
                        progressBar.style.width = percentComplete + "%";
                        if(percentComplete == 100)
                        {
                            successLoad.style.display = "";
                            progressConteiner.style.display = "none";
                        }
                    }
                }, false);
                return xhr;
            }
        });
    }

    file.addEventListener("change",fileUploaded);

    /*$("#file").change(function (event) {
        var file = $('#file').val();
        console.log(file);

        if (file != '') {
            var formData = new FormData();
            formData.append('file', $('input[type=file]')[0].files[0]);
            $.ajax({
                url: 'upload',
                data: formData,
                dataType: "json",
                processData: false,
                contentType: false,
                type: 'POST',
                success: function (data) {
                    var json = $.evalJSON($.toJSON(data));
                    dataId = json.dataId;
                    dataLink = json.dataLink;
                    $('#labe').html("Success upload");
                },
                error: function (err) {
                    alert("Не удалось загрузить файл на сервер");
                }
            });

        }

    });*/


    //elem.scrollTop = elem.scrollHeight;




    function showChatPanel()
    {
        var label = this.getElementsByTagName("label")[0];
        chatId = label.innerHTML;
        var remEl = $(".removableElement");
        for(i=0;i<remEl.length;i++)
        {
            remEl[i].style.display = "none";
        }
        chatPanel.style.display = "";
        getData();
    }

    function addEventOnChats()
    {
        var buttons = listChats.getElementsByTagName('div');
        for(i=0;i<buttons.length;i++)
        {
            buttons[i].addEventListener("click",showChatPanel);
        }
    }

    addEventOnChats();

    function showChatListPanel(event)
    {
        var remEl = $(".removableElement");
        for(i=0;i<remEl.length;i++)
        {
            remEl[i].style.display = "none";
        }
        chatListPanel.style.display = "";
        event.preventDefault();
    }

    menuMyMessage.addEventListener("click",showChatListPanel);

    function showSearchPanel(event)
    {
        var remEl = $(".removableElement");
        for(i=0;i<remEl.length;i++)
        {
            remEl[i].style.display = "none";
        }
        searchPanel.style.display="";
        event.preventDefault();
    }

    menuSearch.addEventListener("click",showSearchPanel);

    function showMainPanels(event)
    {
        var remEl = $(".removableElement");
        for(i=0;i<remEl.length;i++)
        {
            remEl[i].style.display = "none";
        }
        userinfotable.style.display="";
        userAvaTable.style.display="";
        event.preventDefault();
    }

    menuMain.addEventListener("click",showMainPanels);

    function getUserPhotos()
    {
        $.ajax({
            url: "userPhoto",
            type: "GET",
            dataType: "json",
            error: function(jqXHR, textStatus, errorThrown) {
            },
            success: function (data) {
                var result;
                try {
                    result = JSON.parse(data);
                } catch (e) {
                    result = data;
                }
                for (i=0; i<result.length; i++)
                {
                    $('#myPhotoContent').append('<img src="'+result[i]+'" class="media-object" ' +
                        'style="width:120px; height:80px; display: inline-block; margin-top: 10px; margin-left:10px;">');
                }
            }
        });
    }

    function showMyPhotoPanel(event)
    {
        var remEl = $(".removableElement");
        for(i=0;i<remEl.length;i++)
        {
            remEl[i].style.display = "none";
        }
        myPhotoPanel.style.display="";
        getUserPhotos();
        event.preventDefault();
    }

    menuPhoto.addEventListener("click",showMyPhotoPanel);





</script>

<script type="text/javascript">
    function SearchScriptObject()
    {
        var csrfHeader = $("meta[name='_csrf_header']").attr("content");
        var csrfToken = $("meta[name='_csrf']").attr("content");

        $.ajaxSetup({
            headers: {csrfHeader:csrfToken}
        });

        var data=[];

        function clearListSelectedInterest()
        {
            var childList = listInterest.getElementsByClassName("listSelInterest");
            var length=childList.length;
            for(i=0;i<length-1;i++)
            {
                childList[1].remove();
            }
        }

        function clickOnOkBtn(e)
        {
            if (data.length > 0)//если есть данные на обновление, то посылаем соответствующий запрос
            {
                $.ajax({
                    type: 'POST',
                    url: "search",
                    headers:{'X-CSRF-TOKEN':csrfToken},
                    contentType: 'application/json',
                    data: JSON.stringify(data),
                    success: function (res) {
                        cancelBtn.click();
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                    }
                });
                data = [];
                clearListSelectedInterest()
            }
            else
            {
                cancelBtn.click();
            }
        }

        okBtn.addEventListener("click",clickOnOkBtn);



        function getParent(el){
            return el.parentElement || el.parentNode;
        }


        function deleteInterest(e)
        {
            var listBtn = listInterest.getElementsByClassName("btn");
            var index;
            for(i=0;i<listBtn.length;i++)
            {
                if(listBtn[i]==this)
                {
                    index=i;
                    break;
                }
            }
            if(!!index)
            {
                var parent = getParent(listBtn[index]);
                var indexInData = indexDataByInterestId(parent.getElementsByTagName("label")[0].innerHTML);
                data.splice(indexInData,1);
                parent.remove();
            }
            else
            {
                return;
            }
        }

        function existInData(interestId)
        {
            for(i=0;i<data.length;i++)
            {
                if(data[i].interestId == interestId)
                {
                    return true;
                }
            }
            return false;
        }

        function indexDataByInterestId(interestId)
        {
            for(i=0;i<data.length;i++)
            {
                if(data[i].interestId == interestId)
                {
                    return i;
                }
            }
            return -1;
        }

        function clickOnInterest(e)
        {
            var parentEl = getParent(this);
            if(!existInData(parentEl.getElementsByTagName("label")[0].innerHTML))
            {
                var element = $("#flistelement").clone();
                element[0].getElementsByTagName("label")[0].innerHTML = parentEl.getElementsByTagName("label")[0].innerHTML;
                element[0].getElementsByTagName("span")[0].innerHTML = this.innerHTML;
                element[0].style.display = "inline";
                element[0].getElementsByTagName("div")[0].addEventListener("click",deleteInterest);
                listInterest.appendChild(element[0]);
                //если такого элемента нет, то вносим его в список интересов
                data.push({ "interestId": parentEl.getElementsByTagName("label")[0].innerHTML, "interestName": this.innerHTML });
            }
        }

        function getInterestsByCategory(e)
        {
            $.ajax({
                type: 'GET',
                url: "search/getInterests/"+category.options[category.selectedIndex].innerText,
                contentType: 'application/json',
                dataType: "json",
                success: function (res) {
                    var result;
                    try {
                        result = JSON.parse(res);
                    } catch (e) {
                        result = res;
                    }

                    var childList = interests.getElementsByTagName("div");
                    var length=childList.length;
                    for(i=0;i<length-1;i++)
                    {
                        childList[1].remove();
                    }

                    var element;

                    for (i = 0; i < result.length; i++) {
                        element = $("#fdivinterest").clone();
                        element[0].getElementsByTagName("label")[0].innerHTML=result[i].interestId;
                        element[0].getElementsByTagName("button")[0].innerHTML = result[i].interestName;
                        element[0].getElementsByTagName("button")[0].addEventListener("click",clickOnInterest);
                        element[0].getElementsByTagName("button")[0].style.display = "block";
                        interests.appendChild(element[0]);
                    }
                },
                error: function (jqXHR, textStatus, errorThrown) {
                }
            });
        }

        category.addEventListener("change",getInterestsByCategory);

        function getCategory()
        {

            $.ajax({
                type: 'GET',
                url: "search/getCategories",
                contentType: 'application/json',
                dataType: "json",
                success: function (res) {
                    var result;
                    try {
                        result = JSON.parse(res);
                    } catch (e) {
                        result = res;
                    }
                    var element;
                    for (i = 0; i < result.length; i++) {
                        element = document.createElement("option");
                        element.innerHTML=result[i].categoryName;
                        category.append(element);
                    }
                    category.options[0].select=true;
                    getInterestsByCategory();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                }
            });
        }

        getCategory();
    }
    var searchObj = new SearchScriptObject();
</script>

</body>
</html>