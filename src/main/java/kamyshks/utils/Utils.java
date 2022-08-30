package kamyshks.utils;

import lombok.SneakyThrows;

import javax.validation.*;
import java.util.Set;

public class Utils {

    @SneakyThrows
    public static void validate(Object o) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(o);
        if (constraintViolations.size() > 0) throw new ConstraintViolationException(constraintViolations);
    }

}
