<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="editUserInfoTable" class="row col-sm-12 removableElement">
    <div class="panel panel-default">
        <div class="panel-heading">
            <img src="resources/images/logo4.png" alt="Logo" height='40' align='top'>
            <button id="closeEditUserInfoBtn" type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        <div class="panel-body">
            <div id="edituserform">
                <div class="form-group">
                    <label for="email" style="color:#337ab7">Email</label>
                    <input type="text" class="form-control" name="email" value="${userInfo.email}">
                    <label id="emailError" class="label label-danger">${emailError}</label>
                    <form:errors name="email" cssClass="error"/>
                </div>
                <div class="form-group">
                    <label for="country" style="color:#337ab7">Страна</label>
                    <input type="text" class="form-control" name="country" value="${userInfo.country}">
                    <label id="countryError" class="label label-danger">${countryError}</label>
                    <form:errors name="country" cssClass="error"/>
                </div>
                <div class="form-group">
                    <label for="city" style="color:#337ab7">Город</label>
                    <input type="text" class="form-control" name="city" value="${userInfo.city}">
                    <label id="cityError" class="label label-danger">${cityError}</label>
                    <form:errors name="city" cssClass="error"/>
                </div>
                <div class="form-group">
                    <label for="name" style="color:#337ab7">Имя</label>
                    <input type="text" class="form-control" name="name" value="${userInfo.name}">
                    <label id="nameError" class="label label-danger">${nameError}</label>
                    <form:errors name="name" cssClass="error"/>
                </div>
                <div class="form-group">
                    <label for="surname" style="color:#337ab7">Фамилия</label>
                    <input type="text" class="form-control" name="surname" value="${userInfo.surname}">
                    <label id="surnameError" class="label label-danger">${surnameError}</label>
                    <form:errors name="surname" cssClass="error"/>
                </div>
                <div class="form-group">
                    <label for="age" style="color:#337ab7">Возраст</label>
                    <input type="text" class="form-control" name="age" value="${userInfo.age}">
                    <label id="ageError" class="label label-danger">${ageError}</label>
                    <form:errors name="age" cssClass="error"/>
                </div>
                <div class="form-group">
                    <label style="color:#337ab7">Пол</label>
                    <select class="form-control" name="sex" value="${userInfo.sex}">
                        <c:if test="${userInfo.sex=='мужской'}">
                            <option selected="true">мужской</option>
                            <option>женский</option>
                        </c:if>
                        <c:if test="${userInfo.sex=='женский'}">
                            <option>мужской</option>
                            <option selected="true">женский</option>
                        </c:if>
                    </select>
                </div>
                <div class="form-group">
                    <button id="saveUserInfoBtn" type="submit" class="btn btn-primary btn-block">Сохранить</button>
                </div>
            </div>
        </div>
    </div>
</div>