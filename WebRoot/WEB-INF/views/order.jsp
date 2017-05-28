<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>订单查询</title>
    <link rel="stylesheet" href="resources/statics/css/default.css" type="text/css">
    <link rel="stylesheet" href="resources/statics/plugins/bootstrap-3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="resources/statics/plugins/bootstrap-table/src/bootstrap-table.css">
    <script src="resources/statics/js/plugins/third/jquery-1.11.1.js"></script>
    <style>
    </style>
</head>

<body>
<div id="main-user" style="display: none;">${user.userName}</div>
<jsp:include page="model/head.html"/>
<jsp:include page="model/menu.html"/>


<div class="right">
    <div class="tabs">
        <div class="tab selected" id="tab1">
            订单列表
        </div>
        <div class="tab" id="tab2">
            订单详情
        </div>
    </div>
    <div class="main-container">
        <div id="con1">
            <table id="orderTable"></table>
        </div>
        <div id="con2" style="display: none;">
            <form name="order-detail" style="width: 900px;margin: auto;">
                <div class="form-head first-head">
                    订单总览
                </div>
                <div class="form-body">
                    <div class="form-group">
                        <label class="col-sm-2 text-right">订单号:</label>
                        <input class="col-sm-2 show-only" id="code" disabled/>
                        <label class="col-sm-2 text-right">客户姓名:</label>
                        <input class="col-sm-2 show-only" id="customerName" disabled/>
                        <label class="col-sm-2 text-right">联系方式:</label>
                        <input class="col-sm-2 show-only" id="customerPhone" disabled/>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 text-right">订单日期:</label>
                        <input class="col-sm-2 show-only" id="createdDate" disabled/>
                        <label class="col-sm-2 text-right">送货日期:</label>
                        <input class="col-sm-2 show-only" id="deliveryDate" disabled/>
                        <label class="col-sm-2 text-right">总金额:</label>
                        <input class="col-sm-2 show-only" id="totalPrice" disabled/>
                    </div>
                    <div class="form-group" style="height: 100px;">
                        <label class="col-sm-2 text-right">备  注:</label>
                        <textarea class="col-sm-10" id="description" disabled></textarea>
                    </div>
                    <div style="overflow: auto;">
                        <label class="col-sm-2 text-right">订单附件:</label>
                        <div class="col-sm-10" id="files"></div>
                    </div>
                </div>
                <div class="form-head">
                    订单详情
                </div>
                <div class="form-body" id="order_detail_form_body">
                </div>
            </form>
        </div>
    </div>
</div>

</body>
<script src="resources/statics/plugins/bootstrap-3.3.7/js/bootstrap.min.js"></script>
<script src="resources/statics/plugins/bootstrap-table/src/bootstrap-table.js"></script>
<script src="resources/statics/plugins/bootstrap-table/src/locale/bootstrap-table-zh-CN.js"></script>
<script src="resources/statics/js/order.js"></script>
<script src="resources/statics/js/plugins/head.js"></script>
<script src="resources/statics/js/plugins/menu.js"></script>
</html>