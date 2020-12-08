package fr.axzial.catmanager.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
public class ErrorDto {
    private String message;
    private String desc;

    public String getMessage() {
        return message;
    }

    public ErrorDto setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public ErrorDto setDesc(String description) {
        this.desc = description;
        return this;
    }
}
