<%@ page import="com.shakemate.post.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    PostVO postVO = (PostVO) request.getAttribute("post");
%>

<html>
<head>
    <meta charset="UTF-8">
    <title>貼文新增</title>
</head>
<body>
<h3>貼文新增 - addPost.jsp</h3>
<h4><a href="${pageContext.request.contextPath}/post/post_page.jsp">回首頁</a></h4>

<h3>資料新增:</h3>

<c:if test="${not empty errorMsgs}">
    <font style="color:red">請修正以下錯誤:</font>
    <ul>
        <c:forEach var="message" items="${errorMsgs}">
            <li style="color:red">${message}</li>
        </c:forEach>
    </ul>
</c:if>

<!-- ✅ 加上 enctype="multipart/form-data" -->
<form method="post" action="${pageContext.request.contextPath}/post.do" enctype="multipart/form-data">
    <table>
        <tr>
            <td>使用者ID:</td>
            <td><input type="text" name="userId"
                       value="${post.userId}" size="45"/></td>
        </tr>

        <tr>
            <td>貼文內容:</td>
            <td><textarea name="postText" rows="4" cols="45">${post.postText}</textarea></td>
        </tr>

        <tr>
            <td>上傳圖片:</td>
            <td><input type="file" name="imageFile" accept="image/*"/></td>
        </tr>

        <tr>
            <td>觀看權限:</td>
            <td>
                <select name="viewerPermission">
                    <option value="0" <c:if test="${post.viewerPermission == 0}">selected</c:if>>所有人</option>
                    <option value="1" <c:if test="${post.viewerPermission == 1}">selected</c:if>>配對成功者</option>
                    <option value="2" <c:if test="${post.viewerPermission == 2}">selected</c:if>>僅限自己</option>
                </select>
            </td>
        </tr>
    </table>

    <br>
    <input type="hidden" name="action" value="insert">
    <input type="submit" value="送出新增">
</form>
</body>
</html>
