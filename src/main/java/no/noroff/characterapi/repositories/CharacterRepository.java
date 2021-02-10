package no.noroff.characterapi.repositories;

import no.noroff.characterapi.models.MovieCharacter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<MovieCharacter, Long> {
}
