package mservice.reader_module.repositories;

import mservice.reader_module.models.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;

//@Repository
public interface ReaderRepository extends JpaRepository<Reader, Long> {
}
