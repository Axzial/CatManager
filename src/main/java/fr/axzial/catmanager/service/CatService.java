package fr.axzial.catmanager.service;

import fr.axzial.catmanager.dto.CatDto;
import fr.axzial.catmanager.exception.CatNotFoundException;
import fr.axzial.catmanager.model.Cat;

import java.util.List;
import java.util.Optional;

public interface CatService {
    List<Cat> findAll();
    Optional<Cat> findById(long id);
    Cat save(CatDto catDto);
    Optional<Cat> update(long id, CatDto catDto) throws CatNotFoundException;
    void delete(long id) throws CatNotFoundException;
}
