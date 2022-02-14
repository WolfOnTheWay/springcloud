import com.tedu.pojo.User;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.InvocationTargetException;

public class TestSpring {
    /*1.通过Spring容器创建User类的对象
    * (1)在spring配置文件中声明User类的Bean对象
    *    如果没有配置文件，先创建配置文件
    * （2）在程序中通过Spring容器来获取User类的对象*/
    @Test
    public void testIOC() {
        /*  读取Spring核心配置文件*/
        ClassPathXmlApplicationContext xml = new ClassPathXmlApplicationContext("applicationContext.xml");
        User user = (User) xml.getBean("user");
        System.out.println(user);
    }

    @Test
    public void testRelect() throws Exception {
        //模拟通过解析xml获取类的全路径
        String className = "com.tedu.pojo.User";
        Class clz = Class.forName(className);
        User user = (User)clz.getDeclaredConstructor().newInstance();
        System.out.println(user);
    }
    @Test
    public void testDI(){
        /*  读取Spring核心配置文件*/
        ClassPathXmlApplicationContext xml =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        User user = (User) xml.getBean("user");
        System.out.println(user);
    }
}
