package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")    //hello로 요청을 받을 때
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
        return "hello"; //hello.html로 렌더링
    }
}
