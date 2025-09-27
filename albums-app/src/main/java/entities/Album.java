package entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

//Creating the 'Album' entity
@Entity
@Table(name = "albums")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String artist;
    private Long year;

    //Creating the many to many relationship between the entities.
    //Album will be the father entity.
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Genre> genres;

    //Initializing the genres list to ease synchronization between the entities
    public Album() {
        this.genres = new ArrayList<>();
    }

    public Album(String name, String artist, Long year) {
        //Calling the empty constructor to initialize the genres list
        this();
        this.name = name;
        this.artist = artist;
        this.year = year;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
}
