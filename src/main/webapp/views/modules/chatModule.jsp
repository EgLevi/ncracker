<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="row">
    <div class="col-sm-12">
        <div class="panel panel-default text-left">
            <div id="con-over" class="panel-body container-overflow">
                <div class="row">
                    <ul class="chat-in list-group">
                        <li class="list-group-item">
                            <div class="row">
                                <div class="col-sm-1 ">
                                    <img class="img-rounded" width="32"
                                         src="http://ehoop.ru/uploads/posts/2016-02/1455402445_512_52.jpg">
                                </div>
                                <div class="col-sm-11 message">Лови файлик
                                </div>
                                <div class="col-sm-4 col-sm-offset-8">
                                    <a href="#" class="btn btn-link"><span
                                            class="glyphicon glyphicon-paperclip"></span> Filename</a>
                                </div>

                            </div>
                        </li>
                        <li class="list-group-item">
                            <div class="row">
                                <div class="col-sm-1 ">
                                    <img class="img-rounded" width="32"
                                         src="http://ehoop.ru/uploads/posts/2016-02/1455402445_512_52.jpg">
                                </div>
                                <div class="col-sm-11 message">О, спасибо!
                                </div>
                            </div>
                        </li>

                    </ul>
                </div>
            </div>
            <div class="row send-msg">
                <div class="col-sm-9 center-block">
                    <div>
                        <textarea placeholder="Введите сообщение" class="form-control"
                                  style="resize: vertical"></textarea>
                    </div>
                </div>
                <div class="col-sm-3 ">
                    <div class="col-sm-3"><span id="send-file" class="glyphicon glyphicon-paperclip"></span>
                    </div>
                    <div class="col-sm-4">
                        <button id="send-msg" class="btn btn-default"><span id="send-msg-span"
                                                                            class="glyphicon glyphicon-send"></span>
                        </button>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>