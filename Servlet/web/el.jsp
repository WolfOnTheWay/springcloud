<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page pageEncoding="UTF-8" %>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8"/>
</head>
<body>
  <h3>常量、表达式、变量</h3>
  ${"Hello El"}  <br/>
  ${123+100}  <br/>
  ${name}

  <h3>数组和集合</h3>
    <%
        String[] names = {"A","B","C"};
        request.setAttribute("ns",names);
    %>
   <%--  el不能遍历--%>
   ${ns[0]} <br>
   ${ns[1]} <br>
   ${ns[2]} <br>
  <h3>map</h3>
<%
    Map map = new HashMap();
    map.put("name","阿凡提");
    map.put("age","10");
    request.setAttribute("m",map);
%>
  ${m.name} <br>
  ${m.age} <br>
</body>
</html>
