package br.com.jhonatanserafim.peoplemanager.v1.dto;

import io.swagger.annotations.ApiModel;
import org.springframework.validation.Errors;

import java.util.ArrayList;
import java.util.List;

@ApiModel("ErrorResponseV1")
public class ErrorResponseV1 {

    private final List<String> errors = new ArrayList<>();

    public ErrorResponseV1(Errors errors) {
        errors.getFieldErrors().forEach(fieldError ->
                this.errors.add(fieldError.getField() + ": " + fieldError.getDefaultMessage()));

        errors.getGlobalErrors().forEach(fieldError ->
                this.errors.add(fieldError.getObjectName() + ": " + fieldError.getDefaultMessage()));
    }

    public List<String> getErrors() {
        return errors;
    }
}
