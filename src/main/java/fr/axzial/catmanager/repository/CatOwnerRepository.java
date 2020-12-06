package fr.axzial.catmanager.repository;

import fr.axzial.catmanager.model.CatOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public abstract class CatOwnerRepository implements JpaRepository<CatOwner, Long> {
}
