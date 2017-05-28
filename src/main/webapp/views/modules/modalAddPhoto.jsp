<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal fade" id="showAddPhoto" role="dialog" style="position:absolute; top:30px; height:600px;">
    <div class="modal-dialog my-modal-dialog" style="width:900px; margin:auto">
        <div class="panel panel-default" style="width:900px; height:500px;">
            <div class="panel-heading">
                <img src="resources/images/logo4.png" alt="Logo" height='40' align='top'>
                <button id="closeModalAddPhoto" type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="panel-body" style="position:relative; height:74%;">
                <img id="addPhoto" src="" style="position:absolute;">
            </div>
            <div class="panel-footer">
                <form id="addPhotoForm" method="get" action="userpage" enctype="multipart/form-data" float="right">
                    <table width="0">
                        <tr>
                            <td>
                                <div class="form-group">
                                    <div class="inputFile">
                                        <label style="margin-bottom: 0px;">
                                                    <span class="btn btn-primary fileinput-button" id="IFBtn" style="">
                                                        <span>Выбрать фото</span>
                                                        <input id="addPhotoSelect" name="photo" type="file" multiple/>
                                                    </span>
                                        </label>
                                        <label id="addPhotoError" class="label label-danger"></label>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="form-group">
                                    <button id="addPhotoSubmit" type="submit" class="btn btn-primary">Сохранить</button>
                                </div>
                            </td>
                            <td>
                                <div id="successLoadPhoto" style="margin-left: 10px; margin-bottom: 7px; display:none;"><span class="glyphicon glyphicon-ok" style="color:#337ab7; transform: scale(2);"></span></div>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </div>
</div>