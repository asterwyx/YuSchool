<%--
  Created by IntelliJ IDEA.
  User: qwe13
  Date: 2020/7/2
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>课程详情</title>
    <link rel="stylesheet" href="./static/css/class.css"/>
    <link rel="stylesheet" href="./static/css/common.css"/>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons"
          rel="stylesheet">
    <link rel="stylesheet" href="./static/css/font-awesome.min.css">
    <script src="./static/js/jquery-3.4.1.min.js"></script>
    <script src="./static/js/common.js"></script>
    <script>
        var user_id = ${user.primaryKey};
        var course_id = ${course_in_over_view.primaryKey};
        var comment_data = 1;
        //显示面板
        function display(key) {
            $(".panel").each((index, e) => {
                if(index === key) {
                    if(key === 3) $(e).css("display", "block");
                    else $(e).css("display", "inline");
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
        //展示课件
        function show_sub_chapter(o) {
            let $o = $(o);
            let is_show = $o.attr("is_show");
            if(is_show === "true") is_show = true;
            if(is_show === "false") is_show = false;
            if(typeof(is_show) == undefined || (!is_show)) {
                $o.children("i").removeClass("fa-chevron-down");
                $o.children("i").addClass("fa-chevron-up");
                $o.siblings(".c-body").css("display", "inline");
                $o.attr("is_show", true);
            }else {
                $o.children("i").removeClass("fa-chevron-up");
                $o.children("i").addClass("fa-chevron-down");
                $o.siblings(".c-body").css("display", "none");
                $o.attr("is_show", false);
            }
        }
        function show_discussion(o) {
            $(".response").remove();
            let id = $(o).attr("discussion_id");
            let data_data = comment_data[id].commentVec;
            let length = Object.keys(data_data).length;
            for (let i = 0; i < length; i++) {
                let response = $('<div class="response"></div>');
                let r_body = $('<p class="r-body">' + data_data[id].detail + '</p>');
                let r_info = $('<div class="r-info"><a>' + data_data[id].userObject.userName + '</a> ' + new Date(data_data[id].publishTime) + '发表</div>');
                let r_options = $('<div class="r-options"><div class="report_response_btn">举报</div><div class="divider"></div><div><i class="fa fa-thumbs-o-up"></i> 63</div></div>');
                let divider = $('<div class="divider-flat"></div>');
                response.append(r_body, r_info, r_options ,divider);
                $(".responses").append(response);
            }
            display(3);
        }
        function back_to_discussions() {
            display(2);
        }
        function close_post_panel() {
            $("#post_new_discuss_panel").hide();
        }
        function show_post_panel() {
            $("#post_new_discuss_panel").show();
        }
        function get_all_post() {
            $.ajax({
                url:domain.concat("/courses/", course_id, '/comment_areas'),
                type: "GET",
                data:{"max": 999, "page": 1},
                success:(data) => {
                    if (data.flag) {
                        $(".discussion").remove();
                        let datas = data.data;
                        let length = Object.keys(datas).length;
                        comment_data = datas;
                        for (let i = 0; i < length; i++) {
                            let a = Object.keys(datas[i].comments).length;
                            let discussion = $('<div class="discussion"></div>');
                            let d_head = $('<div class="d-head" onclick="show_discussion(this)" discussion_id=' + i + '>' + datas[i].commentAreaName + '</div>');
                            let d_response = $('<div class="d-response"></div>');
                            let d_info = $('<div class="d-info" style="float: right;">回复: ' + a + '</div>');
                            let divider = $('<div class="divider-flat"></div>');
                            d_response.append(d_info);
                            discussion.append(d_head, d_response, divider);
                            $(".discussions").append(discussion);
                        }
                    } else {
                        alert(data.errorMsg);
                    }
                },
                error:(data) => {
                    alert("获取讨论区失败");
                }
            });
        }
    </script>
</head>
<body>
<div id="post_new_discuss_panel" style="display: none;">
    <div id="closeBtn" onclick="close_post_panel()">
        <span class="fa fa-close"></span>
    </div>
    <form>
        <table class="class-info">
            <tr>
                <td style="width: 60px; vertical-align: middle;">标题:</td>
                <td style="line-height: 50px;"><input type="text" /></td>
            </tr>
            <tr>
                <td>内容:</td>
                <td><textarea></textarea></td>
            </tr>
            <tr style="height: 90px;">
                <td></td>
                <td><div id="post_new_discuss_btn" class="a-btn">发布</div></td>
            </tr>
        </table>
    </form>
</div>
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
        <div class="head"><img src="./static/image/classes/1.jpg"></div>
        <div class="lists">
            <div class="list bgc-d-blue" onclick="change_style_1(0); display(0)">
                <label class="color-black float">公告</label>
            </div>
            <div class="list" onclick="change_style_1(1); display(1)" ondrag="">
                <label class="color-black float">课件</label>
            </div>
            <div class="list" onclick="change_style_1(2); display(2); get_all_post()" ondrag="">
                <label class="color-black float">讨论区</label>
            </div>
        </div>
    </div>
    <div id="right" class="float">
        <div class="panel located" style="">
            <div class="head-head located">
                <div>亲爱的${user.userName},欢迎回来~,</div><br/>
                <div>你上次学习到<a>第一章 1.1操作系统初步认识</a></div>
                <div id="continue_btn" class="a-btn">继续学习</div>
            </div>
            <div class="head located">
                <div class="flow font-20 table" style="left: 15px; width: 90px;">公告</div>
                <div class="divider flow bgc-l-blue-2" style="left: 0;"></div>
                <div class="divider-flat flow"></div>
            </div>
            <div class="announcements">
                <div class="announcement">
                    <div class="a-head">期末考试将于2020-06-28发布</div>
                    <div class="a-body">期末考试将于2020-06-28发布</div>
                    <div class="a-time">2020/6/23 21:26</div>
                    <div class="divider-flat"></div>
                </div>
                <div class="announcement">
                    <div class="a-head">最后的课件将于2020-06-21发布</div>
                    <div class="a-body">最后的课件将于2020-06-21发布，请耐心等待！</div>
                    <div class="a-time">2020/6/19 12:49</div>
                    <div class="divider-flat"></div>
                </div>
            </div>
        </div>
        <div class="panel located" style="display: none;">
            <div class="head located">
                <div class="flow font-20 table" style="left: 15px; width: 90px;">课件</div>
                <div class="divider flow bgc-l-blue-2" style="left: 0;"></div>
                <div class="divider-flat flow"></div>
            </div>
            <div class="chapters">
                <div class="chapter">
                    <div class="c-head" onclick="show_sub_chapter(this)"><i class="fa fa-chevron-down"></i>第一章 操作系统概述</div>
                    <div class="c-body" style="display: none;">
                        <div class="sub-chapter"><i class="fa fa-circle-o"></i>1.1 操作系统初步认识</div>
                        <div class="sub-chapter"><i class="fa fa-circle-o"></i>1.2 操作系统功能和定义</div>
                    </div>
                </div>
                <div class="chapter">
                    <div class="c-head" onclick="show_sub_chapter(this)"><i class="fa fa-chevron-down"></i>第二章 操作系统逻辑结构</div>
                    <div class="c-body" style="display: none;">
                        <div class="sub-chapter"><i class="fa fa-circle-o"></i>2.1 操作系统逻辑结构</div>
                        <div class="sub-chapter"><i class="fa fa-circle-o"></i>2.2 CPU的态</div>
                    </div>
                </div>
            </div>
        </div>
        <div class="panel located" style="display: none;">
            <div class="head-head located">
                <div class="font-25">综合讨论区</div><br/>
                <div class="font-15">发表任何想与大家分享的经验以及想法！关于本课程、学习、工作、生活等一般性话题</div>
                <div id="new_post" class="a-btn" onclick="show_post_panel()">发帖</div>
            </div>
            <div class="head located">
                <div class="flow font-20 table" style="left: 15px; width: 90px;">全部主题</div>
                <div class="divider flow bgc-l-blue-2" style="left: 0;"></div>
                <div class="divider-flat flow"></div>
            </div>
            <div class="discussions">
                <div class="discussion">
                    <div class="d-head" onclick="show_discussion(this)">把扇区集合成簇有什么意义</div>
                    <div class="d-response">
                        <div class="d-response-user"><a>第一个mt</a> 2020/6/19 发表</div>
                        <div class="divider"></div>
                        <div class="d-response-user"><a>第二个mt</a> 2020/6/19 最后回复</div>
                        <div class="d-info" style="float: right;">浏览: 21 回复: 2 点赞: 3</div>
                    </div>
                    <div class="divider-flat"></div>
                </div>
                <div class="discussion">
                    <div class="d-head">内存管理</div>
                    <div class="d-response">
                        <div class="d-response-user"><a>第一个mt</a> 2020/6/19 发表</div>
                        <div class="divider"></div>
                        <div class="d-response-user"><a>第二个mt</a> 2020/6/19 最后回复</div>
                        <div class="d-info" style="float: right;">浏览: 21 回复: 2 点赞: 3</div>
                    </div>
                    <div class="divider-flat"></div>
                </div>
            </div>
        </div>
        <div id="discussion_panel" class="panel located" style="display: none;">
            <div id="back">
                <div class="flow font-20 ssd" onclick="back_to_discussions()"><i class="fa fa-arrow-left"></i> 返回</div>
                <div class="divider flow bgc-l-blue-2" style="left: 0;"></div>
                <div class="divider-flat flow" style="width: 100%; height: 5px;"></div>
            </div>
            <div class="main-head located">
                <div class="font-25" id="discussion_head_name">把扇区集合成簇有什么意义</div><br/>
                <p class="font-15" id="discussion_detail" style="text-align: left;">RT，把扇区集合成簇有什么意义</p>
                <div id="discussion_main_user" class="font-12"><a>第一个mt</a>5月29日</div>
                <div class="options">
                    <div id="report_btn">举报</div>
                    <div class="divider"></div>
                    <div><i class="fa fa-thumbs-o-up"></i> 63</div>
                </div>
            </div>
            <div class="head located">
                <div class="flow font-20 table" style="left: 15px; text-align: left;">共2个回复</div>
                <div class="divider flow bgc-l-blue-2" style="left: 0;"></div>
                <div class="divider-flat flow"></div>
            </div>
            <div class="responses">
                <div class="response">
                    <p class="r-body">读取方便：由于扇区比较小，数目众多，在寻址时比较困难，所以操作系统就将相邻的扇区组合在一起，形成一个簇，再对簇进行整体的操作。
                        <br/>分离对底层的依赖：操作系统忽略对底层物理存储结构的设计。通过虚拟出来簇的概念，在系统中认为块是最小的单位</p>
                    <div class="r-info"><a>第一个mt</a> 2020/6/19 发表</div>
                    <div class="r-options">
                        <div class="report_response_btn">举报</div>
                        <div class="divider"></div>
                        <div><i class="fa fa-thumbs-o-up"></i> 63</div>
                    </div>
                    <div class="divider-flat"></div>
                </div>
                <div class="response">
                    <p class="r-body">读取方便：由于扇区比较小，数目众多，在寻址时比较困难，所以操作系统就将相邻的扇区组合在一起，形成一个簇，再对簇进行整体的操作。
                        <br/>分离对底层的依赖：操作系统忽略对底层物理存储结构的设计。通过虚拟出来簇的概念，在系统中认为块是最小的单位</p>
                    <div class="r-info"><a>第二个mt</a> 2020/6/19 发表</div>
                    <div class="r-options">
                        <div class="report_response_btn">举报</div>
                        <div class="divider"></div>
                        <div><i class="fa fa-thumbs-o-up"></i> </div>
                    </div>
                    <div class="divider-flat"></div>
                </div>
            </div>
            <div style="width: 100%; height: 100px;">
                <form id="comment_form">
                    <textarea name="comment"></textarea><br/>
                    <input type="submit" value="提交评论"/>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>