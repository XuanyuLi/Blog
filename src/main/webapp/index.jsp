<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/7/1
  Time: 8:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>首页</title>
  </head>
  <body>
 <h1>首页</h1>
 <form action="user" method="post">
     <input type="hidden" name="action" value="login">
     <input type="email" name="email" placeholder="邮箱"><br>
     <input type="password" name="password" placeholder="密码"><br>
     <input type="submit" value="登录">
     <a href="register.jsp">注册</a>
 </form>
 ${requestScope.message}
  </body>
</html>
