var CustomerController = {
    selectedCustomer: {},
    uploadedFileIds: new Array(),
    init: function () {
        CustomerController.changeMenuClass();
        CustomerController.initCustomerTable();
        // 切换Tab
        $("#tab1").click(function () {
            CustomerController.toTab_1();
        });
        $("#tab2").click(function () {
            CustomerController.toTab_2();
        });
        // 新增客户
        $("#add_customer").click(function () {
            CustomerController.toTab_3();
        });
        // 保存客户
        $("#save_customer").click(function () {
            CustomerController.saveCustomer();
        });
        // 新建订单
        $("#add_order").click(function () {
            CustomerController.toTab_4();
            CustomerController.orderForm();
        });
        // 保存订单
        $("#save_order").click(function () {
            CustomerController.saveOrder();
        });
    },
    changeMenuClass: function () {
        $(".left .menu li").removeClass("selected");
        $(".left .menu li").eq(2).addClass("selected");
    },
    initCustomerTable: function () {
        $('#customerTable').bootstrapTable({
            url: "customer/table",//这个接口需要处理bootstrap table传递的固定参数
            method: "post",
            dataType: "json",
            queryParamsType: "limit",
            pagination: true, //分页
            singleSelect: false,
            search: false, //显示搜索框
            sidePagination: "server", //服务端处理分页
            striped: true,      //是否显示行间隔色
            clickToSelect: true,
            showRefresh: true,
            columns: [
                {
                    field: 'name',
                    title: '客户姓名',
                    width: '20%'
                },
                {
                    field: 'phone',
                    title: '联系电话',
                    width: '20%'
                },
                {
                    field: 'address',
                    title: '地址'
                }
            ],
            onClickRow: function (row, $element, field) {
                CustomerController.selectedCustomer = {
                    customerId: row.id,
                    name: row.name,
                    phone: row.phone,
                    address: row.address
                };
                CustomerController.initCustomerOrderTable(row.id);
                CustomerController.toTab_2();
            }
        });
    },
    initCustomerOrderTable: function (customerId) {
        $("#customerOrderTable").bootstrapTable('destroy');
        $('#customerOrderTable').bootstrapTable({
            url: "order/customer/table",//这个接口需要处理bootstrap table传递的固定参数
            method: "post",
            dataType: "json",
            queryParamsType: "limit",
            pagination: true, //分页
            singleSelect: false,
            search: false, //显示搜索框
            sidePagination: "server", //服务端处理分页
            striped: true,      //是否显示行间隔色
            clickToSelect: true,
            queryParams: function (params) {
                var data = {
                    "limit": params.limit,
                    "offset": params.offset,
                    "search": params.search,
                    "sort": params.sort,
                    "order": params.order,
                    "customerId": customerId
                }
                return data;
            },
            columns: [
                {
                    field: 'code',
                    title: '订单编号',
                    width: '15%'
                },
                {
                    field: 'createdDate',
                    title: '订单日期',
                    width: '15%'
                },
                {
                    field: 'deliveryDate',
                    title: '送货日期',
                    width: '15%'
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
                CustomerController.fillOrderData(row);
                var orderId = row.id;
                $.ajax({
                    url: "order/details",
                    type: "post",
                    data: {"orderId": orderId},
                    success: function (data) {
                        CustomerController.fillOrderDetailData(data);
                    }
                })
                CustomerController.toTab_5();
            }
        });
    },
    fillOrderData: function (row) {
        $("#files_show").html("");
        $("#code_show").val(row.code);
        $("#customerName_show").val(CustomerController.selectedCustomer.name);
        $("#customerPhone_show").val(CustomerController.selectedCustomer.phone);
        $("#createdDate_show").val(row.createdDate);
        $("#deliveryDate_show").val(row.deliveryDate);
        $("#totalPrice_show").val(row.totalPrice);
        $("#description_show").html(row.description);
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
                    $("#files_show").append(html_);
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
            $("#order_detail_form_body_show").append(html_);
        }
    },
    saveCustomer: function () {
        var customerData = {};
        var data = $("#add_customer_form").serializeArray();
        $.each(data, function () {
            customerData[this.name] = this.value;
        });
        $.ajax({
            url: "customer/add",
            type: "post",
            data: JSON.stringify(customerData),
            contentType : 'application/json',
            dataType: "json",
            success: function (data) {
                $('#customerTable').bootstrapTable('refresh');
                CustomerController.toTab_1();
            }
        });
    },
    orderForm: function () {
        CustomerController.clearOrderForm();
        CustomerController.appendForm_0();
        var index = 1;
        var customerId = CustomerController.selectedCustomer["customerId"];
        $("#customerName_a").val(CustomerController.selectedCustomer["name"]);
        $("#customerPhone_a").val(CustomerController.selectedCustomer["phone"]);
        $("#address_a").val(CustomerController.selectedCustomer["address"]);
        $("#add_row").click(function () {
            var html_ = '<form class="bs-callout bs-callout-info form_order_detail" id="form_' + index + '">';
            html_ += '<div class="form-group">';
            html_ += '<label class="col-sm-2 text-right">名  称:</label>';
            html_ += '<input class="col-sm-2" id="name_' + index + '" name="name" />';
            html_ += '<label class="col-sm-2 text-right">单  价:</label>';
            html_ += '<input class="col-sm-2" id="price_' + index + '" name="price" />';
            html_ += '<label class="col-sm-2 text-right">数  量:</label>';
            html_ += '<input class="col-sm-2" id="number_' + index + '" name="number" />';
            html_ += '</div>';
            html_ += '<div class="form-group" style="height: 75px;">';
            html_ += '<label class="col-sm-2 text-right">备  注:</label>';
            html_ += '<textarea class="col-sm-8" id="description_' + index + '" style="height: 75px;" name="description"></textarea>';
            html_ += '<div class="col-sm-2" style="text-align: center;">';
            html_ += '<button type="button" class="btn btn-danger del_row" id="del_row_' + index + '" style="margin-top: 30px;width: 80px;"> - </button>';
            html_ += '</div></div></form>';
            $("#order_detail").append(html_);
            index ++;
        });
        $(document).on("click", "#order_detail .del_row", function () {
            $(this).parent().parent().parent().remove();
        });
    },
    clearOrderForm: function () {
        // 文件上传 -- 先销毁，再初始化
        $('#file').fileinput('destroy');
        initFileInput("file");
        $("#file").on("fileuploaded", function (event, data, previewId, index) {
            CustomerController.uploadedFileIds.push(data.response.data[0]);
        });
        // input和textarea清空
        $("form[id='add_order_form'] input").val("");
        $("form[id='add_order_form'] textarea").val("");
        // detail清空
        $("#order_detail").html("");
    },
    clearCustomerForm: function () {
        // input和textarea清空
        $("#add_customer_form input").val("");
        $("#add_customer_form textarea").val("");
    },
    appendForm_0: function () {
        var html_ = '<form class="bs-callout bs-callout-info form_order_detail" id="form_0">';
        html_ += '<div class="form-group">';
        html_ += '<label class="col-sm-2 text-right">名 称:</label>';
        html_ += '<input class="col-sm-2" id="name_0" name="name"/>';
        html_ += '<label class="col-sm-2 text-right">单 价:</label>';
        html_ += '<input class="col-sm-2" id="price_0" name="price"/>';
        html_ += '<label class="col-sm-2 text-right">数 量:</label>';
        html_ += '<input class="col-sm-2" id="number_0" name="number"/>';
        html_ += '</div>';
        html_ += '<div class="form-group" style="height: 75px;">';
        html_ += '<label class="col-sm-2 text-right">备 注:</label>';
        html_ += '<textarea class="col-sm-8" id="description_0" style="height: 75px;" name="description"></textarea>';
        html_ += '<div class="col-sm-2" style="text-align: center;">';
        html_ += '<button type="button" class="btn btn-success" id="add_row" style="margin-top: 30px;width: 80px;"> + </button>';
        html_ += '</div></div></form>';
        $("#order_detail").append(html_);
    },
    saveOrder: function () {
        // 客户Id
        var customerId = CustomerController.selectedCustomer["customerId"];
        // 订单备注
        var desc = $("#description_o").val();
        // 订单详情
        var orderDetails = new Array();
        for (var i = 0; i < $(".form_order_detail").length; i++) {
            var orderDetailData = {};
            var data = $($(".form_order_detail")[i]).serializeArray();
            $.each(data, function () {
                orderDetailData[this.name] = this.value;
            });
            orderDetails.push(orderDetailData);
        }
        // 打包数据
        var data = {
            customerId: customerId,
            description:desc,
            deliveryDate: new Date(),
            details: orderDetails,
            files: CustomerController.uploadedFileIds
        };
        $.ajax({
            url: "order/add",
            type: "post",
            data: JSON.stringify(data),
            contentType : 'application/json',
            dataType: "json",
            success: function (data) {
                $('#customerOrderTable').bootstrapTable('refresh');
                CustomerController.toTab_2();
            }
        });
    },
    toTab_1: function () {
        $("#tab1").removeClass("unactive").addClass("selected");
        $("#tab2").removeClass("selected").removeClass("unactive");
        $("#tab3").removeClass("selected");
        $("#tab4").removeClass("selected");
        $("#tab5").removeClass("selected");
        $("#con1").show();
        $("#con2").hide();
        $("#con3").hide();
        $("#con4").hide();
        $("#con5").hide();
        $("#add_customer").show();
        $("#add_order").hide();
        $("#save_customer").hide();
        $("#save_order").hide();
    },
    toTab_2: function () {
        $("#tab1").removeClass("selected").addClass("unactive");
        $("#tab2").addClass("selected").removeClass("unactive");
        $("#tab3").removeClass("selected");
        $("#tab4").removeClass("selected");
        $("#tab5").removeClass("selected");
        $("#con1").hide();
        $("#con2").show();
        $("#con3").hide();
        $("#con4").hide();
        $("#con5").hide();
        $("#add_customer").hide();
        $("#add_order").show();
        $("#save_customer").hide();
        $("#save_order").hide();
    },
    toTab_3: function () {
        CustomerController.clearCustomerForm();
        $("#tab1").removeClass("selected").addClass("unactive");
        $("#tab2").removeClass("selected").removeClass("unactive");
        $("#tab3").addClass("selected");
        $("#tab4").removeClass("selected");
        $("#tab5").removeClass("selected");
        $("#con1").hide();
        $("#con2").hide();
        $("#con3").show();
        $("#con4").hide();
        $("#con5").hide();
        $("#add_customer").hide();
        $("#add_order").hide();
        $("#save_customer").show();
        $("#save_order").hide();
    },
    toTab_4: function () {
        $("#tab1").removeClass("selected").addClass("unactive");
        $("#tab2").removeClass("selected").addClass("unactive");
        $("#tab3").removeClass("selected");
        $("#tab4").addClass("selected");
        $("#tab5").removeClass("selected");
        $("#con1").hide();
        $("#con2").hide();
        $("#con3").hide();
        $("#con4").show();
        $("#con5").hide();
        $("#add_customer").hide();
        $("#add_order").hide();
        $("#save_customer").hide();
        $("#save_order").show();
    },
    toTab_5: function () {
        $("#tab1").removeClass("selected").addClass("unactive");
        $("#tab2").removeClass("selected").addClass("unactive");
        $("#tab3").removeClass("selected");
        $("#tab4").removeClass("selected");
        $("#tab5").addClass("selected");
        $("#con1").hide();
        $("#con2").hide();
        $("#con3").hide();
        $("#con4").hide();
        $("#con5").show();
        $("#add_customer").hide();
        $("#add_order").hide();
        $("#save_customer").hide();
        $("#save_order").hide();
    }
}

$(document).ready(function () {
    CustomerController.init();
});