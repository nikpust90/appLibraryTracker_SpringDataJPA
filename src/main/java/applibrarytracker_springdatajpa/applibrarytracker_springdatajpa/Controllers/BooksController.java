package applibrarytracker_springdatajpa.applibrarytracker_springdatajpa.Controllers;

import applibrarytracker_springdatajpa.applibrarytracker_springdatajpa.Model.Book;
import applibrarytracker_springdatajpa.applibrarytracker_springdatajpa.Model.Person;
import applibrarytracker_springdatajpa.applibrarytracker_springdatajpa.service.BookService;
import applibrarytracker_springdatajpa.applibrarytracker_springdatajpa.service.PersonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BooksController {
    private final BookService bookService;
    private final PersonService personService;

    // Получить список книг
    @GetMapping
    public String getAllBooks(Model model) {
        try {
            List<Book> allBooks = bookService.getAllBooks();
            model.addAttribute("keyAllBooks", allBooks);
            return "books/view-with-all-books";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Ошибка при загрузке данных");
            return "books/error-view";
        }
    }

    // Создание книги GET
    @GetMapping("/new")
    public String giveToUserPageToCreateNewBook(Model model) {
        model.addAttribute("keyOfNewBook", new Book());
        return "books/view-to-create-new-book";
    }

    // Создание книги POST
    @PostMapping
    public String createBook(@ModelAttribute("keyOfNewBook") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/view-to-create-new-book";
        }
        try {
            bookService.saveBook(book);
            return "redirect:/books";
        } catch (Exception e) {
            return "books/error-view";
        }
    }

    // Получение книги по ID GET
    @GetMapping("/{id}")
    public String getBookById(@PathVariable("id") Long id, Model model) {
        Book bookById = bookService.getBookById(id);
        if (bookById == null) {
            model.addAttribute("errorMessage", "Книга не найдена");
            return "books/error-view";
        }
        model.addAttribute("keyBookById", bookById);
        model.addAttribute("people", personService.getAllPersons());
        return "books/view-with-book-by-id";
    }

    // Редактирование книги по ID GET
    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable("id") Long id, Model model) {
        Book bookToBeEdited = bookService.getBookById(id);
        if (bookToBeEdited == null) {
            model.addAttribute("errorMessage", "Книга не найдена");
            return "books/error-view";
        }
        model.addAttribute("Book", bookToBeEdited);
        return "books/view-to-edit-book";
    }

    // Редактирование книги по ID POST
    @PostMapping("/edit/{id}")
    public String editBook(@PathVariable("id") Long id,
                           @ModelAttribute("keyOfBookToBeEdited") @Valid Book bookFromForm,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/view-to-edit-book";
        }
        bookFromForm.setId(id);
        bookService.saveBook(bookFromForm);
        return "redirect:/books";
    }

    // Удаление книги по ID DELETE
    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }

    // Назначение книги читателю
    @PostMapping("/assign/{id}")
    public String assignBook(@PathVariable("id") Long bookId, @RequestParam("personId") Long personId) {
        Person person = personService.getPersonById(personId);
        if (person != null) {
            bookService.assignBookToPerson(bookId, person);
        }
        return "redirect:/books/" + bookId;
    }

    // Удаляем книгу у читателя
    @PostMapping("/loose/{id}")
    public String looseBook(@PathVariable("id") Long bookId) {
        bookService.removeBookFromPerson(bookId);
        return "redirect:/books/" + bookId;
    }
}
