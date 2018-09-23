package com.bestpay.dubbo.demo.util;

import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * @author kevin
 * Date 2018/9/17
 * Time 14:49
 */
public interface ValidatorUtil {

    /**
     * Build ValidatorFactory
     */
    ValidatorFactory FACTORY = Validation.byProvider(HibernateValidator.class).configure().failFast(true).buildValidatorFactory();

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
