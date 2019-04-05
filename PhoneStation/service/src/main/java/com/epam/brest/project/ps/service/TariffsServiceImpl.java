package com.epam.brest.project.ps.service;

import com.epam.brest.project.ps.dao.TariffsDao;
import com.epam.brest.project.ps.model.Tariff;
import com.epam.brest.project.ps.stub.TariffStub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class TariffsServiceImpl implements TariffsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TariffsServiceImpl.class);

    private TariffsDao dao;

    public TariffsServiceImpl(TariffsDao dao) {
        this.dao = dao;
    }

    /**
     * Get all tariffs with count people.
     *
     * @return tariffs stream.
     */
    @Override
    public List<TariffStub> findAllStubs() {
        LOGGER.debug("findAllStubs()");
        return dao.findAllStubs().collect(Collectors.toList());
    }

    /**
     * Get all tariffs.
     *
     * @return tariffs stream.
     */
    @Override
    public List<Tariff> findAll() {
        LOGGER.debug("findAll()");
        return dao.findAll().collect(Collectors.toList());
    }

    /**
     * Get tariff by id.
     *
     * @param tariffId for getting.
     * @return tariff by tariffId.
     */
    @Override
    public Tariff findById(Integer tariffId) {
        LOGGER.debug("findById({})", tariffId);
        return dao.findById(tariffId).get();
    }

    /**
     * Add new tariff.
     *
     * @param tariff new tariff.
     */
    @Override
    public void add(Tariff tariff) {
        LOGGER.debug("add({})", tariff);
        dao.add(tariff);
    }

    /**
     * Edit tariff in base.
     *
     * @param tariff for editing.
     */
    @Override
    public void update(Tariff tariff) {
        LOGGER.debug("update({})", tariff);
        dao.update(tariff);
    }

    /**
     * Delete tariff with specified id.
     *
     * @param tariffId tariff for delete.
     */
    @Override
    public void delete(Integer tariffId) {
        LOGGER.debug("delete({})", tariffId);
        dao.delete(tariffId);
    }
}
