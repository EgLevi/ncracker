<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="well">
    <img id="avaOnPage" src="/${userInfo.photo_ava}" class="img-circle" height="140" width="140" alt="Avatar">
    <button type="button" id="editBtn" class="btn btn-default btn-sm edit-ava-button" data-toggle="modal" data-target="#modalPhoto">Редактировать</button>
</div>
<jsp:include page="menuModule.jsp"/>
<jsp:include page="interestsModule.jsp"/>

<div class="alert alert-success fade in">
    <a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>
    <p><strong>Ey!</strong></p>
    People are looking at your profile. Find out who.
</div>
<p><a href="#">Link</a></p>
<p><a href="#">Link</a></p>
<p><a href="#">Link</a></p>
