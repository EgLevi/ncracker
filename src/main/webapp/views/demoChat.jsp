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
    <link href="../resources/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <style>
        .ws {
            white-space: pre-wrap;
        }

        #chat-history .col-md-12 {
            display: block;
            padding: 4pt;
        }
    </style>
</head>
<body>
<script>
    $(document).ready(function () {
        var elem = document.getElementById('history');

        function getData() {
            $.ajax({
                url: "chat/${chatId}",
                type: "GET",
                dataType: "json",
                context: document.body,
                error: function () {
                    getData();
                },
                success: function (data) {
                    var json = $.evalJSON($.toJSON(data));
                    $('#chat-history')
                        .append('<dt> <label>' + json.username + ' </dt> <dd>' + json.message + '</dd>');

                    elem.scrollTop = elem.scrollHeight;
                    getData();
                }
            });
        }

        $("#sendMsg").click(function (event) {
            $.post("chat/${chatId}", $("#msgForm").serialize());
            $('#message').val('');

            var file = $('#file').val();
            console.log(file);
            if (file != '') {
                var formData = new FormData();
                formData.append('file', $('input[type=file]')[0].files[0]);
                console.log("form data " + formData);
                $.ajax({
                    url: 'upload',
                    data: formData,
                    processData: false,
                    contentType: false,
                    type: 'POST',
                    success: function (data) {
                        $("#labe").html(data)
                    },
                    error: function (err) {
                        alert(err);
                    }
                });

            }
        });

        $('form#msgForm input[type="text"]').each(function () {
            if (!$(this).val() || $(this).val() == '') {
                $(this).css('border-color', 'red');//Сделаем бордер красным
                send = false;
            }
        });
        elem.scrollTop = elem.scrollHeight;
        getData();
    });
</script>
<div class="container">
    <h3>Примерчик Чата на AJAX</h3>
    <br/>
    <div class="container">
        <div class="row">
            <div id="history" class="col-md-12 col-xs-12 col-sm-12"
                 style="overflow: scroll;overflow-x:hidden; height: 300pt">
                <div id="chat-history" class="col-sm-12">
                    <c:forEach items="${messages}" var="msg">
                        <div class="col-md-12">
                            <div class="col-md-3 col-sm-3 col-xs-3 text-right"><label>${msg.userInfo.login}</label>
                            </div>
                            <div class="col-md-9 col-sm-9 col-xs-9 ws">${msg.message}</div>
                        </div>
                    </c:forEach>
                </div>
            </div>

            <div class="row">
                <div class="col-md-6 col-md-offset-3">
                    <form:form id="msgForm" name="msgForm" enctype="multipart/form-data">
                        <textarea placeholder="Введите сообщение" id="message" type="text" name="message"
                                  style="width: 100%" value=""></textarea>
                        <input id="file" type="file" name="file"/>
                    </form:form>
                    <input class="btn btn-danger" type="submit" id="sendMsg" name="sendMsg" value="Send message"/>
                    <label id="labe"></label>
                </div>
            </div>
        </div>

    </div>
</div>
</body>
</html>