<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<script>
    $(function () {
        $('#userTable').jqGrid({
            //加入整合bootstrap的属性
            styleUI: 'Bootstrap',
            url: '/user/showAllUser',
            datatype: 'json',
            colNames: ['编号', '头像', '用户名', '法号', '省份', '城市', '性别', '创建日期', '联系方式', '师傅', '状态'],
            editurl: '/user/editUser',
            colModel: [
                {name: 'u_id', key: true, hidden: true, align: 'center'},
                {
                    name: 'u_photo', align: 'center',
                    formatter: function (value, options, row) {
                        var v = "<img src='img/" + row.u_photo + "' width='100px' height='100px'>";
                        return v;
                    }
                },
                {name: 'u_username', align: 'center'},
                {name: 'u_dharma', align: 'center'},
                {name: 'u_province', align: 'center'},
                {name: 'u_city', align: 'center'},
                {name: 'u_sex', align: 'center'},
                {
                    name: 'u_create_date', align: 'center',
                    formatter: 'date',
                    formatoptions: {newformat: 'Y-m-d'}
                },
                {name: 'u_phone', align: 'center'},
                {name: 'm_name', align: 'center'},
                {
                    name: 'u_status', editable: true, align: 'center',
                    edittype: 'select',// 指定编辑类型为下拉列表
                    editoptions: {value: "正常:正常;冻结:冻结"}
                }
            ],
            autowidth: true,
            rownumbers: true,
            prmNames: {id: 'u_id'},
            // 加入分页
            pager: '#userPage',
            rowList: [3, 5, 10, 15],
            rowNum: 5,
            page: 1,
            viewrecords: true,
            mtype: 'POST',// 指定请求方式  默认为get
            caption: "<font size='5' face='楷体' >用户管理系统</font>",// 展示表格的标题
            height: '400px',// 设置数据表格高度
        }).navGrid('#userPage',
            {// 控制是否显示指定工具栏
                add: false, edit: true, del: false, search: false, refresh: true
            },
            { // 控制编辑的表单
                closeAfterEdit: true
            }
        );
    })

    function UserOut() {
        window.location.href = "${pageContext.request.contextPath}/user/userOut"
    }

    /*打开用户模态框*/
    function createShow() {
        $("#user-modal").modal("show");
    }
</script>
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
            data: ['男', '女']
        },
        xAxis: {
            data: ["第一周", "第二周", "第三周"]
        },
        yAxis: {},
        series: []
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    $.ajax({
        url: "${pageContext.request.contextPath}/user/getAllCeeate",
        dataType: "json",
        type: "get",
        success: function (data) {
            console.log(data.Man);
            console.log(data.Woman);
            myChart.setOption({
                series: [{
                    name: '男',
                    type: 'line',
                    data: data.Man
                }, {
                    name: '女',
                    type: 'line',
                    data: data.Woman
                }]
            });
        }
    })
</script>
<div>
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab"
                                                  data-toggle="tab">展示用户</a></li>
        <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" onclick="UserOut()"
                                   data-toggle="tab">用户数据导出</a></li>
        <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" onclick="createShow()"
                                   data-toggle="modal">近期注册情况展示</a></li>
    </ul>
</div>
<table id="userTable"></table>
<div id="userPage" style="height: 80px"></div>

<%--模态框--%>
<div class="modal fade" id="user-modal" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content" style="width: 702px">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">用户近期注册情况展示图</h4>
            </div>
            <div class="modal-body">
                <div id="main" style="width: 600px;height:400px;"></div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">不想看了</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
<!-- /.modal -->