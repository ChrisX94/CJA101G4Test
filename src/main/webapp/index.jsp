<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Home Page" %>
</h1>
<br>
<a href="${pageContext.request.contextPath}/login/login.jsp">Login</a>
<br/>
<a href="${pageContext.request.contextPath}/shop/shop.jsp">Shop 測試 Filter 登入用</a>
<br/>

</body>
</html>