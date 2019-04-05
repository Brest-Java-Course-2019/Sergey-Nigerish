package com.epam.brest.project.ps.service;

import com.epam.brest.project.ps.dao.TariffsDao;
import com.epam.brest.project.ps.model.Tariff;
import com.epam.brest.project.ps.stub.TariffStub;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class TariffsServiceImplMockTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(TariffsServiceImplMockTest.class);

    private static final int ONCE = 1;
    private static final int TEN = 10;
    private static final String TARIFF_NAME = "Testing Tariff";

    private TariffsDao dao;

    private TariffsService service;

    private Tariff tariff= new Tariff();
    private TariffStub tariffStub= new TariffStub();

    @BeforeEach
    void setup() {
        dao = Mockito.mock(TariffsDao.class);
        service = new TariffsServiceImpl(dao);

        tariff.setTariffId(1);
        tariff.setTariffName("Testing Tariff");
        tariff.setTariffDeleted(false);

        tariffStub.setTariffId(1);
        tariffStub.setTariffName("Testing Tariff");
        tariffStub.setTariffDeleted(false);
        tariffStub.setTariffCountClients(3);
    }

    @AfterEach
    public void tearDowns() {
        Mockito.verifyNoMoreInteractions(dao);
        Mockito.reset(dao);
    }

    @Test
    void findAll() {
        Mockito.when(dao.findAll()).thenReturn(Stream.of(tariff));

        List<Tariff> result = service.findAll();
        assertNotNull(result);
        assertEquals(ONCE, result.size());

        LOGGER.debug("@Test findAll() result size: expected({}) - actual({})", ONCE, result.size());
        Mockito.verify(dao, Mockito.times(ONCE)).findAll();
        Mockito.verifyNoMoreInteractions(dao);
    }

    @Test
    void findAllStubs() {
        Mockito.when(dao.findAllStubs()).thenReturn(Stream.of(tariffStub));

        List<TariffStub> result = service.findAllStubs();
        assertNotNull(result);
        assertEquals(ONCE, result.size());

        LOGGER.debug("@Test findAllStubs() result size: expected({}) - actual({})", ONCE, result.size());
        Mockito.verify(dao, Mockito.times(ONCE)).findAllStubs();
        Mockito.verifyNoMoreInteractions(dao);
    }

    @Test
    void findById() {
        Mockito.when(dao.findById(Mockito.any())).thenReturn(Optional.of(tariff));

        Tariff result = service.findById(Mockito.any());
        assertNotNull(result);
        assertEquals(TARIFF_NAME, result.getTariffName());

        LOGGER.debug("@Test findById() result tariff name: expected({}) - actual({})", TARIFF_NAME, result.getTariffName());
        Mockito.verify(dao, Mockito.times(ONCE)).findById(Mockito.any());
        Mockito.verifyNoMoreInteractions(dao);
    }

    @Test
    void add() {
        dao.add(tariff);

        LOGGER.debug("@Test add()");
        Mockito.verify(dao, Mockito.times(ONCE)).add(tariff);
        Mockito.verifyNoMoreInteractions(dao);
    }

    @Test
    void update() {
        dao.update(tariff);

        LOGGER.debug("@Test update({})", tariff);
        Mockito.verify(dao, Mockito.times(ONCE)).update(tariff);
        Mockito.verifyNoMoreInteractions(dao);
    }

    @Test
    void delete() {
        dao.delete(Mockito.any());

        LOGGER.debug("@Test delete()");
        Mockito.verify(dao, Mockito.times(ONCE)).delete(Mockito.any());
        Mockito.verifyNoMoreInteractions(dao);
    }
}