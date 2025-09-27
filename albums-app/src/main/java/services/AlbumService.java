package services;

import java.util.List;
import java.util.Optional;

import entities.Album;

public interface AlbumService {
    //Get all the albums
    List<Album> findAll();

    //Find a certain album
    Optional<Album> findById(Long id);

    //Find an album regarding its genre
    Optional<Album> findAlbumsByGenre(String genre);

    //Persisting the new album in the DB
    Album save (Album album);

    //Deleting an album
    void delete (Album album);
}
