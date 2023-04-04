package fr.serveurregistrefacturation.repositories;

import fr.serveurregistrefacturation.commun.Facturation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FacturationRepository extends JpaRepository<Facturation, Integer> {
    public Optional<Facturation> findById(Integer Id);
}
