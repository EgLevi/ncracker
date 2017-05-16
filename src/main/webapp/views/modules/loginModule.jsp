<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="panel panel-primary" style="background-color:#e5e8ed">
    <div class="panel-heading" style="background-color:#e5e8ed">
        <img src="resources/images/logo4.png" alt="Logo" height='40' align='top'>
    </div>
    <div class="panel-body">
        <form:form method="post" action="j_spring_security_check">
            <div class="form-group">
                <input type="text" class="form-control" name="j_username" placeholder="Логин">
            </div>
            <div class="form-group">
                <input type="password" class="form-control" name="j_password" placeholder="Пароль">
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-primary btn-block">Войти</button>
            </div>
            <div class="form-group">
                <input id="remember_me" name="remeber_me_parameter" type="checkbox"/>
                <label for="remember_me" class="inline">Запомнить</label>
            </div>
            <div class="login-help">
                <a href="registration" style="color:#777777">Регистрация</a>
                <a href="adminpage" style="color:#777777">Войти как админ</a>
            </div>
        </form:form>
    </div>
</div>
