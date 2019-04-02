package com.epam.brest.project.ps.web_app.validators;

import com.epam.brest.project.ps.model.Tariff;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class TariffValidator implements Validator {

    public static final int TARIFF_NAME_MAX_SIZE = 255;

    @Override
    public boolean supports(Class<?> aClass) {
        return Tariff.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmpty(errors, "TariffName", "TariffName.empty");
        Tariff tariff = (Tariff) target;

        if (StringUtils.hasLength(tariff.getTariffName())
                && tariff.getTariffName().length() > TARIFF_NAME_MAX_SIZE) {
            errors.rejectValue("tariffName", "tariffName.maxSize255");
        }
    }

    public void validate(Integer countUsers, Errors errors) {

        if (countUsers > 0) {
            errors.rejectValue("tariff", "tariff.count");
        }
    }
}
