package entities;

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
}
