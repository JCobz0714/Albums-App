package repositories;

import org.springframework.data.repository.CrudRepository;

import entities.Genre;

public interface GenreRepository extends CrudRepository<Genre, Long> {

}
