package fr.axzial.catmanager.service;

import fr.axzial.catmanager.dto.CatOwnerDto;
import fr.axzial.catmanager.exception.BreedNotFoundException;
import fr.axzial.catmanager.model.CatOwner;

import java.util.List;
import java.util.Optional;

public interface CatOwnerService {
    List<CatOwner> findAll();
    Optional<CatOwner> findById(long id);
    CatOwner save(CatOwnerDto catOwnerDto);
    CatOwner saveWithCatsId(CatOwnerDto catOwnerto);
    Optional<CatOwner> update(long id, CatOwnerDto catOwnerDto) throws BreedNotFoundException;
    void delete(long id) throws BreedNotFoundException;
}
