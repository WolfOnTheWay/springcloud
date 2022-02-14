package tedu.com;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
//            1.获取请求中的所有cookie
//            2.找到名称为prod的cookie
//            3.获取到要进行支付的商品
//            4.模拟支付
            Cookie[] cs = request.getCookies();
            String prod = null;
            if(cs!=null){
                for(Cookie c:cs){
                    if("prod".equals(c.getName())){
                        //中文解码
                        prod = URLDecoder.decode(c.getValue(),"UTF-8");
                    }
                }
            }
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