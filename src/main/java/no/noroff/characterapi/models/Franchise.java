package no.noroff.characterapi.models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name="franchise")
public class Franchise {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotNull //hibernate infers @Column(nullable = false) from this
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany
    @JoinColumn(name="franchise_id")
    List<Movie> movies;

    //I think this can be done without a formal relation
    //Not implementing for now
    //List<MovieCharacter> movieCharacters;


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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
