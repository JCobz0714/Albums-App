package com.jacobo.springboot.albums_app.albums_app.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

//Creating the 'Genre' entity
@Entity
@Table(name = "genres")
public class Genre {
    //Creating the ID with auto incremental strategy
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    //Changing the name of "genreName" atribute to avoid conflicts with MySQL
    @Column(name = "genre_name")
    private String genreName;

    //Creating the many to many relationship between the entities.
    @ManyToMany(mappedBy = "genres")
    //Annotation JsonIgnore to avoid loops in the JSON file
    @JsonIgnore
    private List<Album> albums;

    //Initializing the albums list to ease synchronization between the entities
    public Genre() {
        this.albums = new ArrayList<>();
    }

    public Genre(String genreName) {
        //Calling the empty constructor to initialize the albums list
        this();
        this.genreName = genreName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((genreName == null) ? 0 : genreName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Genre other = (Genre) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (genreName == null) {
            if (other.genreName != null)
                return false;
        } else if (!genreName.equals(other.genreName))
            return false;
        return true;
    }
}
