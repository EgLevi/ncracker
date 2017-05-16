<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script src="../resources/js/jquery-3.2.0.min.js"></script>
    <script src="../resources/js/jquery.json.js"></script>
    <link href="../resources/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <title>SelectChat</title>


    <style>
        .lichats {
            height: 63px;
            box-sizing: border-box;
            padding: 0 0 0 15px;
            display: block;
            width: 100%;
            cursor: pointer;
            line-height: 63px;
            margin: 0px;
        }

        .lichats:hover {
            background: #afd9ee;
        }

        .lichats:active {
            background: #2aabd2;
        }

        .lichats a {
            display: block;
            height: 100%;

        }

        .lichats a:hover {
            text-decoration: none;
        }

        .left_fixer {
            width: 100%;
            height: 30pt;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="row">

                <div class="col-md-12">
                    <ul>
                        <c:forEach items="${chats}" var="chat">
                            <li class="lichats text-left">
                                <a href="/chats/${chat.chatId}">
                                    <span class="left_fixer">
                                            ${chat.nameChat}
                                    </span>
                                </a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>

            </div>
        </div>
    </div>
</div>


</body>
</html>
