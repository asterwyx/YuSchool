<%--
  Created by IntelliJ IDEA.
  User: qwe13
  Date: 2020/7/2
  Time: 12:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>课程概览</title>
    <link rel="stylesheet" href="./static/css/overView.css"/>
    <link rel="stylesheet" href="./static/css/common.css"/>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons"
          rel="stylesheet">
    <link rel="stylesheet" href="./static/css/font-awesome.min.css">
    <script src="./static/js/jquery-3.4.1.min.js"></script>
    <script src="./static/js/common.js"></script>
    <script>
        var user_id = ${user.primaryKey};
        var course_id = ${course_in_over_view.primaryKey};
        $(function () {
            $("#comment_form").submit(function () {
                $.ajax({
                    url: domain.concat('/courses/', course_id, '/mark_area'),
                    type: "POST",
                    data: {"detail": $("#comment_detail").val(), "user_id": user_id},
                    success: (data) => {
                        if (data.flag) {
                            alert("评价成功");
                            window.location = window.location;
                        } else {
                            alert(data.errorMsg);
                        }
                    }
                });
                return false;
            })
        });
        function display_sub(key, o) {
            $(o).parent().siblings(".sub-panel").each((index, e) => {
                if(index === key) {
                    $(e).css("display", "inline");
                }else {
                    $(e).css("display", "none");
                }
            })
        }
        function change_style_2(key, o) {
            $(o).attr("ondrag", "void(0)");
            $(o).siblings(".table").each((index, e) => {
                $(e).removeAttr("ondrag");
            })
        }
        function attend_course() {
            $.ajax({
                url:domain.concat('/users/', user_id, '/stars/', course_id),
                type:"POST",
                data:{},
                success:(data) => {
                    if (data.flag) {
                        let btn = $('<div class="font-25" onclick="continue_class()">继续学习</div>');
                        $(".attend-btn").remove();
                        $("#class-btn").append(btn);
                    } else {
                        alert(data.errorMsg);
                    }
                },
                error:(data) => {

                },
                dataType:"json"
            })
        }
        function continue_class() {
            window.location = domain.concat('/classPage?course_id=', course_id);
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
<div id="head_overview" class="located" style="margin-top: 80px; ">
    <div id="head_overview_img"><img src="./static/image/classes/1.jpg"/></div>
    <div id="head_overview_body">
        <div class="located" style="width: 100%;height: 100%;">
            <div class="font-30">${course_in_over_view.courseName}</div>
            <div class="bottom" id="class-btn">
                <div class="font-12">已有${star_num}人参加</div>
                <c:if test="${is_star}">
                    <div class="font-25" onclick="continue_class()">继续学习</div>
                </c:if>
                <c:if test="${!is_star}">
                    <div class="font-25 attend-btn" onclick="attend_course()">立即参加</div>
                    <div class="font-25 attend-btn" style="left: 220px;" onclick="attend_course()">加入收藏</div>
                </c:if>
            </div>
        </div>
    </div>
    <div id="head_overview_share">
        分享
        <i class="fa fa-qq"></i>
        <i class="fa fa-wechat"></i>
        <i class="fa fa-weibo"></i>
    </div>
</div>
<div id="backgrand" class="located">
    <div id="body" class="flow">
        <div class="head located">
            <div class="flow font-20 table" style="left: 0;" onclick="change_style_2(0, this); display_sub(0, this)" ondrag="">课程详情</div>
            <div class="flow font-20 table" style="left: 165px;" onclick="change_style_2(1, this); display_sub(1, this)">课程评价(${comment_num})</div>
            <div class="divider flow bgc-l-blue-2" style="left: 150px;"></div>
            <div class="divider-flat flow"></div>
        </div>
        <div class="sub-panel" style="">
            <div class="sub-head" style="margin-top: 20px;"><i class="fa fa-folder-open"></i>&nbsp;课程概述</div>
            <p class="textarea">${course_in_over_view.introduction}</p>
            <div class="sub-head"><i class="fa fa-graduation-cap"></i>&nbsp;课程目标</div>
            <p class="textarea">掌握操作系统的基本概念、核心原理、关键技术。通过理论学习和实践环节，能掌握操作系统的结构与设计，能分析和设计简单的操作系统，使学生初步掌握阅读、分析和裁剪现有开源操作系统的能力；具备设计、实现、开发小型或简化的操作系统的基础；培养大型程序设计的方法和技巧，提高学生编制清晰、合理、可读性好的系统程序。</p>
            <div class="sub-head"><i class="fa fa-book"></i>&nbsp;课程大纲</div>
            <div class="class-list">
                <div class="chapter">
                    <div>第一章 操作系统概述</div>
                    <div class="sub-chapter">1.1 操作系统的初步认识</div>
                    <div class="sub-chapter">1.2 操作系统的功能和定义</div>
                </div>
                <div class="chapter">
                    <div>第二章 操作系统逻辑结构</div>
                    <div class="sub-chapter">2.1 操作系统逻辑结构</div>
                    <div class="sub-chapter">2.2 CPU的态</div>
                </div>
            </div>
        </div>
        <div class="sub-panel" style="display: none;">
            <div style="width: 100%; margin-top: 10px;">
                <div style="width: 100%; height: 100px;">
                    <form id="comment_form">
                        <textarea name="detail" id="comment_detail"></textarea>
                        <input type="submit" value="提交评论"/>
                    </form>
                </div>
                <div class="comments located">
                    <c:forEach items="${comments}" var="comment" varStatus="comment_status">
                        <div class="comment located">
                            <div style="width: 50px; height: 100%;"><img src="./static/image/head_black.png" /></div>
                            <div class="comment-body">
                                <div class="user-body" style="font-size: 15px;" user_id="${comment.userObject.primaryKey}" onclick="show_other_page(this)">${comment.userObject.userName}</div>
                                <p>${comment.detail}</p>
                                <div class="located">
                                    <div class="flow time">发表于${comment.publishTime}</div>
                                    <div class="flow thumbs"><i class="fa fa-thumbs-up"></i>&nbsp;${comment.likes}</div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
    <div id="teachers" class="flow">
        <div class="head located">
            <div class="flow font-20 table" style="left: 15px; width: 120px;">${collaborator_num_in_over_view}位授课老师</div>
            <div class="divider flow bgc-l-blue-2" style="left: 0;"></div>
            <div class="divider-flat flow"></div>
        </div>
        <div class="teacher" user_id="${owner_in_over_view.primaryKey}" onclick="show_other_page(this)">
            <img class="normal-user-head" src="${owner_in_over_view.headFilePath}" />
            <div>
                <div>${owner_in_over_view.userName}(上传者)</div>
                <div>教授</div>
            </div>
        </div>
        <c:forEach items="${collaborator_in_over_view}" var="teacher" varStatus="status">
            <div class="teacher" user_id="${teacher.primaryKey}" onclick="show_other_page(this)">
                <img class="normal-user-head" src="${teacher.headFilePath}"/>
                <div>
                    <div>${teacher.userName}</div>
                    <div>教师</div>
                </div>
            </div>
        </c:forEach>
        <div id="attend_class_modify">申请参与课程制作</div>
    </div>
    <div id="relative" class="flow">
        <div class="head located">
            <div class="flow font-20 table" style="left: 15px; width: 100px;">相关课程</div>
            <div class="divider flow bgc-l-blue-2" style="left: 0;"></div>
            <div class="divider-flat flow"></div>
        </div>
        <div class="class_overview">
            <img src="./static/image/classes/1.jpg"/>
            <div class="font-15">操作系统原理</div>
            <div class="font-12" style="bottom: 20px;">第一个mt教授</div>
            <div class="font-12">1234567人参加</div>
        </div>
    </div>
</div>
</body>
</html>