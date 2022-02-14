package tedu.com;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Date;
public class CartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("text/html;charset=UTF-8");
            //getWriter必须再设置字符格式下面
            PrintWriter out = response.getWriter();
           //获取要加入的商品
           //将商品信息保存再Cookie中并发送给浏览器
            // 响应浏览器
            String prod = request.getParameter("prod");
            //中文数据得进行编码，否则会报错(cookie中不能存放中文)
            Cookie cookie = new Cookie("prod", URLEncoder.encode(prod,"UTF-8"));
            cookie.setMaxAge(60*60*24*10);
            response.addCookie(cookie);
            out.write("成功将加入"+prod+"了购物车");
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

