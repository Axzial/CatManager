package fr.axzial.catmanager.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(fluent = true)
public class ErrorDto {
    private String message;
    private String desc;
}
