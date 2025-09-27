package services;

import java.util.List;
import java.util.Optional;

import entities.Genre;

public interface GenreService {
    //Get all the genres
    List<Genre> findAll();

    //Find a certain genre
    Optional<Genre> findById(Long id);

    //Persisting the new genre in the DB
    Genre save (Genre genre);

    //Deleting a genre
    void delete (Genre genre);
}
