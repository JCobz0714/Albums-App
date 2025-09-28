package com.jacobo.springboot.albums_app.albums_app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.jacobo.springboot.albums_app.albums_app.entities.Album;

public interface AlbumRepository extends CrudRepository<Album, Long> {
    //Making the search for an album by its genre using a query
    @Query("SELECT a from Album a JOIN a.genres g WHERE g = :genre")
    List<Album> findAlbumByGenre(String genre);
}
