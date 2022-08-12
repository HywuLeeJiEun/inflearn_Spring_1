package HywuLeeJiEun.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller //스프링은 @controller를 작성해주어야 함!
public class HelloController {

    // Gethost 할때의 get, 읽어옴(가져옴)
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello"; //resources:templates/ 안의 파일을 찾음.
    }
    // MVC(Model, View, Controller) 이해하기
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam ("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    // API 실습 - 데이터를 그대로 보여준다. (소스코드가 보이지 않음!)
    @GetMapping("hello-string")
    @ResponseBody //http의 바디부에 데이터를 넣고자 함.
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    // 가장 많이 쓰는 API 사용 방식
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }
    static class Hello {
        private String name;
        //getter
        public String getName() {
            return name;
        }
        //setter - (json 방식 (key, value) 형식))으로 반응
        public void setName(String name) {
            this.name = name;
        }
    }
}
