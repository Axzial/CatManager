package fr.axzial.catmanager.dto.requestbody;

import fr.axzial.catmanager.model.CatBreed;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CatWithOwnerIdDto {
    private String name;
    private long catBreedId;
    private String color;
    private long ownerId;
}
