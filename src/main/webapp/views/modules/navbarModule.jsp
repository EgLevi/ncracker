<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            </ul>
        </div>
    </div>
</nav>