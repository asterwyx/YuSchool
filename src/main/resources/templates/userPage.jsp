<%--
  Created by IntelliJ IDEA.
  User: qwe13
  Date: 2020/7/2
  Time: 0:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>个人中心</title>
    <link rel="stylesheet" href="./static/css/userPage.css"/>
    <link rel="stylesheet" href="./static/css/common.css"/>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons"
          rel="stylesheet">
    <link rel="stylesheet" href="./static/css/font-awesome.min.css">
    <script src="./static/js/jquery-3.4.1.min.js"></script>
    <script src="./static/js/common.js"></script>
    <script>
        var is_select_mode = false;
        var is_user_select_mode = false;
        var user_id = ${user.primaryKey};
        $(function () {
            $("#user-info-form").submit(function () {
                $.ajax({
                    url:domain.concat('/users/', ${user.primaryKey}),
                    method:'POST',
                    data:$(this).serialize(),
                    success:(data) => {
                        if(data.flag) {
                            alert("用户信息更新成功!");
                        }else {
                            alert(data.errorMsg);
                        }
                    },
                    error:(data) => {

                    },
                    dataType:'json'
                });
                return false;
            })
        });
        //显示面板
        function display(key) {
            if(key === 4) {
                show_concerns();
            }
            if(key === 5) {
                show_fans();
            }
            $(".panel").each((index, e) => {
                if(index === key) {
                    $(e).css("display", "inline");
                    let o = $(e).children(".head").children(".table").eq(0)
                    change_style_2(0, o);
                    $(e).children(".sub-panel").each((index, e) => {
                        if(index === 0) {
                            $(e).css("display", "inline");
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
        //课程选择部分
        function change_select_mode(o) {
            //重置选择的课程
            reset_select();

            if(is_select_mode) {
                $(o).parent().css("display", "none");
                $(o).parent().siblings(".select-start").css("display", "inline");
                $(".select").css("display", "none");
                is_select_mode = false;
            }else {
                $(o).css("display", "none");
                $(o).siblings(".select-choose").css("display", "inline");
                $(".select").css("display", "inline");
                is_select_mode = true;
            }
        }
        //改变选择课程的样式
        function change_select(o) {
            if (!is_select_mode) return;
            let $o = $(o);
            let is_select = $o.attr("is_select");
            if(is_select === "true") is_select = true;
            if(is_select === "false") is_select = false;
            if(typeof(is_select) == undefined || (!is_select)) {
                $o.children(".select").attr("ondrag", "void(0)");
                $o.attr("is_select", "true");
            }else {
                $o.children(".select").removeAttr("ondrag");
                $o.attr("is_select", "false");
            }
        }
        function reset_select() {
            $(".select").each((index, e) => {
                $(e).parent(".class").attr("is_select", "false");
                $(e).removeAttr("ondrag");
            })
        }
        function reverse_selection(o) {
            $(o).parent().parent().siblings(".grup").children(".classes").children(".class").each((index, e) => {
                change_select(e);
            })
        }
        function select_all(o) {
            $(o).parent().parent().siblings(".grup").children(".classes").children(".class").each((index, e) => {
                $(e).children(".select").attr("ondrag", "void(0)");
                $(e).attr("is_select", "true");
            })
        }
        //取消收藏
        function cancel_select_star(o) {
            let $o = $(o);
            let list = "";
            $o.parent().parent().siblings(".grup").children(".classes").children(".class").each((index, e) => {
                let is_select = $(e).attr("is_select");
                if(is_select === "true") is_select = true;
                if(is_select === "false") is_select = false;
                if (is_select) {
                    list = list.concat($(e).attr("course_id"), ',');
                }
            });
            $.ajax({
                url:domain.concat('/users/', user_id, '/stars'),
                type:"POST",
                data:{'range': list, 'method':'DELETE'},
                success:(data) => {
                    if (data.flag) {
                        alert("操作成功");
                        window.location = window.location;
                    } else {
                        alert(data.errorMsg);
                    }
                },
                error:(data) => {
                    alert("未知错误");
                },
                dataType:"json"
            })
            reset_select();
        }
        //消息部分
        //获取所有消息
        function get_massages() {
            $.ajax({
                url:domain.concat('/users/', user_id, '/messages'),
                method:"GET",
                data:{"method": "GET"},
                success:(data) => {
                    $(".massages").children(".massage").remove();
                    let datas = data.data;
                    let length = Object.keys(datas).length;
                    for (let i = 0; i < length; i++) {
                        let massage = $('<div class="massage" massage_id=' + datas[i].primaryKey + '></div>');
                        let check_box = $('<input type="checkbox" class="massage-check" onclick="swicth_massage_state(this)"/>');
                        let body = $('<div class="flow massage-body"></div>');
                        let b_1 = $('<label>' + datas[i].detail +  '</label>');
                        let _new = 1;
                        if (datas[i].read == 1) {
                            _new = $('<div class="new" ondrag="">新</div>');
                        } else {
                            _new = $('<div class="new">新</div>');
                        }
                        body.append(b_1, _new);
                        massage.append(check_box, body);
                        $(".massages").append(massage);
                    }
                },
                error:(data) => {

                },
                dataType:'json'
            })
        }
        function swicth_massage_state(o) {
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
        function select_all_massage(o) {
            $(o).parent().siblings(".massages").children(".massage").each((index, e) => {
                $(e).attr({"ondrag":"void(0)", "is_select":"true"});
                $(e).children(".massage-check").prop("checked", true);
            })
        }
        function reverse_selection_massage(o) {
            $(o).parent().siblings(".massages").children(".massage").children(".massage-check").each((index, e) => {
                swicth_massage_state(e);
                let $e = $(e);
                if($e.prop("checked")) {
                    $e.prop("checked", false);
                }else {
                    $e.prop("checked", true);
                }
            })
        }
        function reset_select_massage(o) {
            $(o).parent().siblings(".massages").children(".massage").each((index, e) => {
                let $e = $(e);
                $e.removeAttr("ondrag");
                $e.attr("is_select", "false");
                $e.children(".massage-check").prop("checked", false);
            })
        }
        function flag_read(o, key) {
            let list = "";
            $(o).parent().siblings(".massages").children(".massage").each((index, e) => {
                let $e = $(e);
                let is_select = $e.attr("is_select");
                if(is_select === "true") is_select = true;
                if(is_select === "false") is_select = false;
                if(is_select) {
                    if(key) {
                        list = list.concat($e.attr("massage_id"), ',');
                        $e.children(".massage-body").children(".new").removeAttr("ondrag");
                    }else {
                        $e.children(".massage-body").children(".new").attr("ondrag", "void(0)");
                    }
                }
            });
            if (key) {
                $.ajax({
                    url:domain.concat('/users/', user_id, '/messages'),
                    type:"POST",
                    data:{'range': list, 'method':'PUT'},
                    success:(data) => {
                        if (data.flag) {
                            alert("操作成功");
                        } else {
                            alert(data.errorMsg);
                        }
                    },
                    error:(data) => {
                        alert("未知错误");
                    },
                    dataType:"json"
                })
            }
            reset_select_massage(o);
        }
        //签到
        function everyday_check_in() {
            $.ajax({
                url:domain.concat('/users/', ${user.primaryKey}, '/checkin_records'),
                method:"POST",
                data:{},
                success:(data) => {
                    if (data.flag) {
                        alert("签到成功,这是您的第" + data.data + "天签到!");
                    } else {
                        alert(data.errorMsg);
                    }
                },
                error:(data) => {
                    alert("未知错误!");
                },
                dataType:"json"
            });
        }
        //登出
        function user_logout() {
            if(window.confirm("您确定要登出吗?")) {
                $.ajax({
                url:domain.concat("/users/logout"),
                type:"POST",
                data:{},
                success:(data) => {
                    if (data.flag) {
                        alert("登出成功");
                        window.location = domain + "/login.html";
                    } else {
                        alert(data.errorMsg);
                    }
                },
                error:(data) => {

                },
                dataType:"json"
                })
            }
        }

        //用户关注部分
        function show_options(o, key) {
            let $o = $(o);
            if(key == true) {
                is_user_select_mode = true;
                reset_select($o.siblings(".options").children(".table").eq(0));
                $o.css("display", "none");
                $o.siblings(".options").css("display", "inline");
                $o.parent().siblings(".users").children(".user").each((index ,e) => {
                    $(e).children(".checke-box").css("display", "inline");
                })
            }else {
                is_user_select_mode = false;
                reset_select(o);
                $o = $o.parent();
                $o.css("display", "none");
                $o.siblings(".table").css("display", "inline");
                $o.parent().siblings(".users").children(".user").each((index ,e) => {
                    $(e).children(".checke-box").css("display", "none");
                })
            }
        }
        function swicth_select_state(o) {
            if (!is_user_select_mode) return;
            let $o = $(o);
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
        function select_all_user(o) {
            $(o).parent().parent().siblings(".users").children(".user").each((index, e) => {
                $(e).attr({"ondrag":"void(0)", "is_select":"true"});
            })
        }
        function reverse_selection_user(o) {
            $(o).parent().parent().siblings(".users").children(".user").each((index, e) => {
                swicth_select_state(e);
            })
        }
        function reset_select_user(o) {
            $(o).parent().parent().siblings(".users").children(".user").each((index, e) => {
                let $e = $(e);
                $e.removeAttr("ondrag");
                $e.attr("is_select", "false");
            })
        }
        //关注,被关注
        function cancle_follow(o, key) {
            let $o = $(o);
            let list = "";
            $o.parent().parent().siblings(".users").children(".user").each((index, e) => {
                let is_select = $(e).attr("is_select");
                if(is_select === "true") is_select = true;
                if(is_select === "false") is_select = false;
                if (is_select) {
                    list = list.concat($(e).attr("user_id"), ',');
                }
            });
            $.ajax({
                url:domain.concat('/users/', user_id, '/concerns'),
                type:"POST",
                data:{"method": "DELETE", 'range': list},
                success:(data) => {
                    if (data.flag) {
                        show_concerns();
                    } else {
                        alert(data.errorMsg);
                    }
                },
                error:(data) => {
                    alert("未知错误");
                }
            });
            reset_select(o);
        }
        function show_concerns() {
            $.ajax({
                url:domain.concat('/users/', user_id, '/concerns'),
                type:"GET",
                data:{},
                success:(data) => {
                    $("#concerns_list").children(".user").remove();
                    if (data.flag) {
                        let datas = data.data;
                        let length = Object.keys(datas).length;
                        for (let i = 0; i < length; i++) {
                            let user = create_user(datas[i]);
                            $("#concerns_list").append(user);
                        }
                    }
                },
                error:(data) => {

                },
                dataType:"json"
            })
        }
        function show_fans() {
            $.ajax({
                url:domain.concat('/users/', user_id, '/fans'),
                type:"GET",
                data:{},
                success:(data) => {
                    $("#fans_list").children(".user").remove();
                    if (data.flag) {
                        let datas = data.data;
                        let length = Object.keys(datas).length;
                        for (let i = 0; i < length; i++) {
                            let user = create_user(datas[i]);
                            $("#fans_list").append(user);
                        }
                    }
                },
                error:(data) => {

                },
                dataType:"json"
            })
        }
        function create_user(o) {
            let user = $('<div class="user" onclick="a_123(this)" user_id=' + o.primaryKey + ' </div>');
            let check_box = $('<div class="checke-box" style="display: none;"><i class="fa fa-check-square-o"></i></div>');
            let u_head = $('<div class="u-head"><img class="normal-user-head" src="./static/image/head_black.png" /></div>');
            let u_body = $('<div class="u-body"></div>');
            let u_1 = $('<div style="text-align: left;">' + o.userName + '</div>');
            let detail = o.detail == "" ? '这个人什么都没有留下' : o.detail;
            let u_2 = $('<div>' + o.detail + '</div>');
            u_body.append(u_1, u_2);
            user.append(check_box, u_head, u_body);
            return $(user).clone();
        }
        function show_class(o) {
            if (is_select_mode) return;
            let id = $(o).attr("course_id");
            window.location = domain + "/overViewPage?course_id=" + id;
        }
        function a_123(o) {
            if (is_user_select_mode) {
                swicth_select_state(o);
            } else {
                show_other_page(o);
            }
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
<div id="img" style="margin-top: 80px;">
    <img src="./static/image/head_1.jpg">
</div>
<div class="located" style="width: 80%; margin: 0 auto;">
    <div class="left_list float">
        <div class="head">
            <div><img class="head_img" src="${user.headFilePath}" style="width: 80px; height: 80px; border-radius: 50%"/></div>
            <div class="font-20">${user.userName}</div>
            <div class="located" style="justify-content: center;">
                <div class="color-black font-20" style="display: inline; margin: 5px; cursor: pointer" onclick="display(5)">粉丝${follower_num}</div>
                <div class="color-black font-20" style="display: inline; margin: 5px; cursor: pointer" onclick="display(4)">关注${follow_num}</div>
            </div>
        </div>
        <div class="lists">
            <div class="list bgc-d-blue color-white" onclick="change_style_1(0); display(0)">
                <i class="fa fa-book"></i>
                <label class="color-black float">课程</label>
            </div>
            <div class="list" onclick="change_style_1(1); display(1)" ondrag="">
                <i class="fa fa-list-ol"></i>
                <label class="color-black float">学习计划</label>
            </div>
            <div class="list" onclick="change_style_1(2); display(2); get_massages()" ondrag="">
                <i class="fa fa-envelope"></i>
                <label class="color-black float">消息</label>
            </div>
            <div class="list" onclick="change_style_1(3); display(3)" ondrag="">
                <i class="fa fa-cog fa-fw" style="left: 5px;"></i>
                <label class="color-black float">个人设置</label>
            </div>
            <div class="list" onclick="change_style_1(4); everyday_check_in()" ondrag="">
                <i class="fa fa-circle"></i>
                <label class="color-black float">每日签到</label>
            </div>
            <div class="list" onclick="user_logout()">
                <i class="fa fa-share-square-o"></i>
                <label class="color-black float">退出登录</label>
            </div>
        </div>
    </div>
    <div id="right" class="float">
        <div class="located panel" style="">
            <div class="head located">
                <div class="flow font-20 table" style="left: 0;" onclick="change_style_2(0, this); display_sub(0, this)" ondrag="">我参与的课程</div>
                <div class="flow font-20 table" style="left: 165px;" onclick="change_style_2(1, this); display_sub(1, this)">我管理的课程</div>
                <div class="flow font-20 table" style="left: 330px;" onclick="change_style_2(2, this); display_sub(2, this)">我收藏的课程</div>
                <div class="divider flow bgc-l-blue-2" style="left: 150px;"></div>
                <div class="divider flow bgc-l-blue-2" style="left: 315px;"></div>
                <div class="divider-flat flow"></div>
            </div>
            <div class="sub-panel" style="">
                <c:forEach items="${all_user_course}" var="course" varStatus="status">
                    <c:if test="${(status.index % 3) == 0}">
                        <div class="classes">
                    </c:if>
                        <div class="class" course_id="${course.primaryKey}" onclick="show_class_over_view(this)">
                            <div><img src="${course.coverFilePath}"/></div>
                            <div>${course.courseName}</div>
                        </div>
                    <c:if test="${(status.index % 3) == 2}">
                        </div>
                    </c:if>
                    <c:if test="${status.last && ((status.index % 3) != 2)}">
                        </div>
                    </c:if>
                </c:forEach>
            </div>
            <div class="sub-panel" style="display: none;">
                <c:forEach items="${all_user_manager_course}" var="course" varStatus="status">
                    <c:if test="${(status.index % 3) == 0}">
                        <div class="grup">
                        <div class="classes">
                    </c:if>
                        <div class="class" course_id="${course.primaryKey}" onclick="show_class_edit(this)">
                            <div><img src="${course.coverFilePath}"/></div>
                            <div>${course.courseName}</div>
                        </div>
                    <c:if test="${(status.index % 3) == 2}">
                        </div>
                        </div>
                    </c:if>
                    <c:if test="${status.last && ((status.index % 3) != 2)}">
                        </div>
                        </div>
                    </c:if>
                </c:forEach>
                <div class="grup">
                    <div class="classes">
                        <div class="class located class-plus">
                            <div style="width: 270px;height: 190px;border: 2px solid black;line-height: 190px;">
                                <i class="fa fa-plus-square-o" style="font-size: 70px;margin-top: 60px;"></i>
                            </div>
                            <div>
                                发布新课程
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="sub-panel" style="display: none;">
                <div class="sub-head">
                    <div class="font-15 table select-start" onclick="change_select_mode(this)">批量操作</div>
                    <div class="select-choose" style="display: none;">
                        <div class="font-15 table" onclick="change_select_mode(this)">返回</div>
                        <div class="font-15 table" onclick="select_all(this)">全选</div>
                        <div class="font-15 table" onclick="reverse_selection(this)">反选</div>
                        <div class="font-15 table" onclick="reset_select(this)">取消选择</div>
                        <div class="font-15 table" onclick="cancel_select_star(this)">取消收藏</div>
                    </div>
                </div>
                <c:forEach items="${all_user_star_course}" var="course" varStatus="status">
                    <c:if test="${(status.index % 3) == 0}">
                        <div class="grup">
                        <div class="classes">
                    </c:if>
                        <div class="class located" course_id="${course.primaryKey}" onclick="change_select(this); show_class(this)">
                            <div><img src="${course.coverFilePath}"/></div>
                            <div>${course.courseName}</div>
                            <div class="select"><i class="fa fa-check-square-o"></i></div>
                        </div>
                    <c:if test="${(status.index % 3) == 2}">
                        </div>
                        </div>
                    </c:if>
                    <c:if test="${status.last && ((status.index % 3) != 2)}">
                        </div>
                        </div>
                    </c:if>
                </c:forEach>
            </div>
        </div>
        <div class="located panel" style="display: none;">
            <div class="head located">
                <div class="flow font-20 table" style="left: 0;" ondrag="">学习计划表</div>
                <div class="divider-flat flow"></div>
            </div>
        </div>
        <div class="located panel" style="display: none;">
            <div class="head located">
                <div class="flow font-20 table" style="left: 0;" onclick="change_style_2(0, this); display_sub(0, this)" ondrag="">动态</div>
                <div class="flow font-20 table" style="left: 165px;" onclick="change_style_2(1, this); display_sub(1, this)">系统消息</div>
                <div class="divider flow bgc-l-blue-2" style="left: 150px;"></div>
                <div class="divider-flat flow"></div>
            </div>
            <div class="sub-panel" style="">
                <div class="sub-head">
                    <div class="font-15 table" onclick="select_all_massage(this)">全选</div>
                    <div class="font-15 table" onclick="reverse_selection_massage(this)">反选</div>
                    <div class="font-15 table" onclick="reset_select_massage(this)">取消选择</div>
                    <div class="font-15 table" onclick="flag_read(this, true)">标记为已读</div>
                    <div class="font-15 table" onclick="flag_read(this, false)">标记为未读</div>
                </div>
                <div class="massages">
                    <div class="massage">
                        <input type="checkbox" class="massage-check" onclick="swicth_massage_state(this)"/>
                        <div class="flow massage-body">
                            <label>用户</label>
                            <a class="remote-url">第一个mt</a>
                            <label>给您的视频</label>
                            <a class="remote-url">第二个mt的观察日记</a>
                            <label>点了个赞</label>
                            <div class="new" ondrag="">新</div>
                        </div>
                    </div>
                    <div class="massage">
                        <input type="checkbox" class="massage-check" onclick="swicth_massage_state(this)"/>
                        <div class="flow massage-body">
                            <label>用户</label>
                            <a class="remote-url">第一个mt</a>
                            <label>给您的视频</label>
                            <a class="remote-url">第二个mt的观察日记</a>
                            <label>点了个赞</label>
                            <div class="new" ondrag="">新</div>
                        </div>
                    </div>
                    <div class="massage">
                        <input type="checkbox" class="massage-check" onclick="swicth_massage_state(this)"/>
                        <div class="flow massage-body">
                            <label>用户</label>
                            <a class="remote-url">第一个mt</a>
                            <label>给您的视频</label>
                            <a class="remote-url">第二个mt的观察日记</a>
                            <label>点了个赞</label>
                            <div class="new" ondrag="">新</div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="sub-panel" style="display: none;">
                <div class="sub-head">
                    <div class="font-15 table" onclick="select_all_massage(this)">全选</div>
                    <div class="font-15 table" onclick="reverse_selection_massage(this)">反选</div>
                    <div class="font-15 table" onclick="reset_select_massage(this)">取消选择</div>
                    <div class="font-15 table" onclick="flag_read(this, true)">标记为已读</div>
                    <div class="font-15 table" onclick="flag_read(this, false)">标记为未读</div>
                </div>
                <div class="massages">
                    <div class="massage">
                        <input type="checkbox" class="massage-check" onclick="swicth_massage_state(this)"/>
                        <div class="flow massage-body">
                            <label>您的课程</label>
                            <a class="remote-url">操作系统原理1</a>
                            <label>被管理员锁定</label>
                            <div class="new" ondrag="">新</div>
                        </div>
                    </div>
                    <div class="massage">
                        <input type="checkbox" class="massage-check" onclick="swicth_massage_state(this)"/>
                        <div class="flow massage-body">
                            <label>您的课程</label>
                            <a class="remote-url">操作系统原理2</a>
                            <label>审核通过，已发布</label>
                            <div class="new" ondrag="">新</div>
                        </div>
                    </div>
                    <div class="massage">
                        <input type="checkbox" class="massage-check" onclick="swicth_massage_state(this)"/>
                        <div class="flow massage-body">
                            <label>用户</label>
                            <a class="remote-url">第一个mt</a>
                            <label>关注了您</label>
                            <div class="new" ondrag="">新</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="located panel" style="display: none;">
            <div class="head located">
                <div class="flow font-20 table" style="left: 0;" onclick="change_style_2(0, this); display_sub(0, this)" ondrag="">基本资料</div>
                <div class="flow font-20 table" style="left: 165px;" onclick="change_style_2(1, this); display_sub(1, this)">修改头像</div>
                <div class="flow font-20 table" style="left: 330px;" onclick="change_style_2(2, this); display_sub(2, this)">密码安全</div>
                <div class="flow font-20 table" style="left: 495px;" onclick="change_style_2(3, this); display_sub(3, this)">其他设置</div>
                <div class="divider flow bgc-l-blue-2" style="left: 150px;"></div>
                <div class="divider flow bgc-l-blue-2" style="left: 315px;"></div>
                <div class="divider flow bgc-l-blue-2" style="left: 480px;"></div>
                <div class="divider-flat flow"></div>
            </div>
            <div class="sub-panel" style="">
                <div style="width: 100%; height: 500px;">
                    <form id="user-info-form">
                        <table class="user-table">
                            <tr>
                                <td>用户名</td>
                                <td><input type="text" name="user_name" class="input" required value="${user.userName}"/></td>
                            </tr>
                            <tr>
                                <td>性别</td>
                                <td>
                                    <input type="radio" name="gender" value="男" ${gender_1}/>男
                                    <input type="radio" name="gender" value="女" ${gender_2}/>女
                                    <input type="radio" name="gender" value="其他" ${gender_3}/>其他
                                </td>
                            </tr>
                            <tr>
                                <td>年龄</td>
                                <td><input type="text" name="age" class="input" oninput="value=value.replace(/[^\d]/g,'')" value="${user.age}"/></td>
                            </tr>
                            <tr>
                                <td>QQ</td>
                                <td><input type="text" name="phone_num" class="input" value="${user.phoneNum}"/></td>
                            </tr>
                            <tr style="height: 100px;">
                                <td style="vertical-align: top;">个人简介</td>
                                <td><textarea class="input" name="detail" style="resize: none;">${user.detail}</textarea></td>
                            </tr>
                            <tr style="height: 30px;">
                                <td></td>
                                <td><input type="submit" class="submit" value="提交"/></td>
                            </tr>
                        </table>
                        <input type="hidden" value="PUT" name="method">
                    </form>
                </div>
            </div>
            <div class="sub-panel" style="float: left; display: none;">
                <div class="font-20">当前我的头像</div>
                <div>
                    <img src="./static/image/head_black.png" style="width: 200px; height: 200px;"/>
                </div>
                <div style="height: 30px;">
                    <form id="head_url">
                        <label>外链地址:<input type="text" name="head_url" style="width: 300px; margin-left: 10px;" /></label>
                        <input type="submit" class="submit" value="更新">
                    </form>
                </div>
                <div class="color-red font-25 flow">由于服务器压力,本站暂时不支持上传用户头像,请使用外链!</div>
            </div>
            <div class="sub-panel" style="display: none;">
                <div style="width: 100%; height: 500px;">
                    <form id="user-password-form" >
                        <table class="user-table">
                            <tr>
                                <td>旧密码</td>
                                <td><input type="password" name="old_password" minlength="8" maxlength="16" class="input" placeholder="您必须先填入此项才能进行下列修改" required onkeyup="value=value.replace(/[/W]/g,'')"/></td>
                            </tr>
                            <tr>
                                <td>新密码</td>
                                <td><input type="password" name="new_password" minlength="8" maxlength="16" class="input" placeholder="如不修改请留空" onkeyup="value=value.replace(/[/W]/g,'')"/></td>
                            </tr>
                            <tr>
                                <td>重复密码</td>
                                <td><input type="password" name="repeat_password" minlength="8" maxlength="16" class="input" placeholder="如不修改请留空" onkeyup="value=value.replace(/[/W]/g,'')"/></td>
                            </tr>
                            <tr>
                                <td>Email</td>
                                <td><input type="text" name="email" class="input" /><a style="cursor: pointer;">(进行验证)</a></td>
                            </tr>
                            <tr>
                                <td>安全问题</td>
                                <td><input type="text" name="security_question" class="input" /></td>
                            </tr>
                            <tr>
                                <td>回答</td>
                                <td><input type="text" name="security_answer" class="input" /></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td><input type="submit" value="提交" class="submit" /></td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
            <div class="sub-panel" style="display: none;">
                <div style="width: 100%; height: 500px;">
                    <form id="user-setting-form">
                        <table id="user-setting-table" class="user-table">
                            <tr>
                                <td style="width: 220px;">不可通过搜索找到我</td>
                                <td>
                                    <input type="radio" name="search_setting"/>是
                                    <input type="radio" name="search_setting" ${search_setting}/>否
                                </td>
                            </tr>
                            <tr>
                                <td>允许他人查看我的课程</td>
                                <td>
                                    <input type="radio" name="class_setting"/>是
                                    <input type="radio" name="class_setting" ${search_setting}/>否
                                </td>
                            </tr>
                            <tr>
                                <td>允许他人查看我的关注</td>
                                <td>
                                    <input type="radio" name="follow_setting"/>是
                                    <input type="radio" name="follow_setting" ${search_setting}/>否
                                </td>
                            </tr>
                            <tr>
                                <td>允许他人查看我的收藏</td>
                                <td>
                                    <input type="radio" name="star_setting"/>是
                                    <input type="radio" name="star_setting" ${search_setting}/>否
                                </td>
                            </tr>
                            <tr>
                                <td>允许他人查看我参与的课程</td>
                                <td>
                                    <input type="radio" name="attend_setting"/>是
                                    <input type="radio" name="attend_setting" ${search_setting}/>否
                                </td>
                            </tr>
                            <tr>
                                <td></td>
                                <td><input type="submit" value="提交" class="submit" /></td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
        </div>
        <div class="located panel" style="display: none;">
            <div class="head located">
                <div class="flow font-20 table" style="left: 15px;" ondrag="">我的关注</div>
                <div class="divider flow bgc-l-blue-2" style="left: 0;"></div>
                <div class="divider-flat flow"></div>
            </div>
            <div class="sub-head">
                <div class="font-15 table" style="float: right;" onclick="show_options(this, true)">批量操作</div>
                <div class="options" style="display: none;">
                    <div class="font-15 table" onclick="select_all_user(this)">全选</div>
                    <div class="font-15 table" onclick="reverse_selection_user(this)">反选</div>
                    <div class="font-15 table" onclick="reset_select_user(this)">取消选择</div>
                    <div class="font-15 table" onclick="cancle_follow(this, true)">取消关注</div>
                    <div class="font-15 table" style="float: right;" onclick="show_options(this, false)">返回</div>
                </div>
            </div>
            <div class="users" id="concerns_list">

            </div>
        </div>
        <div class="located panel" style="display: none;">
            <div class="head located">
                <div class="flow font-20 table" style="left: 15px;" ondrag="">关注我的</div>
                <div class="divider flow bgc-l-blue-2" style="left: 0;"></div>
                <div class="divider-flat flow"></div>
            </div>
            <div class="users" id="fans_list">

            </div>
        </div>
    </div>
</div>
<footer>

</footer>
</body>
</html>