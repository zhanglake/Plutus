<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>订单查询</title>
    <link rel="stylesheet" href="resources/statics/css/default.css" type="text/css">
    <link rel="stylesheet" href="resources/statics/css/button.css" type="text/css">
    <link rel="stylesheet" href="resources/statics/plugins/bootstrap-3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="resources/statics/plugins/bootstrap-table/src/bootstrap-table.css">
    <link rel="stylesheet" href="resources/statics/css/plugins/third/fileinput.css">
    <script src="resources/statics/js/plugins/third/jquery-1.11.1.js"></script>
    <style>
    </style>
</head>

<body>
<div id="main-user" style="display: none;">${user.userName}</div>
<%--<div class="alert alert-success" role="alert">xxxxxxxxxxxxxxxx</div>--%>
<jsp:include page="model/head.html"/>
<jsp:include page="model/menu.html"/>
<div class="right">
    <div class="tabs">
        <div class="tab selected" id="tab1">
            客户列表
        </div>
        <div class="tab" id="tab2">
            客户订单
        </div>
        <div class="tab" id="tab3">
            新增客户
        </div>
        <div class="tab" id="tab4">
            新建订单
        </div>
        <div class="tab" id="tab5">
            订单详情
        </div>
        <button type="button" class="button btn-4" id="add_customer" style="float: right;margin: 8px;">
            新增客户
        </button>
        <button type="button" class="button btn-4" id="add_order" style="float: right;margin: 8px;display: none;">
            新建订单
        </button>
        <button type="button" class="button btn-1" id="save_customer" style="float: right;margin: 8px;display: none;">
            保  存
        </button>
        <button type="button" class="button btn-1" id="save_order" style="float: right;margin: 8px;display: none;">
            保  存
        </button>
    </div>
    <div class="main-container">
        <div id="con1">
            <table id="customerTable"></table>
        </div>
        <div id="con2" style="display: none;">
            <table id="customerOrderTable"></table>
        </div>
        <div id="con3" style="display: none;">
            <form id="add_customer_form" style="width: 900px;margin: auto;">
                <div class="form-head first-head">
                    新增客户
                </div>
                <div class="form-body" style="height: 255px;">
                    <div class="form-group">
                        <label class="col-sm-2 text-right">客户姓名:</label>
                        <input class="col-sm-6" id="customerName" name="customerName"/>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 text-right">联系电话:</label>
                        <input class="col-sm-6" id="customerPhone" name="customerPhone"/>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 text-right">地 址:</label>
                        <textarea class="col-sm-6" id="address" name="address"></textarea>
                    </div>
                </div>
            </form>
        </div>
        <div id="con4" style="display: none;">
            <form id="add_order_form" style="width: 900px;margin: auto;">
                <div class="form-head first-head">
                    当前客户
                </div>
                <div class="form-body">
                    <div class="form-group">
                        <label class="col-sm-2 text-right">客户姓名:</label>
                        <input class="col-sm-4 show-only" id="customerName_a" disabled/>
                        <label class="col-sm-2 text-right">联系电话:</label>
                        <input class="col-sm-4 show-only" id="customerPhone_a" disabled/>
                    </div>
                    <div class="form-group" style="height: 100px;">
                        <label class="col-sm-2 text-right">地 址:</label>
                        <textarea class="col-sm-10" id="address_a" disabled></textarea>
                    </div>
                    <div class="form-group" style="height: 100px;">
                        <label class="col-sm-2 text-right">订单备注:</label>
                        <textarea class="col-sm-10" id="description_o"></textarea>
                    </div>
                    <div style="overflow: auto;">
                        <label class="control-label col-sm-2 text-right">上传文件</label>
                        <div class="col-sm-10" style="padding-left: 0px;">
                            <div id="fileuploadcontain">
                                <input id="file" name="file" class="file-loading" type="file" multiple data-min-file-count="1" data-show-preview="true">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-head">
                    填写订单
                </div>
                <div class="form-body" id="order_detail">
                </div>
            </form>
        </div>
        <div id="con5" style="display: none;">
            <form name="order-detail" style="width: 900px;margin: auto;">
                <div class="form-head first-head">
                    订单总览
                </div>
                <div class="form-body">
                    <div class="form-group">
                        <label class="col-sm-2 text-right">订单号:</label>
                        <input class="col-sm-2 show-only" id="code_show" disabled/>
                        <label class="col-sm-2 text-right">客户姓名:</label>
                        <input class="col-sm-2 show-only" id="customerName_show" disabled/>
                        <label class="col-sm-2 text-right">联系方式:</label>
                        <input class="col-sm-2 show-only" id="customerPhone_show" disabled/>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 text-right">订单日期:</label>
                        <input class="col-sm-2 show-only" id="createdDate_show" disabled/>
                        <label class="col-sm-2 text-right">送货日期:</label>
                        <input class="col-sm-2 show-only" id="deliveryDate_show" disabled/>
                        <label class="col-sm-2 text-right">总金额:</label>
                        <input class="col-sm-2 show-only" id="totalPrice_show" disabled/>
                    </div>
                    <div class="form-group" style="height: 100px;">
                        <label class="col-sm-2 text-right">备  注:</label>
                        <textarea class="col-sm-10" id="description_show" disabled></textarea>
                    </div>
                    <div style="overflow: auto;">
                        <label class="col-sm-2 text-right">订单附件:</label>
                        <div class="col-sm-10" id="files_show"></div>
                    </div>
                </div>
                <div class="form-head">
                    订单详情
                </div>
                <div class="form-body" id="order_detail_form_body_show">
                </div>
            </form>
        </div>
    </div>
</div>
</body>
<script src="resources/statics/plugins/bootstrap-3.3.7/js/bootstrap.min.js"></script>
<script src="resources/statics/plugins/bootstrap-table/src/bootstrap-table.js"></script>
<script src="resources/statics/plugins/bootstrap-table/src/locale/bootstrap-table-zh-CN.js"></script>
<script src="resources/statics/js/plugins/third/fileinput.js"></script>
<script src="resources/statics/js/plugins/file.js"></script>
<script src="resources/statics/js/customer.js"></script>
<script src="resources/statics/js/plugins/head.js"></script>
<script src="resources/statics/js/plugins/menu.js"></script>
</html>