package no.noroff.characterapi.controllers;

import no.noroff.characterapi.models.Franchise;
import no.noroff.characterapi.models.Movie;
import no.noroff.characterapi.models.MovieCharacter;
import no.noroff.characterapi.repositories.FranchiseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RequestMapping(ApiConstants.FRANCHISE_PATH)
@RestController
public class FranchiseController {
    Logger logger = LoggerFactory.getLogger(FranchiseController.class);

    @Autowired
    private FranchiseRepository franchiseRepository;

    //Return all franchises
    @GetMapping()
    public ResponseEntity<List<Franchise>> getAllFranchises() {
        return ResponseEntity.ok(franchiseRepository.findAll());
    }

    //Return a specific franchise
    //Let ResponseEntity.of() handle the Optional and return the correct status code
    @GetMapping("/{id}")
    public ResponseEntity<Franchise> getFranchise(@PathVariable long id) {
        return ResponseEntity.of(franchiseRepository.findById(id));
    }

    //Add a franchise to the DB
    @PostMapping()
    public ResponseEntity<Franchise> addFranchise(@RequestBody Franchise franchise) {
        return new ResponseEntity<>(franchiseRepository.save(franchise), HttpStatus.CREATED);
    }

    //Update a franchise
    //Check that IDs match
    @PutMapping("/{id}")
    public ResponseEntity<Franchise> updateFranchise(@PathVariable long id, @RequestBody Franchise franchise) {
        if (id == franchise.getId()) {
            franchiseRepository.save(franchise);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().build();
    }

    //Soft delete a franchise
    @DeleteMapping("/{id}")
    public ResponseEntity<Franchise> deleteFranchise(@PathVariable long id) {
        if (franchiseRepository.existsById(id)) {
            Franchise franchise = franchiseRepository.getOne(id);
            franchise.setDeleted();
            franchiseRepository.save(franchise);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().build();

    }

    //Return all movie characters in a franchise
    @GetMapping("/{id}/characters")
    public ResponseEntity<Set<MovieCharacter>> getFranchiseCharacters(@PathVariable long id) {
        if (franchiseRepository.existsById(id)) {
            Set<MovieCharacter> characters = new HashSet<>();
            Franchise franchise = franchiseRepository.getOne(id);

            for (Movie movie: franchise.getMovies())
                characters.addAll(movie.getMovieCharacters());

            return ResponseEntity.ok(characters);
        }
        return ResponseEntity.notFound().build();
    }

    //Return all movies in a franchise
    @GetMapping("/{id}/movies")
    public ResponseEntity<List<Movie>> getFranchiseMovies(@PathVariable long id) {
        if (franchiseRepository.existsById(id)) {
            Franchise franchise = franchiseRepository.getOne(id);
            return ResponseEntity.ok(franchise.getMovies());
        }
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus( HttpStatus.BAD_REQUEST )
    public void IllegalArgumentHandler(HttpServletRequest req, Exception ex) {
        logger.info("Invalid request received: " + req.getRequestURI());
        logger.info("Invalid request received: " + req.getMethod());
    }
}
