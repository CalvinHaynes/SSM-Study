<%--
  Created by IntelliJ IDEA.
  User: CalvinHaynes
  Date: 2021/8/30
  Time: 13:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
    <h1>登录</h1>
    <form method="post" action="${pageContext.request.contextPath}/login">
        用户名: <input type="text" name="username"/>      <br>
        密码: <input type="password" name="password"/>    <br>

        掌握语言:                                         <br>
        <input type="checkbox" name="languages" value="Java"/>Java
        <input type="checkbox" name="languages" value="python"/>python
        <input type="checkbox" name="languages" value="C/C++"/>C/C++
        <input type="checkbox" name="languages" value="javascript"/>javascript
        <br>

        <input type="submit"/>
    </form>
</body>
</html>
