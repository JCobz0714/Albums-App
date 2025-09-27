package repositories;

import org.springframework.data.repository.CrudRepository;

import entities.Album;

public interface AlbumRepository extends CrudRepository<Album, Long> {

}
