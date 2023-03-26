package fr.serveurregistrecompte.repositories;

import fr.serveurregistrecompte.commun.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompteRepository extends JpaRepository<Compte, String> {
    public Optional<Compte> findByEmail(String email);
}
