<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page pageEncoding="UTF-8" %>

<%@taglib prefix="c"
          uri="http://java.sun.com/jstl/core_rt"
          %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8"/>
</head>
    <h3>c:set</h3>
    <%-- 添加属性 --%>
    <c:set scope="request"
           var="name"
            value="李志杰"/>
    ${name} <br>
    <c:set scope="request"
       var="name"
       value="刘梦媛"/>
    ${name} <br>
    <%-- 修改属性值 --%>
    <h3>c:if</h3>
    <c:set scope="request" var="score" value="78"/>
    <c:if test="${score>=80}">优秀</c:if>
    <c:if test="${score<80 and score>=60}">中等</c:if>
    <c:if test="${score<60}">不及格</c:if>
    <h3>c:forEach</h3>
    <h5>遍历数组或集合</h5>
    <% String[] names={"A","B","C","D"};
        request.setAttribute("ns",names);%>
    <c:forEach items="${ns}" var="name">
        ${name}<br>
    </c:forEach>

    <h5>遍历map</h5>
    <%Map map = new HashMap();
    map.put("name","李志杰");
    map.put("age",18);
    request.setAttribute("map",map);%>
    <c:forEach items="${map}" var="m">
        ${m.key}:${m.value}<br>
    </c:forEach>
    <h5>0-100，将3的倍数输出到浏览器</h5>
    <%--step默认1，包含begin和end  varstatus:表示当前循环遍历状态 status.first:第一次遍历返回true，同时有count和last--%>
    <c:forEach begin="0" end="100" var="v" step="1" varStatus="status">
        <c:if test="${v%3==0}">
            ${v}<br>
        </c:if>
    </c:forEach>
<body>
</body>
</html>
