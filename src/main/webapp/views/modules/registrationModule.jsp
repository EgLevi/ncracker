<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="panel panel-primary" style="background-color:#e5e8ed">
    <div class="panel-heading" style="background-color:#e5e8ed">
        <img src="resources/images/logo4.png" alt="Logo" height='40' align='top'>
    </div>
    <div class="panel-body">
        <form:form method="post" action="registration">
            <div class="form-group">
                <label for="login">Логин</label>
                <div class="input-group">
                    <input id="login" type="text" class="form-control" name="login"
                           placeholder="Имя пользователя" style="margin-bottom:7px; height: 35px;">
                    <div class="input-group-btn">
                        <button id="checkBtn" type="button" data-title="Проверить существоание логина"
                                class="btn btn-default" style="margin-bottom:7px;"><span id="okIcon"
                                                                                         class="glyphicon glyphicon-ok"
                                                                                         style="color:#999999; margin-bottom:6px;"></span>
                        </button>
                    </div>
                </div>
                <label class="label label-danger">${loginError}</label>
                <form:errors name="login" cssClass="error"/>
            </div>
            <div class="form-group">
                <label for="password">Пароль</label>
                <input type="password" class="form-control" name="password" placeholder="Пароль"
                       style="margin-bottom:7px;"/>
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