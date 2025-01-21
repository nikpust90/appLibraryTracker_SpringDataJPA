package applibrarytracker_springdatajpa.applibrarytracker_springdatajpa.repositories;

import applibrarytracker_springdatajpa.applibrarytracker_springdatajpa.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // При необходимости можно добавить кастомные запросы
}
