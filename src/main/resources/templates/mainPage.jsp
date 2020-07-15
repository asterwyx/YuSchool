<%--
  Created by IntelliJ IDEA.
  User: qwe13
  Date: 2020/6/30
  Time: 15:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>主页</title>
    <link rel="stylesheet" href="static/css/mainPage.css"/>
    <link rel="stylesheet" href="static/css/common.css"/>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons"
          rel="stylesheet">
    <link rel="stylesheet" href="static/css/font-awesome.min.css">
    <script src="static/js/jquery-3.4.1.min.js"></script>
    <script src="./static/js/common.js"></script>
    <script>
        $(function () {

        })
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
    <img src="static/image/head_2.jpg">
</div>
<div class="located bgc-l-gray" style="width: 80%;height: 200px;margin: 0 auto;margin-top: 10px;">
    <div id="recommend" class="float">
        <div class="head located">
            <div class="divider flow bgc-l-blue-2"></div>
            <label class="color-black flow font-20" style="left: 30px;height: 40px;top: 50%;transform: translateY(-50%);">特别推荐</label>
            <div class="flow font-20 more bgc-d-gray-1 border-5" style="right: 12px;"><a href="javascript:void(0)" class="full">更多</a></div>
        </div>
        <div class="classes" style="overflow: hidden">
            <c:forEach items="${recommend_course}" var="course" varStatus="status" end="3">
                <div class="class" course_id="${course.course.primaryKey}" onclick="show_class_over_view(this)">
                    <div><img src="${course.course.coverFilePath}"/></div>
                    <div>${course.course.courseName}</div>
                </div>
            </c:forEach>
        </div>
    </div>
    <div id="announcement" class="float">
        <div class="head located">
            <div class="divider flow bgc-l-blue-2"></div>
            <label class="color-black flow font-20" style="left: 30px;height: 40px;top: 50%;transform: translateY(-50%);">通知公告</label>
            <div class="flow font-20 more bgc-d-gray-1 border-5" style="right: 12px;"><a href="javascript:void(0)" class="full">更多</a></div>
        </div>
        <div class="notices">
            <div class="notice font-20"><a href="javascript:void(0)">2020.6.25停机维护公告</a></div>
            <div class="notice font-20"><a href="javascript:void(0)">2020.6.19版本更新公告</a></div>
        </div>
    </div>
</div>
<div class="located" style="width: 80%;height: 500px;margin: 0 auto;margin-top: 10px;">
    <div id="all" class="float">
        <div class="head located">
            <div class="divider flow bgc-l-blue-2"></div>
            <label class="color-black flow font-20" style="left: 30px;height: 40px;top: 50%;transform: translateY(-50%);">所有课程</label>
            <div class="flow font-20 more bgc-d-gray-1 border-5" style="right: 12px;"><a href="javascript:void(0)" class="full">更多</a></div>
        </div>
        <c:forEach items="${all_course}" var="course" varStatus="status">
            <c:if test="${(status.index % 4) == 0}">
                <div class="grup">
                <div class="classes">
            </c:if>
            <div class="class" course_id="${course.primaryKey}" onclick="show_class_over_view(this)">
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
    <div id="list" class="float">
        <div class="head located">
            <div class="divider flow bgc-l-blue-2"></div>
            <label class="color-black flow font-20" style="left: 30px;height: 40px;top: 50%;transform: translateY(-50%);">排行榜</label>
            <div class="flow font-20 more bgc-d-gray-1 border-5" style="right: 12px;"><a href="javascript:void(0)" class="full">更多</a></div>
        </div>
        <div class="ranks">
            <c:forEach items="${rank_course}" var="course" varStatus="status" end="9">
                <div class="rank font-20 float" course_id="${course.primaryKey}" onclick="show_class_over_view(this)">
                    <label>${status.count}</label>
                    <a href="javascript:void(0)">${course.courseName}</a>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<div id="foot"></div>
</body>
</html>