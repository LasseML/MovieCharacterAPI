package no.noroff.characterapi.repositories;

import no.noroff.characterapi.models.Franchise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FranchiseRepository extends JpaRepository<Franchise, Long> {
}
