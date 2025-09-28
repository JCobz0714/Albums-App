package com.jacobo.springboot.albums_app.albums_app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jacobo.springboot.albums_app.albums_app.entities.Genre;
import com.jacobo.springboot.albums_app.albums_app.repositories.GenreRepository;

//Using the service interface methods
@Service
public class GenreServiceImpl implements GenreService {

    //Injecting the repository dependency for genre
    @Autowired
    private GenreRepository repository;

    //Method that returns all genres
    @Transactional(readOnly = true)
    @Override
    public List<Genre> findAll() {
        return (List<Genre>) repository.findAll();
    }

    //Finding one album by its ID
    @Transactional(readOnly = true)
    @Override
    public Optional<Genre> findById(Long id) {
        return repository.findById(id);
    }

    //Persisting the data in the DB using the injected repository object
    @Transactional
    @Override
    public Genre save(Genre genre) {
        return repository.save(genre);
    }

    //Updating a genre in the DB
    @Transactional
    @Override
    public Optional<Genre> update(Long id, Genre genre) {
        Optional<Genre> genreOptional = repository.findById(id);

        if(genreOptional.isPresent()){
            Genre genreDb = genreOptional.orElseThrow();

            genreDb.setGenreName(genre.getGenreName());
            genreDb.setAlbums(genre.getAlbums());
            
            return Optional.of(repository.save(genreDb));
        }

        return genreOptional;
    }

    //Deleting a genre from the DB
    @Transactional
    @Override
    public Optional<Genre> delete(Long id) {
        Optional<Genre> genreOptional = repository.findById(id);

        genreOptional.ifPresent(genreDb -> {
            repository.delete(genreDb);
        });

        return genreOptional;
    }
}
