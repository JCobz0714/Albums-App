package services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import entities.Album;
import repositories.AlbumRepository;

//Using the service interface methods
@Service
public class AlbumServiceImpl implements AlbumService {

    //Injecting the repository dependency for album
    @Autowired
    private AlbumRepository repository;

    //Method that returns all albums
    @Transactional(readOnly = true)
    @Override
    public List<Album> findAll() {
        return (List<Album>) repository.findAll();
    }

    //Finding one album by its ID
    @Transactional(readOnly = true)
    @Override
    public Optional<Album> findById(Long id) {
        return repository.findById(id);
    }

    //Finding albums using the genre
    @Transactional(readOnly = true)
    @Override
    public List<Album> findAlbumsByGenre(String genre) {
        return repository.findAlbumByGenre(genre);
    }

    //Persisting the data in the DB using the injected repository object
    @Transactional
    @Override
    public Album save(Album album) {
        return repository.save(album);
    }
    
    //Updating a genre in the DB
    @Transactional
    @Override
    public Optional<Album> update(Long id, Album album) {
        Optional<Album> albumOptional = repository.findById(id);

        if(albumOptional.isPresent()){
            Album albumDb = albumOptional.orElseThrow();

            albumDb.setName(album.getName());
            albumDb.setArtist(album.getArtist());
            albumDb.setYear(album.getYear());
            albumDb.setGenres(album.getGenres());

            return Optional.of(repository.save(albumDb));
        }

        return albumOptional;
    }

    //Deleting an album from the DB
    @Transactional
    @Override
    public Optional<Album> delete(Long id) {
        Optional<Album> albumOptional = repository.findById(id);

        albumOptional.ifPresent(albumDb -> {
            repository.delete(albumDb);
        });

        return albumOptional;
    }
}
