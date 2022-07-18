package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")    //hello로 요청을 받을 때
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
        return "hello"; //hello.html로 렌더링
    }
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(name = "name") String name, Model model) {//이름이 name인 파라미터를 받음
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody   //http의 바디부의 데이터를 직접 넣어주겠다. ("hello" + name)
    public String helloString(@RequestParam(name = "name") String name){
        return "hello" + name;  //"hello spring"
    }

    //API방식
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){ //문자대신 객체를 넘겨주는 방식
        Hello hello = new Hello();
        hello.setName(name); //(name) = VALUE
        return hello;
        //JSON 방식 / HELLO 객체의 KEY, VALUE
        //객체를 반환하고 @ResponseBody를 해놓으면 JSON으로 반환하는게 기본이다.
    }

    static class Hello{
        private String name; // KEY

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
