package no.noroff.characterapi.controllers;

import no.noroff.characterapi.models.Movie;
import no.noroff.characterapi.models.MovieCharacter;
import no.noroff.characterapi.repositories.MovieCharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(ApiConstants.CHARACTER_PATH)
@RestController
public class MovieCharacterController {

    @Autowired
    private MovieCharacterRepository movieCharacterRepository;

    @GetMapping()
    public ResponseEntity<List<MovieCharacter>> getAllMovieCharacters(){
        List<MovieCharacter> movieCharacters = movieCharacterRepository.findAll();
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(movieCharacters, status);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieCharacter> getMovieCharacter(@PathVariable Long id){
        MovieCharacter returnMovieCharacter = new MovieCharacter();
        HttpStatus status;

        if(movieCharacterRepository.existsById(id)){
            status = HttpStatus.OK;
            returnMovieCharacter = movieCharacterRepository.findById(id).get();
        } else {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(returnMovieCharacter, status);
    }

    @PostMapping
    public ResponseEntity<MovieCharacter> addMovieCharacter(@RequestBody MovieCharacter movieCharacter){
        HttpStatus status;
        movieCharacter = movieCharacterRepository.save(movieCharacter);
        status = HttpStatus.CREATED;
        return new ResponseEntity<>(movieCharacter, status);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieCharacter> updateMovieCharacter(@PathVariable Long id,
                                                               @RequestBody MovieCharacter movieCharacter){
        MovieCharacter returnMovieCharacter = new MovieCharacter();
        HttpStatus status;

        if (!id.equals(movieCharacter.getId())){
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(returnMovieCharacter, status);
        }
        returnMovieCharacter = movieCharacterRepository.save(movieCharacter);
        status = HttpStatus.NO_CONTENT;
        return new ResponseEntity<>(returnMovieCharacter, status);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MovieCharacter> deleteMovieCharacter(@PathVariable long id) {
        HttpStatus status;

            if (!movieCharacterRepository.existsById(id)) {
                status = HttpStatus.BAD_REQUEST;
                return new ResponseEntity<>(status);
            }
            MovieCharacter movieCharacter = movieCharacterRepository.getOne(id);
            movieCharacter.setDeleted();
            movieCharacterRepository.save(movieCharacter);
            status = HttpStatus.NO_CONTENT;

        return new ResponseEntity<>(status);
    }
}
