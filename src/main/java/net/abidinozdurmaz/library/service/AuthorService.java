package net.abidinozdurmaz.library.service;

import net.abidinozdurmaz.library.model.Author;
import net.abidinozdurmaz.library.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author save(Author author){
        return authorRepository.save(author);
    }

    public List<Author> findAll(){
        return authorRepository.findAll();
    }

    public void deleteById(long id){
        authorRepository.deleteById(id);
    }

    public Author findById(long id){
        return authorRepository.findById(id);
    }

    public void delete(Author author){
        authorRepository.delete(author);
    }

    public List<Author> findAllSortName(){
        return authorRepository.findAllSortName();
    }





}
