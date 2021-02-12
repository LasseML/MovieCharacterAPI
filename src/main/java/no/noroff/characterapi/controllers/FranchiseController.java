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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequestMapping(ApiConstants.FRANCHISE_PATH)
@RestController
public class FranchiseController {
    Logger logger = LoggerFactory.getLogger(FranchiseController.class);

    @Autowired
    private FranchiseRepository franchiseRepository;

    @GetMapping()
    public ResponseEntity<List<Franchise>> getAllFranchises() {
        return ResponseEntity.ok(franchiseRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Franchise> getFranchise(@PathVariable long id) {
        return ResponseEntity.of(franchiseRepository.findById(id));
    }

    @PostMapping()
    public ResponseEntity<Franchise> addFranchise(@RequestBody Franchise franchise) {
        return new ResponseEntity<>(franchiseRepository.save(franchise), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Franchise> updateFranchise(@PathVariable long id, @RequestBody Franchise franchise) {
        if (id == franchise.getId() && franchiseRepository.existsById(id)) {
            franchiseRepository.save(franchise);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().build();
    }

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
