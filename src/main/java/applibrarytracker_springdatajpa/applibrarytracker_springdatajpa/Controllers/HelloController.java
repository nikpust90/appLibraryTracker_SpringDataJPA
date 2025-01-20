package applibrarytracker_springdatajpa.applibrarytracker_springdatajpa.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
