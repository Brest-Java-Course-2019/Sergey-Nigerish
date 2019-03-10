package com.epam.brest.project.ps.service;

import com.epam.brest.project.ps.model.Tariff;

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
     * Get tariffs with the number of users.
     *
     * @param tariffId tariff for counting.
     * @return count users.
     */
    Integer countUsers(final Integer tariffId);

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
