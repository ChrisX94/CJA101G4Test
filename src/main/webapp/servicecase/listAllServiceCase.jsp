<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.shakemate.servicecase.model.*" %>
<%@ page import="java.util.*" %>

<%
    ServiceCaseService svc = new ServiceCaseService();
    List<ServiceCaseVO> list = svc.getAll();
    pageContext.setAttribute("list", list);
%>

<html>
<head>
<title>所有案件資料 - listAllServiceCase.jsp</title>

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
 h4 {
	color: blue;
	display: inline;
 }
 table {
	width: 100%;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
	border-collapse: collapse;
 }
 th, td {
	border: 1px solid #CCCCFF;
	padding: 5px;
	text-align: center;
 }
</style>
</head>
<body>

<table id="table-1">
	<tr>
		<td>
			<h3>所有案件資料</h3>
			<h4><a href="${pageContext.request.contextPath}/servicecase/select_page.jsp"><img src="/CJA101G4_Carter/resource/images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
		</td>
	</tr>
</table>

<%@ include file="page1.file" %>

<table>
	<tr>
		<th>案件編號</th>
		<th>會員ID</th>
		<th>管理員ID</th>
		<th>案件類型ID</th>
		<th>建立時間</th>
		<th>修改時間</th>
		<th>標題</th>
		<th>內容</th>
		<th>狀態</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<c:forEach var="vo" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr>
			<td>${vo.caseId}</td>
			<td>${vo.userId}</td>
			<td>${vo.admId}</td>
			<td>${vo.caseTypeId}</td>
			<td>${vo.createTime}</td>
			<td>${vo.updateTime}</td>
			<td>${vo.title}</td>
			<td>${vo.content}</td>
			<td>${vo.caseStatus}</td>
			<td>
				<form method="post" action="<%=request.getContextPath()%>/servicecase/servicecase.do">
					<input type="hidden" name="action" value="getOne_For_Update">
					<input type="hidden" name="caseId" value="${vo.caseId}">
					<input type="submit" value="修改">
				</form>
			</td>
			<td>
				<form method="post" action="<%=request.getContextPath()%>/servicecase/servicecase.do">
					<input type="hidden" name="action" value="delete">
					<input type="hidden" name="caseId" value="${vo.caseId}">
					<input type="submit" value="刪除">
				</form>
			</td>
		</tr>
	</c:forEach>
</table>

<%@ include file="page2.file" %>

</body>
</html>
