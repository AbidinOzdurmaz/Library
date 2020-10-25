package net.abidinozdurmaz.library.repository;

import net.abidinozdurmaz.library.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author,Long> {

    Author findById(long id);
    List<Author> findAllSortName();

}
