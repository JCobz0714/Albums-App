package com.jacobo.springboot.albums_app.albums_app.repositories;

import org.springframework.data.repository.CrudRepository;

import com.jacobo.springboot.albums_app.albums_app.entities.Genre;

public interface GenreRepository extends CrudRepository<Genre, Long> {

}
