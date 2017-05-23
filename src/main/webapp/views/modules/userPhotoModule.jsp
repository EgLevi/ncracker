<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="myPhotoPanel" class="col-md-9 removableElement">
    <div class="panel panel-default">
        <div class="panel-heading">
            <label>Мои Фото</label>
            <div class="close" data-toggle="modal" data-target="#showAddPhoto" style="float:right"><span class="glyphicon glyphicon-plus" style="color:#337ab7;"></span></div>
        </div>
        <div class="panel-body" style="height: 420px">
            <div id="myPhotoContent" class="table-responsive" style="height: 380px">
                <table width="0">
                    <tbody>
                        <c:set var = "i" scope = "session" value = "${0}"/>
                        <c:forEach items="${photos}" var="photo">
                            <c:if test="${i%4==0}">
                                <tr>
                            </c:if>
                                <td>
                                    <span class="button photoBtn" style="padding-right: 0px;padding-left: 0px; position:relative;">
                                        <button type="button" style="position:absolute; right:-10px; top:-42px; color:#ff0000; display: none;" class="close">×</button>
                                        <img class="hrefImg media-object" src = "${photo.photoRef}" style="width:120px; height:80px; display: inline-block; margin-top: 10px; margin-left:17px;">
                                    </span>
                                </td>
                            <c:if test="${i%4==3}">
                                </tr>
                            </c:if>
                            <c:set var = "i" scope = "session" value = "${i+1}"/>
                        </c:forEach>
                        <c:if test="${i%4!=3}">
                            </tr>
                        </c:if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>