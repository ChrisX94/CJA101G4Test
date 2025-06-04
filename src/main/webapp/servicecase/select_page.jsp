<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="zh-TW">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>智能客服系統 - ServiceCase</title>

<style>
 table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	border: 3px ridge Gray;
	height: 80px;
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
</style>
</head>
<body>

<table id="table-1">
 <tr><td><h3>Service Case: Home</h3><h4>( MVC )</h4>
 <h5><img src="${pageContext.request.contextPath}/servicecase/images/tomcat.png" width="100" height="80" border="0"></h5>
 </td></tr>
</table>

<p>這是 ServiceCase 的首頁</p>

<h3>資料查詢:</h3>

<form method="post" action="<%=request.getContextPath()%>/servicecase/servicecase.do">
 輸入案件編號：
 <input type="text" name="caseId">
 <input type="hidden" name="action" value="getOne_For_Display">
 <input type="submit" value="查詢">
 
 		<%-- 錯誤訊息 --%>
		<c:if test="${not empty errorMsgs}">
			<ul>
				<c:forEach var="msg" items="${errorMsgs}">
					<li style="color: red">${msg}</li>
				</c:forEach>
				<font color="red">請重新輸入查詢條件!</font>
			</ul>
		</c:if>
 
</form>

<ul>
 <li><a href="${pageContext.request.contextPath}/servicecase/listAllServiceCase.jsp">List all cases</a></li>
 <li><a href="${pageContext.request.contextPath}/servicecase/addServiceCase.jsp">Add new case</a></li>
</ul>

</body>
</html>
