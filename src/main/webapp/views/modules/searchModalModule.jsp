<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal fade" id="searchModal" role="dialog" style="position:absolute;">
    <div class="modal-dialog my-modal-dialog" style="">
        <div class="panel panel-primary" style="background-color:#e5e8ed; width: 400px; height:483px;">
            <div class="panel-heading" style="background-color:#e5e8ed">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <label style="color:#337ab7">Интересы для поиска</label>
            </div>
            <div class="panel-body" style="height: 75%">
                <div>
                    <div style="width:100px;margin:auto;">
                        <label class="fontface" style="text-align: center">Выбранные интересы</label>
                    </div>
                    <div id="listInterest" style="overflow: auto; height:55px;">
                        <div id="flistelement" class="listSelInterest" style="display: none;">
                            <label id="flabel" style="display: none;">0</label>
                            <span id="fspan" class="label fontface selInterest" style="color:#337ab7">Default Label</span>
                            <div id="fbtn" class="btn remBtn"><span class="glyphicon glyphicon-remove" style="color:#337ab7"></span></div>
                        </div>
                    </div>
                    <div style="margin-top:20px">
                        <label class="fontface">Категория</label>
                        <select id="category" class="form-control fontface" style="width:100%">
                        </select>

                        <div style="margin-top:10px;"><label class="fontface">Интересы</label></div>
                        <div class="table-responsive" style="height: 130px;">
                            <div id="interests" class="btn-group-vertical table-responsive" style="width:100%;">
                                <div id="fdivinterest">
                                    <label id="flabelinterest" style="display:none">0</label>
                                    <button id="finterest" type="button" class="btn btn-default fontface"
                                            style="display:none; width:100%">Apple
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="panel-footer" style="background-color:#e5e8ed;">
                <div style="margin-left:auto; width:35%">
                    <button id="okBtn" type="button" class="btn btn-primary">OK</button>
                    <button id="cancelBtn" type="button" class="btn btn-primary" data-dismiss="modal">Cancel</button>
                </div>
            </div>
        </div>
    </div>
</div>