package com.epam.brest.project.ps.dao;

import com.epam.brest.project.ps.model.Tariff;
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

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:test-db.xml", "classpath:test-dao.xml"})
@Rollback
@Transactional
class TariffsDaoJdbcImplTest {

    private static final int COUNT_TARIFFS = 4;

    private static final int FIRST_TARIFF_ID = 1;
    private static final String FIRST_TARIFF_NAME = "Talkative";
    private static final boolean FIRST_TARIFF_DELETE_STATUS = false;
    private static final int COUNT_USERS_FOR_FIRST_TARIFF = 4;

    private static final int TARIFF_ID_IS_DELETED = 5;

    private static final String NEW_TARIFF_NAME = "TestTariff";
    private static final int COUNT_NEW_TARIFFS = 1;

    private static final Logger LOGGER = LoggerFactory.getLogger(TariffsDaoJdbcImplTest.class);

    @Autowired
    TariffsDao tariffsDao;

    @Test
    public void findAll() {
        List<Tariff> tariffsList = tariffsDao.findAll().collect(Collectors.toList());
        LOGGER.debug("@Test findAll() tariffs.isEmpty(): {}", tariffsList.isEmpty());
        assertFalse(tariffsList.isEmpty());
    }

    @Test
    public void findAll_count() {
        long countTariffs = tariffsDao.findAll().count();
        LOGGER.debug("@Test findAll_count() result: expected({}) - actual({})", COUNT_TARIFFS, countTariffs);
        assertEquals(COUNT_TARIFFS, countTariffs);
    }

    @Test
    void findById() {
        Tariff tariff = tariffsDao.findById(FIRST_TARIFF_ID).get();
        LOGGER.debug("@Test findById({}) result: expected(Tariff{tariffId={}, tariffName='{}', tariffDeleted={}}) - actual({})",
                FIRST_TARIFF_ID, FIRST_TARIFF_ID, FIRST_TARIFF_NAME, FIRST_TARIFF_DELETE_STATUS, tariff);
        assertNotNull(tariff);
        assertEquals(FIRST_TARIFF_ID, (int) tariff.getTariffId());
        assertEquals(FIRST_TARIFF_NAME, tariff.getTariffName());
        assertEquals(FIRST_TARIFF_DELETE_STATUS, tariff.getTariffDeleted());
    }

    @Test
    void findById_deleted() {
        Throwable exception = assertThrows(EmptyResultDataAccessException.class, () -> {
            tariffsDao.findById(TARIFF_ID_IS_DELETED);
        });
        LOGGER.debug("@Test findById_deleted({}) waiting exception: {}", TARIFF_ID_IS_DELETED, exception.toString());
    }

    @Test
    void create() {
        long countBefore = tariffsDao.findAll().count();

        Tariff tariff = new Tariff();
        tariff.setTariffName(NEW_TARIFF_NAME);
        Tariff newTariff = tariffsDao.add(tariff).get();
        assertNotNull(newTariff.getTariffId());

        long countAfter = tariffsDao.findAll().count();
        LOGGER.debug("@Test create({}) result id: {}, count additions: expected({}) - actual({})",
                NEW_TARIFF_NAME, newTariff.getTariffId(), COUNT_NEW_TARIFFS, countAfter - countBefore);
        assertEquals(COUNT_NEW_TARIFFS, countAfter - countBefore);
    }

    @Test
    void createDuplicateTariff() {
        Tariff tariff = new Tariff();
        tariff.setTariffName(FIRST_TARIFF_NAME);

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            tariffsDao.add(tariff);
        });
        LOGGER.debug("@Test createDuplicateTariff({}) waiting exception: {}", FIRST_TARIFF_NAME, exception.toString());
    }

    @Test
    void update() {
        Tariff tariff = new Tariff();
        tariff.setTariffName(FIRST_TARIFF_NAME + "_2");
        tariff.setTariffId(FIRST_TARIFF_ID);
        tariffsDao.update(tariff);

        Tariff updatedTariff = tariffsDao.findById(tariff.getTariffId()).get();
        LOGGER.debug("@Test update() result: expected({}) - actual({})", tariff, updatedTariff);
        assertEquals(FIRST_TARIFF_NAME + "_2", updatedTariff.getTariffName());
    }

    @Test
    void delete() {
        long countBefore = tariffsDao.findAll().count();
        tariffsDao.delete(FIRST_TARIFF_ID);
        long countAfter = tariffsDao.findAll().count();

        Throwable exception = assertThrows(EmptyResultDataAccessException.class, () -> {
            tariffsDao.findById(FIRST_TARIFF_ID);
        });
        LOGGER.debug("@Test delete({}) waiting exception: {}", FIRST_TARIFF_ID, exception.toString());
        LOGGER.debug("@Test delete({}) result count : expected({}) - actual({})",
                FIRST_TARIFF_ID, countBefore - 1, countAfter);
        assertEquals(countBefore - 1, countAfter);
    }

    @Test
    void countUsers() {
        int countUsers = tariffsDao.countUsers(FIRST_TARIFF_ID);

        LOGGER.debug("@Test countUsers() result: expected({}) - actual({})", COUNT_USERS_FOR_FIRST_TARIFF, countUsers);
        assertEquals(COUNT_USERS_FOR_FIRST_TARIFF, countUsers);
    }
}