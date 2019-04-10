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

class TariffValidatorTest {

    private Tariff tariff;

    private TariffValidator tariffValidator = new TariffValidator();
    private BindingResult result;

    @BeforeEach
    void setup() {
        tariff = Mockito.mock(Tariff.class);
        result = new BeanPropertyBindingResult(tariff, "tariff");
    }

    @Test
    void shouldRejectNullTariffName() {
        Mockito.when(tariff.getTariffName()).thenReturn(null);
        tariffValidator.validate(tariff, result);
        assertTrue(result.hasErrors());
    }

    @Test
    void shouldRejectEmptyTariffName() {
        Mockito.when(tariff.getTariffName()).thenReturn("");
        tariffValidator.validate(tariff, result);
        assertTrue(result.hasErrors());
    }

    @Test
    void shouldRejectLargeTariffName() {
        String filled = StringUtils.repeat("+", 300);
        Mockito.when(tariff.getTariffName()).thenReturn(filled);
        tariffValidator.validate(tariff, result);
        assertTrue(result.hasErrors());
    }

    @Test
    void shouldValidateTariffName() {
        String filled = StringUtils.repeat("+", 255);
        Mockito.when(tariff.getTariffName()).thenReturn(filled);
        tariffValidator.validate(tariff, result);
        assertFalse(result.hasErrors());
    }

    @Test
    void shouldValidateClass() {
        assertEquals(true, tariffValidator.supports(Tariff.class));
    }


    @Test
    void shouldValidateAnotherClass() {
        assertEquals(false, tariffValidator.supports(Client.class));
    }
}



