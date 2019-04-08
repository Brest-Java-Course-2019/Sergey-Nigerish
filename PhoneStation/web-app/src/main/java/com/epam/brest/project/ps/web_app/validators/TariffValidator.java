package com.epam.brest.project.ps.web_app.validators;

import com.epam.brest.project.ps.model.Tariff;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class TariffValidator implements Validator {

    private static final int FIELD_MAX_LENGTH = 255;

    @Override
    public boolean supports(Class<?> aClass) {
        return Tariff.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmpty(errors, "tariffName", "emptyField");

        Tariff tariff = (Tariff) target;

        if (StringUtils.hasLength(tariff.getTariffName())
                && tariff.getTariffName().length() > FIELD_MAX_LENGTH) {
            errors.rejectValue("tariffName", "maxLength255");
        }
    }
}
