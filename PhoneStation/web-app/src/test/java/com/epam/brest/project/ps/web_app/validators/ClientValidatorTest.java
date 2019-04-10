package com.epam.brest.project.ps.web_app.validators;

import com.epam.brest.project.ps.model.Client;
import com.epam.brest.project.ps.model.Tariff;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.thymeleaf.util.StringUtils;


import static org.junit.jupiter.api.Assertions.*;

class ClientValidatorTest {

    private Client client;

    private ClientValidator clientValidator = new ClientValidator();
    private BindingResult result;

    private String longFilled = StringUtils.repeat("+", 300);
    private String maxFilled = StringUtils.repeat("+", 255);

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
        Mockito.when(client.getClientFIO()).thenReturn(longFilled);
        clientValidator.validate(client, result);
        assertTrue(result.hasErrors());
    }

    @Test
    void shouldValidateClientName() {
        Mockito.when(client.getClientFIO()).thenReturn(maxFilled);
        assertFalse(result.hasErrors());
    }

    @Test
    void shouldRejectNullAddress() {
        Mockito.when(client.getClientAddress()).thenReturn(null);
        clientValidator.validate(client, result);
        assertTrue(result.hasErrors());
    }

    @Test
    void shouldRejectEmptyAddress() {
        Mockito.when(client.getClientAddress()).thenReturn("");
        clientValidator.validate(client, result);
        assertTrue(result.hasErrors());
    }

    @Test
    void shouldRejectLargeAddress() {
        Mockito.when(client.getClientAddress()).thenReturn(longFilled);
        clientValidator.validate(client, result);
        assertTrue(result.hasErrors());
    }

    @Test
    void shouldValidateAddress() {
        Mockito.when(client.getClientAddress()).thenReturn(maxFilled);
        clientValidator.validate(client, result);
        assertTrue(result.hasErrors());
    }

    @Test
    void shouldValidateClass() {
        assertEquals(true, clientValidator.supports(Client.class));
    }

    @Test
    void shouldValidateAnotherClass() {
        assertEquals(false, clientValidator.supports(Tariff.class));
    }
}