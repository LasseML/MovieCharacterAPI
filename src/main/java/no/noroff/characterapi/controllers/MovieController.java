package no.noroff.characterapi.controllers;

import no.noroff.characterapi.models.Movie;
import no.noroff.characterapi.models.MovieCharacter;
import no.noroff.characterapi.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(ApiConstants.MOVIE_PATH)
@RestController
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    //Return all movies
    @GetMapping()
    public ResponseEntity<List<Movie>> getAllMovies(){
        List<Movie> movies = movieRepository.findAll();
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(movies,status);
    }

    //Return one movie by id if provided id exists
    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovie(@PathVariable Long id) {
        Movie movie = new Movie();
        HttpStatus status;
        if(movieRepository.existsById(id)) {
            status = HttpStatus.OK;
            movie = movieRepository.findById(id).get();
        } else {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(movie, status);
    }

    //Add a new movie to the database
    @PostMapping
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie){
        Movie returnMovie = movieRepository.save(movie);
        HttpStatus status = HttpStatus.CREATED;
        return new ResponseEntity<>(returnMovie, status);
    }

    //Check that IDs match
    //If yes, update the movie
    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie movie){
        Movie returnMovie = new Movie();
        HttpStatus status;
        if(!id.equals(movie.getId())){
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(returnMovie,status);
        }
        returnMovie = movieRepository.save(movie);
        status = HttpStatus.NO_CONTENT;
        return new ResponseEntity<>(returnMovie, status);
    }

    //Return all characters in a movie
    @GetMapping("/{id}/characters")
    public ResponseEntity<List<MovieCharacter>> getAllCharactersInMovie(@PathVariable Long id){
        HttpStatus status;
        List<MovieCharacter> characters = null;
        if(movieRepository.existsById(id)) {
            Movie movie = movieRepository.getOne(id);
            characters = movie.getMovieCharacters();
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(characters, status);
    }

    //Soft deletes a movie character
    @DeleteMapping("/{id}")
    public ResponseEntity<Movie> deleteMovie(@PathVariable long id) {
        HttpStatus status;
        if (!movieRepository.existsById(id)) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(status);
        }
        Movie movie = movieRepository.getOne(id);
        movie.setDeleted();
        movieRepository.save(movie);
        status = HttpStatus.NO_CONTENT;

        return new ResponseEntity<>(status);
    }
}