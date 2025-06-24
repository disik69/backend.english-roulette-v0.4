package ua.pp.disik.englishroulette.backend.validation;

import org.apache.commons.lang3.StringUtils;
import ua.pp.disik.englishroulette.backend.repository.WordRepository;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(FIELD)
@Constraint(validatedBy = WordBody.Validator.class)
@Retention(RUNTIME)
public @interface WordBody {
    String message();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    class Validator implements ConstraintValidator<WordBody, String> {
        private final WordRepository wordRepository;

        public Validator(WordRepository wordRepository) {
            this.wordRepository = wordRepository;
        }

        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            if (StringUtils.isNotEmpty(value)) {
                return ! wordRepository.findByBody(value).isPresent();
            } else {
                return false;
            }
        }

        @Override
        public void initialize(WordBody constraintAnnotation) {}
    }
}
