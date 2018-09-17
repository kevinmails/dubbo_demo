package com.bestpay.dubbo.demo.util;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * @author 陈彬
 * Date 2018/9/17
 * Time 14:49
 */
public interface ValidatorUtil {

    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    static <T> void validateParams(T validateModel) {
        javax.validation.Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(validateModel);
        if (violations.size() == 0) {
            return;
        }
        ConstraintViolation cv = violations.iterator().next();
        System.out.println(cv.getPropertyPath().toString());
        throw new IllegalArgumentException(cv.getMessage());
    }

}
