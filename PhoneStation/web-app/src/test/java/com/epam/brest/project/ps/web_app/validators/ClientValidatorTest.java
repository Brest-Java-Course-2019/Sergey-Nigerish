package com.epam.brest.project.ps.web_app.validators;

import com.epam.brest.project.ps.model.Client;
import com.epam.brest.project.ps.model.Tariff;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.thymeleaf.util.StringUtils;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class ClientValidatorTest {

    private Client client;

    private ClientValidator clientValidator = new ClientValidator();
    private BindingResult result;

    private static final Date START_DATE = Date.valueOf("2016-03-11");
    private static final Date END_DATE = Date.valueOf("2018-01-11");

    @BeforeEach
    void setup() {
        client = Mockito.mock(Client.class);
        result = new BeanPropertyBindingResult(client, "client");
    }

    @Test
    void shouldRejectNullClientName() {
        Mockito.when(client.getClientFIO()).thenReturn(null);
        clientValidator.validate(client, result);
        assertTrue(result.hasErrors());
    }

    @Test
    void shouldRejectEmptyClientName() {
        Mockito.when(client.getClientFIO()).thenReturn("");
        clientValidator.validate(client, result);
        assertTrue(result.hasErrors());
    }

    @Test
    void shouldRejectLargeClientName() {
        String filled = StringUtils.repeat("+", 300);
        Mockito.when(client.getClientFIO()).thenReturn(filled);
        clientValidator.validate(client, result);
        assertTrue(result.hasErrors());
    }

    @Test
    void shouldValidateClientName() {
        String filled = StringUtils.repeat("+", 250);
        Mockito.when(client.getClientFIO()).thenReturn(filled);
        clientValidator.validate(client, result);
        assertFalse(result.hasErrors());
    }

    @Test
    void shouldValidateClass() {
        assertEquals(true, clientValidator.supports(Client.class));
    }

    @Test
    void shouldValidateAnotherClass() {
        assertEquals(false, clientValidator.supports(Tariff.class));
    }

    @Test
    void validateDate() {
        clientValidator.validate(START_DATE, END_DATE, result);
        assertTrue(result.hasErrors());
    }

    @Test
    void validateWrongDate() {
        clientValidator.validate(END_DATE, START_DATE, result);
        assertFalse(result.hasErrors());
    }
}