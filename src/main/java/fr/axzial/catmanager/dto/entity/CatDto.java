package fr.axzial.catmanager.dto.entity;

import fr.axzial.catmanager.model.CatBreed;
import fr.axzial.catmanager.model.CatOwner;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CatDto {
    private String name;
    private CatBreed catBreed;
    private String color;
    private CatOwner owner;
}
