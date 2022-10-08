package ua.pp.disik.englishroulette.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Validator;
import ua.pp.disik.englishroulette.backend.exception.HttpErrorException;

import java.util.HashMap;
import java.util.Map;

@Service
public class ValidationService {
    private final Validator validator;

    public ValidationService(Validator validator) {
        this.validator = validator;
    }

    public void validate(Object entity) {
        DataBinder dataBinder = new DataBinder(entity);
        dataBinder.setValidator(validator);
        dataBinder.validate();
        BindingResult bindingResult = dataBinder.getBindingResult();

        if (bindingResult.hasErrors()) {
            Map<String, String> wrongFields = new HashMap<String, String>() {{
                bindingResult.getFieldErrors().forEach(error -> put(error.getField(), error.getDefaultMessage()));
            }};
            HttpErrorException exception = new HttpErrorException(422, "Validation error");
            exception.setWrongFields(wrongFields);

            throw exception;
        }
    }
}
