<%--
  Created by IntelliJ IDEA.
  User: qwe13
  Date: 2020/7/2
  Time: 16:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${other_user.userName}的主页</title>
    <link rel="stylesheet" href="./static/css/otherPage.css"/>
    <link rel="stylesheet" href="./static/css/common.css"/>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons"
          rel="stylesheet">
    <link rel="stylesheet" href="./static/css/font-awesome.min.css">
    <script src="./static/js/jquery-3.4.1.min.js"></script>
    <script src="./static/js/common.js"></script>
    <script>
        var user_id = ${user.primaryKey};
        var other_id = ${other_user.primaryKey};
        function cancel_follow() {
            $.ajax({
                url:domain.concat('/users/', user_id, '/concerns'),
                type:"POST",
                data:{"method": "DELETE", 'range': other_id},
                success:(data) => {
                    if (data.flag) {
                        let btn = $('<div id="follow_btn" onclick="follow()">关注</div>');
                        $("#123").append(btn);
                    } else {
                        alert(data.errorMsg);
                    }
                },
                error:(data) => {
                    alert("未知错误");
                }
            })
        }
        function follow() {
            $.ajax({
                url:domain.concat('/users/', user_id, '/concerns/', other_id),
                type:"POST",
                data:{},
                success:(data) => {
                    if (data.flag) {
                        let btn = $('<div id="follow_btn" onclick="cancel_follow()">取消关注</div>');
                        $("#123").append(btn);
                    } else {
                        alert(data.errorMsg);
                    }
                },
                error:(data) => {
                    alert("未知错误");
                }
            })
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
<div id="img" class="located" style="margin-top: 80px;">
    <img src="./static/image/head_1.jpg">
    <div id="head_in_img">
        <div class="located" id="123" style="width: 100%; height: 100%;">
            <div class="flow" style="left: 10px; bottom: 0;">
                <div style="float: left;"><img src="./static/image/head_white.png" style="width: 60px; height: 60px; border-radius: 50%;"/></div>
                <div style="float: left; margin-left: 20px;">
                    <div class="font-20" style="text-align: left; color: white;">${other_user.userName}</div>
                    <div class="font-12" style="text-align: left; color: black; margin-top: 10px;">这个人什么都没留下</div>
                </div>
            </div>
            <c:if test="${is_follow}">
                <div id="follow_btn" onclick="follow()">关注</div>
            </c:if>
            <c:if test="${!is_follow}">
                <div id="follow_btn" onclick="cancel_follow()">取消关注</div>
            </c:if>
            <div class="flow" style="right: 0; bottom: 5px;">
                <div class="float color-white" style="margin: 0 5px;">关注数${follow_num}</div>
                <div class="float color-white" style="margin: 0 5px;">粉丝数${follower_num}</div>
            </div>
        </div>
    </div>
</div>
<div class="located" style="width: 80%; height: 900px; margin: 0 auto;">
    <div id="left" class="float">
        <div>
            <div class="head located">
                <div class="divider flow bgc-l-blue-2"></div>
                <label class="color-black flow font-20" style="left: 25px;height: 40px;top: 50%;transform: translateY(-50%);">发布的课程</label>
                <div class="flow font-20 more bgc-d-gray-1 border-5" style="right: 0px;"><a href="javascript:void(0)" class="full">更多</a></div>
                <div class="divider-flat flow"></div>
            </div>
            <c:forEach items="${all_user_manager_course}" var="course" varStatus="status" end="4">
                <c:if test="${(status.index % 4) == 0}">
                    <div class="grup">
                    <div class="classes">
                </c:if>
                    <div class="class" course_id="${course.primaryKey}">
                        <div><img src="${course.coverFilePath}"/></div>
                        <div>${course.courseName}</div>
                    </div>
                 <c:if test="${(status.index % 4) == 3}">
                    </div>
                    </div>
                </c:if>
                <c:if test="${status.last && ((status.index % 4) != 3)}">
                    </div>
                    </div>
                </c:if>
            </c:forEach>
        </div>
        <div>
            <div class="head located">
                <div class="divider flow bgc-l-blue-2"></div>
                <label class="color-black flow font-20" style="left: 25px;height: 40px;top: 50%;transform: translateY(-50%);">参与的课程</label>
                <div class="flow font-20 more bgc-d-gray-1 border-5" style="right: 0px;"><a href="javascript:void(0)" class="full">更多</a></div>
                <div class="divider-flat flow"></div>
            </div>
            <c:forEach items="${all_user_course}" var="course" varStatus="status" end="4">
                <c:if test="${(status.index % 4) == 0}">
                    <div class="grup">
                    <div class="classes">
                </c:if>
                    <div class="class" course_id="${course.primaryKey}">
                        <div><img src="${course.coverFilePath}"/></div>
                        <div>${course.courseName}</div>
                    </div>
                <c:if test="${(status.index % 4) == 3}">
                    </div>
                    </div>
                </c:if>
                <c:if test="${status.last && ((status.index % 4) != 3)}">
                    </div>
                    </div>
                </c:if>
            </c:forEach>
        </div>
        <div>
            <div class="head located">
                <div class="divider flow bgc-l-blue-2"></div>
                <label class="color-black flow font-20" style="left: 25px;height: 40px;top: 50%;transform: translateY(-50%);">收藏的课程</label>
                <div class="flow font-20 more bgc-d-gray-1 border-5" style="right: 0px;"><a href="javascript:void(0)" class="full">更多</a></div>
                <div class="divider-flat flow"></div>
            </div>
            <c:forEach items="${all_user_star_course}" var="course" varStatus="status" end="4">
                <c:if test="${(status.index % 4) == 0}">
                    <div class="grup">
                    <div class="classes">
                </c:if>
                    <div class="class" course_id="${course.primaryKey}">
                        <div><img src="${course.coverFilePath}"/></div>
                        <div>${course.courseName}</div>
                    </div>
                <c:if test="${(status.index % 4) == 3}">
                    </div>
                    </div>
                </c:if>
                <c:if test="${status.last && ((status.index % 4) != 3)}">
                    </div>
                    </div>
                </c:if>
            </c:forEach>
        </div>
    </div>
    <div id="right" class="float">
        <div class="head located">
            <div class="divider flow bgc-l-blue-2"></div>
            <label class="color-black flow font-20" style="left: 25px;height: 40px;top: 50%;transform: translateY(-50%);">最近动态</label>
            <div class="divider-flat flow"></div>
        </div>
        <div class="actions">
            <c:forEach items="${other_massage}" var="action" varStatus="status">
                <div class="action located">
                    <div class="action-body">${action.detail}</div>
                    <div class="action-time">${action.sendTime}</div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>

</body>
</html>