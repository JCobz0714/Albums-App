package com.jacobo.springboot.albums_app.albums_app.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jacobo.springboot.albums_app.albums_app.entities.Genre;
import com.jacobo.springboot.albums_app.albums_app.services.GenreService;
import com.jacobo.springboot.albums_app.albums_app.utils.ValidationUtil;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


//Creating the CRUD methods for genre entity
@RestController
@RequestMapping("/genre")
public class GenreController {
    //Injecting the service as a dependency
    @Autowired
    private GenreService service;
    
    //Get method, listing all the genres
    @GetMapping
    public List<Genre> list() {
        return service.findAll();
    }
    
    //Get method for searching a certain genre by its ID
    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id) {
        //Making the search using the service dependency
        Optional<Genre> genreOptional = service.findById(id);

        //Validating if the object was found
        if (genreOptional.isPresent()){
            return ResponseEntity.ok(genreOptional.orElseThrow());
        }

        //Return a not found error
        return ResponseEntity.notFound().build();
    }

    //Post method, creating a new genre
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Genre genre, BindingResult result) {
        //Doing validations using validation method created inside ValidationUtil class
        if(result.hasFieldErrors()){
            return ResponseEntity.badRequest().body(ValidationUtil.validation(result));
        }

        //Return a 200 ok response stating that the object was created
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(genre));
    }

    //Put method, updating a genre
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Genre genre, BindingResult result, @PathVariable Long id) {
        //Doing validations using validation method created inside ValidationUtil class
        if(result.hasFieldErrors()){
            return ResponseEntity.badRequest().body(ValidationUtil.validation(result));
        }

        //Updating the object with the injected service object
        Optional<Genre> genreOptional = service.update(id, genre);

        //Returning a 200 ok response stating that the object was updated
        if(genreOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(genreOptional.orElseThrow());
        }

        //Return an error stating that the object was not found
        return ResponseEntity.notFound().build();
    }

    //Delete method
    @DeleteMapping("/{id}")
    public ResponseEntity<Genre> delete(@PathVariable Long id){
        //Using the delete method in the service
        Optional<Genre> genreOptional = service.delete(id);

        //Returning an ok response if the object was deleted succesfully
        if(genreOptional.isPresent()){
            return ResponseEntity.ok(genreOptional.orElseThrow());
        }

        //Returning a not found message if the object was not found
        return ResponseEntity.notFound().build();
    }
}
