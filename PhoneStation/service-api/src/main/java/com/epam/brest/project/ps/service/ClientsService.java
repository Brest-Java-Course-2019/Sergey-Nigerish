package com.epam.brest.project.ps.service;

import com.epam.brest.project.ps.model.Client;

import java.sql.Date;
import java.util.List;

/**
 * Clients Service Interface.
 */
public interface ClientsService {
    /**
     * Get all clients.
     *
     * @return clients stream.
     */
    List<Client> findAll();

    /**
     * Return all clients filtering by date.
     *
     * @param startDate first date.
     * @param endDate last date.
     * @return clients stream filtering by date.
     */
    List<Client> findAllByDate(final Date startDate, final Date endDate);

    /**
     * Return all clients filtering by blocking.
     *
     * @param blocking client status.
     * @return clients stream filtering by blocking.
     */
    List<Client> findAllByBlocking(final Boolean blocking);


    /**
     * Get client by id.
     *
     * @param clientId for getting.
     * @return client by id.
     */
    Client findById(final Integer clientId);

    /**
     * Add new client.
     *
     * @param client new client.
     */
    void add(final Client client);

    /**
     * Edit client in base.
     *
     * @param client for editing.
     */
    void update(final Client client);

    /**
     * Change tariff for client.
     *
     * @param clientId for editing.
     * @param tariffId new tariff id for client.
     */
    void updateTariff(final Integer clientId, final Integer tariffId);

    /**
     * Change locking status for client.
     *
     * @param clientId for editing.
     * @param lockingStatus new locking status.
     */
    void updateBlocking(final Integer clientId, final Boolean lockingStatus);

    /**
     * Delete client with specified id.
     *
     * @param clientId client for delete.
     */
    void delete(final Integer clientId);
}
