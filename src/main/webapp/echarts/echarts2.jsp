<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<html lang="en">
<head>
    <script src="${pageContext.request.contextPath}/echarts/echarts.min.js"></script>
    <script src="${pageContext.request.contextPath}/echarts/china.js"></script>
    <script src="${pageContext.request.contextPath}/statics/boot/js/jquery-2.2.1.min.js"></script>
    <title>Document</title>
    <!-- 引入 ECharts 文件 -->
</head>
<body>
<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="main" style="width: 600px;height:400px;"></div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: 'ECharts 入门示例'
        },
        tooltip: {},//展示对应光标移入的详细信息
        legend: {
            data: ['1', '2']
        },
        xAxis: {
            data: ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10"]
        },
        yAxis: {},
        series: [{
            name: '1',
            type: 'line',
            data: [3.5, 2, 1, 0.4, 0, 0.4, 1, 2, 3.5]
        }, {
            name: '1',
            type: 'line',
            data: [3.5, 5, 5.6, 5.2, 4, 5.2, 5.6, 5.2, 3.5]
        }]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    /*$.ajax({
        url:"{pageContext.request.contextPath}/echarts/getAll",
        dataType:"json",
        type:"get",
        success:function (data) {
            myChart.setOption({
                series: [{
                    data: data
                }]
            });
        }
    })*/
</script>
</body>
</html>