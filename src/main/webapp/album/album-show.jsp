<%@page isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<script>
    $(function () {
        $("#albumTable").jqGrid({
            //加入整合bootstrap的属性
            styleUI: 'Bootstrap',
            url: '${pageContext.request.contextPath}/album/showAllAlbum',
            datatype: "json",
            colNames: ['编号', '标题', '封面', '作者', '播音', '分数', '集数', '简介', '时间'],
            editurl: '${pageContext.request.contextPath}/album/editAlbum',
            colModel: [
                {name: 'album_id', hidden: true, key: true, align: 'center'},
                {
                    name: 'album_title',
                    align: 'center',     //展示居中
                    editable: true,     //可以进行操作
                    search: true,        //查询的时候带它
                    editrules: {required: true}   //必须有值
                },
                {
                    name: 'album_cover', align: 'center', search: false, editable: true,
                    formatter: function (value, options, row) {
                        var v = "<img src='img/" + row.album_cover + "' width='100px' height='100px'>";
                        return v;
                    },
                    edittype: 'file'
                },
                {
                    name: 'album_author', align: 'center', editable: true, search: true,
                    editrules: {required: true}
                },
                {
                    name: 'album_beam', align: 'center', editable: true, search: true,
                    editrules: {required: true}
                },
                {
                    name: 'album_score', align: 'center', editable: true, search: true,
                    editrules: {required: true}
                },
                {
                    name: 'album_count', align: 'center', search: true,
                },
                {
                    name: 'album_intro', align: 'center', editable: true, search: true,
                    editrules: {required: true}
                },
                {
                    name: 'album_create_date', align: 'center', search: false,
                    formatter: 'date',
                    formatoptions: {newformat: 'Y-m-d'}
                }
            ],
            autowidth: true,
            prmNames: {id: 'album_id'},
            height: 400,
            rowList: [3, 5, 10, 20],
            rowNum: 3,
            page: 1,
            // 加入分页
            pager: '#albumPager',
            viewrecords: true,
            rownumbers: true,
            mtype: 'POST',// 指定请求方式  默认为get
            caption: "<font size='5' face='楷体' >展示专辑</font>",// 展示表格的标题
            multiselect: false,
            subGrid: true,

            subGridRowExpanded: function (subgrid_id, row_id) {//1.当前父容器的id   2.当前专辑的id
                var chapterTable = subgrid_id + "_t";
                var chapterPager = "p_" + chapterTable;
                $("#" + subgrid_id).html(
                    "<table id='" + chapterTable + "' class='scroll'></table>" +
                    "<div id='" + chapterPager + "' class='scroll'></div>");
                $("#" + chapterTable).jqGrid(
                    {
                        styleUI: "Bootstrap",
                        url: '${pageContext.request.contextPath}/chapter/showAllChapter?album_id=' + row_id,
                        datatype: "json",
                        colNames: ['编号', '标题', '大小', '时长', '文件名', '创建时间', '在线播放'],
                        editurl: '${pageContext.request.contextPath}/chapter/editChapter',
                        colModel: [
                            {name: "chapter_id", key: true, hidden: true},
                            {
                                name: "chapter_title", editable: true, align: 'center'
                            },
                            {name: "chapter_size", align: 'center'},
                            {name: "chapter_duration", align: 'center'},
                            {
                                name: "chapter_cover",
                                editable: true,
                                align: 'center',
                                edittype: 'file',
                                editoptions: {enctype: "multipart/form-data"},
                            },
                            {
                                name: "chapter_create_date", align: 'center',
                                formatter: 'date',
                                formatoptions: {newformat: 'Y-m-d'}
                            },
                            {
                                name: "aaa", align: 'center', width: 300,
                                formatter: function (value, options, rows) {
                                    return "<audio controls loop>\n" +
                                        "<source src='${pageContext.request.contextPath}/album/music/" + rows.chapter_cover + "' type=\"audio/ogg\">\n" +
                                        "</audio>"
                                }
                            },
                        ],
                        autowidth: true,
                        height: '100%',
                        rowList: [3, 5, 10, 20],
                        rowNum: 3,
                        page: 1,
                        prmNames: {id: 'chapter_id'},
                        pager: "#" + chapterPager, // 加入分页
                        viewrecords: true,
                        rownumbers: true,
                        mtype: 'POST',   // 指定请求方式  默认为get
                    }).jqGrid('navGrid',
                    "#" + chapterPager, {
                        edit: true, add: true, del: false
                    }, {
                        //控制章节修改
                        closeAfterEdit: true,
                        beforeShowForm: function (frm) {
                            frm.find("#chapter_cover").attr("disabled", true);
                            frm.find("#tr_chapter_cover").css("display", "none");
                        }
                    }, {
                        //控制章节的添加
                        closeAfterAdd: true,
                        editData: {album_id: row_id},
                        afterSubmit: function (response) {
                            var status = response.responseJSON.status;
                            var id = response.responseJSON.message;
                            console.log(id);
                            if (status) {
                                $.ajaxFileUpload({
                                    type: "post",
                                    url: "/chapter/upload",
                                    data: {chapter_id: id},
                                    fileElementId: "chapter_cover",
                                    success: function () {
                                        $("#" + chapterTable).trigger("reloadGrid");
                                    }
                                })
                            }
                            return "123";
                        }
                    });
            },
        }).navGrid("#albumPager",
            {
                edit: true, add: true, del: false, search: false
            },
            {
                //控制修改的相关操作
                closeAfterEdit: true,
                beforeShowForm: function (frm) {
                    frm.find("#album_cover").attr("disabled", true);
                    frm.find("#tr_album_cover").css("display", "none");
                }
            }, {
                //控制添加的相关操作
                closeAfterAdd: true,
                afterSubmit: function (response) {
                    var status = response.responseJSON.status;
                    var id = response.responseJSON.message;
                    if (status) {
                        $.ajaxFileUpload({
                            type: "post",
                            url: "${pageContext.request.contextPath}/album/upload",
                            data: {album_id: id},
                            fileElementId: "album_cover",
                            success: function () {
                                $("#albumTable").trigger("reloadGrid");
                            }
                        })
                    }
                    return "hjh";
                }
            });
    })
</script>
<table id="albumTable"></table>
<div id="albumPager" style="height: 80px"></div>