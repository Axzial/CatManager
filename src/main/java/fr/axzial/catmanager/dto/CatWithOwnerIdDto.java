package fr.axzial.catmanager.dto;

import fr.axzial.catmanager.model.CatBreed;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CatWithOwnerIdDto {
    private String name;
    private CatBreed catBreed;
    private String color;
    private long ownerId;
}
