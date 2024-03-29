<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap Login Form Template</title>
    <!-- CSS -->
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="assets/css/form-elements.css">
    <link rel="stylesheet" href="assets/css/style.css">
    <link rel="shortcut icon" href="assets/ico/favicon.png">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="assets/ico/apple-touch-icon-57-precomposed.png">
    <script src="assets/js/jquery-2.2.1.min.js"></script>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="assets/js/jquery.backstretch.min.js"></script>
    <script src="assets/js/scripts.js"></script>
    <script src="assets/js/jquery.validate.min.js"></script>
    <script>

        $(function () {
            $("#captchaImage").click(function () {
                $("#captchaImage").prop("src", "${pageContext.request.contextPath}/admin/getCode?time=" + new Date().getTime());
            });

        });

        function login() {
            var username = $("#form-username").val();
            var password = $("#form-password").val();
            var a = {"username": username, "password": password};
            console.log(a);
            var admin = JSON.stringify(a);
            $.ajax({
                url: '${pageContext.request.contextPath}/admin/adminLogin',
                type: 'post',
                data: admin,
                contentType: "application/json",
                success: function (data) {
                    console.log(data);
                    var code = $("#form-code").val();
                    console.log(code);
                    $.ajax({
                        url: '${pageContext.request.contextPath}/admin/checkCode',
                        type: 'post',
                        data: code,
                        contentType: "application/json",
                        success: function (data) {
                            alert(data);
                            window.location.href = "${pageContext.request.contextPath}/home.jsp";
                        },
                        error: function () {
                            $("#span").html("<h4 style='color: red'>验证码输入错误</h4>");
                            $("#form-username").val("");
                            $("#form-password").val("");
                            $("#form-code").val("");
                        }
                    });
                },
                error: function () {
                    $("#span").html("<h4 style='color: red'>用户名或密码错误</h4>");
                    $("#form-username").val("");
                    $("#form-password").val("");
                    $("#form-code").val("");
                }
            });
        }

    </script>
</head>

<body>

<!-- Top content -->
<div class="top-content">

    <div class="inner-bg">
        <div class="container">
            <div class="row">
                <div class="col-sm-8 col-sm-offset-2 text">
                    <h1><strong>CMFZ</strong> Login Form</h1>
                    <div class="description">
                        <p>
                            <a href="#"><strong>CMFZ</strong></a>
                        </p>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-6 col-sm-offset-4 form-box">
                    <div class="form-top" style="width: 450px">
                        <div class="form-top-left">
                            <h3>Login to showall</h3>
                            <p>Enter your username and password to log on:</p>
                        </div>
                        <div class="form-top-right">
                            <i class="fa fa-key"></i>
                        </div>
                    </div>
                    <div class="form-bottom" style="width: 450px">
                        <form role="form" action="${pageContext.request.contextPath}/Admin/login" method="post"
                              class="login-form" id="loginForm">
                            <span id="span">${requestScope.message}</span>
                            <span id="msgDiv"></span>
                            <div class="form-group">
                                <label class="sr-only" for="form-username">Username</label>
                                <input type="text" name="username" placeholder="请输入用户名..."
                                       class="form-username form-control" id="form-username" required>
                            </div>
                            <div class="form-group">
                                <label class="sr-only" for="form-password">Password</label>
                                <input type="password" name="password" placeholder="请输入密码..."
                                       minlength="2" class="form-password form-control" id="form-password" required>
                            </div>
                            <div class="form-group">
                                <img id="captchaImage" style="height: 47px" class="captchaImage"
                                     src="${pageContext.request.contextPath}/admin/getCode">
                                <input style="width: 289px;height: 50px;border:3px solid #ddd;border-radius: 4px;"
                                       type="text" placeholder="请输入验证码..." name="enCode" id="form-code" required>
                            </div>
                            <input type="button" onclick="login()"
                                   style="width: 400px;border:1px solid #9d9d9d;border-radius: 4px;"
                                   id="loginButtonId" value="登录">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>


</body>

</html>
