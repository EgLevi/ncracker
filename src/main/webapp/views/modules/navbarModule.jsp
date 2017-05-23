<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#"><img src="/resources/images/logo4.png"  height="30"></a>
        </div>
        <form action="/logout" id="logout" method="post">
            <!--<input type="hidden" name="${_csrf.parameterName}"
                   value="${_csrf.token}" />-->
        </form>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">

                <li><a href="/">Главная</a></li>
                <li><a href="/chats">Сообщения</a></li>


            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li><a href="#" onclick="document.getElementById('logout').submit();"  >Выйти</a></li>
            </ul>
        </div>
    </div>
</nav>