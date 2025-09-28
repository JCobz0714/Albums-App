package com.jacobo.springboot.albums_app.albums_app.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Utils.ValidationUtil;
import entities.Genre;
import jakarta.validation.Valid;
import services.GenreService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/albums/genre")
public class GenreController {
    @Autowired
    private GenreService service;
    
    @GetMapping
    public List<Genre> list() {
        return service.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id) {
        Optional<Genre> genreOptional = service.findById(id);

        if (genreOptional.isPresent()){
            return ResponseEntity.ok(genreOptional.orElseThrow());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Genre genre, BindingResult result) {
        if(result.hasFieldErrors()){
            return ResponseEntity.badRequest().body(ValidationUtil.validation(result));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(genre));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Genre genre, BindingResult result, @PathVariable Long id) {
        if(result.hasFieldErrors()){
            return ResponseEntity.badRequest().body(ValidationUtil.validation(result));
        }

        Optional<Genre> genreOptional = service.update(id, genre);

        if(genreOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(genreOptional.orElseThrow());
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Genre> delete(@PathVariable Long id){
        Optional<Genre> genreOptional = service.delete(id);

        if(genreOptional.isPresent()){
            return ResponseEntity.ok(genreOptional.orElseThrow());
        }

        return ResponseEntity.notFound().build();
    }
}
