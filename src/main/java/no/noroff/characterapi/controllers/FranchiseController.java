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
    @Autowired
    private FranchiseRepository franchiseRepository;

    private final Logger logger = LoggerFactory.getLogger(FranchiseController.class);


    /*
        GET
    */

    @GetMapping
    public ResponseEntity<List<Franchise>> getAllFranchises() {
        logger.info("Received GET ALL");
        return ResponseEntity.ok(franchiseRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Franchise> getFranchise(@PathVariable long id) {
        logger.info("Received GET with id: " + id);
        if (franchiseRepository.findById(id).isPresent()) {
            logger.info("Franchise found");
            return ResponseEntity.ok(franchiseRepository.findById(id).get());
        }
        logger.info("Franchise not found");
        return ResponseEntity.notFound().build();
    }

    /*
        POST
    */

    @PostMapping
    public ResponseEntity<Franchise> addFranchise(@RequestBody Franchise franchise) {
        logger.info("Received POST request");
        Franchise newFranchise = franchiseRepository.save(franchise);

        return ResponseEntity.status(HttpStatus.CREATED).body(newFranchise);
    }

    /*
        PUT
    */

    @PutMapping("/{id}")
    public ResponseEntity<Franchise> updateFranchise(@PathVariable long id, @RequestBody Franchise franchise) {
        logger.info("Received PUT with id: " + id);
        franchise.setId(id);
        franchise = franchiseRepository.save(franchise);

        return ResponseEntity.ok(franchise);
    }


    /*
        Exception Handling
    */

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus( HttpStatus.BAD_REQUEST )
    public void IllegalArgumentHandler(HttpServletRequest req, Exception ex) {
        logger.info("Invalid request received: " + req.getRequestURI());
        logger.info("Invalid request received: " + req.getMethod());
    }

}
