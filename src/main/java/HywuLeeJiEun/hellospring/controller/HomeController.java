package HywuLeeJiEun.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/") //localhost:8080으로 접속,
    public String home() {
        return "home";
    }
}