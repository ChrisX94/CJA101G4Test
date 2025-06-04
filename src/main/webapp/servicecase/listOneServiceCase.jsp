<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.shakemate.servicecase.model.*" %>

<%
	ServiceCaseVO vo = (ServiceCaseVO) request.getAttribute("serviceCaseVO");
%>

<html>
<head>
<title>查詢結果 - listOneServiceCase.jsp</title>
<style>
 table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
 }
 table#data-table {
	width: 600px;
	background-color: white;
	border-collapse: collapse;
	margin-top: 20px;
 }
 th, td {
	border: 1px solid #CCCCFF;
	padding: 8px;
	text-align: left;
 }
</style>
</head>
<body>

<table id="table-1">
	<tr>
		<td>
			<h3>案件資料 - 查詢結果</h3>
			<h4><a href="${pageContext.request.contextPath}/servicecase/select_page.jsp"><img src="/CJA101G4_Carter/resource/images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
		</td>
	</tr>
</table>

<% if (vo == null) { %>
	<p style="color:red;">查無資料！</p>
<% } else { %>
	<table id="data-table">
		<tr><th>案件編號</th><td><%= vo.getCaseId() %></td></tr>
		<tr><th>會員ID</th><td><%= vo.getUserId() %></td></tr>
		<tr><th>管理員ID</th><td><%= vo.getAdmId() %></td></tr>
		<tr><th>案件類型ID</th><td><%= vo.getCaseTypeId() %></td></tr>
		<tr><th>建立時間</th><td><%= vo.getCreateTime() %></td></tr>
		<tr><th>修改時間</th><td><%= vo.getUpdateTime() %></td></tr>
		<tr><th>標題</th><td><%= vo.getTitle() %></td></tr>
		<tr><th>內容</th><td><%= vo.getContent() %></td></tr>
		<tr><th>案件狀態</th><td><%= vo.getCaseStatus() %></td></tr>
	</table>
<% } %>

</body>
</html>
