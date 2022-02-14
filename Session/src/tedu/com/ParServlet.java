package tedu.com;
import javax.servlet.http.*;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.Date;
public class ParServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("text/html;charset=UTF-8");
            //getWriter必须再设置字符格式下面
            PrintWriter out = response.getWriter();
//            1.获取请求中的session
//            2.从session中获取到商品
//            3.获取到要进行支付的商品
//            4.模拟支付
            HttpSession session = request.getSession();
            String prod = (String)session.getAttribute("prod");
            if(prod==null)
            {
                out.write("您还没有将商品加入购物车");
            }else{
                out.write("成功为"+prod+"支付了1000元");
            }
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