package com.epam.brest.project.ps.service;

import com.epam.brest.project.ps.dao.ClientsDao;
import com.epam.brest.project.ps.model.Client;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.mockito.Mockito;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ClientsServiceImplMockTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientsServiceImplMockTest.class);

    private static final int ONCE = 1;
    private static final String CLIENT_FULL_NAME = "Testing Client";

    private ClientsDao dao;

    private ClientsService service;

    private Client client = new Client();

    @BeforeEach
    void setup() {
        dao = Mockito.mock(ClientsDao.class);
        service = new ClientsServiceImpl(dao);

        client.setClientContractId(1);
        client.setClientFIO("Testing Client");
        client.setClientAddress("Testing address");
        client.setClientContractDay_date(Date.valueOf("2019-03-10"));
        client.setClientBlocked(false);
        client.setClient_to_idTariff(1);
        client.setClientDeleted(false);
    }

    @AfterEach
    public void tearDowns() {
        Mockito.verifyNoMoreInteractions(dao);
        Mockito.reset(dao);
    }

    @Test
    void findAll() {
        Mockito.when(dao.findAll()).thenReturn(Stream.of(client));

        List<Client> result = service.findAll();
        assertNotNull(result);
        assertEquals(ONCE, result.size());

        LOGGER.debug("@Test findAll() result size: expected({}) - actual({})", ONCE, result.size());
        Mockito.verify(dao, Mockito.times(ONCE)).findAll();
        Mockito.verifyNoMoreInteractions(dao);
    }

    @Test
    void findAllByDate() {
        Mockito.when(dao.findAllByDate(Mockito.any(), Mockito.any())).thenReturn(Stream.of(client));

        List<Client> result = service.findAllByDate(Mockito.any(), Mockito.any());
        assertNotNull(result);
        assertEquals(ONCE, result.size());

        LOGGER.debug("@Test findAllByDate() result size: expected({}) - actual({})", ONCE, result.size());
        Mockito.verify(dao, Mockito.times(ONCE)).findAllByDate(Mockito.any(), Mockito.any());
        Mockito.verifyNoMoreInteractions(dao);
    }

    @Test
    void findAllByBlocking() {
        Mockito.when(dao.findAllByBlocking(Mockito.any())).thenReturn(Stream.of(client));

        List<Client> result = service.findAllByBlocking(false);
        assertNotNull(result);
        assertEquals(ONCE, result.size());

        LOGGER.debug("@Test findAllByBlocking() result size: expected({}) - actual({})", ONCE, result.size());
        Mockito.verify(dao, Mockito.times(ONCE)).findAllByBlocking(Mockito.any());
        Mockito.verifyNoMoreInteractions(dao);
    }

    @Test
    void findById() {
        Mockito.when(dao.findById(Mockito.any())).thenReturn(Optional.of(client));

        Client result = service.findById(Mockito.any());
        assertNotNull(result);
        assertEquals(CLIENT_FULL_NAME, result.getClientFIO());

        LOGGER.debug("@Test findById() result full name: expected({}) - actual({})", CLIENT_FULL_NAME, result.getClientFIO());
        Mockito.verify(dao, Mockito.times(ONCE)).findById(Mockito.any());
        Mockito.verifyNoMoreInteractions(dao);
    }

    @Test
    void add() {
        dao.add(client);

        LOGGER.debug("@Test add()");
        Mockito.verify(dao, Mockito.times(ONCE)).add(client);
        Mockito.verifyNoMoreInteractions(dao);
    }

    @Test
    void update() {
        dao.update(client);

        LOGGER.debug("@Test update({})", client);
        Mockito.verify(dao, Mockito.times(ONCE)).update(client);
        Mockito.verifyNoMoreInteractions(dao);
    }

    @Test
    void updateTariff() {
        dao.updateTariff(Mockito.any(), Mockito.any());

        LOGGER.debug("@Test updateTariff()");
        Mockito.verify(dao, Mockito.times(ONCE)).updateTariff(Mockito.any(), Mockito.any());
        Mockito.verifyNoMoreInteractions(dao);
    }

    @Test
    void updateBlocking() {
        dao.updateBlocking(Mockito.any(), Mockito.any());

        LOGGER.debug("@Test updateBlocking()");
        Mockito.verify(dao, Mockito.times(ONCE)).updateBlocking(Mockito.any(), Mockito.any());
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