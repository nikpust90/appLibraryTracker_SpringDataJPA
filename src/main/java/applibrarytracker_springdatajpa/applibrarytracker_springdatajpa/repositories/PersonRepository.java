package applibrarytracker_springdatajpa.applibrarytracker_springdatajpa.repositories;

import applibrarytracker_springdatajpa.applibrarytracker_springdatajpa.Model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
