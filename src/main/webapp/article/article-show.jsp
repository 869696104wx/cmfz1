<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/kindeditor-all-min.js"></script>
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/lang/zh-CN.js"></script>
<script>
    $(function () {
        $('#articleTable').jqGrid({
            //加入整合bootstrap的属性
            styleUI: 'Bootstrap',
            url: '/article/showAllArticle',
            datatype: 'json',
            colNames: ['编号', '标题', '文章内容', '添加时间', '作者', '操作'],
            editurl: '/article/editArticle',
            colModel: [
                {
                    name: 'a_id',
                    key: true,
                    hidden: true,
                    align: 'center'
                },
                {
                    name: 'a_title',
                    align: 'center',
                    editable: true,
                    editrules: {required: true}
                },
                {
                    name: 'a_content',
                    align: 'center',
                    editable: true,
                    editrules: {required: true}
                },
                {
                    name: 'a_create_date',
                    align: 'center',
                    formatter: 'date',
                    formatoptions: {newformat: 'Y-m-d'}
                },
                {
                    name: 'a_author',
                    editable: true,
                    align: 'center',
                    edittype: 'select',// 指定编辑类型为下拉列表
                    /*editoptions:{value:"展示中:展示中;未展示:未展示"}*/
                    editoptions: {dataUrl: "/master/getMaster"} // dataUrl 加载返回的数据格式必须是一个下拉列表
                },
                {
                    name: 'aaa',
                    formatter: function (value, options, rows) {
                        return "<a class='btn btn-success' onclick=\"openModal('edit','" + rows.a_id + "')\" >修改文章</a>"
                    }
                }
            ],
            autowidth: true,
            prmNames: {id: 'a_id'},
            rownumbers: true,
            // 加入分页
            pager: '#articlePage',
            rowList: [3, 5, 7, 10],
            rowNum: 5,
            page: 1,
            viewrecords: true,
            mtype: 'POST',// 指定请求方式  默认为get
            caption: "<font size='5' face='楷体' >上师文章管理系统</font>",// 展示表格的标题
            height: '400px',// 设置数据表格高度
        }).navGrid('#articlePage',
            {// 控制是否显示指定工具栏
                add: false, edit: false, del: true, search: false, refresh: true
            }
        );
    })

    function openModal(oper, a_id) {
        KindEditor.html("#editor_id", "");
        var article = $("#articleTable").jqGrid("getRowData", a_id);
        //文章的数据赋值给form表单
        $("#article-title").val(article.a_title);
        $("#article-author").val(article.a_author);
        $("#article-id").val(article.a_id);
        KindEditor.html("#editor_id", article.a_content);
        $("#article-oper").val(oper);
        $("#article-modal").modal("show");
    }

    KindEditor.create("#editor_id", {
        allowFileManager: true,
        uploadJson: "${pageContext.request.contextPath}/kindeditor/upload",
        filePostName: "img",
        fileManagerJson: "${pageContext.request.contextPath}/kindeditor/getAll",
        resizeType: 1,
        //将文本域中的值同步到form当中
        afterBlur: function () {
            this.sync();
        }
    });

    function dealSave() {
        $.ajax({
            url: "${pageContext.request.contextPath}/article/editArticle",
            type: "post",
            data: {
                a_id: $("#article-id").val(),
                oper: $("#article-oper").val(),
                a_title: $("#article-title").val(),
                a_author: $("#article-author").val(),
                a_content: $(document.getElementsByTagName('iframe')[0].contentWindow.document.body).html()
            },
            dataType: "json",
            success: function () {
                $("#article-modal").modal("hide");
                $("#articleTable").trigger("reloadGrid");
            }
        })
    }
</script>
<div>
    <!-- Nav tabs -->
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab"
                                                  data-toggle="tab">展示文章</a></li>
        <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" onclick="openModal('add')"
                                   data-toggle="tab">添加文章</a></li>
    </ul>
</div>
<table id="articleTable"></table>
<div id="articlePage" style="height: 80px"></div>

<%--模态框--%>
<div class="modal fade" id="article-modal" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content" style="width: 702px">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">请输入文章信息</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="article-form">
                    <input id="article-id" type="hidden" name="id">
                    <input id="article-oper" type="hidden" name="oper">
                    <div class="form-group">
                        <label for="article-title" class="col-sm-2 control-label">标题</label>
                        <div class="col-sm-10">
                            <input type="text" name="title" class="form-control" id="article-title" placeholder="title">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="article-author" class="col-sm-2 control-label">作者</label>
                        <div class="col-sm-10">
                            <input type="text" name="author" class="form-control" id="article-author"
                                   placeholder="author">
                        </div>
                    </div>
                    <div class="form-group">
                         <textarea id="editor_id" name="content" style="width:700px;height:300px;">
                         </textarea>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="dealSave()">保存</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
<!-- /.modal -->