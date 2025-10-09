package com.jacobo.springboot.albums_app.albums_app.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jacobo.springboot.albums_app.albums_app.entities.Album;
import com.jacobo.springboot.albums_app.albums_app.services.AlbumService;
import com.jacobo.springboot.albums_app.albums_app.utils.ValidationUtil;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;



//Creating the CRUD methods for album entity
@RestController
@RequestMapping("/albums")
public class AlbumController {
    //Injecting the service as a dependency
    @Autowired
    private AlbumService service;

    //Get method, listing all the albums
    @GetMapping
    public List<Album> list() {
        return service.findAll();
    }
    
    //Get method for searching a certain album by its ID
    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id) {
        //Making the search using the service dependency
        Optional<Album> albumOptional = service.findById(id);

        //Validating if the object was found
        if (albumOptional.isPresent()){
            return ResponseEntity.ok(albumOptional.orElseThrow());
        }

        //Return a not found error
        return ResponseEntity.notFound().build();
    }

    //Get method to search an album by its genre ID
    @GetMapping("/genre/{genreId}")
    public ResponseEntity<List<Album>> viewGenre(@PathVariable Long genreId) {
        List<Album> albumsGenre = service.findAlbumsByGenre(genreId);

        //Checking if any album with that genre is found
        if(albumsGenre.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        //Return the object with all the info
        return ResponseEntity.ok(albumsGenre);
    }
    

    //Post method, creating a new album
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Album album, BindingResult result) {
        //Doing validations using validation method created inside ValidationUtil class
        if(result.hasFieldErrors()){
            return ResponseEntity.badRequest().body(ValidationUtil.validation(result));
        }

        //Return a 200 ok response stating that the object was created
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(album));
    }
    
    //Put method, updating an album
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Album album, BindingResult result, @PathVariable Long id) {
        //Doing validations using validation method created inside ValidationUtil class
        if(result.hasFieldErrors()){
            return ResponseEntity.badRequest().body(ValidationUtil.validation(result));
        }

        //Updating the object with the injected service object
        Optional<Album> albumOptional = service.update(id, album);

        //Returning a 200 ok response stating that the object was updated
        if(albumOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(albumOptional.orElseThrow());
        }

        //Return an error stating that the object was not found
        return ResponseEntity.notFound().build();
    }

    //Delete method
    @DeleteMapping("/{id}")
    public ResponseEntity<Album> delete(@PathVariable Long id){
        //Using the delete method in the service
        Optional<Album> albumOptional = service.delete(id);

        //Returning an ok response if the object was deleted succesfully
        if(albumOptional.isPresent()){
            return ResponseEntity.ok(albumOptional.orElseThrow());
        }

        //Returning a not found message if the object was not found
        return ResponseEntity.notFound().build();
    }
}
