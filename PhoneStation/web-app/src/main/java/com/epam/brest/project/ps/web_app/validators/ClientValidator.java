package com.epam.brest.project.ps.web_app.validators;

import com.epam.brest.project.ps.model.Client;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.sql.Date;

@Component
public class ClientValidator implements Validator {

    private static final int FIELD_MAX_LENGTH = 255;

    @Override
    public boolean supports(Class<?> aClass) {
        return Client.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmpty(errors, "clientFIO", "emptyField");
        ValidationUtils.rejectIfEmpty(errors, "clientAddress", "emptyField");

        Client client = (Client) target;

        if (StringUtils.hasLength(client.getClientFIO())
                && client.getClientFIO().length() > FIELD_MAX_LENGTH) {
            errors.rejectValue("clientFIO", "maxLength255");
        }

        if (StringUtils.hasLength(client.getClientAddress())
                && client.getClientAddress().length() > FIELD_MAX_LENGTH) {
            errors.rejectValue("clientAddress", "maxLength255");
        }
    }
}
