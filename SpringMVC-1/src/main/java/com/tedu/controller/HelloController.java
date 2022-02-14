package com.tedu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/*表示当前类属于Controller层
将当前类的对象的创建交给Spring容器来负责
* */
@Controller
public class HelloController {
    @RequestMapping("/hello")
    public String toHello(){
        //返回了一个jsp页面，前缀+home+后缀
        System.out.println("11111");
        return "home";
    }
    /*通过浏览器访问/param1,并在url拼接参数name和age发送给服务器，在下面的方法中获取name和age两个参数的值
    * /param1?name=zhangsan&age=20*/
    /*在方法中添加和请求参数重名的参数，如果正确，那么就会将请求中的参数赋值给函数的参数*/
    @RequestMapping("/param1")
    public String testParam1(String name,Integer age){
        System.out.println(name+" " + age);
        return "home";
    }
    /*日期类型参数
    * /testDate?date=2021/8/25 9:26:30*/
    @RequestMapping("/testDate")
    public String testParam2(Date date){
        System.out.println(date);
        return "home";
    }

    /*
    * 转发到/hello
    * 默认其实就是转发
    * */
    @RequestMapping("/testForward")
    public String testForward(){
        System.out.println("testForward");
        return "forward:hello";
    }
    @RequestMapping("/testRedirect")
    public String testRedirect(){
        System.out.println("testRedirect");
        return "redirect:hello";
    }

    /*
    * model加转发带数据到jsp*/
    @RequestMapping("/testModel")
    public String testModel(Model model){
        /*底层就是将值存到request域中*/
        model.addAttribute("name","王海淘");//相当于request.setAttriute()
        model.addAttribute("age",20);
        return "test";
    }


    @RequestMapping("/testJson")
    public String testJson(Model model){

        return "test";
    }
}
