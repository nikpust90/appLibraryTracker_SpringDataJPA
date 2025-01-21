package applibrarytracker_springdatajpa.applibrarytracker_springdatajpa.repositories;

import applibrarytracker_springdatajpa.applibrarytracker_springdatajpa.Model.PersonSecurity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<PersonSecurity, Long> {
    Optional<PersonSecurity> findByUsername(String userName);

    //Optional<PersonSecurity> findByUsername(String username);
}
