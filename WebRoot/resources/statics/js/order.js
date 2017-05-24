var OrderController = {
    init: function () {
        OrderController.changeMenuClass();
        OrderController.initOrderTable();
        // 切换Tab
        $("#tab1").click(function () {
            OrderController.toTab_1();
        });
    },
    changeMenuClass: function () {
        $(".left .menu li").removeClass("selected");
        $(".left .menu li").eq(1).addClass("selected");
    },
    initOrderTable: function () {
        $('#orderTable').bootstrapTable({
            url: "order/table",//这个接口需要处理bootstrap table传递的固定参数
            method: "post",
            dataType: "json",
            queryParamsType: "limit",
            pagination: true, //分页
            singleSelect: false,
            search: false, //显示搜索框
            sidePagination: "server", //服务端处理分页
            striped: true,      //是否显示行间隔色
            clickToSelect: true,
            columns: [
                {
                    field: 'code',
                    title: '订单编号',
                    width: '10%'
                },
                {
                    field: 'customerName',
                    title: '客户姓名',
                    width: '10%'
                },
                {
                    field: 'customerPhone',
                    title: '联系电话',
                    width: '15%'
                },
                {
                    field: 'createdDate',
                    title: '订单日期',
                    width: '10%'
                },
                {
                    field: 'deliveryDate',
                    title: '送货日期',
                    width: '10%'
                },
                {
                    field: 'totalPrice',
                    title: '金额',
                    width: '10%'
                },
                {
                    field: 'description',
                    title: '备注'
                }
            ],
            onClickRow: function (row, $element, field) {
                OrderController.fillOrderData(row);
                var orderId = row.id;
                $.ajax({
                    url: "order/details",
                    type: "post",
                    data: {"orderId": orderId},
                    success: function (data) {
                        OrderController.fillOrderDetailData(data);
                    }
                })
                OrderController.toTab_2();
            }
        });
    },
    fillOrderData: function (row) {
        $("#files").html("");
        $("#code").val(row.code);
        $("#customerName").val(row.customerName);
        $("#customerPhone").val(row.customerPhone);
        $("#createdDate").val(row.createdDate);
        $("#deliveryDate").val(row.deliveryDate);
        $("#totalPrice").val(row.totalPrice);
        $("#description").html(row.description);
        // 查询附件
        if (row.files) {
            $.ajax({
                url: "file/query",
                type: "post",
                data: {fileStr: row.files},
                success: function (data) {
                    var files = data.data;
                    var html_ = '';
                    for (var i = 0; i < files.length; i++) {
                        html_ += '<div style="margin: 10px 20px;display: inline-block;width: 70px;">';
                        if (files[i].fileType && (files[i].fileType == 'jpg' || files[i].fileType == 'jpeg'
                            || files[i].fileType == 'png' || files[i].fileType == 'gif')) {
                            html_ += '<img src="' + files[i].path + '" style="width: 70px;height: 90px;">';
                        } else {
                            html_ += '<img src="resources/statics/img/file.jpg" style="width: 70px;height: 90px;">';
                        }
                        html_ += '<a target="_blank" href="' + files[i].path + '" style="display: inline-block;max-width: 70px;overflow: hidden;">' + files[i].fileName + '</a>';
                        html_ += '</div>';
                    }
                    $("#files").append(html_);
                }
            })
        }
    },
    fillOrderDetailData: function (details) {
        for (var i = 0; i < details.length; i++) {
            var totalPrice = parseFloat(details[i].price) * parseFloat(details[i].number);
            var html_ = '';
            html_ += '<div class="bs-callout bs-callout-info">';
            html_ += '<div class="form-group">';
            html_ += '<label class="col-sm-1 text-right">名  称:</label>';
            html_ += '<input class="col-sm-2 show-only" id="name_' + i + '" disabled value="' + details[i].name + '" />';
            html_ += '<label class="col-sm-1 text-right">单  价:</label>';
            html_ += '<input class="col-sm-2 show-only" id="price_' + i + '" disabled value="' + details[i].price + '" />';
            html_ += '<label class="col-sm-1 text-right">数  量:</label>';
            html_ += '<input class="col-sm-2 show-only" id="number_' + i + '" disabled value="' + details[i].number + '" />';
            html_ += '<label class="col-sm-1 text-right">总  价:</label>';
            html_ += '<input class="col-sm-2 show-only" id="detail_totalPrice_' + i + '" disabled value="' + totalPrice + '" />';
            html_ += '</div>';
            html_ += '<div class="form-group" style="height: 75px;">';
            html_ += '<label class="col-sm-1 text-right">备  注:</label>';
            html_ += '<textarea class="col-sm-11" id="detail_description_' + i + '" style="height: 50px" disabled>';
            html_ += details[i].description;
            html_ += '</textarea>';
            html_ += '</div></div>';
            $("#order_detail_form_body").append(html_);
        }
    },
    toTab_1: function () {
        $("#tab1").removeClass("unactive").addClass("selected");
        $("#tab2").removeClass("selected");
        $("#con1").show();
        $("#con2").hide();
    },
    toTab_2: function () {
        $("#tab1").removeClass("selected").addClass("unactive");
        $("#tab2").addClass("selected");
        $("#con1").hide();
        $("#con2").show();
    }
}

$(document).ready(function () {
    OrderController.init();
});