package net.abidinozdurmaz.library.service;

import net.abidinozdurmaz.library.model.Author;
import net.abidinozdurmaz.library.model.Publisher;
import net.abidinozdurmaz.library.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {


    private final PublisherRepository publisherRepository;

    @Autowired
    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public Publisher save(Publisher publisher){
        return publisherRepository.save(publisher);
    }

    public List<Publisher> findAll(){
        return publisherRepository.findAll();
    }

    public void deleteById(long id){
        publisherRepository.deleteById(id);
    }

    public Publisher findById(long id){
        return publisherRepository.findById(id);
    }

    public void delete(Publisher publisher){
        publisherRepository.delete(publisher);
    }

    public List<Publisher> findAllSortName(){
        return publisherRepository.findAllSortName();
    }
}
