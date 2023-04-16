package mservice.lending_module.repositories;

import mservice.lending_module.models.Lending;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//@Repository
public interface LendingRepository extends JpaRepository<Lending, Long> {
    List<Lending> findByReaderId(Long readerId);
    List<Lending> findByBookIsbn(String bookIsbn);
}



/* 
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.miageif.mservice.lending_service.models.Lending;
import com.miageif.mservice.reader_service.models.Reader;

@Repository
public interface LendingRepository extends JpaRepository<Lending, Long> {
    
    public List<Lending> findByReader(Reader reader);
    void createLending(Lending Lending);
    List<Lending> getLendingsByStartDate(LocalDate startDate);
    List<Lending> getLendingsByISBN(String ISBN);
    List<Lending> getLendingsByReader(String borrowerId);
    void updateLending(Lending Lending);
    void deleteLending(int LendingId);

}

*/
