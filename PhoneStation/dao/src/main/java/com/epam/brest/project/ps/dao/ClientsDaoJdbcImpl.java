package com.epam.brest.project.ps.dao;

import com.epam.brest.project.ps.model.Client;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.Date;
import java.util.Optional;
import java.util.stream.Stream;

public class ClientsDaoJdbcImpl implements ClientsDao {

    final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ClientsDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    /**
     * Get all clients.
     *
     * @return clients stream.
     */
    @Override
    public Stream<Client> findAll() {
        return null;
    }

    /**
     * Return all clients filtering by date.
     *
     * @param startDate first date.
     * @param endDate   last date.
     * @return clients stream filtering by date.
     */
    @Override
    public Stream<Client> findAllByDate(Date startDate, Date endDate) {
        return null;
    }

    /**
     * Return all clients filtering by blocking.
     *
     * @param blocking client status.
     * @return clients stream filtering by blocking.
     */
    @Override
    public Stream<Client> findAllByBlocking(Boolean blocking) {
        return null;
    }

    /**
     * Get client by id.
     *
     * @param id for getting.
     * @return client by id.
     */
    @Override
    public Optional<Client> findById(Integer id) {
        return Optional.empty();
    }

    /**
     * Add new client.
     *
     * @param client new client.
     * @return new client.
     */
    @Override
    public Optional<Client> add(Client client) {
        return Optional.empty();
    }

    /**
     * Edit client in base.
     *
     * @param client for editing.
     */
    @Override
    public void update(Client client) {

    }

    /**
     * Change tariff for client.
     *
     * @param clientId for editing.
     * @param tariffId new tariff id for client.
     */
    @Override
    public void updateTariff(Integer clientId, Integer tariffId) {

    }

    /**
     * Change locking status for client.
     *
     * @param clientId      for editing.
     * @param lockingStatus new locking status.
     */
    @Override
    public void updateBlocking(Integer clientId, Boolean lockingStatus) {

    }

    /**
     * Delete client with specified id.
     *
     * @param id client for delete.
     */
    @Override
    public void delete(Integer id) {

    }
}
