package no.noroff.characterapi.repositories;

import no.noroff.characterapi.models.MovieCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<MovieCharacter, Long> {
}
