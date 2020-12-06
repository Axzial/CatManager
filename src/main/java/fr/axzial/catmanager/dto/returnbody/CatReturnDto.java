package fr.axzial.catmanager.dto.returnbody;

import fr.axzial.catmanager.model.CatBreed;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CatReturnDto {
    private long id;
    private String name;
    private CatBreed catBreed;
    private String color;
    private CatOwnerSimpleDto owner;
}