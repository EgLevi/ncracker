<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <title><spring:message code="label.title"/></title>
</head>
<body>

<a href="<c:url value="/logout" />">
    <spring:message code="label.logout"/>
</a>

<h2><spring:message code="label.title"/></h2>

<form:form method="post" action="add" commandName="users_log">

    <table>
        <tr>
            <td><form:label path="login">
                <spring:message code="label.login"/>
            </form:label></td>
            <td><form:input path="login"/></td>
        </tr>
        <tr>
            <td><form:label path="password">
                <spring:message code="label.password"/>
            </form:label></td>
            <td><form:input path="password"/></td>
        </tr>
        <tr>
            <td><form:label path="email">
                <spring:message code="label.email"/>
            </form:label></td>
            <td><form:input path="email"/></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit"
                                   value="<spring:message code="label.addUser"/>"/></td>
        </tr>
    </table>
</form:form>

<h3><spring:message code="label.users"/></h3>
<c:if test="${!empty users_logList}">
    <table class="data">
        <tr>
            <th><spring:message code="label.login"/></th>
            <th><spring:message code="label.password"/></th>
            <th><spring:message code="label.email"/></th>
            <th>&nbsp;</th>
        </tr>
        <c:forEach items="${users_logList}" var="users_log">
            <tr>
                <td>${users_log.iduserlog}</td>
                <td>${users_log.login}</td>
                <td>${users_log.password}</td>
                <td>${users_log.email}</td>
                <td><a href="delete/${users_log.iduserlog}"><spring:message code="label.delete"/></a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>

</body>
</html>