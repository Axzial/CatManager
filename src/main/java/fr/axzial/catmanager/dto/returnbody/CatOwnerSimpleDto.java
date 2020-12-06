package fr.axzial.catmanager.dto.returnbody;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CatOwnerSimpleDto {
    private long id;
    private String name;
}