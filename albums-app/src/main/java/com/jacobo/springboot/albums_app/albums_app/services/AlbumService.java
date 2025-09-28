package com.jacobo.springboot.albums_app.albums_app.services;

import java.util.List;
import java.util.Optional;

import com.jacobo.springboot.albums_app.albums_app.entities.Album;

public interface AlbumService {
    //Get all the albums
    List<Album> findAll();

    //Find a certain album
    Optional<Album> findById(Long id);

    //Find an album regarding its genre
    List<Album> findAlbumsByGenre(String genre);

    //Persisting the new album in the DB
    Album save (Album album);

    //Updating an album in the DB
    Optional<Album> update(Long id, Album album);

    //Deleting an album
    Optional<Album> delete (Long id);
}
