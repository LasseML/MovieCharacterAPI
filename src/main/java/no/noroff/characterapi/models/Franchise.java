package no.noroff.characterapi.models;


import com.fasterxml.jackson.annotation.JsonGetter;
import no.noroff.characterapi.controllers.ApiConstants;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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


    @JsonGetter("movies")
    public List<String> moviesGetter() {
        if(movies != null){
            return movies.stream()
                    .map(movie -> ApiConstants.MOVIE_PATH +"/"+ movie.getId()).collect(Collectors.toList());
        }
        return new ArrayList<>();
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
