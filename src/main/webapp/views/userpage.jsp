<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script src="resources/js/jquery-1.4.2.min.js" type="text/javascript"></script>
<body>
    <input type="text" id="message"><button id="test">Load</button>
    <ul id="list"></ul>
    <script type="text/javascript">

    	var counter=0;
    	
        function testClick(e)
        {

            $.ajax({
                type: 'POST',
                url: "chat/1",
                contentType: 'application/json',
                data: JSON.stringify(message.value),
                success: function (res) {
                    var result;
                    try {
                        result = JSON.parse(res);
                    } catch (e) {
                        result = res;
                    }
                },
                error: function (jqXHR, textStatus, errorThrown) {
                }
            });
        }
        test.addEventListener("click", testClick);

        function getData()
        {
            $.ajax({
                type: 'GET',
                url: "chat/1/"+counter,
                contentType: 'application/json',
                success: function (res) {
                    var result;
                    try {
                        result = JSON.parse(res);
                    } catch (e) {
                        result = res;
                    }
                    var element;
                    element = document.createElement('li');
                    element.innerHTML = result;
                    list.appendChild(element);
                    counter++;
                    getData();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                	getData();
                }
            });
        }

        getData();

        
    </script>
</body>
</html>