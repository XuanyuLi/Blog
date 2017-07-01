<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/7/1
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>主页</title>
</head>
<body>
<h1>主页</h1>
${requestScope.nick}
<a href="article.jsp">发表博文</a>
<form action="" method="post">
    <input type="text" name="title" placeholder="标题"><br>
    <input type="datetime" name="time" placeholder="发布时间"><br>
    <input type="text" name="content" placeholder="博文内容"><br>
    <input type="text" name="userId" placeholder="发布者"><br>
</form>

</body>
</html>
