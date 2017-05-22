function initFileInput(ctrlName, uploadUrl) {
    var control = $('#' + ctrlName);

    control.fileinput({
        uploadUrl: "aaa/bbb",//上传的地址
        uploadAsync: true,
        language: "zh",//设置语言
        showCaption: true,//是否显示标题
        showUpload: true, //是否显示上传按钮
        browseClass: "btn btn-primary", //按钮样式
        maxFileCount: 10,//最大上传文件数限制
        uploadAsync: true,
        previewFileIcon: '<i class="glyphicon glyphicon-file"></i>',
        allowedPreviewTypes: null,
        previewFileIconSettings: {
            'docx': '<i class="glyphicon glyphicon-file"></i>',
            'xlsx': '<i class="glyphicon glyphicon-file"></i>',
            'pptx': '<i class="glyphicon glyphicon-file"></i>',
            'jpg': '<i class="glyphicon glyphicon-picture"></i>',
            'pdf': '<i class="glyphicon glyphicon-file"></i>',
            'zip': '<i class="glyphicon glyphicon-file"></i>'
        }
    });

    control.on("fileuploaded", function (event, data, previewId, index) {
        alert("上传成功!");
    });
}