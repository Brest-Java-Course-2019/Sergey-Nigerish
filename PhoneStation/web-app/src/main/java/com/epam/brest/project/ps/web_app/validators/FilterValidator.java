package com.epam.brest.project.ps.web_app.validators;

import com.epam.brest.project.ps.model.Filter;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class FilterValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Filter.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Filter filter = (Filter) target;

        if (filter.getStartDate().compareTo(filter.getEndDate()) < 0) {
            errors.rejectValue("startDate", "dateRange");
        }
    }
}
