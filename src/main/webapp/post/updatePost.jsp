<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.shakemate.post.model.PostVO" %>

<%
  PostVO postVO = (PostVO) request.getAttribute("post");
%>
<%= postVO == null %>

<html>
<head>
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
  <title>貼文修改 - update_post_input.jsp</title>

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
  </style>

  <style>
    table {
      width: 600px;
      background-color: white;
      margin-top: 1px;
      margin-bottom: 1px;
    }
    table, th, td {
      border: 0px solid #CCCCFF;
    }
    th, td {
      padding: 1px;
    }
  </style>
</head>

<body bgcolor='white'>

<table id="table-1">
  <tr><td>
    <h3>貼文修改 - update_post_input.jsp</h3>
    <h4><a href="<%=request.getContextPath()%>/post/select_page.jsp">
      <img src="<%=request.getContextPath()%>/images/back1.gif" width="100" height="32" border="0">回首頁</a>
    </h4>
  </td></tr>
</table>

<h3>資料修改:</h3>

<c:if test="${not empty errorMsgs}">
  <font style="color:red">請修正以下錯誤:</font>
  <ul>
    <c:forEach var="message" items="${errorMsgs}">
      <li style="color:red">${message}</li>
    </c:forEach>
  </ul>
</c:if>

<FORM METHOD="post" ACTION="post.do" name="form1">
  <table>
    <tr>
      <td>貼文編號:</td>
      <td><%= postVO.getPostId() %></td>
    </tr>
    <tr>
      <td>會員編號:</td>
      <td><input type="TEXT" name="userId" value="<%= postVO.getUserId() %>" size="45" readonly /></td>
    </tr>
    <tr>
      <td>貼文內容:</td>
      <td><textarea name="postText" rows="5" cols="45"><%= postVO.getPostText() %></textarea></td>
    </tr>
    <tr>
      <td>圖片連結:</td>
      <td><input type="TEXT" name="imageUrl" value="<%= postVO.getImageUrl() %>" size="45" /></td>
    </tr>
    <tr>
      <td>公開權限:</td>
      <td>
        <select name="viewerPermission">
          <option value="PUBLIC" <%= "PUBLIC".equals(postVO.getViewerPermission()) ? "selected" : "" %>>公開</option>
          <option value="FRIENDS" <%= "FRIENDS".equals(postVO.getViewerPermission()) ? "selected" : "" %>>好友</option>
          <option value="PRIVATE" <%= "PRIVATE".equals(postVO.getViewerPermission()) ? "selected" : "" %>>私人</option>
        </select>
      </td>
    </tr>
  </table>
  <br>
  <input type="hidden" name="action" value="update">
  <input type="hidden" name="postId" value="<%= postVO.getPostId() %>">
  <input type="submit" value="送出修改">
</FORM>
</body>
</html>
