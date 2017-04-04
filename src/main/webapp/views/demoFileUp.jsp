<%-- 
    Document   : demoFileUp
    Created on : 05.04.2017, 0:57:56
    Author     : vara
--%>

<%@page contentType="text/html" pageEncoding="windows-1251"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
        <title>Upload file to server</title>
    </head>
    <body>
        <h1>Привет. Просто показать как работает загрузка файла на сервер</h1>
        <form action="http://localhost:8080/upload" method="post" enctype="multipart/form-data">
		<input name="description" type="text"><br>
		<input name="data" type="file"><br>
		<input type="submit"><br>
	</form>
    </body>
</html>
