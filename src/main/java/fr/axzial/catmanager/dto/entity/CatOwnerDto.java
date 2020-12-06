package fr.axzial.catmanager.dto.entity;

import fr.axzial.catmanager.model.Cat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class CatOwnerDto {
    private String name;
    private List<Cat> catList;
}
