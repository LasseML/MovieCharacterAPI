package no.noroff.characterapi.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import no.noroff.characterapi.controllers.ApiConstants;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Where(clause = "DELETED = 0")
@Table(name="movie_character")
public class MovieCharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "alias")
    private String alias;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;
    @Column(name = "picture")
    private String picture;

    @Column(name = "DELETED")
    private Integer deleted = 0;

    @ManyToMany
    @JoinTable(
            name = "movie_moviecharacter",
            joinColumns = {@JoinColumn(name = "movie_character_id")},
            inverseJoinColumns = {@JoinColumn(name = "movie_id")}
    )
    private List<Movie> movies;

    @JsonGetter("movies")
    public List<String> moviesGetter() {
        if(movies != null){
            return movies.stream()
                    .map(movie -> {
                        return ApiConstants.MOVIE_PATH +"/"+ movie.getId();
                    }).collect(Collectors.toList());
        }
        return null;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public void setDeleted() { this.deleted = 1; }

}




