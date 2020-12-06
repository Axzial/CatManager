package fr.axzial.catmanager.dto.returnbody;

import fr.axzial.catmanager.model.Cat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class CatOwnerReturnDto {
    private long id;
    private String name;
    private List<CatSimpleDto> catList;
}
