package applibrarytracker_springdatajpa.applibrarytracker_springdatajpa.security;

import applibrarytracker_springdatajpa.applibrarytracker_springdatajpa.Model.PersonSecurity;
import applibrarytracker_springdatajpa.applibrarytracker_springdatajpa.repositories.PeopleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonDetailsService implements UserDetailsService {

    private final PeopleRepository peopleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<PersonSecurity> person = peopleRepository.findByUsername(username);

        if (person.isEmpty()) {
            throw new UsernameNotFoundException("Repository has not found user with username: " + username);
        }

        return new PersonDetails(person.get());
    }

}
