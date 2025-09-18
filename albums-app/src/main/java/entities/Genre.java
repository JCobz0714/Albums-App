package entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

//Creating the 'Genre' entity
@Entity
@Table(name = "genres")
public class Genre {
    //Creating the ID with auto incremental strategy
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Changing the name of "genreName" atribute to avoid conflicts with MySQL
    @Column(name = "genre_name")
    private String genreName;

    //Creating the many to many relationship between the entities.
    @ManyToMany(mappedBy = "albums")
    private List<Album> albums;
}
