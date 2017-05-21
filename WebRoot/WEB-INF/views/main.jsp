<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>首页</title>
    <link rel="stylesheet" href="resources/statics/css/default.css" type="text/css">
    <script src="resources/statics/js/plugins/third/jquery-1.11.1.js"></script>
</head>

<body>
<div id="main-user" style="display: none;">${user.userName}</div>
<jsp:include page="model/head.html" />
<jsp:include page="model/menu.html" />


</body>
<script src="resources/statics/js/main.js"></script>
<script src="resources/statics/js/plugins/head.js"></script>
<script src="resources/statics/js/plugins/menu.js"></script>
</html>