<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="well">
    <p><a href="#">Мои интересы</a></p>
    <p>
        <c:forEach items="${userInfo.interests}" var="i">
        <span class="label label-primary">${i.interestName}</span>
    </c:forEach>
    </p>
</div>