<%@page pageEncoding="UTF-8" contentType="text/html; UTF-8" isELIgnored="false" %>
<html lang="en">
<head>
    <%--引入bootStrap的css样式--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/boot/css/bootstrap.min.css">
    <%--引入jqgrid与bootstrap整合的css样式--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/jqgrid/css/trirand/ui.jqgrid-bootstrap.css">
    <%--引入jquery的js文件--%>
    <script src="${pageContext.request.contextPath}/statics/boot/js/jquery-2.2.1.min.js"></script>
    <%--引入bootStrap的js文件--%>
    <script src="${pageContext.request.contextPath}/statics/boot/js/bootstrap.min.js"></script>
    <%--引入jqgrid的js文件--%>
    <script src="${pageContext.request.contextPath}/statics/jqgrid/js/trirand/i18n/grid.locale-cn.js"></script>
    <script src="${pageContext.request.contextPath}/statics/jqgrid/js/trirand/jquery.jqGrid.min.js"></script>
    <script src="${pageContext.request.contextPath}/statics/jqgrid/js/ajaxfileupload.js"></script>
    <script src="${pageContext.request.contextPath}/echarts/echarts.min.js"></script>
    <script src="${pageContext.request.contextPath}/echarts/china.js"></script>
    <title>驰名法洲后台管理系统</title>
</head>

<body>
<%--顶部导航栏--%>
<div class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a href="" class="navbar-brand">驰名法州后台管理系统</a>
        </div>
        <div class="navbar-brand navbar-right" style="padding-right: 80px;">
            <a href="${pageContext.request.contextPath}/login/login.jsp">安全退出</a>
        </div>
        <div class="navbar-brand navbar-right" style="padding-right: 40px;">
            欢迎&nbsp;&nbsp;
            <span style="color: #fcf8e3">${sessionScope.admin.username}</span>
        </div>
    </div>
</div>

<%--主体内容--%>
<div class="row">
    <%--左侧手风琴--%>
    <div class="col-md-2" style="padding-left: 30px;">
        <div class="panel-group" id="accordion" align="center">
            <div class="panel panel-default">
                <div class="panel-heading" id="headingOne">
                    <h4 class="panel-title" style="height:35px; ">
                        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne"
                           aria-expanded="true" aria-controls="collapseOne">
                            轮播图管理
                        </a>
                    </h4>
                </div>
                <div id="collapseOne" align="center" class="panel-collapse collapse in" role="tabpanel"
                     aria-labelledby="headingOne">
                    <div class="panel-body">
                        <a class="list-group-item" style="width: 60%"
                           href="javascript:$('#content').load('${pageContext.request.contextPath}banner/banner-show.jsp')">所有轮播图</a>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading" id="headingTwo">
                    <h4 class="panel-title" style="height:35px; ">
                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                           href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                            专辑管理
                        </a>
                    </h4>
                </div>
                <div id="collapseTwo" align="center" class="panel-collapse collapse" role="tabpanel"
                     aria-labelledby="headingTwo">
                    <div class="panel-body">
                        <a class="list-group-item" style="width: 60%"
                           href="javascript:$('#content').load('${pageContext.request.contextPath}/album/album-show.jsp')">所有专辑</a>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading" id="headingThree">
                    <h4 class="panel-title" style="height:35px; ">
                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                           href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                            文章管理
                        </a>
                    </h4>
                </div>
                <div id="collapseThree" align="center" class="panel-collapse collapse" role="tabpanel"
                     aria-labelledby="headingThree">
                    <div class="panel-body">
                        <a class="list-group-item" style="width: 60%"
                           href="javascript:$('#content').load('${pageContext.request.contextPath}/article/article-show.jsp')">所有文章</a>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading" id="headingFour">
                    <h4 class="panel-title" style="height:35px; ">
                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                           href="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
                            用户管理
                        </a>
                    </h4>
                </div>
                <div id="collapseFour" align="center" class="panel-collapse collapse" role="tabpanel"
                     aria-labelledby="headingFour">
                    <div class="panel-body">
                        <a class="list-group-item" style="width: 60%"
                           href="javascript:$('#content').load('${pageContext.request.contextPath}/user/user-show.jsp')">所有用户</a>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <%--主体内容--%>
    <div class="col-md-10 " id="content">
        <%--巨幕--%>
        <div class="jumbotron">
            <h3 style="padding-left: 80px;">欢迎来到驰名法州后台管理系统!</h3>
        </div>
        <%--图片--%>
        <img src="img/shouye.jpg" alt="Responsive image" width="100%">
    </div>
</div>

<%--脚注--%>
<div class="panel panel-default">
    <div class="panel-footer" align="center" style="height: 50px;">驰名法州后台管理系统@百知教育2019.7.8</div>
</div>
</body>
</html>
