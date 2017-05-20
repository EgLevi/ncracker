<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal fade" id="modalPhoto" role="dialog" style="position:absolute; top:30px;">
    <div class="modal-dialog my-modal-dialog" style="width:450px; margin:auto">
        <div class="panel panel-primary" style="background-color:#e5e8ed; width:450px">
            <div class="panel-heading" style="background-color:#e5e8ed">
                <img src="resources/images/logo4.png" alt="Logo" height='40' align='top'>
                <button id="closeModal" type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="panel-body">
                <div class="photo-wrapper" style="width:150px; margin: 0 auto;">
                    <h1 class="logo">
                        <img id="avatar" src="${userInfo.photo_ava}" class="img-circle" height="140" width="140" alt="Avatar">
                    </h1>
                </div>
                <form id="photoeditform" method="get" action="userpage" enctype="multipart/form-data">
                    <div class="form-group">
                        <div class="inputFile">
                            <label style="margin-bottom:7px;">
                                                    <span class="btn btn-primary fileinput-button" id="IFBtn" style="width:417px;">
                                                        <span>Выбрать фото</span>
                                                        <input id="selectPhoto" name="photo_ava" type="file" multiple/>
                                                    </span>
                            </label>
                            <label id="photoError" class="label label-danger"></label>
                        </div>
                    </div>
                    <div class="form-group" id="btnSubmit">
                        <button type="submit" class="btn btn-primary btn-block">Сохранить</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
