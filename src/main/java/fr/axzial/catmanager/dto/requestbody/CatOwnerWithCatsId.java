package fr.axzial.catmanager.dto.requestbody;

import fr.axzial.catmanager.model.Cat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class CatOwnerWithCatsId {
    private String name;
    private List<Long> catList;
}
