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
import entities.Album;
import services.AlbumService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/albums")
public class AlbumController {
    @Autowired
    private AlbumService service;

    @GetMapping
    public List<Album> list() {
        return service.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id) {
        Optional<Album> albumOptional = service.findById(id);

        if (albumOptional.isPresent()){
            return ResponseEntity.ok(albumOptional.orElseThrow());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Album album, BindingResult result) {
        if(result.hasFieldErrors()){
            return ResponseEntity.badRequest().body(ValidationUtil.validation(result));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(album));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Album album, BindingResult result, @PathVariable Long id) {
        if(result.hasFieldErrors()){
            return ResponseEntity.badRequest().body(ValidationUtil.validation(result));
        }

        Optional<Album> albumOptional = service.update(id, album);

        if(albumOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(albumOptional.orElseThrow());
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Album> delete(@PathVariable Long id){
        Optional<Album> albumOptional = service.delete(id);

        if(albumOptional.isPresent()){
            return ResponseEntity.ok(albumOptional.orElseThrow());
        }

        return ResponseEntity.notFound().build();
    }
}
