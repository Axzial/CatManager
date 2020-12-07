package fr.axzial.catmanager.service;

import fr.axzial.catmanager.dto.entity.CatOwnerDto;
import fr.axzial.catmanager.dto.requestbody.CatOwnerWithCatsId;
import fr.axzial.catmanager.dto.returnbody.CatOwnerReturnDto;
import fr.axzial.catmanager.dto.returnbody.CatOwnerSimpleDto;
import fr.axzial.catmanager.exception.BreedNotFoundException;
import fr.axzial.catmanager.exception.CatOwnerNotFoundException;
import fr.axzial.catmanager.model.CatOwner;

import java.util.List;
import java.util.Optional;

public interface CatOwnerService {
    List<CatOwner> findAll();
    List<CatOwnerReturnDto> findAllSimple();
    Optional<CatOwner> findById(long id);
    CatOwner save(CatOwnerDto catOwnerDto);
    CatOwnerReturnDto saveWithCatsId(CatOwnerWithCatsId catOwnerWithCatsId);
    Optional<CatOwner> update(long id, CatOwnerDto catOwnerDto) throws CatOwnerNotFoundException;
    void delete(long id) throws CatOwnerNotFoundException;
}
