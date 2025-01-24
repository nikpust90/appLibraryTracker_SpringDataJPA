package applibrarytracker_springdatajpa.applibrarytracker_springdatajpa.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController  {
    @GetMapping("/")
    public String showMainPage() {
        return "index"; // Thymeleaf будет искать файл "index.html" в папке templates
    }
}
