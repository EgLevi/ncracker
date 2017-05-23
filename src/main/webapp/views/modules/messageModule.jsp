<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="chatPanel" class="row removableElement">
    <div class="col-sm-12">
        <div class="panel panel-default text-left">
            <div id="chatHistory" class="panel-body container-overflow">
                <div class="row">
                    <ul class="chat-in list-group">
                        <c:forEach items="${messages}" var="message">
                            <li class="list-group-item">
                                <div class="media">
                                    <div class="media-left">
                                        <img src="/${message.userInfo.photo_ava}" class="media-object img-rounded" style="width:60px; height:60px;">
                                    </div>
                                    <div class="media-body">
                                        <h5 class="media-heading" style="font-weight: 600;">${message.userInfo.login}</h5>
                                        <p>${message.textMessage}</p>
                                    </div>
                                    <c:if test="${message.userData.dataLink != null}">
                                        <div class="media-left">
                                            <a href="${message.userData.dataLink}" download class="btn btn-link"><span
                                                    class="glyphicon glyphicon-paperclip"></span> Скачать</a>
                                        </div>
                                    </c:if>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
            <div class="row send-msg">
                <div class="col-sm-9 center-block">
                    <div>
                        <textarea id="message" placeholder="Введите сообщение" class="form-control"
                                  style="resize: vertical"></textarea>
                    </div>
                </div>
                <div class="col-sm-3">
                    <form id="fileForm" method="post" enctype="multipart/form-data">
                        <div class="col-sm-3 btn" style="width:65px; padding-top: 0px;">
                            <div class="inputFile" style="">
                                <label style="margin-bottom: 0px;">
                                    <button id="fileBtn" class="btn btn-default" style="height: 34px; width:43px; position:relative;">
		                                <span class="btn fileinput-button glyphicon glyphicon-paperclip" style="border-left-width: 0px; padding: 0px; border-right-width: 0px; position:absolute;left:14px; transform:scale(1.3); top:4px;">
	                                        <input id="file" name="file" type="file" multiple="">
	                                    </span>
                                    </button>
                                    <div id="progressConteiner" class="progress" style="width:40px; height: 7px; margin-bottom: 0px; margin-top: 5px;">
                                        <div id="progressBar" class="progress-bar" role="progressbar" aria-valuenow="10" aria-valuemin="0" aria-valuemax="100" style="width:0%"></div>
                                    </div>
                                    <div id="successLoad" style="width:13px;margin:auto; display:none"><span class="glyphicon glyphicon-ok" style="color:#00FF00;"></span></div>
                                    <div id="badLoad" style="width:13px;margin:auto; display:none"><span class="glyphicon glyphicon-remove" style="color:#FF0000;"></span></div>
                                </label>
                            </div>
                        </div>
                    </form>
                    <div class="col-sm-4">
                        <button id="sendMsg" class="btn btn-default"><span id="send-msg-span"
                                                                            class="glyphicon glyphicon-send"></span>
                        </button>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>