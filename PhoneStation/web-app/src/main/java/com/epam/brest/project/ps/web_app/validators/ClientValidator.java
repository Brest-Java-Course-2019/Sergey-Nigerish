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

    public static final int CLIENT_FULL_NAME_MAX_SIZE = 255;

    @Override
    public boolean supports(Class<?> aClass) {
        return Client.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmpty(errors, "clientFIO", "clientFIO.empty");
        Client client = (Client) target;

        if (StringUtils.hasLength(client.getClientFIO())
                && client.getClientFIO().length() > CLIENT_FULL_NAME_MAX_SIZE) {
            errors.rejectValue("clientFIO", "clientFIO.maxSize255");
        }
    }

    public void validate(Date startDate, Date endDate, Errors errors) {

        if (startDate.compareTo(endDate) < 0) {
            errors.rejectValue("clientFIO", "clientFIO.date");
        }
    }
}
