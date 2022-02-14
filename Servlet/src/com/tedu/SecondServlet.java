package com.tedu;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Date;

public class SecondServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("text/html;charset=utf-8");
            //模拟jdbc
            String name = "李志杰";
            String addr = "北京海淀";
            //将数据存入到request对象的map集合中
            request.setAttribute("name",name);
            request.setAttribute("addr",addr);
            //将request转发至jsp中，取出数据进行展示
            //indexjsp和本类在网络中的访问路径是一样的
            request.getRequestDispatcher("el.jsp").forward(request,response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //如果请求方式未get，那么服务器将调用get方法处理这次请求
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            PrintWriter writer = response.getWriter();
            writer.write("Hello.." + new Date());

        } catch (Exception e) {
            doGet(request, response);
        }
    }
}
