package fr.axzial.catmanager.controller;

import fr.axzial.catmanager.dto.entity.CatDto;
import fr.axzial.catmanager.dto.requestbody.CatWithOwnerIdDto;
import fr.axzial.catmanager.dto.returnbody.CatReturnDto;
import fr.axzial.catmanager.exception.CatNotFoundException;
import fr.axzial.catmanager.model.Cat;
import fr.axzial.catmanager.service.CatService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cat")
public class CatController {

    private final CatService catService;

    public CatController(CatService catService) {
        this.catService = catService;
    }

    @GetMapping
    public ResponseEntity<List<CatReturnDto>> getCats(){
        List<CatReturnDto> catList = catService.findAllSimple();
        return new ResponseEntity<>(catList, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cat> getCat(@PathVariable long id){
        return catService.findById(id).map(ResponseEntity::ok)
                .orElseThrow(() -> new CatNotFoundException("Can't find Cat with id: " + id));
    }

    @PutMapping
    public ResponseEntity<CatReturnDto> addCat(@RequestBody CatWithOwnerIdDto catWithOwnerIdDto){
        return new ResponseEntity<>(catService.saveWithOwnerIdDto(catWithOwnerIdDto), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Cat> update(@PathVariable long id, @RequestBody CatDto catDto){
        return catService.update(id, catDto).map(ResponseEntity::ok)
                .orElseThrow(() -> new CatNotFoundException("Can't find Cat with id: " + id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        catService.delete(id);
    }
}
