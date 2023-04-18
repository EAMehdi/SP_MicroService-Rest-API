package mservice.lending_module.repositories;

import mservice.lending_module.models.Lending;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//@Repository
public interface LendingRepository extends JpaRepository<Lending, Long> {
    List<Lending> findByReaderId(Long readerId);
    List<Lending> findByBookIsbn(String bookIsbn);
}


