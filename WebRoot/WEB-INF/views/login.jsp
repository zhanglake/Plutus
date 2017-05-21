<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Plutus</title>
</head>
<body>
<div style="margin:100px auto;width: 600px;height:400px;background-color: lavender;text-align: center">
    <br>

    <form method="post" action="tologin">
        <h3>登录</h3>
        <br>
        <br>
        <label>用户名:</label><input type="text" name="userName">
        <br> <br>
        <label>密 码:</label><input type="password" name="password">
        <br><br> <br>

        <div style="text-align: center">
            <button type="submit">登录</button>
            <button type="reset">重置</button>
            <br>

            <p><a href="./model/head.html">还没有帐号？点击这里注册！</a></p>
        </div>
    </form>
</div>
</body>
</html>
