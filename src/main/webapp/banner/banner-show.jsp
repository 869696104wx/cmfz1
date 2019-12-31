<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<script>
    $(function () {
        $('#bannerTable').jqGrid({
            //加入整合bootstrap的属性
            styleUI: 'Bootstrap',
            url: '/banner/showAllBanner',
            datatype: 'json',
            colNames: ['图编号', '图名', '图片展示', '图简介', '展出状态', '填充日期'],
            editurl: '/banner/editBanner',
            colModel: [
                {name: 'b_id', key: true, align: 'center', hidden: true, search: false},
                {
                    name: 'b_name', align: 'center', editable: true, search: false,
                    editrules: {required: true}
                },
                {
                    name: 'b_cover', align: 'center', search: false, editable: true,
                    formatter: function (value, options, row) { // 允许自定义单元格展示的内容，函数的返回值会展示在单元格中
                        /** value 单元格正常匹配到的数据
                         * options 当前单元格操作属性对象
                         * row 当前行的数据对象*/
                        /*console.log(value, options, row);*/
                        var v = "<img src='img/" + row.b_cover + "' title='" + row.b_describe + "' width='100px' height='100px'>";
                        return v;
                    },
                    edittype: 'file'
                },
                {name: 'b_describe', align: 'center', editable: true, search: false, editrules: {required: true}},
                {
                    name: 'b_status', editable: true, align: 'center', search: false,
                    edittype: 'select',// 指定编辑类型为下拉列表
                    editoptions: {value: "展示中:展示中;未展示:未展示"}
                },
                {
                    name: 'b_create_date', align: 'center', search: false,
                    formatter: 'date',
                    formatoptions: {newformat: 'Y-m-d'}
                }
            ],
            autowidth: true,
            prmNames: {id: 'b_id'},
            // 加入分页
            pager: '#bannerPage',
            rowList: [1, 2, 3, 5],
            rowNum: 3,
            page: 1,
            rownumbers: true,
            viewrecords: true,
            mtype: 'POST',// 指定请求方式  默认为get
            caption: "<font size='5' face='楷体' >轮播图管理系统</font>",// 展示表格的标题
            height: '400px',// 设置数据表格高度
        }).navGrid('#bannerPage',
            {// 控制是否显示指定工具栏
                add: true, edit: true, del: true, search: false, refresh: true
            },
            { // 控制编辑的表单
                closeAfterEdit: true,
                beforeShowForm: function (frm) {
                    var v = $("#b_cover");
                    console.log(v)
                    frm.find("#b_cover").attr("disabled", true);
                    frm.find("#tr_b_cover").css("display", "none");
                }
            },
            { // 控制添加
                closeAfterAdd: true,
                afterSubmit: function (response) {
                    var status = response.responseJSON.status;	//返回的map中的status
                    var mes = response.responseJSON.message;	//返回的map中的message，保存的是插入的banner的id
                    if (status) {
                        // 文件上传		ajaxFileUpload
                        $.ajaxFileUpload({
                            url: '${pageContext.request.contextPath}/banner/upload',
                            type: "post",
                            fileElementId: "b_cover",
                            data: {b_id: mes},
                            success: function () {
                                //刷新页面
                                $("#bannerTable").trigger("reloadGrid");
                            }
                        })
                    }
                    return "hjh";
                }
            },
            {},// 控制删除操作
            { // 控制搜索
                closeAfterSearch: true,
            }
        );
    })
</script>

<table id="bannerTable"></table>
<div id="bannerPage" style="height: 80px"></div>

