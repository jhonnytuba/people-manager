package br.com.jhonatanserafim.peoplemanager.v2.dto;

import io.swagger.annotations.ApiModel;
import org.springframework.validation.Errors;

import java.util.ArrayList;
import java.util.List;

@ApiModel("ErrorResponseV2")
public class ErrorResponseV2 {

    private final List<String> errors = new ArrayList<>();

    public ErrorResponseV2(Errors errors) {
        errors.getFieldErrors().forEach(fieldError ->
                this.errors.add(fieldError.getField() + ": " + fieldError.getDefaultMessage()));

        errors.getGlobalErrors().forEach(fieldError ->
                this.errors.add(fieldError.getObjectName() + ": " + fieldError.getDefaultMessage()));
    }

    public List<String> getErrors() {
        return errors;
    }
}
