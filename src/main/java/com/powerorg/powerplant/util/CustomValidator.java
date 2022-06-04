package com.powerorg.powerplant.util;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomValidator {

    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public static List<String> validateCollection(Collection<?> collection) {
        List<String> errorMessageList = new ArrayList<>();
        int index = 0;
        for (Object entity : collection) {
            Set<? extends ConstraintViolation<?>> violations = validator.validate(entity);
            index++;
            if (!violations.isEmpty()) {
                errorMessageList.add(
                        "Entity " + index + " : " + violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(" and ")));
            }
        }
        return errorMessageList;
    }

}
