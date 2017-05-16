<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="myPhotoPanel" class="col-md-8 removableElement" style="display:none;">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3>Мои Фото</h3>
        </div>
        <div class="panel-body" style="height: 420px">
            <div id="myPhotoContent" class="table-responsive" style="height: 380px">
            </div>
        </div>
    </div>
</div>