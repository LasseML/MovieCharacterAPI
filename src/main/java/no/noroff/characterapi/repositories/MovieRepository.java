package no.noroff.characterapi.repositories;

import no.noroff.characterapi.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
