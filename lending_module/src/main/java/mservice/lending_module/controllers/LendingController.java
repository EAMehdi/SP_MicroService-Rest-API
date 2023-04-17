package mservice.lending_module.controllers;


import mservice.lending_module.models.Lending;
import mservice.lending_module.repositories.LendingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lendings")
public class LendingController {

    @Autowired
    private LendingRepository lendingRepository;

    @PostMapping
    public ResponseEntity<Lending> addLending(@RequestBody Lending lending) {
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

    //Methode pour recuperer tous les emprunts
    @GetMapping
    public List<Lending> getAllLendings() {
        return lendingRepository.findAll();
    }
}





/* 
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.miageif.mservice.book_service.models.Book;
import com.miageif.mservice.lending_service.models.Lending;
import com.miageif.mservice.lending_service.services.LendingService;
import com.miageif.mservice.reader_service.models.Reader;

@RestController
@RequestMapping("/lending")
public class LendingController {

    @Autowired
    private LendingService lendingService;

    // Endpoint pour créer un nouveau prêt
    @PostMapping("/")
    public Lending createLending(@RequestBody Lending lending) {
        // Vérifier si le livre existe
        Book book = lendingService.getBookByIsbn(lending.getIsbn());
        if (book == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Le livre n'existe pas");
        }

        // Vérifier si le lecteur existe
        Reader reader = lendingService.getReaderById(lending.getReaderId());
        if (reader == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Le lecteur n'existe pas");
        }

        // Créer le prêt
        return lendingService.createLending(lending);
    }

    // Endpoint pour récupérer un prêt par son ID
    @GetMapping("/{id}")
    public Lending getLendingById(@PathVariable Long id) {
        Lending lending = lendingService.getLendingById(id);
        if (lending == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Le prêt n'existe pas");
        }
        return lending;
    }

    // Endpoint pour récupérer tous les prêts
    @GetMapping("/")
    public List<Lending> getAllLendings() {
        return lendingService.getAllLendings();
    }

    // Endpoint pour mettre à jour un prêt existant
    @PutMapping("/")
    public Lending updateLending(@RequestBody Lending lending) {
        // Vérifier si le prêt existe
        Lending existingLending = lendingService.getLendingById(lending.getId());
        if (existingLending == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Le prêt n'existe pas");
        }

        // Vérifier si le livre existe
        Book book = lendingService.getBookByIsbn(lending.getIsbn());
        if (book == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Le livre n'existe pas");
        }

        // Vérifier si le lecteur existe
        Reader reader = lendingService.getReaderById(lending.getReaderId());
        if (reader == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Le lecteur n'existe pas");
        }

        // Mettre à jour le prêt
        return lendingService.updateLending(lending);
    }

    // Endpoint pour supprimer un prêt existant
    @DeleteMapping("/{id}")
    public void deleteLending(@PathVariable Long id) {
        // Vérifier si le prêt existe
        Lending lending = lendingService.getLendingById(id);
        if (lending == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Le prêt n'existe pas");
        }

        // Supprimer le prêt
        lendingService.deleteLending(id);
    }

    // Endpoint pour récupérer tous les prêts effectués à une date donnée
    @GetMapping("/date/{date}")
    public List<Lending> getLendingsByDate(@PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return lendingService.getLendingsByDate(date);
    }

    // Endpoint pour récupérer tous les prêts en cours
    @GetMapping("/ongoing")
    public List<Lending> getOngoingLendings() {
        return lendingService.getOngoingLendings();
    }

    // Endpoint pour récupérer tous les prêts en retard
    @GetMapping("/late")
    public List<Lending> getLateLendings() {
        return lendingService.getLateLendings();
    }
}


/*
package com.miageif.mservice.lending_service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.miageif.mservice.book_service.BookServiceApplication;
import com.miageif.mservice.book_service.models.Book;
import com.miageif.mservice.lending_service.LendingServiceApplication;
import com.miageif.mservice.lending_service.models.Lending;
import com.miageif.mservice.reader_service.ReaderServiceApplication;
import com.miageif.mservice.reader_service.models.Reader;

@RestController
@RequestMapping("/lending")
public class LendingController {
    
    @Autowired
    private LendingServiceApplication lendingService;
    
    @Autowired
    private BookServiceApplication bookService;
    
    @Autowired
    private ReaderServiceApplication readerService;
    
    // Endpoint pour créer un nouveau prêt
    @PostMapping("/")
    public Lending createLending(@RequestBody Lending lending) {
        // Vérifier si le livre existe
        Book book = bookService.getBook(lending.getIsbn());
        if (book == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Le livre n'existe pas");
        }
        
        // Vérifier si le lecteur existe
        Reader reader = readerService.getReader(lending.getReaderId());
        if (reader == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Le lecteur n'existe pas");
        }
        
        // Créer le prêt
        return lendingService.createLending(lending);
    }
    
    // Endpoint pour récupérer un prêt par son ID
    @GetMapping("/{id}")
    public Lending getLendingById(@PathVariable Long id) {
        Lending lending = lendingService.getLendingById(id);
        if (lending == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Le prêt n'existe pas");
        }
        return lending;
    }
    
    // Endpoint pour récupérer tous les prêts
    @GetMapping("/")
    public List<Lending> getAllLendings() {
        return lendingService.getAllLendings();
    }
    
    // Endpoint pour mettre à jour un prêt existant
    @PutMapping("/")
    public Lending updateLending(@RequestBody Lending lending) {
        // Vérifier si le prêt existe
        Lending existingLending = lendingService.getLendingById(lending.getId());
        if (existingLending == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Le prêt n'existe pas");
        }
        
        // Vérifier si le livre existe
        Book book = bookService.getBook(lending.getIsbn());
        if (book == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Le livre n'existe pas");
        }
        
        // Vérifier si le lecteur existe
        Reader reader = readerService.getReader(lending.getReaderId());
        if (reader == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Le lecteur n'existe pas");
        }
        
        // Mettre à jour le prêt
        return lendingService.updateLending(lending);
    }
    
    // Endpoint pour supprimer un prêt existant
    @DeleteMapping("/{id}")
    public void deleteLending(@PathVariable Long id) {
        // Vérifier si le prêt existe
        Lending lending = lendingService.getLendingById(id);
        if (lending == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Le prêt n'existe pas");
        }
        
        // Supprimer le prêt
        lendingService.deleteLending(id);
    }
    
    // Endpoint pour récupérer tous les prêts effectués à une date donnée
    @GetMapping("/date/{date}")
    public List<Lending> getLendingsByDate(@PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return lendingService.getLendingsByDate(date);
    }
    }
*/
