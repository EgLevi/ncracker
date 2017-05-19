<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>${userInfo.login}</title>
    <sec:csrfMetaTags />
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="../resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="../resources/css/styles.css">
    <link rel="stylesheet" href="../resources/css/userpagestyles.css">
    <script src="../resources/js/jquery-3.2.0.min.js"></script>
    <script src="../resources/js/bootstrap.min.js"></script>
    <script src="../resources/js/jquery-3.2.0.min.js"></script>
    <script src="../resources/js/customscript.js"></script>
    <script src="../resources/js/userpagescript.js"></script>
</head>
<body>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Logo</a>
        </div>
        <form action="/logout" id="logout" method="post">
            <!--<input type="hidden" name="${_csrf.parameterName}"
                   value="${_csrf.token}" />-->
        </form>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li><a href="#" onclick="document.getElementById('logout').submit();">Выйти</a></li>
                <li><a href="/">Главная</a></li>
                <li><a href="/messages">Messages</a></li>
            </ul>
            <form class="navbar-form navbar-right" role="search">
                <div class="form-group input-group">
                    <input type="text" class="form-control" placeholder="Search..">
                    <span class="input-group-btn">
            <button class="btn btn-default" type="button">
              <span class="glyphicon glyphicon-search"></span>
            </button>
          </span>
                </div>
            </form>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#"><span class="glyphicon glyphicon-user"></span> My Account</a></li>
            </ul>
        </div>
    </div>
</nav>







<button id="showPhotoBtn" type="button" data-toggle="modal" data-target="#showPhoto" style="display:none"></button>


<jsp:include page="modules/modalUserPhotoModule.jsp"/>
<jsp:include page="modules/modalSearchModule.jsp"/>

<div class="container text-center">
    <div class="row">
        <div class="col-sm-3 well">
            <jsp:include page="modules/leftModule.jsp"/>
        </div>

        <div class="col-sm-9">
            <jsp:include page="modules/searchButtonModule.jsp"/>
            <jsp:include page="modules/aboutModule.jsp"/>
            <jsp:include page="modules/editInfoModule.jsp"/>
            <jsp:include page="modules/messagesModule.jsp"/>
            <jsp:include page="modules/chatModule.jsp"/>
            <jsp:include page="modules/userPhotoModule.jsp"/>
            <jsp:include page="modules/searchModule.jsp"/>
            #dashboard
        </div>


        <jsp:include page="modules/modalPhotoModule.jsp"/>


    </div>
</div>
</div>

<footer class="container-fluid text-center">
    <p>Footer Text</p>
</footer>

</body>
</html>