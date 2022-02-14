<%--
  Created by IntelliJ IDEA.
  User: 李志杰
  Date: 2021/8/27
  Time: 15:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" %>
<%--el不显示--%>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c"
          uri="http://java.sun.com/jstl/core_rt"
%>
<!DOCTYPE html>
<html>
<head>
    <title>门店管理</title>
    <style type="text/css">
        table{
            align-content: center;
            margin: auto;
        }

    </style>
</head>
<body>
    <div align="center"><a href="door_add"  target="right" >新增门店</a></div>
    <br/>
    <table border="1">

        <tr>
            <th>序号</th>
            <th width="200px">门店名称</th>
            <th width="200px">电话</th>
            <th width="300px">门店地址</th>
            <th width="200px">操作</th>
        </tr>
        <c:forEach items="${list}" var="door" varStatus="status">

            <tr>
                <td>${status.count}</td>
                <td>${door.name}</td>
                <td>${door.tel}</td>
                <td>${door.addr}</td>
                <td>
                    <a href="doorDelete?id=${door.id}">删除</a>
                    &nbsp;|&nbsp;
                    <a href="doorInfo?id=${door.id}" target="right">修改</a>
                </td>
            </tr>

        </c:forEach>
    </table>
</body>
</html>
