package net.abidinozdurmaz.library.repository;

import net.abidinozdurmaz.library.model.Book;
import net.abidinozdurmaz.library.model.BookDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {
    List<BookDetail> findAllBookDetail();
    BookDetail findAllBookDetailById(long id);
    Book findById(long id);
    BookDetail findByIsbnNo(String isbnNo);


    @Query("select new net.abidinozdurmaz.library.model.BookDetail(b.bookId,b.name,b.subName,b.seriesName,a.name,p.name,b.isbnNo,b.description) " +
                    "from Book b left join b.author a left join b.publisher p where b.name LIKE %:name%")
    List<BookDetail> findByNameLike(@Param("name") String name);

    @Query("select new net.abidinozdurmaz.library.model.BookDetail(b.bookId,b.name,b.subName,b.seriesName,a.name,p.name,b.isbnNo,b.description) " +
            "from Book b left join b.author a left join b.publisher p where a.name LIKE %:name%")
    List<BookDetail> findByAuthorNameLike(@Param("name") String name);
}
