package org.desafioestagio.backend.validations;

import jakarta.validation.*;
import jakarta.validation.constraints.*;
import java.util.Set;

public class Validate {
    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public static <T> void validate(T object, Class<?>... groups) {
        Set<ConstraintViolation<T>> violations = validator.validate(object, groups);
        if (!violations.isEmpty()) {
            StringBuilder errorMessage = new StringBuilder("Validation failed: ");
            for (ConstraintViolation<T> violation : violations) {
                errorMessage.append("\n")
                        .append("Property: ").append(violation.getPropertyPath())
                        .append(" - Error: ").append(violation.getMessage());
            }
            throw new ConstraintViolationException(errorMessage.toString(), violations);
        }
    }
}
