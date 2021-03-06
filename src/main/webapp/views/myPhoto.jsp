<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Галерея</title>
    <sec:csrfMetaTags />
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="../resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="../resources/css/styles.css">
    <link rel="stylesheet" href="../resources/css/userpagestyles.css">
    <script src="../resources/js/jquery-3.2.0.min.js"></script>
    <script src="../resources/js/bootstrap.min.js"></script>
    <script src="../resources/js/jquery-3.2.0.min.js"></script>
    <script src="../resources/js/editUserInfo.js"></script>
    <script src="../resources/js/csrfscript.js"></script>
    <script src="../resources/js/modalUserPhoto.js"></script>
    <script src="../resources/js/globalscript.js"></script>
    <script src="../resources/js/userPhotoModulescript.js"></script>
    <script src="../resources/js/modalAddPhotoModule.js"></script>
</head>
<body style="padding-right: 0px;">

<jsp:include page="modules/navbarModule.jsp"/>





<jsp:include page="modules/modalPhotoModule.jsp"/>
<jsp:include page="modules/modalUserPhotoModule.jsp"/>
<jsp:include page="modules/modalAddPhoto.jsp"/>

<button id="showPhotoBtn" type="button" data-toggle="modal" data-target="#showPhoto" style="display:none"></button>


<div class="container text-center">
    <div class="row">
        <div class="col-sm-4 well">
            <jsp:include page="modules/leftModule.jsp"/>
        </div>

        <div class="col-sm-8">
            <jsp:include page="modules/userPhotoModule.jsp"/>
        </div>

        <div id="hidePanel" style="display: none;">
        </div>



    </div>
</div>
</div>

<jsp:include page="modules/footerModule.jsp"/>

</body>
</html>