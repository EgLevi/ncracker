<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal fade" id="showPhoto" role="dialog" style="position:absolute; top:30px; height:600px;">
    <div class="modal-dialog my-modal-dialog" style="width:900px; margin:auto">
        <div class="panel panel-default" style="width:900px; height:500px;">
            <div class="panel-heading">
                <img src="resources/images/logo4.png" alt="Logo" height='40' align='top'>
                <button id="closeModalPhoto" type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="panel-body" style="position:relative;">
                <img id="currentPhoto" src="" style="position:absolute;">
            </div>
        </div>
    </div>
</div>