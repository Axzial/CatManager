package fr.axzial.catmanager.service;

import fr.axzial.catmanager.dto.entity.CatDto;
import fr.axzial.catmanager.dto.requestbody.CatWithOwnerIdDto;
import fr.axzial.catmanager.dto.returnbody.CatReturnDto;
import fr.axzial.catmanager.dto.returnbody.CatSimpleDto;
import fr.axzial.catmanager.exception.CatNotFoundException;
import fr.axzial.catmanager.model.Cat;

import java.util.List;
import java.util.Optional;

public interface CatService {
    List<Cat> findAll();
    Optional<Cat> findById(long id);
    Cat save(CatDto catDto);
    CatReturnDto saveWithOwnerIdDto(CatWithOwnerIdDto catWithOwnerIdDto);
    Optional<Cat> update(long id, CatDto catDto) throws CatNotFoundException;
    void delete(long id) throws CatNotFoundException;
    List<CatReturnDto> findAllSimple();
    Optional<CatReturnDto> findByIdSimple(long id);

    Optional<CatReturnDto> updateWithOwnerIdDto(long id, CatWithOwnerIdDto catWithOwnerIdDto);
}
