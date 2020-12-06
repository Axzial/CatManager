package fr.axzial.catmanager.repository;

import fr.axzial.catmanager.model.CatBreed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public abstract class CatBreedRepository implements JpaRepository<CatBreed, Long> {
}
