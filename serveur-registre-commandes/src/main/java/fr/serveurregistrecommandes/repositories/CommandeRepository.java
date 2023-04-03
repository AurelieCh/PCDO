package fr.serveurregistrecommandes.repositories;

import fr.serveurregistrecommandes.commun.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Integer> {
    public Optional<Commande> findById(Integer Id);
}
