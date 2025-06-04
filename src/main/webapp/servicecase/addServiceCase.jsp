<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="com.shakemate.servicecase.model.*" %>

<%
	ServiceCaseVO vo = (ServiceCaseVO) request.getAttribute("serviceCaseVO");
%>

<html>
<head>
<title>新增案件 - addServiceCase.jsp</title>
<style>
 table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
 }
 table#table-1 h4 {
	color: red;
	margin-bottom: 1px;
 }
 table {
	width: 600px;
	background-color: white;
	border: 1px solid #CCCCFF;
	margin-top: 5px;
 }
 th, td {
	padding: 5px;
	text-align: left;
 }
</style>
</head>
<body>

<table id="table-1">
	<tr>
		<td><h3>新增案件</h3></td>
		<td><h4><a href="${pageContext.request.contextPath}/servicecase/select_page.jsp"><img src="${pageContext.request.contextPath}/servicecase/images/tomcat.png" width="100" height="80" border="0">回首頁</a></h4></td>
	</tr>
</table>

<h3>填寫表單:</h3>

<%-- 錯誤訊息 --%>
<c:if test="${not empty errorMsgs}">
	<font color="red">請修正以下錯誤：</font>
	<ul>
		<c:forEach var="msg" items="${errorMsgs}">
			<li style="color:red">${msg}</li>
		</c:forEach>
	</ul>
</c:if>

<form method="post" action="<%=request.getContextPath()%>/servicecase/servicecase.do">
	<table>
		<tr><td>會員ID：</td>
			<td><input type="text" name="userId" value="<%= (vo==null) ? "" : vo.getUserId() %>"></td></tr>

		<tr><td>管理員ID：</td>
			<td><input type="text" name="admId" value="<%= (vo==null) ? "" : vo.getAdmId() %>"></td></tr>

		<tr><td>案件類型ID：</td>
			<td>
				<select name="caseTypeId">
					<option value="1" selected>1 - 一般問題</option>
					<option value="2">2 - 技術支援</option>
					<option value="3">3 - 投訴建議</option>
				</select>
			</td>
		</tr>

		<tr><td>標題：</td>
			<td><input type="text" name="title" size="50" value="<%= (vo==null) ? "" : vo.getTitle() %>"></td></tr>

		<tr><td>內容：</td>
			<td><textarea name="content" rows="5" cols="40"><%= (vo==null) ? "" : vo.getContent() %></textarea></td></tr>

		<tr><td>狀態：</td>
			<td>
				<select name="caseStatus">
					<option value="0" selected>0 - 未處理</option>
					<option value="1">1 - 處理中</option>
					<option value="2">2 - 已結案</option>
				</select>
			</td>
		</tr>
	</table>

	<input type="hidden" name="action" value="insert">
	<input type="submit" value="送出新增">
</form>

</body>
</html>
