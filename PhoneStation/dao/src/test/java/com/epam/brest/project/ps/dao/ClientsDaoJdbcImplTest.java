package com.epam.brest.project.ps.dao;

import com.epam.brest.project.ps.model.Client;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:test-db.xml", "classpath:test-dao.xml"})
@Rollback
@Transactional
class ClientsDaoJdbcImplTest {

    private static final int COUNT_CLIENTS_BLOCKED = 1;
    private static final int COUNT_CLIENTS_NO_BLOCKING = 7;

    private static final Integer FIRST_CLIENT_ID = 1;
    private static final Integer SECOND_CLIENT_ID = 2;
    private static final String FIRST_CLIENT_FULL_NAME = "Sergey Pupkin";
    private static final String FIRST_CLIENT_ANDRESS = "Lesnaya st.";
    private static final Date FIRST_CLIENT_DATE = Date.valueOf("2015-10-02");
    private static final Boolean FIRST_CLIENT_BLOCKED = false;
    private static final Boolean FIRST_CLIENT_DELETED = false;
    private static final Integer FIRST_CLIENT_TARIFF_ID = 1;

    private static final String NEW_CLIENT_FULL_NAME = "Vasya Loginov";
    private static final String NEW_CLIENT_ANDRESS = "Zolotaya st.";
    private static final Integer NEW_CLIENT_TARIFF_ID = 2;
    private static final int COUNT_NEW_CLIENTS = 1;

    private static final Date START_DATE = Date.valueOf("2016-03-11");
    private static final Date END_DATE = Date.valueOf("2018-01-11");
    private static final int COUNT_CLIENTS_BY_DATE = 5;
    private static final int COUNT_CLIENTS_AFTER_DATE = 7;
    private static final int COUNT_CLIENTS_BEFORE_DATE = 6;

    @Autowired
    ClientsDao clientsDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientsDaoJdbcImplTest.class);

    @Test
    public void findAll() {
        List<Client> clientList = clientsDao.findAll().collect(Collectors.toList());
        LOGGER.debug("@Test findAll() clientList.isEmpty(): {}", clientList.isEmpty());
        assertFalse(clientList.isEmpty());
    }

    @Test
    void findAllByDate() {
        long countClients = clientsDao.findAllByFilter(null, START_DATE, END_DATE).count();
        LOGGER.debug("@Test findAllByDate({}, {}, {}) result: expected({}) - actual({})",
                null, START_DATE, END_DATE, COUNT_CLIENTS_BY_DATE, countClients);
        assertEquals(COUNT_CLIENTS_BY_DATE, countClients);
    }

    @Test
    void findAllByDateAndBlocking() {
        long countClients = clientsDao.findAllByFilter(true, START_DATE, END_DATE).count();
        LOGGER.debug("@Test findAllByDate({}, {}, {}) result: expected({}) - actual({})",
                null, START_DATE, END_DATE, COUNT_CLIENTS_BY_DATE, countClients);
        assertEquals(COUNT_CLIENTS_BLOCKED, countClients);
    }

    @Test
    void findAllBeforeDate() {
        long countClients = clientsDao.findAllByFilter(null,null, END_DATE).count();
        LOGGER.debug("@Test findAllBeforeDate({}, {}, {}) result: expected({}) - actual({})",
                null, null, END_DATE, COUNT_CLIENTS_BEFORE_DATE, countClients);
        assertEquals(COUNT_CLIENTS_BEFORE_DATE, countClients);
    }

    @Test
    void findAllAfterDate() {
        long countClients = clientsDao.findAllByFilter(null, START_DATE, null).count();
        LOGGER.debug("@Test findAllAfterDate({}, {}, {}) result: expected({}) - actual({})",
                null, START_DATE, null, COUNT_CLIENTS_AFTER_DATE, countClients);
        assertEquals(COUNT_CLIENTS_AFTER_DATE, countClients);
    }

    @Test
    void findAllByBlocking() {
        long countClients = clientsDao.findAllByBlocking(true).count();
        LOGGER.debug("@Test findAllByBlocking(true) result: expected({}) - actual({})",
                COUNT_CLIENTS_BLOCKED, countClients);
        assertEquals(COUNT_CLIENTS_BLOCKED, countClients);
    }

    @Test
    void findAllByNoBlocking() {
        long countClients = clientsDao.findAllByBlocking(false).count();
        LOGGER.debug("@Test findAllByNoBlocking(false) result: expected({}) - actual({})",
                COUNT_CLIENTS_NO_BLOCKING, countClients);
        assertEquals(COUNT_CLIENTS_NO_BLOCKING, countClients);
    }

    @Test
    void findById() {
        Client client = clientsDao.findById(FIRST_CLIENT_ID).get();
        LOGGER.debug("@Test findById({}) result: expected(Client{clientContractId={}, clientContractDay_date={}, " +
                        "clientFIO='{}', clientAddress='{}', clientBlocked={}, client_to_idTariff={}, " +
                        "clientDeleted={}}) - actual({})",
                FIRST_CLIENT_ID, FIRST_CLIENT_ID, FIRST_CLIENT_DATE, FIRST_CLIENT_FULL_NAME, FIRST_CLIENT_ANDRESS,
                FIRST_CLIENT_BLOCKED, FIRST_CLIENT_TARIFF_ID, FIRST_CLIENT_DELETED, client);
        assertNotNull(client);
        assertEquals(FIRST_CLIENT_ID, client.getClientContractId());
        assertEquals(FIRST_CLIENT_DATE, client.getClientContractDay_date());
        assertEquals(FIRST_CLIENT_FULL_NAME, client.getClientFIO());
        assertEquals(FIRST_CLIENT_ANDRESS, client.getClientAddress());
        assertEquals(FIRST_CLIENT_BLOCKED, client.getClientBlocked());
    }

    @Test
    void add() {
        long countBefore = clientsDao.findAll().count();

        Client client = new Client();
        client.setClientFIO(NEW_CLIENT_FULL_NAME);
        client.setClientAddress(NEW_CLIENT_ANDRESS);
        client.setClient_to_idTariff(NEW_CLIENT_TARIFF_ID);

        clientsDao.add(client).get();
        assertNotNull(client.getClientContractId());

        long countAfter = clientsDao.findAll().count();
        LOGGER.debug("@Test add({}) result id: {}, count additions: expected({}) - actual({})",
                client, client.getClientContractId(), COUNT_NEW_CLIENTS, countAfter - countBefore);
        assertEquals(COUNT_NEW_CLIENTS, countAfter - countBefore);
    }

    @Test
    void update() {
        Client client = new Client();
        client.setClientContractId(FIRST_CLIENT_ID);
        client.setClientFIO(FIRST_CLIENT_FULL_NAME + " update");
        client.setClientAddress(FIRST_CLIENT_ANDRESS + " update");
        client.setClient_to_idTariff(NEW_CLIENT_TARIFF_ID);

        clientsDao.update(client);

        Client updatedClient = clientsDao.findById(client.getClientContractId()).get();
        LOGGER.debug("@Test update() result: expected({}) - actual({})", client, updatedClient);
        assertEquals(FIRST_CLIENT_FULL_NAME + " update", updatedClient.getClientFIO());
        assertEquals(FIRST_CLIENT_ANDRESS + " update", updatedClient.getClientAddress());
    }

    @Test
    void updateTariff() {
        clientsDao.updateTariff(FIRST_CLIENT_ID, NEW_CLIENT_TARIFF_ID);

        Client updatedClient = clientsDao.findById(FIRST_CLIENT_ID).get();
        LOGGER.debug("@Test updateTariff() result: expected({}) - actual({})", NEW_CLIENT_TARIFF_ID, updatedClient.getClient_to_idTariff());
        assertEquals(NEW_CLIENT_TARIFF_ID, updatedClient.getClient_to_idTariff());
    }

    @Test
    void updateBlocking() {
        clientsDao.updateBlocking(FIRST_CLIENT_ID, true);
        Client updatedClient = clientsDao.findById(FIRST_CLIENT_ID).get();
        LOGGER.debug("@Test updateBlocking() result: expected({}) - actual({})", true, updatedClient.getClientBlocked());
        assertEquals(true, updatedClient.getClientBlocked());

        clientsDao.updateBlocking(FIRST_CLIENT_ID, false);
        updatedClient = clientsDao.findById(FIRST_CLIENT_ID).get();
        LOGGER.debug("@Test updateBlocking() result: expected({}) - actual({})", false, updatedClient.getClientBlocked());
        assertEquals(false, updatedClient.getClientBlocked());
    }

    @Test
    void delete() {
        long countBefore = clientsDao.findAll().count();
        clientsDao.delete(SECOND_CLIENT_ID);
        long countAfter = clientsDao.findAll().count();

        Throwable exception = assertThrows(EmptyResultDataAccessException.class, () -> {
            clientsDao.findById(SECOND_CLIENT_ID);
        });
        LOGGER.debug("@Test delete({}) waiting exception: {}", SECOND_CLIENT_ID, exception.toString());
        LOGGER.debug("@Test delete({}) result count : expected({}) - actual({})",
                SECOND_CLIENT_ID, countBefore - 1, countAfter);
        assertEquals(countBefore - 1, countAfter);
    }
}