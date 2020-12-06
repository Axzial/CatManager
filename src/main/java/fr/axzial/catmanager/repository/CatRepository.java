package fr.axzial.catmanager.repository;

import fr.axzial.catmanager.model.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public abstract class CatRepository implements JpaRepository<Cat, Long> {
}
