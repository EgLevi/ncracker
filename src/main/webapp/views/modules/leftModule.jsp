<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="well">
    <img id="avaOnPage" src="/${userInfo.photo_ava}" class="img-circle" height="140" width="140" alt="Avatar">
    <button type="button" id="editBtn" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#modalPhoto" style="background-color:#d9edf7; color:#0066FF; margin-top:10px;">Редактировать</button>
</div>
<jsp:include page="interestsModule.jsp"/>
<jsp:include page="menuModule.jsp"/>
<div class="alert alert-success fade in">
    <a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>
    <p><strong>Ey!</strong></p>
    People are looking at your profile. Find out who.
</div>
<p><a href="#">Link</a></p>
<p><a href="#">Link</a></p>
<p><a href="#">Link</a></p>
