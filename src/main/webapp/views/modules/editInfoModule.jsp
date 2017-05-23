<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="editUserInfoTable" class="row col-md-8 removableElement">
    <div class="panel panel-default">
        <div class="panel-heading">
            <img src="resources/images/logo4.png" alt="Logo" height='40' align='top'>
            <button id="closeEditUserInfoBtn" type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        <div class="panel-body">
            <div id="edituserform">
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="text" class="form-control" name="email">
                    <label id="emailError" class="label label-danger">${emailError}</label>
                    <form:errors name="email" cssClass="error"/>
                </div>
                <div class="form-group">
                    <label for="country">Страна</label>
                    <input type="text" class="form-control" name="country" >
                    <label id="countryError" class="label label-danger">${countryError}</label>
                    <form:errors name="country" cssClass="error"/>
                </div>
                <div class="form-group">
                    <label for="city">Город</label>
                    <input type="text" class="form-control" name="city" >
                    <label id="cityError" class="label label-danger">${cityError}</label>
                    <form:errors name="city" cssClass="error"/>
                </div>
                <div class="form-group">
                    <label for="name">Имя</label>
                    <input type="text" class="form-control" name="name" >
                    <label id="nameError" class="label label-danger">${nameError}</label>
                    <form:errors name="name" cssClass="error"/>
                </div>
                <div class="form-group">
                    <label for="surname">Фамилия</label>
                    <input type="text" class="form-control" name="surname" >
                    <label id="surnameError" class="label label-danger">${surnameError}</label>
                    <form:errors name="surname" cssClass="error"/>
                </div>
                <div class="form-group">
                    <label for="age">Возраст</label>
                    <input type="text" class="form-control" name="age" >
                    <label id="ageError" class="label label-danger">${ageError}</label>
                    <form:errors name="age" cssClass="error"/>
                </div>
                <div class="form-group">
                    <label>Пол</label>
                    <select class="form-control" name="sex" >
                        <option>м</option>
                        <option>ж</option>
                    </select>
                </div>
                <div class="form-group">
                    <button id="saveUserInfoBtn" type="submit" class="btn btn-primary btn-block">Сохранить</button>
                </div>
            </div>
        </div>
    </div>
</div>