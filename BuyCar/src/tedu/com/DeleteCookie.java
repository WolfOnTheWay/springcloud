package tedu.com;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class DeleteCookie extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            /*
            获取所有cookie，循环遍历，找到指定cookie，设置生存时间，发送给浏览器

            在创建一个重名的cookie，把次cookie的时间设置为，再发送给浏览器
            * */
            Cookie cookie = new Cookie("prod","prod");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
            out.write("已删除当前Cookie");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //如果请求方式未get，那么服务器将调用get方法处理这次请求
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            PrintWriter writer = response.getWriter();
            doGet(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
