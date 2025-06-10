<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.shakemate.post.model.*"%>
<%@ page import="java.util.List" %>
<%@ page import="com.shakemate.post.vo.PostVO" %>

<%
  // 建立 PostService 物件，並呼叫 getAll() 取得貼文列表
  PostService postSvc = new PostService();
  List<PostVO> postList = postSvc.getAll();
  // 放到 pageContext 讓 JSTL EL 可以取得
  pageContext.setAttribute("postList", postList);
%>

<html>
<head>
  <title>Shakemate: Post Home</title>

  <style>
    table#table-1 {
      width: 450px;
      background-color: #CCCCFF;
      margin-top: 5px;
      margin-bottom: 10px;
      border: 3px ridge Gray;
      height: 80px;
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

</head>
<body bgcolor='white'>

<table id="table-1">
  <tr><td><h3>Shakemate: Post Home</h3><h4>(MVC)</h4></td></tr>
</table>

<p>This is the Home page for Post Management.</p>

<h3>資料查詢:</h3>

<!-- 錯誤表列 -->
<c:if test="${not empty errorMsgs}">
  <font style="color:red">請修正以下錯誤:</font>
  <ul>
    <c:forEach var="message" items="${errorMsgs}">
      <li style="color:red">${message}</li>
    </c:forEach>
  </ul>
</c:if>

<ul>
  <li><a href='${pageContext.request.contextPath}/post/listAllPost.jsp'>List</a> all Posts.<br><br></li>

  <li>
    <form method="post" action="${pageContext.request.contextPath}/post.do">
      <b>輸入貼文編號 (如1):</b>
      <input type="text" name="postId" />
      <input type="hidden" name="action" value="getOne_For_Display" />
      <input type="submit" value="送出" />
    </form>
  </li>

  <li>
    <form method="post" action="${pageContext.request.contextPath}/post.do">
      <b>選擇貼文編號:</b>
      <select size="1" name="postId">
        <c:forEach var="postVO" items="${postList}">
          <option value="${postVO.postId}">${postVO.postId}</option>
        </c:forEach>
      </select>
      <input type="hidden" name="action" value="getOne_For_Display" />
      <input type="submit" value="送出" />
    </form>
  </li>

  <li>
    <form method="post" action="${pageContext.request.contextPath}/post.do">
      <b>選擇貼文文字:</b>
      <select size="1" name="postId">
        <c:forEach var="postVO" items="${postList}">
          <option value="${postVO.postId}">${postVO.postText}</option>
        </c:forEach>
      </select>
      <input type="hidden" name="action" value="getOne_For_Display" />
      <input type="submit" value="送出" />
    </form>
  </li>
</ul>

<h3>貼文管理</h3>

<ul>
  <li><a href='${pageContext.request.contextPath}/post/addPost.jsp'>Add</a> a new Post.</li>
</ul>

</body>
</html>
