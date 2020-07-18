<%--
  Created by IntelliJ IDEA.
  User: qwe13
  Date: 2020/7/2
  Time: 16:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>课程编辑</title>
    <link rel="stylesheet" href="./static/css/classEditPage.css"/>
    <link rel="stylesheet" href="./static/css/common.css"/>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons"
          rel="stylesheet">
    <link rel="stylesheet" href="./static/css/font-awesome.min.css">
    <script src="./static/js/jquery-3.4.1.min.js"></script>
    <script src="./static/js/common.js"></script>
    <script>
        var domain = 123;
        $(function() {
            domain = 'http://' + document.domain + ':5500/';
            // $(".c-head").click(() => {
            //     alert(123);
            //     event.stopPropagation();
            // });
        })
        //显示面板
        function display(key) {
            $(".panel").each((index, e) => {
                if(index === key) {
                    $(e).css("display", "block");
                    let o = $(e).children(".head").children(".table").eq(0)
                    change_style_2(0, o);
                    $(e).children(".sub-panel").each((index, e) => {
                        if(index === 0) {
                            $(e).css("display", "block");
                        }else {
                            $(e).css("display", "none");
                        }
                    })
                }else {
                    $(e).css("display", "none");
                }
            })
        }
        //显示分面板
        function display_sub(key, o) {
            $(o).parent().siblings(".sub-panel").each((index, e) => {
                if(index === key) {
                    $(e).css("display", "inline");
                }else {
                    $(e).css("display", "none");
                }
            })
        }
        //设置侧边栏的样式
        function change_style_1(key) {
            $(".list").each((index, e) => {
                if(index === key) {
                    $(e).addClass("bgc-d-blue color-white");
                    $(e).removeAttr("ondrag");
                }else {
                    $(e).removeClass("bgc-d-blue color-white");
                    $(e).attr("ondrag", "void(0)");
                }
            })
        }
        //设置面板选项样式
        function change_style_2(key, o) {
            $(o).attr("ondrag", "void(0)");
            $(o).siblings(".table").each((index, e) => {
                $(e).removeAttr("ondrag");
            })
        }
        function save_class() {
            // $.ajax({
            //     url:"",
            //     data:{},
            //     success:(data) => {
            //         alert("保存成功");
            //     },
            //     error:(data) => {

            //     },
            //     dataType:"json"
            // })
            alert("保存成功");
        }
        function delete_class() {
            if(window.confirm("您确定要删除课程吗?")) {
                window.location = domain + 'userPage.html';
                // $.ajax({
                // url:"",
                // data:{},
                // success:(data) => {

                // },
                // error:(data) => {

                // },
                // dataType:"json"
                // })
            }
        }
        //展示课件
        function show_sub_chapter(o) {
            let $o = $(o);
            let is_show = $o.attr("is_show");
            if(is_show === "true") is_show = true;
            if(is_show === "false") is_show = false;
            if(typeof(is_show) == undefined || (!is_show)) {
                $o.children(".arrow").removeClass("fa-chevron-down");
                $o.children(".arrow").addClass("fa-chevron-up");
                $o.siblings(".c-body").css("display", "inline");
                $o.attr("is_show", true);
            }else {
                $o.children(".arrow").removeClass("fa-chevron-up");
                $o.children(".arrow").addClass("fa-chevron-down");
                $o.siblings(".c-body").css("display", "none");
                $o.attr("is_show", false);
            }
        }
        function stop(e) {
            e = e || window.event;
            if(e.stopPropagation) {
                e.stopPropagation();
            } else {
                e.cancelBubble = true;
            }
        }
        function add_chapter() {
            let name = window.prompt("请输入章节名:");
            let index = $(".chapter").length;
            let chapter = $('<div class="chapter"></div>');
            let c_head = $('<div class="c-head" is_show=true onclick="show_sub_chapter(this)"></div>');
            let c_body = $('<div class="c-body"></div>');
            let i_1 = $('<i class="fa fa-chevron-up arrow"></i>');
            let i_2 = $('<i class="chapter-name" style="font-size: 18px;">' + name + '</i>');
            let i_3 = $('<i class="fa fa-plus add-sub-chapter" onclick="add_sub_chapter(event, this)"></i>');
            let i_4 = $('<i class="fa fa-minus remove-chapter" onclick="remove_chapter(event, this)"></i>');
            let i_5 = $('<i class="fa fa-level-down down-chapter" onclick="down_chapter(event, this)" style="right: 55px;">下移</i>');
            let i_6 = $('<i class="fa fa-level-up up-chapter" onclick="up_chapter(event, this)" style="right: 105px;">上移</i>');
            let i_7 = $('<i class="rename-chapter" onclick="rename_chapter(event, this)">重命名</i>');
            c_head.append(i_1, i_2, i_3, i_4, i_5, i_6, i_7);
            chapter.append(c_head, c_body);
            chapter.attr("index", index);
            $(".chapters").append(chapter);
        }
        function remove_chapter(e, o) {
            stop(e);
            let name = $(o).siblings(".chapter-name").html();
            let flag = window.confirm("确定删除章节: '" + name +"' ?");
            if(flag) {
                let $o = $(o).parent().parent();
                let position = parseInt($o.attr("index"));
                $(".chapter").each((index, e) => {
                    if(index > position) {
                        $(e).attr("index", (index - 1));
                    }
                })
                $o.remove();
            }
        }
        function add_sub_chapter(e, o) {
            stop(e);
            let name = window.prompt("请输入小节名:")
            let sub_chapter = $('<div class="sub-chapter"></div>');
            let i_1 = $('<i class="fa fa-circle-o"></i>');
            let i_2 = $('<i class="sub-chapter-name">' + name + '</i>');
            let i_3 = $('<i class="fa fa-minus remove-sub-chapter" onclick="remove_sub_chapter(event, this)"></i>');
            let i_4 = $('<i class="fa fa-level-down down-sub-chapter" style="right: 35px;" onclick="down_sub_chapter(event, this)">下移</i>');
            let i_5 = $('<i class="fa fa-level-up up-sub-chapter" style="right: 85px;" onclick="up_sub_chapter(event, this)">上移</i>');
            let i_6 = $('<i class="rename-sub-chapter" onclick="rename_sub_chapter(event, this)">重命名</i>');
            let i_7 = $('<i class="upload-data" onclick="upload_file(event, this)">上传资源</i>');
            sub_chapter.append(i_1, i_2, i_3, i_4, i_5, i_6, i_7);
            $t = $(o).parent().siblings(".c-body");
            let index = $t.children(".sub-chapter").length;
            sub_chapter.attr("index", index);
            $t.append(sub_chapter);
        }
        function up_chapter(e, o) {
            stop(e);
            let $p_chapter = $(o).parent().parent();
            let position = parseInt($p_chapter.attr("index"));
            let $clone = $p_chapter.clone();
            $clone.attr("index", (position - 1));
            $(".chapters").children(".chapter").each((index, e) => {
                let $e = $(e);
                if((index + 1) == position) {
                    $e.attr("index", position);
                    $e.before($clone);
                }
            })
            $p_chapter.remove();
        }
        function down_chapter(e, o) {
            stop(e);
            let $p_chapter = $(o).parent().parent();
            let position = parseInt($p_chapter.attr("index"));
            let $clone = $p_chapter.clone();
            $clone.attr("index", (position + 1));
            $(".chapters").children(".chapter").each((index, e) => {
                let $e = $(e);
                if((index - 1) == position) {
                    $e.attr("index", position);
                    $e.after($clone);
                }
            })
            $p_chapter.remove();
        }
        function rename_chapter(e, o) {
            stop(e);
            let name = window.prompt("请输入新章节名:");
            let $target = $(o).siblings(".chapter-name");
            $target.html(name);
        }
        //sub chapter
        function remove_sub_chapter(e, o) {
            stop(e);
            let name = $(o).siblings(".sub-chapter-name").html();
            let flag = window.confirm("确定删除小节: '" + name +"' ?");
            if(flag) {
                let $o = $(o).parent();
                let position = parseInt($o.attr("index"));
                $o.parent().children(".sub-chapter").each((index, e) => {
                    if(index > position) {
                        $(e).attr("index", (index - 1));
                    }
                })
                $o.remove();
            }
        }
        function up_sub_chapter(e, o) {
            stop(e);
            let $p_sub_chapter = $(o).parent();
            let position = parseInt($p_sub_chapter.attr("index"));
            let $clone = $p_sub_chapter.clone();
            $clone.attr("index", (position) - 1);
            $p_sub_chapter.parent(".c-body").children(".sub-chapter").each((index, e) => {
                let $e = $(e);
                if((index + 1) == position) {
                    $e.attr("index", position);
                    $e.before($clone);
                }
            })
            $p_sub_chapter.remove();
        }
        function down_sub_chapter(e, o) {
            stop(e);
            let $p_sub_chapter = $(o).parent();
            let position = parseInt($p_sub_chapter.attr("index"));
            let $clone = $p_sub_chapter.clone();
            $clone.attr("index", (position + 1));
            $p_sub_chapter.parent(".c-body").children(".sub-chapter").each((index, e) => {
                let $e = $(e);
                if((index - 1) == position) {
                    $e.attr("index", position);
                    $e.after($clone);
                }
            })
            $p_sub_chapter.remove();
        }
        function rename_sub_chapter(e, o) {
            stop(e);
            let name = window.prompt("请输入新小节名:");
            let $target = $(o).siblings(".sub-chapter-name");
            $target.html(name);
        }
        function upload_file(e, o) {
            stop(e);
            alert("没有");
        }
        //协作部分
        function swicth_state(o) {
            let $o = $(o).parent(".massage");
            let is_select = $o.attr("is_select");
            if(is_select === "true") is_select = true;
            if(is_select === "false") is_select = false;
            if(!is_select) {
                $o.attr({"ondrag":"void(0)", "is_select":"true"});
            }else {
                $o.removeAttr("ondrag");
                $o.attr("is_select", "false");
            }
        }
        function select_all(o) {
            $(o).parent().siblings(".massages").children(".massage").each((index, e) => {
                $(e).attr({"ondrag":"void(0)", "is_select":"true"});
                $(e).children(".massage-check").prop("checked", true);
            })
        }
        function reverse_selection(o) {
            $(o).parent().siblings(".massages").children(".massage").children(".massage-check").each((index, e) => {
                swicth_state(e);
                let $e = $(e);
                if($e.prop("checked")) {
                    $e.prop("checked", false);
                }else {
                    $e.prop("checked", true);
                }
            })
        }
        function reset_select(o) {
            $(o).parent().siblings(".massages").children(".massage").each((index, e) => {
                let $e = $(e);
                $e.removeAttr("ondrag");
                $e.attr("is_select", "false");
                $e.children(".massage-check").prop("checked", false);
            })
        }
        function flag(o, key) {
            $(o).parent().siblings(".massages").children(".massage").each((index, e) => {
                let $e = $(e);
                let is_select = $e.attr("is_select");
                if(is_select === "true") is_select = true;
                if(is_select === "false") is_select = false;
                if(is_select) {
                    $e.children(".massage-body").children(".new").removeAttr("ondrag");
                    // if(key) {
                    //     $e.children(".massage-body").children(".new").removeAttr("ondrag");
                    // }else {
                    //     $e.children(".massage-body").children(".new").attr("ondrag", "void(0)");
                    // }
                }
            })
            reset_select(o);
        }
    </script>
</head>
<body>
<div id="header" class="located font-20">
    <div id="header_left" class="flow">
        <table cellspacing="0" cellpadding="0" style="height: 80px;">
            <tr>
                <td><div class="table_cell bgc-d-blue" onclick="to_main_page()"><a href="javascript:void(0)" class="bgc-black">首页</a></div></td>
                <td><div class="table_cell"><a href="javascript:void(0)" class="bgc-black">我的课程</a></div></td>
                <td><div class="table_cell"><a href="javascript:void(0)" class="bgc-black">社区</a></div></td>
            </tr>
        </table>
    </div>
    <div id="header_right" class="flow">
        <table cellspacing="0" cellpadding="0" style="height: 80px;">
            <tr>
                <td><div class="table_cell" onclick="to_my_page()"><a href="javascript:void(0)" class="bgc-black">欢迎${user.userName}</a></div></td>
                <td><div class="table_cell" onclick="to_my_page()"><a href="javascript:void(0)" class="bgc-black"><i class="fa fa-cog fa-fw"></i>我的</a></div></td>
            </tr>
        </table>
    </div>
</div>
<div class="located" style="width: 80%; margin: 0 auto; margin-top: 80px;">
    <div class="left_list float">
        <div class="head">
            <div><img class="head_img" /><i class="fa fa-user-circle" style="font-size: 80px;"></i></div>
            <div class="font-20">${user.userName}</div>
        </div>
        <div class="lists">
            <div class="list bgc-d-blue color-white" onclick="change_style_1(0); display(0)">
                <i class="fa fa-info-circle"></i>
                <label class="color-black float">简介设定</label>
            </div>
            <div class="list" onclick="change_style_1(1); display(1)" ondrag="">
                <i class="fa fa-list"></i>
                <label class="color-black float">课件设置</label>
            </div>
            <div class="list" onclick="change_style_1(2); display(2)" ondrag="">
                <i class="fa fa-rss"></i>
                <label class="color-black float">公告发布</label>
            </div>
            <div class="list" onclick="change_style_1(3); display(3)" ondrag="">
                <i class="fa fa-plug"></i>
                <label class="color-black float">协作者</label>
            </div>
            <div class="list" onclick="save_class()" ondrag="">
                <i class="fa fa-save"></i>
                <label class="color-black float">保存修改</label>
            </div>
            <div class="list" onclick="delete_class()">
                <i class="fa fa-trash"></i>
                <label class="color-black float">删除课程</label>
            </div>
        </div>
    </div>
    <div id="right" class="float">
        <div class="panel located" style="">
            <div class="heads located">
                <div class="flow font-20 table" style="left: 15px;" ondrag="">简介设定</div>
                <div class="divider flow bgc-l-blue-2" style="left: 0;"></div>
                <div class="divider-flat flow"></div>
            </div>
            <table class="class-info">
                <tr>
                    <td style="width: 100px; vertical-align: middle;">课程名称:</td>
                    <td style="line-height: 50px;"><input type="text" /></td>
                </tr>
                <tr>
                    <td>课程目标:</td>
                    <td><textarea></textarea></td>
                </tr>
                <tr>
                    <td>课程简介:</td>
                    <td><textarea></textarea></td>
                </tr>
                <tr>
                    <td>课程封面:</td>
                    <td><img src="./static/image/classes/1.jpg"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="text" style="width: 500px;"><div id="upload_class_cover_btn">上传</div></td>
                </tr>
            </table>
        </div>
        <div class="panel located" style="display: none;">
            <div class="head located">
                <div class="flow font-20 table" style="left: 15px;" onclick="add_chapter()">添加章节</div>
                <div class="flow font-20 table" style="left: 145px;">添加小节</div>
                <div class="divider flow bgc-l-blue-2"></div>
                <div class="divider-flat flow"></div>
            </div>
            <div class="chapters">
                <div class="chapter" index=0>
                    <div class="c-head" is_show=true onclick="show_sub_chapter(this)">
                        <i class="fa fa-chevron-up arrow"></i>
                        <i class="chapter-name" style="font-size: 18px;">第一章 操作系统概述</i>
                        <i class="fa fa-plus add-sub-chapter" onclick="add_sub_chapter(event, this)"></i>
                        <i class="fa fa-minus remove-chapter" onclick="remove_chapter(event, this)"></i>
                        <i class="fa fa-level-down down-chapter" style="right: 55px;" onclick="down_chapter(event, this)">下移</i>
                        <i class="fa fa-level-up up-chapter" style="right: 105px;" onclick="up_chapter(event, this)">上移</i>
                        <i class="rename-chapter" onclick="rename_chapter(event, this)">重命名</i>
                    </div>
                    <div class="c-body" >
                        <div class="sub-chapter" index=0>
                            <i class="fa fa-circle-o"></i>
                            <i class="sub-chapter-name">1.1 操作系统初步认识</i>
                            <i class="fa fa-minus remove-sub-chapter" onclick="remove_sub_chapter(event, this)"></i>
                            <i class="fa fa-level-down down-sub-chapter" style="right: 35px;" onclick="down_sub_chapter(event, this)">下移</i>
                            <i class="fa fa-level-up up-sub-chapter" style="right: 85px;" onclick="up_sub_chapter(event, this)">上移</i>
                            <i class="rename-sub-chapter" onclick="rename_sub_chapter(event, this)">重命名</i>
                            <i class="upload-file" onclick="upload_file(event, this)">上传资源</i>
                        </div>
                        <div class="sub-chapter" index=1>
                            <i class="fa fa-circle-o"></i>
                            <i class="sub-chapter-name">1.2 操作系统功能和定义</i>
                            <i class="fa fa-minus remove-sub-chapter" onclick="remove_sub_chapter(event, this)"></i>
                            <i class="fa fa-level-down down-sub-chapter" style="right: 35px;" onclick="down_sub_chapter(event, this)">下移</i>
                            <i class="fa fa-level-up up-sub-chapter" style="right: 85px;" onclick="up_sub_chapter(event, this)">上移</i>
                            <i class="rename-sub-chapter" onclick="rename_sub_chapter(event, this)">重命名</i>
                            <i class="upload-file" onclick="upload_file(event, this)">上传资源</i>
                        </div>
                    </div>
                </div>
                <div class="chapter" index=1>
                    <div class="c-head" is_show=true onclick="show_sub_chapter(this)">
                        <i class="fa fa-chevron-up arrow" ></i>
                        <i class="chapter-name" style="font-size: 18px;">第二章 操作系统逻辑结构</i>
                        <i class="fa fa-plus add-sub-chapter" onclick="add_sub_chapter(event, this)"></i>
                        <i class="fa fa-minus remove-chapter" onclick="remove_chapter(event, this)"></i>
                        <i class="fa fa-level-down down-chapter" style="right: 55px;" onclick="down_chapter(event, this)">下移</i>
                        <i class="fa fa-level-up up-chapter" style="right: 105px;" onclick="up_chapter(event, this)">上移</i>
                        <i class="rename-chapter" onclick="rename_chapter(event, this)">重命名</i>
                    </div>
                    <div class="c-body" >
                        <div class="sub-chapter" index=0>
                            <i class="fa fa-circle-o"></i>
                            <i class="sub-chapter-name">2.1 操作系统逻辑结构</i>
                            <i class="fa fa-minus remove-sub-chapter" onclick="remove_sub_chapter(event, this)"></i>
                            <i class="fa fa-level-down down-sub-chapter" style="right: 35px;" onclick="down_sub_chapter(event, this)">下移</i>
                            <i class="fa fa-level-up up-sub-chapter" style="right: 85px;" onclick="up_sub_chapter(event, this)">上移</i>
                            <i class="rename-sub-chapter" onclick="rename_sub_chapter(event, this)">重命名</i>
                            <i class="upload-file" onclick="upload_file(event, this)">上传资源</i>
                        </div>
                        <div class="sub-chapter" index=1>
                            <i class="fa fa-circle-o"></i>
                            <i class="sub-chapter-name">2.2 CPU的态</i>
                            <i class="fa fa-minus remove-sub-chapter" onclick="remove_sub_chapter(event, this)"></i>
                            <i class="fa fa-level-down down-sub-chapter" style="right: 35px;" onclick="down_sub_chapter(event, this)">下移</i>
                            <i class="fa fa-level-up up-sub-chapter" style="right: 85px;" onclick="up_sub_chapter(event, this)">上移</i>
                            <i class="rename-sub-chapter" onclick="rename_sub_chapter(event, this)">重命名</i>
                            <i class="upload-file" onclick="upload_file(event, this)">上传资源</i>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="panel located" style="display: none;">
            <div class="heads located">
                <div class="flow font-20 table" style="left: 15px;" ondrag="">公告发布</div>
                <div class="divider flow bgc-l-blue-2" style="left: 0;"></div>
                <div class="divider-flat flow"></div>
            </div>
            <table class="class-info">
                <tr>
                    <td style="width: 100px; vertical-align: middle;">公告标题:</td>
                    <td style="line-height: 50px;"><input type="text" /></td>
                </tr>
                <tr>
                    <td>公告内容:</td>
                    <td><textarea></textarea></td>
                </tr>
                <tr>
                    <td></td>
                    <td><div id="release_note_btn">发布</div></td>
                </tr>
            </table>
        </div>
        <div class="panel located" style="display: none;">
            <div class="heads located">
                <div class="flow font-20 table" style="left: 0;" onclick="change_style_2(0, this); display_sub(0, this)" ondrag="">协作请求</div>
                <div class="flow font-20 table" style="left: 165px;" onclick="change_style_2(1, this); display_sub(1, this)">当前协作</div>
                <div class="divider flow bgc-l-blue-2" style="left: 150px;"></div>
                <div class="divider-flat flow"></div>
            </div>
            <div class="sub-panel" style="">
                <div class="sub-head">
                    <div class="font-15 table" onclick="select_all(this)">全选</div>
                    <div class="font-15 table" onclick="reverse_selection(this)">反选</div>
                    <div class="font-15 table" onclick="reset_select(this)">取消选择</div>
                    <div class="font-15 table" onclick="flag(this, true)">接受</div>
                    <div class="font-15 table" onclick="flag(this, false)">拒绝</div>
                </div>
                <div class="massages">
                    <div class="massage">
                        <input type="checkbox" class="massage-check" onclick="swicth_state(this)"/>
                        <div class="flow massage-body">
                            <label>用户</label>
                            <a class="remote-url">第三个mt</a>
                            <label>申请参与课程编辑</label>
                            <div class="new" ondrag="">新</div>
                        </div>
                    </div>
                    <div class="massage">
                        <input type="checkbox" class="massage-check" onclick="swicth_state(this)"/>
                        <div class="flow massage-body">
                            <label>用户</label>
                            <a class="remote-url">第四个mt</a>
                            <label>申请参与课程编辑</label>
                            <div class="new" ondrag="">新</div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="sub-panel" style="display: none;">
                <div class="partners">
                    <div class="partner">
                        <div class="p-head"><img class="normal-user-head" src="./static/image/head_black.png" /></div>
                        <div class="p-body">
                            <div style="text-align: left;">第一个mt(创建者)</div>
                            <div>个人简介个人简介个人简介个人简介个人简介个人简介个人简介个人简介</div>
                        </div>
                    </div>
                    <div class="partner">
                        <div class="p-head"><img class="normal-user-head" src="./static/image/head_black.png" /></div>
                        <div class="p-body">
                            <div style="text-align: left;">第五个mt</div>
                            <div>个人简介个人简介个人简介个人简介个人简介个人简介个人简介个人简介</div>
                        </div>
                    </div>
                    <div class="partner">
                        <div class="p-head"><img class="normal-user-head" src="./static/image/head_black.png" /></div>
                        <div class="p-body">
                            <div style="text-align: left;">第六个mt</div>
                            <div>个人简介个人简介个人简介个人简介个人简介个人简介个人简介个人简介</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>