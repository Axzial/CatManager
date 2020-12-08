package fr.axzial.catmanager.dto.catowner;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * The {@link fr.axzial.catmanager.model.CatOwner} with cats id.
 * Contains Cats id stored as {@link Long} in a {@link List}
 */
@NoArgsConstructor
@Data
public class CatOwnerWithCatsIdDto {
    private String name;
    private List<Long> catList;
}