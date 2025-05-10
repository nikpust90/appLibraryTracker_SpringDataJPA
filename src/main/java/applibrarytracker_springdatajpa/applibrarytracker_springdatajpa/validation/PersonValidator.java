package applibrarytracker_springdatajpa.applibrarytracker_springdatajpa.validation;

import applibrarytracker_springdatajpa.applibrarytracker_springdatajpa.Model.PersonSecurity;
import applibrarytracker_springdatajpa.applibrarytracker_springdatajpa.security.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    private final PersonDetailsService personDetailsService;

    @Autowired
    public PersonValidator(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

//    @Override
//    public void validate(Object target, Errors errors) {
//        PersonSecurity person = (PersonSecurity) target;
//
//        try {
//            // Попытка загрузить пользователя
//            personDetailsService.loadUserByUsername(person.getUsername());
//
//            // Если пользователь найден, добавляем ошибку
//            errors.rejectValue("username", "user.found.name",
//                    "User with name " + person.getUsername() + " already exists");
//        } catch (UsernameNotFoundException e) {
//            // Пользователь не найден - это нормально для регистрации
//            // Ошибку добавлять не нужно
//        }
//    }

    @Override
    public void validate(Object target, Errors errors) {
        PersonSecurity personSecurity = (PersonSecurity) target;

        try {
            personDetailsService.loadUserByUsername(personSecurity.getUsername());
        } catch (UsernameNotFoundException e) {
            return;
        }

        errors.rejectValue("username", "user.found.name",
                "User with name " + personSecurity.getUsername() + " already exists");
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return PersonSecurity.class.equals(clazz);
    }
}
