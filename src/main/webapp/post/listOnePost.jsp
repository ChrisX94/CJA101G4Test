<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.shakemate.post.model.PostVO" %>

<%
    PostVO postVO = (PostVO) request.getAttribute("post");
%>

<html>
<head>
    <meta charset="UTF-8">
    <title>貼文資料 - listOnePost.jsp</title>
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
            width: 100%; /* 跟 listAllPost.jsp 一致 */
            background-color: white;
            margin-top: 5px;
            margin-bottom: 5px;
            /*border-collapse: collapse; !* 讓邊框更緊密 *!*/
        }
        table, th, td {
            font-size: 12px; /* 一致 */
            border: 1px solid #CCCCFF;
        }
        th, td {
            padding: 5px;
            text-align: center;
            vertical-align: middle;
        }
        /* 讓圖片連結欄可自動換行顯示長網址 */
        td.image-url {
            word-break: break-all;
        }
    </style>
</head>
<body bgcolor="white">

<h4>此頁暫練習採用 Script 的寫法取值:</h4>

<table id="table-1">
    <tr><td>
        <h3>貼文資料 - listOnePost.jsp</h3>
        <h4><a href="${pageContext.request.contextPath}/post/post_page.jsp">回首頁</a></h4>
    </td></tr>
</table>

<table>
    <tr>
        <th>貼文編號</th>
        <th>使用者ID</th>
        <th>貼文內容</th>
        <th>圖片連結</th>
        <th>發文時間</th>
        <th>觀看權限</th>
        <th>按讚數</th>
        <th>留言數</th>
    </tr>
    <tr>
        <td><%= postVO.getPostId() %></td>
        <td><%= postVO.getUserId() %></td>
        <td><%= postVO.getPostText() %></td>
        <td class="image-url">
            <c:if test="${not empty post.imageUrl}">
                <a href="<%= postVO.getImageUrl() %>" target="_blank">查看圖片</a>
            </c:if>
        </td>
        <td>
            <fmt:formatDate value="<%= postVO.getPostTime() %>" pattern="yyyy-MM-dd a hh:mm:ss"/>
        </td>
        <td>
            <c:choose>
                <c:when test="${post.viewerPermission == 0}">所有人</c:when>
                <c:when test="${post.viewerPermission == 1}">配對成功者</c:when>
                <c:when test="${post.viewerPermission == 2}">僅限自己</c:when>
                <c:otherwise>未知</c:otherwise>
            </c:choose>
        </td>
        <td><%= postVO.getLikesCount() %></td>
        <td><%= postVO.getCommentCount() %></td>
    </tr>
</table>

</body>
</html>
