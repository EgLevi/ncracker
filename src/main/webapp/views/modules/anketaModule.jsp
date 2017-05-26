<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row">
    <div class="col-md-6">
        <div class="about">
            <div class="left-group">

                <div id="categ" class="list-group" >
                    <c:forEach items="${CategoryList}" var="category" varStatus="status">
                        <a href="#" value="${category}" class="list-group-item">${category}</a>
                    </c:forEach>
                </div>

            </div>
        </div>
    </div>

    <div class="col-md-6">
        <form>
            <select id="select" multiple class="chosen-select" tabindex="-1">
            </select>
        </form>
    </div>
</div>

<div class="row">
    <div class="col-md-2">
        <input type="button" value="Сохранить" id="but" onclick="save()">
    </div>
</div>

<div class="row">
    <div class="col-md-6">
        <select id="writeUser" multiple class="select-tag" tabindex="-1" aria-hidden="true">
        </select>
    </div>
</div>
