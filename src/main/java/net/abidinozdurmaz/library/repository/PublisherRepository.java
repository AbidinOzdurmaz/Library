package net.abidinozdurmaz.library.repository;

import net.abidinozdurmaz.library.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PublisherRepository extends JpaRepository<Publisher,Long> {

    Publisher findById(long id);

    List<Publisher> findAllSortName();
}
