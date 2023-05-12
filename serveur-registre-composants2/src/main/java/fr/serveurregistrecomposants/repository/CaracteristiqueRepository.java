package fr.serveurregistrecomposants.repository;

import fr.serveurregistrecomposants.commun.Caracteristique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CaracteristiqueRepository extends JpaRepository<Caracteristique, Integer> {
    public List<Caracteristique> findAllByComposant_IdComposant(Integer id);
}