package fr.axzial.catmanager.dto;

import fr.axzial.catmanager.model.CatBreed;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The {@link fr.axzial.catmanager.model.Cat} return dto.
 * Used to return a {@link fr.axzial.catmanager.model.Cat} with a {@link CatOwnerSimpleDto}
 */
@NoArgsConstructor
@Data
public class CatReturnDto {
    private long id;
    private String name;
    private CatBreed catBreed;
    private String color;
    private CatOwnerSimpleDto owner;
}
