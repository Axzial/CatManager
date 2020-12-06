package fr.axzial.catmanager.controller;

import fr.axzial.catmanager.dto.CatOwnerDto;
import fr.axzial.catmanager.exception.CatOwnerNotFoundException;
import fr.axzial.catmanager.model.CatOwner;
import fr.axzial.catmanager.service.CatOwnerService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/catowner")
public class CatOwnerController {

    private final CatOwnerService catOwnerService;

    public CatOwnerController(CatOwnerService catOwnerService) {
        this.catOwnerService = catOwnerService;
    }

    @GetMapping
    public ResponseEntity<List<CatOwner>> getOwners(){
        return new ResponseEntity<>(catOwnerService.findAll(), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CatOwner> getOwner(@PathVariable long id){
        return catOwnerService.findById(id).map(ResponseEntity::ok)
                .orElseThrow(() -> new CatOwnerNotFoundException("Can't find CatOwner with id: " + id));
    }

    @PutMapping
    public ResponseEntity<CatOwner> addCat(@RequestBody CatOwnerDto catOwnerDto){
        return new ResponseEntity<>(catOwnerService.save(catOwnerDto), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CatOwner> update(@PathVariable long id, @RequestBody CatOwnerDto catOwnerDto){
        return catOwnerService.update(id, catOwnerDto).map(ResponseEntity::ok)
                .orElseThrow(() -> new CatOwnerNotFoundException("Can't find CatOwner with id: " + id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        catOwnerService.delete(id);
    }
}
