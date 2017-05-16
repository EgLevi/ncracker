<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="userinfotable" class="row removableElement">
    <div class="col-sm-12">
        <div class="panel panel-default text-left">
            <div id="aboutUserTable" class="panel-body">
                <p class="bg-info">Личная информация<a id="edituserinfoBtn" class="fa-link pull-right" href="">Редактировать</a></p>

                <h1>${userInfo.name} ${userInfo.surname}</h1>
                <h4><label>Возраст:</label>${userInfo.age}</h4>
                <h4><label>Страна:</label>${userInfo.country}</h4>
                <h4><label>Город:</label> ${userInfo.city} </h4>
                <h4><label>Email:</label> ${userInfo.email}</h4>
                <h4><label>Пол:</label> ${userInfo.sex}</h4>
            </div>
        </div>
    </div>
</div>