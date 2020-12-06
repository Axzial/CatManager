package fr.axzial.catmanager.service;

import fr.axzial.catmanager.dto.CatBreedDto;
import fr.axzial.catmanager.exception.BreedNotFoundException;
import fr.axzial.catmanager.model.CatBreed;

import java.util.List;
import java.util.Optional;

public interface CatBreedService {
    List<CatBreed> findAll();
    Optional<CatBreed> findById(long id);
    CatBreed save(CatBreedDto catBreedDto);
    Optional<CatBreed> update(long id, CatBreedDto catBreedDto) throws BreedNotFoundException;
    void delete(long id) throws BreedNotFoundException;
}
