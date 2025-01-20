package applibrarytracker_springdatajpa.applibrarytracker_springdatajpa.repositories;

import applibrarytracker_springdatajpa.applibrarytracker_springdatajpa.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByUsername(String userName);

    //Optional<Person> findByUsername(String username);
}
