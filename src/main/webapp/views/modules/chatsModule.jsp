<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="chatListPanel" class="col-sm-12 removableElement">
    <div class="panel panel-default text-left">
        <div class="panel-body">
            <h4>Список чатов</h4>
            <div class="row">
                <ul id="listChats" class="nav nav-pills nav-stacked text-left">
                    <c:forEach items="${chats}" var="chat">
                        <li><a href="${chat.chatId}"><img class="img-rounded" width="32"
                                             src="http://ehoop.ru/uploads/posts/2016-02/1455402445_512_52.jpg"><span
                                class="badge pull-right">4</span> ${chat.nameChat}</a></li>
                    </c:forEach>

                </ul>
            </div>
        </div>
    </div>
</div>