import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Date;

public class Deamon extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            PrintWriter writer = response.getWriter();
            writer.write("Hello.." + new Date());

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
