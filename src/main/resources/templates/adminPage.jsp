<%--
  Created by IntelliJ IDEA.
  User: qwe13
  Date: 2020/7/2
  Time: 16:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>后台管理</title>
    <link rel="stylesheet" href="./static/css/adminPage.css"/>
    <link rel="stylesheet" href="./static/css/common.css"/>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons"
          rel="stylesheet">
    <link rel="stylesheet" href="./static/css/font-awesome.min.css">
    <script src="./static/js/jquery-3.4.1.min.js"></script>
    <script src="./static/js/common.js"></script>
    <script>
        var is_select_mode = [false, false, false];
        function display(key) {
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

        function user_logout() {
            if(window.confirm("您确定要登出吗?")) {
                window.location = domain + 'mainPage.html';
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
        //课程推荐部分
        function change_select_mode(o, key) {
            //重置选择的课程
            reset_select();

            if(is_select_mode[key]) {
                $(o).parent().css("display", "none");
                $(o).parent().siblings(".select-start").css("display", "inline");
                $(o).parent().parent().siblings(".grup").children(".classes").children(".class").children(".select").css("display", "none");
                is_select_mode[key] = false;
            }else {
                $(o).css("display", "none");
                $(o).siblings(".select-choose").css("display", "inline");
                $(o).parent().siblings(".grup").children(".classes").children(".class").children(".select").css("display", "inline");
                is_select_mode[key] = true;
            }
        }
        //改变选择课程的样式
        function change_select(o, key) {
            if (!is_select_mode[key]) return;
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
        //消息部分
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
            $(o).parent().siblings(".massages").children(".massage").each((index, e) => {
                let $e = $(e);
                let is_select = $e.attr("is_select");
                if(is_select === "true") is_select = true;
                if(is_select === "false") is_select = false;
                if(is_select) {
                    if(key) {
                        $e.children(".massage-body").children(".new").removeAttr("ondrag");
                    }else {
                        $e.children(".massage-body").children(".new").attr("ondrag", "void(0)");
                    }
                }
            })
            reset_select_massage(o);
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
            <div class="located" style="justify-content: center;"></div>
        </div>
        <div class="lists">
            <div class="list bgc-d-blue color-white" onclick="change_style_1(0); display(0)">
                <i class="fa fa-rss"></i>
                <label class="color-black float">公告发布</label>
            </div>
            <div class="list" onclick="change_style_1(1); display(1)" ondrag="">
                <i class="fa fa-user-o"></i>
                <label class="color-black float">用户管理</label>
            </div>
            <div class="list" onclick="change_style_1(2); display(2)" ondrag="">
                <i class="fa fa-list-ul"></i>
                <label class="color-black float">课程管理</label>
            </div>
            <div class="list" onclick="change_style_1(3); display(3)" ondrag="">
                <i class="fa fa-cog fa-fw" style="left: 5px;"></i>
                <label class="color-black float">举报管理</label>
            </div>
            <div class="list" onclick="user_logout()">
                <i class="fa fa-share-square-o"></i>
                <label class="color-black float">退出登录</label>
            </div>
        </div>
    </div>
    <div id="right" class="float">
        <div class="panel located" style="">
            <div class="head located">
                <div class="flow font-20 table" style="left: 0;" onclick="change_style_2(0, this); display_sub(0, this)" ondrag="">公告发布</div>
                <div class="flow font-20 table" style="left: 165px;" onclick="change_style_2(1, this); display_sub(1, this)">课程推荐</div>
                <div class="divider flow bgc-l-blue-2" style="left: 150px;"></div>
                <div class="divider-flat flow"></div>
            </div>
            <div class="sub-panel" style="">
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
                        <td><div id="release_note_btn" class="btn">发布</div></td>
                    </tr>
                </table>
            </div>
            <div class="sub-panel" style="display: none;">
                <div class="search-head">
                    <div>课程名:</div>
                    <input type="email"/>
                    <div id="search_class_btn_recommend" class="btn">查找</div>
                </div>
                <div class="sub-head">
                    <div class="font-15 table select-start" onclick="change_select_mode(this, 0)">批量操作</div>
                    <div class="select-choose" style="display: none;">
                        <div class="font-15 table" onclick="change_select_mode(this, 0)">返回</div>
                        <div class="font-15 table" onclick="select_all(this)">全选</div>
                        <div class="font-15 table" onclick="reverse_selection(this)">反选</div>
                        <div class="font-15 table" onclick="reset_select()">取消选择</div>
                        <div class="font-15 table" onclick="reset_select()">推荐至首页</div>
                    </div>
                </div>
                <div class="grup" style="margin-top: 0;">
                    <div class="classes">
                        <div class="class located" onclick="change_select(this, 0)">
                            <div><img src="./static/image/classes/1.jpg" /></div>
                            <div>操作系统原理</div>
                            <div class="select"><i class="fa fa-check-square-o"></i></div>
                        </div>
                        <div class="class located" onclick="change_select(this, 0)">
                            <div><img src="./static/image/classes/1.jpg" /></div>
                            <div>操作系统原理</div>
                            <div class="select"><i class="fa fa-check-square-o"></i></div>
                        </div>
                        <div class="class located" onclick="change_select(this, 0)">
                            <div><img src="./static/image/classes/1.jpg" /></div>
                            <div>操作系统原理</div>
                            <div class="select"><i class="fa fa-check-square-o"></i></div>
                        </div>
                    </div>
                </div>
                <div class="grup" style="margin-top: 0;">
                    <div class="classes">
                        <div class="class located" onclick="change_select(this, 0)">
                            <div><img src="./static/image/classes/1.jpg" /></div>
                            <div>操作系统原理</div>
                            <div class="select"><i class="fa fa-check-square-o"></i></div>
                        </div>
                        <div class="class located" onclick="change_select(this, 0)">
                            <div><img src="./static/image/classes/1.jpg" /></div>
                            <div>操作系统原理</div>
                            <div class="select"><i class="fa fa-check-square-o"></i></div>
                        </div>
                        <div class="class located" onclick="change_select(this, 0)">
                            <div><img src="./static/image/classes/1.jpg" /></div>
                            <div>操作系统原理</div>
                            <div class="select"><i class="fa fa-check-square-o"></i></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="panel located" style="display: none;">
            <div class="head located">
                <div class="flow font-20 table" style="left: 15px;" ondrag="">用户管理</div>
                <div class="divider flow bgc-l-blue-2" style="left: 0;"></div>
                <div class="divider-flat flow"></div>
            </div>
            <div class="sub-panel" style="">
                <div class="search-head">
                    <div>用户名:</div>
                    <input type="email"/>
                    <div id="search_class_btn_user" class="btn">查找</div>
                </div>
                <div class="users">
                    <div class="user" onclick="swicth_select_state(this)">
                        <div class="checke-box" style="display: none;"><i class="fa fa-check-square-o"></i></div>
                        <div class="u-head"><img class="normal-user-head" src="./static/image/head_black.png" /></div>
                        <div class="u-body">
                            <div style="text-align: left;">第一个mt</div>
                            <div>个人简介个人简介个人简介个人简介个人简介个人简介个人简介个人简介</div>
                        </div>
                    </div>
                    <div class="user" onclick="swicth_select_state(this)">
                        <div class="checke-box" style="display: none;"><i class="fa fa-check-square-o"></i></div>
                        <div class="u-head"><img class="normal-user-head" src="./static/image/head_black.png" /></div>
                        <div class="u-body">
                            <div style="text-align: left;">第五个mt</div>
                            <div>个人简介个人简介个人简介个人简介个人简介个人简介个人简介个人简介</div>
                        </div>
                    </div>
                    <div class="user" onclick="swicth_select_state(this)">
                        <div class="checke-box" style="display: none;"><i class="fa fa-check-square-o"></i></div>
                        <div class="u-head"><img class="normal-user-head" src="./static/image/head_black.png" /></div>
                        <div class="u-body">
                            <div style="text-align: left;">第六个mt</div>
                            <div>个人简介个人简介个人简介个人简介个人简介个人简介个人简介个人简介</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="panel located" style="display: none;">
            <div class="head located">
                <div class="flow font-20 table" style="left: 0;" onclick="change_style_2(0, this); display_sub(0, this)" ondrag="">待审核课程</div>
                <div class="flow font-20 table" style="left: 165px;" onclick="change_style_2(1, this); display_sub(1, this)">全部课程</div>
                <div class="divider flow bgc-l-blue-2" style="left: 150px;"></div>
                <div class="divider-flat flow"></div>
            </div>
            <div class="sub-panel" style="display: none;">
                <div class="sub-head">
                    <div class="font-15 table select-start" onclick="change_select_mode(this, 1)">批量操作</div>
                    <div class="select-choose" style="display: none;">
                        <div class="font-15 table" onclick="change_select_mode(this, 1)">返回</div>
                        <div class="font-15 table" onclick="select_all(this)">全选</div>
                        <div class="font-15 table" onclick="reverse_selection(this)">反选</div>
                        <div class="font-15 table" onclick="reset_select()">取消选择</div>
                        <div class="font-15 table" onclick="reset_select()">通过</div>
                        <div class="font-15 table" onclick="reset_select()">拒绝</div>
                    </div>
                </div>
                <div class="grup" style="margin-top: 0;">
                    <div class="classes">
                        <div class="class located" onclick="change_select(this, 1)">
                            <div><img src="./static/image/classes/1.jpg" /></div>
                            <div>操作系统原理</div>
                            <div class="select"><i class="fa fa-check-square-o"></i></div>
                        </div>
                        <div class="class located" onclick="change_select(this, 1)">
                            <div><img src="./static/image/classes/1.jpg" /></div>
                            <div>操作系统原理</div>
                            <div class="select"><i class="fa fa-check-square-o"></i></div>
                        </div>
                        <div class="class located" onclick="change_select(this, 1)">
                            <div><img src="./static/image/classes/1.jpg" /></div>
                            <div>操作系统原理</div>
                            <div class="select"><i class="fa fa-check-square-o"></i></div>
                        </div>
                    </div>
                </div>
                <div class="grup" style="margin-top: 0;">
                    <div class="classes">
                        <div class="class located" onclick="change_select(this, 1)">
                            <div><img src="./static/image/classes/1.jpg" /></div>
                            <div>操作系统原理</div>
                            <div class="select"><i class="fa fa-check-square-o"></i></div>
                        </div>
                        <div class="class located" onclick="change_select(this, 1)">
                            <div><img src="./static/image/classes/1.jpg" /></div>
                            <div>操作系统原理</div>
                            <div class="select"><i class="fa fa-check-square-o"></i></div>
                        </div>
                        <div class="class located" onclick="change_select(this, 1)">
                            <div><img src="./static/image/classes/1.jpg" /></div>
                            <div>操作系统原理</div>
                            <div class="select"><i class="fa fa-check-square-o"></i></div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="sub-panel" style="display: none;">
                <div class="search-head">
                    <div>课程名:</div>
                    <input type="email"/>
                    <div id="search_class_btn_class" class="btn">查找</div>
                </div>
                <div class="sub-head">
                    <div class="font-15 table select-start" onclick="change_select_mode(this, 2)">批量操作</div>
                    <div class="select-choose" style="display: none;">
                        <div class="font-15 table" onclick="change_select_mode(this, 2)">返回</div>
                        <div class="font-15 table" onclick="select_all(this)">全选</div>
                        <div class="font-15 table" onclick="reverse_selection(this)">反选</div>
                        <div class="font-15 table" onclick="reset_select()">取消选择</div>
                        <div class="font-15 table" onclick="reset_select()">锁定</div>
                    </div>
                </div>
                <div class="grup" style="margin-top: 0;">
                    <div class="classes">
                        <div class="class located" onclick="change_select(this, 2)">
                            <div><img src="./static/image/classes/1.jpg" /></div>
                            <div>操作系统原理</div>
                            <div class="select"><i class="fa fa-check-square-o"></i></div>
                        </div>
                        <div class="class located" onclick="change_select(this, 2)">
                            <div><img src="./static/image/classes/1.jpg" /></div>
                            <div>操作系统原理</div>
                            <div class="select"><i class="fa fa-check-square-o"></i></div>
                        </div>
                        <div class="class located" onclick="change_select(this, 2)">
                            <div><img src="./static/image/classes/1.jpg" /></div>
                            <div>操作系统原理</div>
                            <div class="select"><i class="fa fa-check-square-o"></i></div>
                        </div>
                    </div>
                </div>
                <div class="grup" style="margin-top: 0;">
                    <div class="classes">
                        <div class="class located" onclick="change_select(this, 2)">
                            <div><img src="./static/image/classes/1.jpg" /></div>
                            <div>操作系统原理</div>
                            <div class="select"><i class="fa fa-check-square-o"></i></div>
                        </div>
                        <div class="class located" onclick="change_select(this, 2)">
                            <div><img src="./static/image/classes/1.jpg" /></div>
                            <div>操作系统原理</div>
                            <div class="select"><i class="fa fa-check-square-o"></i></div>
                        </div>
                        <div class="class located" onclick="change_select(this, 2)">
                            <div><img src="./static/image/classes/1.jpg" /></div>
                            <div>操作系统原理</div>
                            <div class="select"><i class="fa fa-check-square-o"></i></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="panel located" style="display: none;">
            <div class="head located">
                <div class="flow font-20 table" style="left: 15px;" onclick="change_style_2(0, this); display_sub(0, this)" ondrag="">举报管理</div>
                <div class="divider flow bgc-l-blue-2" style="left: 0;"></div>
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
                            <label>举报用户</label>
                            <a class="remote-url">第二个mt</a>
                            <label>位于课程</label>
                            <a class="remote-url">操作系统原理</a>
                            <label>下的</label>
                            <a class="remote-url">评论</a>
                            <label>,理由为</label>
                            <label>&lt;恶意刷屏&gt;</label>
                            <div class="new" ondrag="">新</div>
                        </div>
                    </div>
                    <div class="massage">
                        <input type="checkbox" class="massage-check" onclick="swicth_massage_state(this)"/>
                        <div class="flow massage-body">
                            <label>用户</label>
                            <a class="remote-url">第一个mt</a>
                            <label>举报用户</label>
                            <a class="remote-url">第二个mt</a>
                            <label>位于课程</label>
                            <a class="remote-url">操作系统原理</a>
                            <label>下的</label>
                            <a class="remote-url">评论</a>
                            <label>,理由为</label>
                            <label>&lt;恶意刷屏&gt;</label>
                            <div class="new" ondrag="">新</div>
                        </div>
                    </div>
                    <div class="massage">
                        <input type="checkbox" class="massage-check" onclick="swicth_massage_state(this)"/>
                        <div class="flow massage-body">
                            <label>用户</label>
                            <a class="remote-url">第一个mt</a>
                            <label>举报用户</label>
                            <a class="remote-url">第二个mt</a>
                            <label>位于课程</label>
                            <a class="remote-url">操作系统原理</a>
                            <label>下的</label>
                            <a class="remote-url">评论</a>
                            <label>,理由为</label>
                            <label>&lt;恶意刷屏&gt;</label>
                            <div class="new" ondrag="">新</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>