package fr.axzial.catmanager.controller;

import fr.axzial.catmanager.dto.ErrorDto;
import fr.axzial.catmanager.exception.CatBreedNotFoundException;
import fr.axzial.catmanager.exception.CatNotFoundException;
import fr.axzial.catmanager.exception.CatOwnerNotFoundException;
import fr.axzial.catmanager.model.CatBreed;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(CatOwnerNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto catOwnerNotFoundExceptionHandle(CatOwnerNotFoundException catOwnerNotFoundException){
        return new ErrorDto().setMessage("CatOwner doesn't exists...").setDesc(catOwnerNotFoundException.getMessage());
    }
    @ExceptionHandler(CatNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto catNotFoundExceptionHandle(CatNotFoundException catNotFoundException){
        return new ErrorDto().setMessage("Cat doesn't exists...").setDesc(catNotFoundException.getMessage());
    }
    @ExceptionHandler(CatBreedNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto catBreedNotFoundExceptionHandle(CatBreedNotFoundException catBreedNotFoundException){
        return new ErrorDto().setMessage("CatBreed doesn't exists...").setDesc(catBreedNotFoundException.getMessage());
    }
}
