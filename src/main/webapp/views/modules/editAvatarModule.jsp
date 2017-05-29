<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="well">
    <img id="avaOnPage" src="/${userInfo.photo_ava}" class="img-circle" height="140" width="140" alt="Avatar">
    </br>
    <button type="button" id="editBtn" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#modalPhoto" style="background-color:#d9edf7; color:#0066FF; margin-top:10px;">Редактировать</button>
</div>
<jsp:include page="interestsModule.jsp"/>
<jsp:include page="menuModule.jsp"/>
