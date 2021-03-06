package com.workshopmongo;

import com.workshopmongo.repository.PostRepository;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jonas created 14/02/2021
 */
//Classe é im servico e pode ser injetado em outras Classes 
@Service
public class PostService {

    //Injecao automatica do Spring
    @Autowired
    private PostRepository repo;

    //Method that found user by ID and handling exception ID incorrect 
    public Post findById(String id) {
        Optional<Post> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    //Method that created automatic queries
    public List<Post> findByTitle(String text) {
        return repo.searchTitle(text);
    }

    //Method that make a query in all Post
    public List<Post> fullSearch(String text, Date minDate, Date maxDate) {
        maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
        return repo.fullSearch(text, minDate, maxDate);
    }

}
