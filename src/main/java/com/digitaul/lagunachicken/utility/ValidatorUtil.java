package com.digitaul.lagunachicken.utility;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Set;


public class ValidatorUtil {

    private static ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private static Validator validator = validatorFactory.getValidator();

    public static <T> Set<ConstraintViolation<T>> validarCampos(T object) {
        return validator.validate(object);
    }

}
