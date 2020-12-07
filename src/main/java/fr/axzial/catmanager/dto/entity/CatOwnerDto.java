package fr.axzial.catmanager.dto.entity;

import fr.axzial.catmanager.model.Cat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * The {@link fr.axzial.catmanager.model.CatOwner} dto.
 */
@NoArgsConstructor
@Data
public class CatOwnerDto {
    private String name;
    private List<Cat> catList;
}
