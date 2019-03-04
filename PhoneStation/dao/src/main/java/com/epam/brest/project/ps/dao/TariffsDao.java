package com.epam.brest.project.ps.dao;

import com.epam.brest.project.ps.model.Tariff;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Tariff DAO Interface.
 */
public interface TariffsDao {
    /**
     * Get tariffs.
     *
     * @return tariffs stream.
     */
    Stream<Tariff> findAll();

    /**
     * Get tariffs with the number of users.
     *
     * @param id tariff for counting.
     * @return count users.
     */
    Integer countUsers(final Integer id);

    /**
     * Get tariffs by id.
     *
     * @param id for getting.
     * @return tariff by id.
     */
    Optional<Tariff> findById(final Integer id);

    /**
     * Add new tariff.
     *
     * @param tariff new tariff.
     * @return tariff id.
     */
    Optional<Tariff> add(final Tariff tariff);

    /**
     * Edit tariff.
     *
     * @param tariff for editing.
     */
    void update(final Tariff tariff);

    /**
     * Delete tariff with specified id.
     *
     * @param id tariff for delete.
     */
    void delete(final Integer id);
}
