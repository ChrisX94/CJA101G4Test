<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.shakemate.post.model.*"%>
<%@ page import="com.shakemate.post.vo.PostVO" %>

<%
    PostService postSvc = new PostService();
    List<PostVO> postList = postSvc.getAll();
    pageContext.setAttribute("postList", postList);
%>

<html>
<head>
    <meta charset="UTF-8">
    <title>所有貼文資料 - listAllPost.jsp</title>
    <style>
        table#table-1 {
            background-color: #CCCCFF;
            border: 2px solid black;
            text-align: center;
        }
        table#table-1 h4 {
            color: red;
            display: block;
            margin-bottom: 1px;
        }
        h4 {
            color: blue;
            display: inline;
        }
        table {
            width: 100%;
            background-color: white;
            margin-top: 5px;
            margin-bottom: 5px;
        }
        table, th, td {
            font-size: 12px;
            border: 1px solid #CCCCFF;
        }
        th, td {
            padding: 5px;
            text-align: center;
        }
    </style>
</head>
<body>

<fmt:setLocale value="zh_TW" />

<table id="table-1">
    <tr>
        <td>
            <h3>所有貼文資料 - listAllPost.jsp</h3>
            <h4><a href="${pageContext.request.contextPath}/post/post_page.jsp">回首頁</a></h4>
        </td>
    </tr>
</table>

<c:if test="${not empty success}">
    <div style="color:green">${success}</div>
</c:if>

<table>
    <tr>
        <th>貼文編號</th>
        <th>使用者編號</th>
        <th>貼文內容</th>
        <th>圖片連結</th>
        <th>貼文時間</th>
        <th>觀看權限</th>
        <th>按讚數</th>
        <th>留言數</th>
        <th>操作</th>
    </tr>

    <c:forEach var="post" items="${postList}">
        <tr>
            <td>${post.postId}</td>
            <td>${post.userId}</td>
            <td>${post.postText}</td>
            <td>
                <c:if test="${not empty post.imageUrl}">
                    <a href="${post.imageUrl}" target="_blank">查看圖片</a>
                </c:if>
            </td>
            <td>
                <fmt:formatDate value="${post.postTime}" pattern="yyyy-MM-dd a hh:mm:ss"/>
            </td>
            <td>
                <c:choose>
                    <c:when test="${post.viewerPermission == 0}">所有人</c:when>
                    <c:when test="${post.viewerPermission == 1}">配對成功者</c:when>
                    <c:when test="${post.viewerPermission == 2}">僅限自己</c:when>
                    <c:otherwise>未知</c:otherwise>
                </c:choose>
            </td>
            <td>${post.likesCount}</td>
            <td>${post.commentCount}</td>
            <td>
                <form method="post" action="${pageContext.request.contextPath}/post.do" style="display:inline;">
                    <input type="hidden" name="action" value="getOne_For_Update"/>
                    <input type="hidden" name="postId" value="${post.postId}"/>
                    <input type="submit" value="修改"/>
                </form>
<%--                <form method="post" action="${pageContext.request.contextPath}/post.do" style="display:inline;">--%>
<%--                    <input type="hidden" name="action" value="delete"/>--%>
<%--                    <input type="hidden" name="postId" value="${post.postId}"/>--%>
<%--                    <input type="submit" value="刪除"/>--%>
<%--                </form>--%>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
