<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Demo CHAT</title>
    <script src="https://code.jquery.com/jquery-3.2.0.min.js"></script>
    <link href="../resources/css/bootstrap_min.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<script>
    $(document).ready(function () {
        function getData() {
            $.ajax({
                url: "chat",
                type: "GET",
                dataType: "xml",
                context: document.body,
                success: function (data) {
                    var username = $(data).find('username').text();
                    var text = $(data).find('text').text();
                    var history = $('#chat_msgs').text();
                    $('#chat_msgs').html(history + username + ": " + text + "\n");
                    getData();
                }
            });
        }

        $("#sendMsg").click(function (event) {
            $.post("chat", $("#msgForm").serialize());
            $('#message').val('');
        });

        getData();
    });
</script>
<div class="text-center">
    <h3>Примерчик Чата на AJAX</h3>
    <textarea class="text-area" cols="60" rows="5" id="chat_msgs" name="chat_msgs"></textarea>
    <br/>
    <form id="msgForm" name="msgForm">
        Username: <input class="text-center" type="text" id="username" name="username" value=""/>
        Message: <input type="text" id="message" name="message" value=""/>
    </form>
    <br/>
    <input class="btn btn-danger" type="submit" id="sendMsg" name="sendMsg" value="Send message"/>
</div>
</body>
</html>