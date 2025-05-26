<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.shakemate.post.model.*" %>

<% //見com.emp.controller.EmpServlet.java第238行存入req的empVO物件 (此為輸入格式有錯誤時的empVO物件)
   PostVO postVO = (PostVO) request.getAttribute("postVO");
%>
--<%= postVO==null %>--${postVO.postId}-- <!-- line 100 -->
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>貼文新增 - addPost.jsp</title>

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
	width: 450px;
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
		 <h3>員貼文新增 - addPost.jsp</h3></td><td>
<%--		 <h4><a href="select_page.jsp">回首頁</a></h4>--%>
	</td></tr>
</table>

<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="emp.do" name="form1">
<table>




	<tr>
		<td>貼文編號:</td>
		<td><input type="TEXT" name="ename" value="<%= (postVO==null)? "()" : postVO.getEname()%>" size="45"/></td>
	</tr>
	<tr>
		<td>發文者:</td>
		<td><input type="TEXT" name="job"   value="<%= (postVO==null)? "()" : postVO.getJob()%>" size="45"/></td>
	</tr>
	<tr>
		<td>內容:</td>
		<td><input name="hiredate" id="f_date1" type="text" ></td>
	</tr>
	<tr>
		<td>附加圖片網址:</td>
		<td><input type="TEXT" name="sal"   value="<%= (postVO==null)? "()" : postVO.getSal()%>" size="45"/></td>
	</tr>
	<tr>
		<td>發文時間:</td>
		<td><input type="TEXT" name="comm"  value="<%= (postVO==null)? "()" : postVO.getComm()%>" size="45"/></td>
	</tr>


<%--    觀看者權限--%>
<%--    按讚總數--%>
<%--    留言數--%>
	<jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" />
	<tr>
		<td>部門:<font color=red><b>*</b></font></td>
		<td><select size="1" name="deptno">
			<c:forEach var="deptVO" items="${deptSvc.all}">
				<option value="${deptVO.deptno}" ${(empVO.deptno==deptVO.deptno)? 'selected':'' } >${deptVO.dname}
			</c:forEach>
		</select></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>

</body>






<script>

</script>
</html>