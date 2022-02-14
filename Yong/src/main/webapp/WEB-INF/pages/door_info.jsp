<%--
  Created by IntelliJ IDEA.
  User: 李志杰
  Date: 2021/8/30
  Time: 9:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" %>
<%--el不显示--%>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c"
          uri="http://java.sun.com/jstl/core_rt"
%>
<html>
<head>
    <title>门店修改</title>
    <style type="text/css">
        table{
            align-content: center;
            margin: auto;
        }
            </style>
</head>
<body>
    <h2 align="center">门店修改</h2>
    <form action="doorUpdate" method="post">
        <input type="hidden" name="id" value="${door.id}">
        <table border="1">
            <tr>
                <td width="30%">门店名称</td>
                <td>
                    <input type="text" name="name" value="${door.name}" />
                </td>
            </tr>
            <tr>
                <td width="30%">联系电话</td>
                <td>
                    <input type="text" name="tel" value="${door.tel}"/>
                </td>
            </tr>
            <tr>
                <td width="30%">门店地址</td>
                <td>
                    <input type="text" name="addr" value="${door.addr}"/>
                </td>
            </tr>
            <tr>
                <!-- colspan来合并单元格 横跨的列数 -->
                <td  id="lasttd"colspan="2" >
                    <input type="submit" value="提交"/>
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
