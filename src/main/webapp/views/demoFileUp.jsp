<%@page contentType="text/html" pageEncoding="windows-1251" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
    <script src="../resources/js/jquery-3.2.0.min.js"></script>
    <script src="../resources/js/jquery.json.js"></script>
    <link href="../resources/css/bootstrap_min.css" rel="stylesheet" type="text/css"/>
    <title>Upload file to server</title>
</head>
<body>
<script>
    $(document).ready(function () {
        $("#submit").click(function () {
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
        });
    })
</script>
<h1>Привет. Просто показать как работает загрузка файла на сервер</h1>
<form id="formFile" enctype="multipart/form-data">
    <input type="file" name="ava"/>
    <div id="submit">Отправить</div>
</form>
<label id="labe"></label>
</body>
</html>
