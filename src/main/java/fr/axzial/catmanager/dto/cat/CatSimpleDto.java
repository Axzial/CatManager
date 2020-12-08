package fr.axzial.catmanager.dto.cat;

import fr.axzial.catmanager.model.CatBreed;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The {@link fr.axzial.catmanager.model.Cat} simple dto.
 * Used to display a {@link fr.axzial.catmanager.model.Cat} without the {@link fr.axzial.catmanager.model.CatOwner}
 */
@NoArgsConstructor
@Data
public class CatSimpleDto {
    private long id;
    private String name;
    private CatBreed catBreed;
    private String color;
}
