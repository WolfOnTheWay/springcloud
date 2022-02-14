package aaa.com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/hi")
public class HelloController {

    @RequestMapping("/hello")//表示返回的是一个字符串
    @ResponseBody
    public String sayHello() {
        return "Hello---World--Haidi";
    }
}

