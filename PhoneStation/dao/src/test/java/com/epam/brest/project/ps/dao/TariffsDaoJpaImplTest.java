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
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:test-db.xml", "classpath:test-dao.xml"})
@Rollback
@Transactional
class TariffsDaoJpaImplTest {

    private static final int COUNT_TARIFFS = 4;

    private static final int FIRST_TARIFF_ID = 1;
    private static final String FIRST_TARIFF_NAME = "Talkative";
    private static final boolean FIRST_TARIFF_DELETE_STATUS = false;

    private static final int TARIFF_ID_IS_DELETED = 5;

    private static final String NEW_TARIFF_NAME = "TestTariff";
    private static final int COUNT_NEW_TARIFFS = 1;

    private static final Logger LOGGER = LoggerFactory.getLogger(TariffsDaoJpaImplTest.class);

    @Autowired
    TariffsDao tariffsDao;

    @Test
    public void findAll() {
        List<Tariff> tariffs = tariffsDao.findAll().collect(Collectors.toList());
        LOGGER.debug("@Test findAll() tariffs.isEmpty(): {}", tariffs.isEmpty());
        assertFalse(tariffs.isEmpty());
    }

    @Test
    public void findAll_count() {
        List<Tariff> tariffs = tariffsDao.findAll().collect(Collectors.toList());
        LOGGER.debug("@Test findAll_count() result: expected({}) - actual({})", COUNT_TARIFFS, tariffs.size());
        assertEquals(COUNT_TARIFFS, tariffs.size());
    }

    @Test
    void findById() {
        Tariff tariff = tariffsDao.findById(FIRST_TARIFF_ID).get();
        LOGGER.debug("@Test findById({}) result: expected(Tariff{tariffsId={}, tariffName='{}', tariffDeleted={}}) - actual({})",
                FIRST_TARIFF_ID, FIRST_TARIFF_ID, FIRST_TARIFF_NAME, FIRST_TARIFF_DELETE_STATUS, tariff);
        assertNotNull(tariff);
        assertTrue(tariff.getTariffId().equals(FIRST_TARIFF_ID));
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
//        Tariff tariff = new Tariff();
//        tariff.setTariffName(NEW_DEPARTMENT_NAME);
//        tariff.setTariffDescription(NEW_DEPARTMENT_NAME);
//        Tariff newTariff = tariffsDao.add(tariff).get();
//        assertNotNull(newTariff.getTariffId());
//
//        tariff.setTariffName(NEW_DEPARTMENT_NAME + "_2");
//        tariff.setTariffDescription(NEW_DEPARTMENT_NAME + "_2");
//        tariffsDao.update(tariff);
//
//        Tariff updatedTariff = tariffsDao.findById(tariff.getTariffId()).get();
//82.209.234.115
//        assertEquals(NEW_DEPARTMENT_NAME + "_2", updatedTariff.getTariffName());
//        assertEquals(NEW_DEPARTMENT_NAME + "_2", updatedTariff.getTariffDescription());
    }
}