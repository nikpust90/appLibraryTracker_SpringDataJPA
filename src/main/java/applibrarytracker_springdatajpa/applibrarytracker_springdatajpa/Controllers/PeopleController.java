package applibrarytracker_springdatajpa.applibrarytracker_springdatajpa.Controllers;



import applibrarytracker_springdatajpa.applibrarytracker_springdatajpa.Model.Person;
import applibrarytracker_springdatajpa.applibrarytracker_springdatajpa.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/people", produces = "text/html; charset=UTF-8")
public class PeopleController {

    private final PersonService personService;

    @Autowired
    public PeopleController(PersonService personService) {
        this.personService = personService;
    }

    // Получение всех людей GET
    @GetMapping
    public String getAllPeople(Model model) {
        try {
            List<Person> allPersons = personService.getAllPersons();
            model.addAttribute("keyAllPeoples", allPersons);
            return "people/view-with-all-people1";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Ошибка при загрузке данных");
            return "people/error-view";
        }
    }

    // Добавление нового человека GET
    @GetMapping("/create")
    public String giveToUserPageToCreateNewPerson(Model model) {
        model.addAttribute("keyOfNewPerson", new Person());
        return "people/view-to-create-new-person";
    }

    // Добавление нового человека POST
    @PostMapping
    public String createPerson(@ModelAttribute("keyOfNewPerson") @Valid Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "people/view-to-create-new-person";
        }
        try {
            personService.savePerson(person);
            return "redirect:/people";
        } catch (Exception e) {
            return "people/error-view";
        }
    }

    // Получение человека по ID GET
    @GetMapping("/{id}")
    public String getPersonById(@PathVariable("id") Long id, Model model) {
        Person personById = personService.getPersonById(id);
        if (personById == null) {
            model.addAttribute("errorMessage", "Человек не найден");
            return "people/error-view";
        }
        model.addAttribute("keyPersonById", personById);
        model.addAttribute("books", personById.getBooks());
        return "people/view-with-person-by-id";
    }

    // Редактирование человека по ID GET
    @GetMapping("/edit/{id}")
    public String editPerson(@PathVariable("id") Long id, Model model) {
        Person personToBeEdited = personService.getPersonById(id);
        if (personToBeEdited == null) {
            model.addAttribute("errorMessage", "Человек не найден");
            return "people/error-view";
        }
        model.addAttribute("keyOfPersonToBeEdited", personToBeEdited);
        return "people/view-to-edit-person";
    }

    // Редактирование человека по ID POST
    @PostMapping("/edit/{id}")
    public String editPerson(@PathVariable("id") Long id,
                             @ModelAttribute("keyOfPersonToBeEdited") @Valid Person personFromForm,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "people/view-to-edit-person";
        }
        personFromForm.setId(id);
        personService.updatePerson(personFromForm);
        return "redirect:/people";
    }

    // Удаление человека по ID DELETE
    @PostMapping("/delete/{id}")
    public String deletePerson(@PathVariable("id") Long id) {
        personService.deletePerson(id);
        return "redirect:/people";
    }
}
