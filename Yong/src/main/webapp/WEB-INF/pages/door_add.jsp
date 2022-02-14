<%--
  Created by IntelliJ IDEA.
  User: 李志杰
  Date: 2021/8/30
  Time: 9:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>门店新增</title>
    <style type="text/css">
        table{
            align-content: center;
            margin: auto;
        }
        div{
            margin: auto;
        }

    </style>
</head>
<body>
    <h2 align="center">门店新增</h2>
    <form action="doorAdd" method="post">
        <table border="1">
            <tr>
                <td width="30%">门店名称</td>
                <td>
                    <input type="text" name="name"/>
                </td>
            </tr>
            <tr>
                <td width="30%">联系电话</td>
                <td>
                    <input type="text" name="tel"/>
                </td>
            </tr>
            <tr>
                <td width="30%">门店地址</td>
                <td>
                    <input type="text" name="addr"/>
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
