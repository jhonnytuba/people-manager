package br.com.jhonatanserafim.peoplemanager.core.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = CpfNonExistentValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CPFNonExistent {

    String message() default "número do registro de contribuinte individual brasileiro (CPF) já utilizado";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
