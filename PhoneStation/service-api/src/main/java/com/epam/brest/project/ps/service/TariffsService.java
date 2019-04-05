package com.epam.brest.project.ps.service;

import com.epam.brest.project.ps.model.Tariff;
import com.epam.brest.project.ps.stub.TariffStub;

import java.util.List;

/**
 * Tariffs Service Interface.
 */
public interface TariffsService {
    /**
     * Get all tariffs.
     *
     * @return tariffs stream.
     */
    List<Tariff> findAll();

    /**
     * Get all tariffs with count people.
     *
     * @return tariffs stream.
     */
    List<TariffStub> findAllStubs();

    /**
     * Get tariff by id.
     *
     * @param tariffId for getting.
     * @return tariff by tariffId.
     */
    Tariff findById(final Integer tariffId);

    /**
     * Add new tariff.
     *
     * @param tariff new tariff.
     */
    void add(final Tariff tariff);

    /**
     * Edit tariff in base.
     *
     * @param tariff for editing.
     */
    void update(final Tariff tariff);

    /**
     * Delete tariff with specified id.
     *
     * @param tariffId tariff for delete.
     */
    void delete(final Integer tariffId);
}
