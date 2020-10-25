package net.abidinozdurmaz.library.service;

import net.abidinozdurmaz.library.model.Book;
import net.abidinozdurmaz.library.model.BookDetail;
import net.abidinozdurmaz.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {


    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookDetail> findAllBookDetail(){
        return bookRepository.findAllBookDetail();
    }

    public Book save(Book book){
        return bookRepository.save(book);
    }
    public BookDetail findAllBookDetailById(long id){
        return bookRepository.findAllBookDetailById(id);
    }

    public void deleteById(long id){
        bookRepository.deleteById(id);
    }

    public void delete(Book book){
        bookRepository.delete(book);
    }

    public Book findById(long id){
        return bookRepository.findById(id);
    }
    public BookDetail findByIsbnNo(String isbnNo){
        return bookRepository.findByIsbnNo(isbnNo);
    }

    public List<BookDetail> findByNameLike(String name){
        return bookRepository.findByNameLike(name);
    }
    public List<BookDetail> findByAuthorNameLike(String name){
        return bookRepository.findByAuthorNameLike(name);
    }
}
