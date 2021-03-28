package br.com.jhonatanserafim.peoplemanager.core.validation;

import br.com.jhonatanserafim.peoplemanager.jpa.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Map;

public class CpfNonExistentValidator implements ConstraintValidator<CPFNonExistent, String> {

    private static final String PATH_VARIABLES = "org.springframework.web.servlet.View.pathVariables";

    private final PersonRepository repository;
    private final HttpServletRequest httpServletRequest;

    public CpfNonExistentValidator(PersonRepository repository,
                                   @Autowired(required = false) HttpServletRequest httpServletRequest) {
        this.repository = repository;
        this.httpServletRequest = httpServletRequest;
    }

    @Override
    public void initialize(CPFNonExistent contactNumber) {
        //NOP
    }

    @Override
    public boolean isValid(String cpf, ConstraintValidatorContext cxt) {
        Long id = getIdPathParam();

        if (id != null) {
            return !repository.existsByCpfAndIdIsNot(cpf, id);
        }
        return !repository.existsByCpf(cpf);
    }

    @SuppressWarnings("unchecked")
    private Long getIdPathParam() {
        if (httpServletRequest == null) {
            return null;
        }

        Map<String, Object> pathVariables = (Map<String, Object>) httpServletRequest.getAttribute(PATH_VARIABLES);

        if (pathVariables != null && pathVariables.containsKey("id")) {
            return Long.parseLong(pathVariables.get("id").toString());
        }

        return null;
    }

}
