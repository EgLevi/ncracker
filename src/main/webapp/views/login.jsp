<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Авторизация</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="../resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="../resources/css/styles.css">
    <script src="../resources/js/globalscript.js"></script>
    <script src="../resources/js/jquery-3.2.0.min.js"></script>
    <script src="../resources/js/bootstrap.min.js"></script>
    <script src="../resources/js/jquery-3.2.0.min.js"></script>
    <script src="../resources/js/customscript.js"></script>
</head>
<body class="">
<div class="container text-center" style="margin-top: 50px;">
    <div class="row">
        <div class="col-sm-12 col-xs-12">
            <div class="panel well"
            <jsp:include page="modules/loginModule.jsp"/>
        </div>


    </div>
</div>
</div>


<style>
    @media (min-width: 768px) {
        .container {
            width: 400px;
        }
    }
</style>

<jsp:include page="modules/footerModule.jsp"/>

</body>
</html>



