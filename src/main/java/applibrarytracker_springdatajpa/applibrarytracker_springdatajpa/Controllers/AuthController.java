package applibrarytracker_springdatajpa.applibrarytracker_springdatajpa.Controllers;

import applibrarytracker_springdatajpa.applibrarytracker_springdatajpa.Model.PersonSecurity;
import applibrarytracker_springdatajpa.applibrarytracker_springdatajpa.service.PeopleService;
import applibrarytracker_springdatajpa.applibrarytracker_springdatajpa.validation.PersonValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final PersonValidator personValidator;
    private final PeopleService peopleService;

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("personSecurity", new PersonSecurity());
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String register(@ModelAttribute("personSecurity") PersonSecurity personSecurity,
                           BindingResult bindingResult) {

        personValidator.validate(personSecurity, bindingResult); // проверим есть ли такой пользователь в базе уже

        if (bindingResult.hasErrors()) {
            return "auth/registration";
        }

        peopleService.savePerson(personSecurity);

        return "redirect:/auth/login";
    }

    @GetMapping("/admin")
    public String getAdminPage() {

        peopleService.doAdminSomething();

        return "auth/admin";
    }

}
