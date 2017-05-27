<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="searchPanel" class="col-xs-12 col-md-8 col-sm-12 removableElement">
    <div class="panel panel-default" style="height:483px;">
        <div class="panel-heading">
            <div style="width:80%; margin:auto; text-align: center; transform:scale(1.5);">
                <img src="resources/images/search-title.png" alt="Logo" height="40" align="top">
            </div>
        </div>
        <div class="panel-body table-responsive" style="height: 420px">
            <div style="width:50%; margin-left: auto;margin-right: auto;margin-top: 100px;">
                <div style="margin-top: 15px">
                    <div style="width:100%; margin-left: auto; margin-right: auto;text-align:center">
                        <button type="button" class="btn btn-link" data-toggle="modal" data-target="#searchModal">
                            параметры поиска
                        </button>

                        <!-- Modal -->
                        <jsp:include page="searchModalModule.jsp"/>
                    </div>
                </div>

            </div>

        </div>
    </div>
</div>
