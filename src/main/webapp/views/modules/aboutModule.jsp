<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="userinfotable" class="row removableElement">
    <div class="col-sm-12">
        <div class="panel panel-default text-left">
            <div id="aboutUserTable" class="panel-body">


                <h1>${userInfo.name} ${userInfo.surname}</h1>
                <hr size="1">
                <h4><label>Возраст: </label> ${userInfo.age}</h4>
                <h4><label>Страна: </label> ${userInfo.country}</h4>
                <h4><label>Город: </label> ${userInfo.city} </h4>
                <h4><label>Email: </label> ${userInfo.email}</h4>

                <p ><a id="edituserinfoBtn" class="fa-link pull-right" href="edituserinfo"> Редактировать</a></p>
                <span> <hr size="1" width="710" align="left" > </span>
            </div>
        </div>
    </div>
</div>