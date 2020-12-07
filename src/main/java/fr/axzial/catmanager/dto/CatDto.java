package fr.axzial.catmanager.dto;

import fr.axzial.catmanager.model.CatBreed;
import fr.axzial.catmanager.model.CatOwner;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The {@link fr.axzial.catmanager.model.Cat} dto.
 */
@NoArgsConstructor
@Data
public class CatDto {
    private String name;
    private CatBreed catBreed;
    private String color;
    private CatOwner owner;
}
