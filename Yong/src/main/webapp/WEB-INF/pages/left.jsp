<%--
  Created by IntelliJ IDEA.
  User: 李志杰
  Date: 2021/8/27
  Time: 15:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <style>
        body{
            background: #222D32;
            margin: 0px;
        }
        div{
            height: 40px;
            border-top: 1px solid #FFFFFF;

            font-size: 22px;
            /* 文本垂直方向剧中 */
            line-height: 40px;
            text-indent: 18px;
            letter-spacing: 5px;
        }
        div:last-child{
            border-bottom: 1px solid #FFFFFF;
        }
        div a{
            color: #FFFFFF;
            text-decoration: none;
        }
        div:hover{
            background: #797979;
        }
    </style>
</head>
<body>
<div>
    <a href="doorList" target="right">门店管理</a>
</div>
<div>
    <a href="#">订单管理</a>
</div>
</body>
</html>
