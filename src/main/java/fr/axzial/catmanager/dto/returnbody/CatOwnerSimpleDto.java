package fr.axzial.catmanager.dto.returnbody;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The {@link fr.axzial.catmanager.model.CatOwner} simple dto.
 * Used to display a {@link fr.axzial.catmanager.model.CatOwner} without the {@link fr.axzial.catmanager.model.Cat} {@link java.util.List}
 */
@NoArgsConstructor
@Data
public class CatOwnerSimpleDto {
    private long id;
    private String name;
}