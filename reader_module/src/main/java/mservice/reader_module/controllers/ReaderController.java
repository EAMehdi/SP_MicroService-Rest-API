package mservice.reader_module.controllers;

import jakarta.annotation.PostConstruct;
import mservice.reader_module.models.Reader;
import mservice.reader_module.repositories.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/readers")
public class ReaderController {

    @Autowired
    private ReaderRepository readerRepository;

    @GetMapping
    public List<Reader> getAllReaders() {
        return readerRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reader> getReaderById(@PathVariable("id") Long id) {
        Optional<Reader> readerData = readerRepository.findById(id);
        if (readerData.isPresent()) {
            return new ResponseEntity<>(readerData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Reader> createReader(@RequestBody Reader reader) {
        try {
            Reader newReader = readerRepository.save(new Reader(reader.getGender(), reader.getFirstName(),
                    reader.getLastName(), reader.getBirthDate(), reader.getAddress()));
            return new ResponseEntity<>(newReader, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reader> updateReader(@PathVariable("id") Long id, @RequestBody Reader reader) {
            Optional<Reader> readerData = readerRepository.findById(id);
    
            if (readerData.isPresent()) {
                Reader updatedReader = readerData.get();
                updatedReader.setGender(reader.getGender());
                updatedReader.setFirstName(reader.getFirstName());
                updatedReader.setLastName(reader.getLastName());
                updatedReader.setBirthDate(reader.getBirthDate());
                updatedReader.setAddress(reader.getAddress());
    
                return new ResponseEntity<>(readerRepository.save(updatedReader), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
    
        @DeleteMapping("/{id}")
        public ResponseEntity<HttpStatus> deleteReader(@PathVariable("id") Long id) {
            try {
                readerRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

    @PostConstruct
    public void addReadersToDB() {
        Reader reader1 = new Reader("Male", "John", "Doe", LocalDate.of(1990, 3, 15), "123 Main St");
        Reader reader2 = new Reader("Female", "Jane", "Doe", LocalDate.of(1995, 8, 27), "456 Oak St");
        Reader reader3 = new Reader("Male", "Bob", "Smith", LocalDate.of(1985, 11, 10), "789 Maple St");
        Reader reader4 = new Reader("Female", "Alice", "Smith", LocalDate.of(1992, 4, 22), "321 Elm St");
        readerRepository.saveAll(Arrays.asList(reader1, reader2, reader3, reader4));
    }




}
