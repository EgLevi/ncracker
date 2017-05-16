<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="chatPanel" class="row removableElement" style="display: none;">
    <div class="col-sm-12">
        <div class="panel panel-default text-left">
            <c:forEach items="${chats}" var="chat">
                <div id="chatHistory${chat.chatId}" class="panel-body container-overflow">
                    <div class="row">
                        <ul class="chat-in list-group">
                        </ul>
                    </div>
                </div>
            </c:forEach>
            <div class="row send-msg">
                <div class="col-sm-9 center-block">
                    <div>
                        <textarea id="message" placeholder="Введите сообщение" class="form-control"
                                  style="resize: vertical"></textarea>
                    </div>
                </div>
                <div class="col-sm-3">
                    <form id="fileForm" method="post" enctype="multipart/form-data">
                        <div class="col-sm-3 btn">
                            <div class="inputFile" style="height:48px;">
                                <label style="margin-bottom: 0px;">
                                    <span class="btn fileinput-button glyphicon glyphicon-paperclip" style="border-left-width: 0px; padding-left: 0px; padding-right: 0px; border-right-width: 0px;">
                                        <input id="file" name="file" type="file" multiple="">
                                    </span>
                                    <div id="progressConteiner" class="progress" style="width:40px; height: 7px;">
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