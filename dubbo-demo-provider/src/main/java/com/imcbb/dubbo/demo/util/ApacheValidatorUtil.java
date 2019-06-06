package com.imcbb.dubbo.demo.util;

import org.apache.bval.jsr.ApacheValidationProvider;
import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * @author kevin.chen
 * Date 2019/6/6
 * Time 14:50
 */
public interface ApacheValidatorUtil {

    /**
     * Build ValidatorFactory
     */
    ValidatorFactory FACTORY = Validation.byProvider(ApacheValidationProvider.class).configure().buildValidatorFactory();

    static <T> void validateParams(T validateModel) {
        javax.validation.Validator validator = FACTORY.getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(validateModel);
        if (violations.size() == 0) {
            return;
        }
        ConstraintViolation cv = violations.iterator().next();
        System.out.println(cv.getPropertyPath().toString());
        throw new IllegalArgumentException(cv.getMessage());
    }
}
