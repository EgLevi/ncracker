<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Demo CHAT</title>
    <script src="../resources/js/jquery-3.2.0.min.js"></script>
    <script src="../resources/js/jquery.json.js"></script>
    <script>
        $(document).ready(function () {
            var elem = document.getElementById('history');
            var dataLink = null;
            var dataId = -1;

            function getData() {
                $.ajax({
                    url: "${chatId}/chat",
                    type: "GET",
                    dataType: "json",
                    context: document.body,
                    error: function () {
                        getData();
                    },
                    success: function (data) {
                        var json = $.evalJSON($.toJSON(data));
                        //$('#chat-history').append('<div class="row"> <div class="col-md-12"> <div class="col-md-3 col-sm-3 col-xs-3 text-right"> <label>' + json.username + '</label> </div> <div class="col-md-9 col-sm-9 col-xs-9 ws">' + json.message + '</div> </div> </div>');
                        if (json.link != null)
                            $('#chat-history')
                                .append('<div class="row"><div class="col-md-12"><div class="row"><div class="col-md-3 col-sm-3 col-xs-3 text-right"><label>' + json.username + '</label></div><div class="col-md-9 col-sm-9 col-xs-9 ws">' + json.message + '</div></div><div class="row"> <div class="col-md-offset-3 linked-file"> <a href="/' + json.link + '"> <img width="30pt" class="img-thumbnail" src="https://image.flaticon.com/icons/png/512/0/532.png"> </a> </div> </div> </div> </div>');
                        else
                            $('#chat-history').append('<div class="row"><div class="col-md-12"><div class="row"><div class="col-md-3 col-sm-3 col-xs-3 text-right"><label>' + json.username + '</label></div><div class="col-md-9 col-sm-9 col-xs-9 ws">' + json.message + '</div></div></div></div>');

                        elem.scrollTop = elem.scrollHeight;
                        getData();
                    }
                });
            }

            $("#sendMsg").click(function (event) {
                var formochka = new FormData();
                formochka.append('message', $('#message').val());
                formochka.append('dataId', dataId);
                $.ajax({
                    url: '${chatId}/chat',
                    data: formochka,
                    dataType: "json",
                    processData: false,
                    contentType: false,
                    type: 'POST',
                });
                $('#message').val('');
                $('#file').val('');
                dataId = -1;
            });

            $("#file").change(function (event) {
                var file = $('#file').val();
                console.log(file);

                if (file != '') {
                    var formData = new FormData();
                    formData.append('file', $('input[type=file]')[0].files[0]);
                    $.ajax({
                        url: 'upload',
                        data: formData,
                        dataType: "json",
                        processData: false,
                        contentType: false,
                        type: 'POST',
                        success: function (data) {
                            var json = $.evalJSON($.toJSON(data));
                            dataId = json.dataId;
                            dataLink = json.dataLink;
                            $('#labe').html("Success upload");
                        },
                        error: function (err) {
                            alert("Не удалось загрузить файл на сервер");
                        }
                    });

                }

            });


            elem.scrollTop = elem.scrollHeight;
            getData();
        });
    </script>
    <link href="../resources/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <style>
        .ws {
            white-space: pre-wrap;
        }

        #chat-history .col-md-12 {
            display: block;
            padding: 4pt;
        }

        .linked-file {
            display: block;
            padding: 5pt;
        }
    </style>
</head>
<body>
<div class="container">
    <h3>Примерчик Чата на AJAX</h3>
    <br/>
    <div class="container">
        <div class="row">
            <div class="row">
                <div id="history" class="col-md-12 col-xs-12 col-sm-12"
                     style="overflow: scroll;overflow-x:hidden; height: 300pt">
                    <div id="chat-history" class="col-sm-12">
                        <c:forEach items="${messages}" var="msg">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="row">
                                        <div class="col-md-3 col-sm-3 col-xs-3 text-right">
                                            <label>${msg.userInfo.login}</label>
                                        </div>
                                        <div class="col-md-9 col-sm-9 col-xs-9 ws">${msg.message}</div>
                                    </div>
                                    <c:if test="${msg.userData.dataLink != null}">
                                        <div class="row">
                                            <div class="col-md-offset-3 linked-file">
                                                <a href="/<c:url value="${msg.userData.dataLink}"/>">
                                                    <img width="30pt" class="img-thumbnail"
                                                         src="../resources/images/download-icon.png">
                                                </a>
                                            </div>
                                        </div>

                                    </c:if>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <form:form id="msgForm" name="msgForm">
                        <textarea placeholder="Введите сообщение" id="message" type="text" name="message"
                                  style="width: 100%" value=""></textarea>
                    </form:form>
                    <input class="btn btn-danger" type="submit" id="sendMsg" name="sendMsg" value="Send message"/>
                    <input id="file" type="file" name="file"/><label id="labe"></label>
                </div>
            </div>
        </div>

    </div>
</div>
</body>
</html>