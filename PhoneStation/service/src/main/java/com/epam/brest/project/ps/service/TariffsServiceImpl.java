package com.epam.brest.project.ps.service;

import com.epam.brest.project.ps.dao.TariffsDao;
import com.epam.brest.project.ps.model.Tariff;
import com.epam.brest.project.ps.stub.TariffStub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service implementation for tariff.
 */
@Transactional
public class TariffsServiceImpl implements TariffsService {

    /**
     * Connects logger.
     */
    private static final Logger LOGGER =
                    LoggerFactory.getLogger(TariffsServiceImpl.class);

    /**
     * Variable for DAO.
     */
    private TariffsDao tariffsDao;

    /**
     * Service constructor.
     *
     * @param dao of tariff objects.
     */
    public TariffsServiceImpl(final TariffsDao dao) {
        this.tariffsDao = dao;
    }

    /**
     * Get all tariffs with count people.
     *
     * @return tariffs stream.
     */
    @Override
    public final List<TariffStub> findAllStubs() {
        LOGGER.debug("findAllStubs()");
        return tariffsDao.findAllStubs().collect(Collectors.toList());
    }

    /**
     * Get all tariffs.
     *
     * @return tariffs stream.
     */
    @Override
    public final List<Tariff> findAll() {
        LOGGER.debug("findAll()");
        return tariffsDao.findAll().collect(Collectors.toList());
    }

    /**
     * Get tariff by id.
     *
     * @param tariffId for getting.
     * @return tariff by tariffId.
     */
    @Override
    public final Tariff findById(final Integer tariffId) {
        LOGGER.debug("findById({})", tariffId);
        return tariffsDao.findById(tariffId).get();
    }

    /**
     * Add new tariff.
     *
     * @param tariff new tariff.
     */
    @Override
    public final void add(final Tariff tariff) {
        LOGGER.debug("add({})", tariff);
        tariffsDao.add(tariff);
    }

    /**
     * Edit tariff in base.
     *
     * @param tariff for editing.
     */
    @Override
    public final void update(final Tariff tariff) {
        LOGGER.debug("update({})", tariff);
        tariffsDao.update(tariff);
    }

    /**
     * Delete tariff with specified id.
     *
     * @param tariffId tariff for delete.
     */
    @Override
    public final void delete(final Integer tariffId) {
        LOGGER.debug("delete({})", tariffId);
        tariffsDao.delete(tariffId);
    }
}
