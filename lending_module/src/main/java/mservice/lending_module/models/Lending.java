package mservice.lending_module.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
//@Table(name = "lendings")
public class Lending {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "reader_id", nullable = false)
    private Reader reader;

    @Column(name = "lending_date")
    private LocalDate lendingDate;

    @Column(name = "return_date")
    private LocalDate returnDate;

    public Lending() {
    }

    public Lending(Book book, Reader reader, LocalDate lendingDate, LocalDate returnDate) {
        this.book = book;
        this.reader = reader;
        this.lendingDate = lendingDate;
        this.returnDate = returnDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public LocalDate getLendingDate() {
        return lendingDate;
    }

    public void setLendingDate(LocalDate lendingDate) {
        this.lendingDate = lendingDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}





/* 
package com.miageif.mservice.lending_service.models;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Lending {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String isbn;
    private Long readerId;
    private LocalDate startDate;

    private LocalDate dueDate;

    private LocalDate endDate;

    private Boolean returned;

    public Lending() {
        // Constructeur vide requis par JPA
    }

    public Lending(String isbn, Long readerId, LocalDate startDate, LocalDate dueDate) {
        this.isbn = isbn;
        this.readerId = readerId;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.returned = false;
    }

    public Long getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Long getReaderId() {
        return readerId;
    }

    public void setReaderId(Long readerId) {
        this.readerId = readerId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Boolean isReturned() {
        return returned;
    }

    public void setReturned(Boolean returned) {
        this.returned = returned;
    }
}




/*package com.miageif.mservice.lending_service.models;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.miageif.mservice.book_service.models.BookStatus;
    
    @Entity
    public class Lending {
        
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        
        private String isbn;
        
        private Long readerId;
        
        private LocalDate lendingDate;
        
        private LocalDate returnDate;
        
        private BookStatus bookStatus;
        
        public Lending() {}
        
        public Lending(String isbn, Long readerId, LocalDate lendingDate, LocalDate returnDate, BookStatus bookStatus) {
            this.isbn = isbn;
            this.readerId = readerId;
            this.lendingDate = lendingDate;
            this.returnDate = returnDate;
            this.bookStatus = bookStatus;
        }
        
        public Long getId() {
            return id;
        }
        
        public String getIsbn() {
            return isbn;
        }
        
        public void setIsbn(String isbn) {
            this.isbn = isbn;
        }
        
        public Long getReaderId() {
            return readerId;
        }
        
        public void setReaderId(Long readerId) {
            this.readerId = readerId;
        }
        
        public LocalDate getLendingDate() {
            return lendingDate;
        }
        
        public void setLendingDate(LocalDate lendingDate) {
            this.lendingDate = lendingDate;
        }
        
        public LocalDate getReturnDate() {
            return returnDate;
        }
        
        public void setReturnDate(LocalDate returnDate) {
            this.returnDate = returnDate;
        }
        
        public BookStatus getBookStatus() {
            return bookStatus;
        }
        
        public void setBookStatus(BookStatus bookStatus) {
            this.bookStatus = bookStatus;
        }
        
    }
*/
