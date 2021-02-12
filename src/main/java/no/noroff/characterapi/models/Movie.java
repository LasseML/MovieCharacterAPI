package no.noroff.characterapi.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import no.noroff.characterapi.controllers.ApiConstants;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Where(clause = "DELETED = 0")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    private String title;
    @Column(name = "genre")
    private String genre;
    @Column(name = "release")
    private Date release;
    @Column(name = "director")
    private String director;
    @Column(name = "picture")
    private String picture;
    @Column(name = "trailer")
    private String trailer;
    @Column(name = "DELETED")
    private Integer deleted = 0;

    @ManyToOne
    @JoinColumn(name = "franchise_id")
    private Franchise franchise;

    @JsonGetter("franchise")
    public String franchise() {
        if(franchise != null){
            return "/api/v1/franchise/" + franchise.getId();
        }else{
            return null;
        }
    }

    @ManyToMany
    @JoinTable(
            name = "movie_moviecharacter",
            joinColumns = {@JoinColumn(name = "movie_id")},
            inverseJoinColumns = {@JoinColumn(name = "movie_character_id")}
    )
    private List<MovieCharacter> movieCharacters;

    @JsonGetter("movieCharacters")
    public List<String> movieCharactersGetter() {
        if(movieCharacters != null){
            return movieCharacters.stream()
                    .map(movieCharacter -> {
                        return ApiConstants.CHARACTER_PATH +"/"+ movieCharacter.getId();
                    }).collect(Collectors.toList());
        }
        return null;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Date getRelease() {
        return release;
    }

    public void setRelease(Date release) {
        this.release = release;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public Franchise getFranchise() {
        return franchise;
    }

    public void setFranchise(Franchise franchise) {
        this.franchise = franchise;
    }

    public List<MovieCharacter> getMovieCharacters() {
        return movieCharacters;
    }

    public void setMovieCharacters(List<MovieCharacter> movieCharacters) {
        this.movieCharacters = movieCharacters;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted() {
        this.deleted = 1;
    }
}
