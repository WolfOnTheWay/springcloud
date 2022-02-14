package tedu.com;
import javax.servlet.http.*;
import java.io.PrintWriter;

public class CartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("text/html;charset=UTF-8");
            //getWriter必须再设置字符格式下面
            PrintWriter out = response.getWriter();
            //获取要加入的商品
            //获取session,将商品信息保存到session中
            // 响应浏览器
            String prod = request.getParameter("prod");
            HttpSession session= request.getSession();
            session.setAttribute("prod",prod);

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

