<%@ page import="java.util.Date" %>
<%--
  Created by IntelliJ IDEA.
  User: 李志杰
  Date: 2021/8/12
  Time: 20:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <style>
        p{border:2px solid red;background: pink;}
    </style>
</head>
<body>
    <h1>index.jsp.....</h1>
    <%--    java代码--%>
    <P><%= request.getAttribute("name") %></p>
    <P><%= request.getAttribute("addr") %></p>
    <%--    java语句--%>
    <h1>hello jsp ... <%= new Date()%></h1>

</body>
</html>
