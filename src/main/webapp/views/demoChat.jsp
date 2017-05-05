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
</head>
<body>
<script>
    $(document).ready(function () {
        var elem = document.getElementById('history');
        function getData() {
            $.ajax({
                url: "chat",
                type: "GET",
                dataType: "json",
                context: document.body,
                success: function (data) {
                    var json = $.evalJSON($.toJSON(data));
                    var message = json.message;
                    $('#chat-history').children('tbody').append('<tr><td>' + message + '</td></tr>');

                    elem.scrollTop = elem.scrollHeight;
                    getData();
                }
            });
        }

        $("#sendMsg").click(function (event) {
            $.post("chat", $("#msgForm").serialize());
            $('#message').val('');
        });
        elem.scrollTop = elem.scrollHeight;
        getData();
    });
</script>
<div class="container text-center">
    <h3>Примерчик Чата на AJAX</h3>
    <br/>
    <div class="container">
        <div class="row">
            <div id="history" class="col-md-6 col-md-offset-3"
                 style="overflow: scroll;overflow-x:hidden; height: 300pt">
                <table id="chat-history" class="table text-justify" style="white-space: pre-wrap">
                    <tbody>
                    <c:forEach items="${messages}" var="msg">
                        <tr>
                            <td>${msg.message}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

                <br/>

            </div>

            <div class="row">
                <div class="col-md-6 col-md-offset-3">
                    <form:form id="msgForm" name="msgForm">
                        <textarea placeholder="Введите сообщение" id="message" type="text" name="message" style="width: 100%" value=""></textarea>
                    </form:form>
                    <input class="btn btn-danger" type="submit" id="sendMsg" name="sendMsg" value="Send message"/>
                </div>
            </div>
        </div>

    </div>
</div>
</body>
</html>