<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="com.shakemate.post.model.PostVO" %>

<html>
<head>
  <meta charset="UTF-8">
  <title>貼文修改 - updatePost.jsp</title>

  <style>
    .readonly-input {
      background-color: #eee;      /* 淺灰底 */
      color: #888;                 /* 文字灰色 */
      border: 1px solid #ccc;      /* 邊框淡灰 */
      cursor: not-allowed;         /* 游標禁用狀態 */
    }
  </style>
</head>
<body>
<h3>貼文修改</h3>
<h4><a href="${pageContext.request.contextPath}/post/post_page.jsp">回首頁</a></h4>


<c:if test="${not empty errorMsgs}">
  <font color="red">請修正以下錯誤:</font>
  <ul>
    <c:forEach var="message" items="${errorMsgs}">
      <li style="color:red">${message}</li>
    </c:forEach>
  </ul>
</c:if>

<form method="post" action="${pageContext.request.contextPath}/post.do">
  <table>
    <tr>
      <td>貼文編號:</td>
      <td>${post.postId}</td>
    </tr>

    <tr>
      <td>會員編號:</td>
      <td><input type="text" name="userId" value="${post.userId}" readonly class="readonly-input"/></td>
    </tr>

    <tr>
      <td>貼文內容:</td>
      <td><textarea name="postText" rows="5" cols="45">${post.postText}</textarea></td>
    </tr>

    <tr>
      <td>圖片連結:</td>
      <td><input type="text" name="imageUrl" value="${post.imageUrl}" size="45" /></td>
    </tr>

    <tr>
      <td>公開權限:</td>
      <td>
        <select name="viewerPermission">
          <option value="0" <c:if test="${post.viewerPermission == 0}">selected</c:if>>所有人</option>
          <option value="1" <c:if test="${post.viewerPermission == 1}">selected</c:if>>配對成功者</option>
          <option value="2" <c:if test="${post.viewerPermission == 2}">selected</c:if>>僅限自己</option>
        </select>
      </td>
    </tr>
  </table>

  <input type="hidden" name="postId" value="${post.postId}" />
  <input type="hidden" name="action" value="update" />
  <input type="submit" value="送出修改" />
</form>
</body>
</html>
