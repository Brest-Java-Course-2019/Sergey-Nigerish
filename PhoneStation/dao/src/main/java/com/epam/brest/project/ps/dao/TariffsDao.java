package com.epam.brest.project.ps.dao;

import com.epam.brest.project.ps.model.Tariff;
import com.epam.brest.project.ps.stub.TariffStub;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Tariffs DAO Interface.
 */
public interface TariffsDao {
    /**
     * Get all tariffs.
     *
     * @return tariffs stream.
     */
    Stream<Tariff> findAll();

    /**
     * Get all tariffs with count people.
     *
     * @return tariffs stream.
     */
    Stream<TariffStub> findAllStubs();

    /**
     * Get tariff by id.
     *
     * @param tariffId for getting.
     * @return tariff by tariffId.
     */
    Optional<Tariff> findById(final Integer tariffId);

    /**
     * Add new tariff.
     *
     * @param tariff new tariff.
     * @return new tariff.
     */
    Optional<Tariff> add(final Tariff tariff);

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
