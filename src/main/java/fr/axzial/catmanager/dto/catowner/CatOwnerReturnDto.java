package fr.axzial.catmanager.dto.catowner;

import fr.axzial.catmanager.dto.cat.CatSimpleDto;
import fr.axzial.catmanager.model.Cat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * The {@link Cat} owner return dto.
 * Used to return a {@link fr.axzial.catmanager.model.CatOwner} but with a {@link CatSimpleDto} {@link List}
 */
@NoArgsConstructor
@Data
public class CatOwnerReturnDto {
    private long id;
    private String name;
    private List<CatSimpleDto> catList;
}
