package no.noroff.characterapi.controllers;

import no.noroff.characterapi.models.Franchise;
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
import java.util.List;

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
        if (id == franchise.getId() && franchiseRepository.existsById(id))
            return ResponseEntity.ok(franchiseRepository.save(franchise));
        return new ResponseEntity<>(new Franchise(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus( HttpStatus.BAD_REQUEST )
    public void IllegalArgumentHandler(HttpServletRequest req, Exception ex) {
        logger.info("Invalid request received: " + req.getRequestURI());
        logger.info("Invalid request received: " + req.getMethod());
    }

}
