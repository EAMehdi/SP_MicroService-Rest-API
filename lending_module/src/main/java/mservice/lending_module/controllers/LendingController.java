package mservice.lending_module.controllers;


import mservice.lending_module.models.Book;
import mservice.lending_module.models.Lending;
import mservice.lending_module.models.Reader;
import mservice.lending_module.repositories.LendingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/lendings")
public class LendingController {

    @Autowired
    private LendingRepository lendingRepository;

    /*
    @PostMapping
    public ResponseEntity<Lending> addLending(@RequestBody Lending lending) {
        Lending savedLending = lendingRepository.save(lending);
        return new ResponseEntity<>(savedLending, HttpStatus.CREATED);
    }
*/

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping
    public ResponseEntity<Lending> addLending(@RequestBody Lending lending) {
        String bookServiceUrl = "http://localhost:8080/books/" + lending.getBook().getIsbn();
        String readerServiceUrl = "http://localhost:8080/readers/" + lending.getReader().getId();

        ResponseEntity<Book> bookResponse = restTemplate.getForEntity(bookServiceUrl, Book.class);
        ResponseEntity<Reader> readerResponse = restTemplate.getForEntity(readerServiceUrl, Reader.class);

        if (bookResponse.getStatusCode() != HttpStatus.OK || readerResponse.getStatusCode() != HttpStatus.OK) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Book book = bookResponse.getBody();
        Reader reader = readerResponse.getBody();

        lending.setBook(book);
        lending.setReader(reader);

        Lending savedLending = lendingRepository.save(lending);
        return new ResponseEntity<>(savedLending, HttpStatus.CREATED);
    }



    @GetMapping("/{id}")
    public ResponseEntity<Lending> getLendingById(@PathVariable(value = "id") Long lendingId) {
        Lending lending = lendingRepository.findById(lendingId).orElse(null);
        if (lending == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(lending, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lending> updateLending(@PathVariable(value = "id") Long lendingId,
                                                  @RequestBody Lending lendingDetails) {
        Lending lending = lendingRepository.findById(lendingId).orElse(null);
        if (lending == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        lending.setBook(lendingDetails.getBook());
        lending.setReader(lendingDetails.getReader());
        lending.setLendingDate(lendingDetails.getLendingDate());
        lending.setReturnDate(lendingDetails.getReturnDate());
        Lending updatedLending = lendingRepository.save(lending);
        return new ResponseEntity<>(updatedLending, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteLending(@PathVariable(value = "id") Long lendingId) {
        Lending lending = lendingRepository.findById(lendingId).orElse(null);
        if (lending == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        lendingRepository.delete(lending);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public List<Lending> getAllLendings() {
        return lendingRepository.findAll();
    }
}


