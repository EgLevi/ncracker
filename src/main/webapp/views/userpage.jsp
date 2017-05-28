<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>${userInfo.login}</title>
    <sec:csrfMetaTags/>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="../resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="../resources/css/styles.css">
    <link rel="stylesheet" href="../resources/css/userpagestyles.css">
    <script src="../resources/js/jquery-3.2.0.min.js"></script>
    <script src="../resources/js/bootstrap.min.js"></script>
    <script src="../resources/js/modalUserPhoto.js"></script>
    <script src="../resources/js/csrfscript.js"></script>
    <script src="../resources/js/menuModulescript.js"></script>
    <script src="../resources/js/searchButtonscript.js"></script>
    <script src="../resources/js/aboutModulescript.js"></script>

</head>
<body>
<jsp:include page="modules/navbarModule.jsp"/>
<jsp:include page="modules/modalPhotoModule.jsp"/>

<div class="container text-center">
    <div class="row">
        <div class="col-sm-3 well">
            <jsp:include page="modules/leftModule.jsp"/>
        </div>
        <div class="col-sm-9">
            <jsp:include page="modules/aboutModule.jsp"/>
            <jsp:include page="modules/searchButtonModule.jsp"/>
            <jsp:include page="modules/dashboardModule.jsp"/>
        </div>
    </div>
</div>

<jsp:include page="modules/footerModule.jsp"/>

</body>
</html>