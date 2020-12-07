package fr.axzial.catmanager.dto.requestbody;

import fr.axzial.catmanager.model.CatBreed;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The {@link fr.axzial.catmanager.model.Cat} with owner id dto.
 * Contains a {@link CatBreed} id as {@link Long} and a {@link fr.axzial.catmanager.model.CatOwner} id as {@link Long}
 */
@NoArgsConstructor
@Data
public class CatWithOwnerIdDto {
    private long id;
    private String name;
    private long catBreedId;
    private String color;
    private long ownerId;
}
