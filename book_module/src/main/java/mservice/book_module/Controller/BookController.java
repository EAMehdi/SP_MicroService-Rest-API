package mservice.book_module.Controller;

import jakarta.annotation.PostConstruct;
import mservice.book_module.Model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import mservice.book_module.Service.BookService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService BookService;


    @PostConstruct
    public void initBooks() {
        Book[] Books = {
                new Book("9782070416867", "Albert Camus", "L'Étranger", "Gallimard", 1942),
                new Book("9780321784421", "Joshua Bloch", "Effective Java", "Addison-Wesley", 2018),
                new Book("9780596007126", "Kathy Sierra, Bert Bates", "Head First Java", "O'Reilly Media", 2005)
                // Ajoutez d'autres Books ici
        };

        for (Book Book : Books) {
            BookService.saveBook(Book);
        }
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(BookService.getAllBooks());
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<Book> getBookById(@PathVariable String isbn) {
        Optional<Book> Book = BookService.getBookById(isbn);
        return Book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book Book) {
        return ResponseEntity.ok(BookService.saveBook(Book));
    }

    @PutMapping("/{isbn}")
    public ResponseEntity<Book> updateBook(@PathVariable String isbn, @RequestBody Book updatedBook) {
        Optional<Book> existingBook = BookService.getBookById(isbn);

        if (existingBook.isPresent()) {
            updatedBook.setIsbn(isbn);
            return ResponseEntity.ok(BookService.saveBook(updatedBook));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<Void> deleteBookById(@PathVariable String isbn) {
        BookService.deleteBookById(isbn);
        return ResponseEntity.noContent().build();
    }
}
