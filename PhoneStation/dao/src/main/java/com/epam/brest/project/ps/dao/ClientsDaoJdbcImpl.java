package com.epam.brest.project.ps.dao;

import com.epam.brest.project.ps.model.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Implementation for ClientsDao.
 */
public class ClientsDaoJdbcImpl implements ClientsDao {

    /**
     * Connects logger.
     */
    private static final Logger LOGGER =
            LoggerFactory.getLogger(ClientsDaoJdbcImpl.class);

    /**
     * SQL field clientId for query.
     */
    @Value("${client.fieldClientId}")
    private String fieldClientId;

    /**
     * SQL field clientBlocked for query.
     */
    @Value("${client.fieldClientBlocked}")
    private String fieldClientBlocked;

    /**
     * SQL field startDate for query.
     */
    @Value("${client.fieldStartDate}")
    private String fieldStartDate;

    /**
     * SQL field endDate for query.
     */
    @Value("${client.fieldEndDate}")
    private String fieldEndDate;

    /**
     * First data for filtering.
     */
    @Value("${client.dataFirstDate}")
    private String dataFirstDate;

    /**
     * SQL Select all clients String.
     */
    @Value("${client.selectAll}")
    private String selectAllSQL;

    /**
     * SQL Select clients by date String.
     */
    @Value("${client.selectByDate}")
    private String selectByDateSQL;

    /**
     * SQL Select all clients by blocking String.
     */
    @Value("${client.selectByBlocking}")
    private String selectByBlockingSQL;

    /**
     * SQL Select all clients by date and blocking String.
     */
    @Value("${client.selectByFilter}")
    private String selectByFilterSQL;

    /**
     * SQL Find by id client String.
     */
    @Value("${client.findById}")
    private String findByIdSQL;


    /**
     * SQL Insert new client String.
     */
    @Value("${client.insert}")
    private String insertSQL;

    /**
     * SQL Update client String.
     */
    @Value("${client.update}")
    private String updateSQL;

    /**
     * Message if failed to update client in DB.
     */
    @Value("${client.failUpdate}")
    private String failUpdate;

    /**
     * Property namedParameterJdbcTemplate.
     */
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * ClientsDaoJdbcImpl setter method for parameterJdbcTemplate property.
     *
     * @param parameterJdbcTemplate input value.
     */
    public ClientsDaoJdbcImpl(
            final NamedParameterJdbcTemplate parameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = parameterJdbcTemplate;
    }

    /**
     * Get all clients.
     *
     * @return clients stream.
     */
    @Override
    public final Stream<Client> findAll() {
        LOGGER.debug("findAll()");
        List<Client> client = namedParameterJdbcTemplate
                .query(selectAllSQL,
                        BeanPropertyRowMapper.newInstance(Client.class));
        return client.stream();
    }

    /**
     * Return all clients filtering by date and blocking.
     *
     * @param blocking  client status.
     * @param startDate first date.
     * @param endDate   last date.
     * @return clients stream filtering by date and blocking.
     */
    @Override
    public final Stream<Client> findAllByFilter(final Boolean blocking,
                                          Date startDate,
                                          Date endDate) {
        LOGGER.debug("findAllByFilter({}, {}, {}) - input default",
                blocking, startDate, endDate);
        if (startDate == null) {
            startDate = Date.valueOf(dataFirstDate);
        }
        if (endDate == null) {
            endDate = new Date(new java.util.Date().getTime());

        }
        LOGGER.debug("findAllByFilter({}, {}, {}) - input modified",
                blocking, startDate, endDate);
        MapSqlParameterSource namedParameters;
        namedParameters = new MapSqlParameterSource(fieldStartDate, startDate);
        namedParameters.addValue(fieldEndDate, endDate);
        String queryToSelect;
        queryToSelect = selectByDateSQL;
        if (blocking != null) {
            namedParameters.addValue(fieldClientBlocked, blocking);
            queryToSelect = selectByFilterSQL;
        }

        List<Client> client = namedParameterJdbcTemplate.query(
                queryToSelect,
                namedParameters,
                BeanPropertyRowMapper.newInstance(Client.class));
        return client.stream();
    }

    /**
     * Return all clients filtering by blocking.
     *
     * @param blocking client status.
     * @return clients stream filtering by blocking.
     */
    @Override
    public final Stream<Client> findAllByBlocking(final Boolean blocking) {
        LOGGER.debug("findAllByBlocking({})", blocking);
        List<Client> client = namedParameterJdbcTemplate.query(
                selectByBlockingSQL,
                new MapSqlParameterSource(fieldClientBlocked, blocking),
                BeanPropertyRowMapper.newInstance(Client.class));
        return client.stream();
    }

    /**
     * Get client by id.
     *
     * @param clientId for getting.
     * @return client by id.
     */
    @Override
    public final Optional<Client> findById(final Integer clientId) {
        LOGGER.debug("findById({})", clientId);
        Client client = namedParameterJdbcTemplate.queryForObject(
                findByIdSQL,
                new MapSqlParameterSource(fieldClientId, clientId),
                BeanPropertyRowMapper.newInstance(Client.class));
        return Optional.ofNullable(client);
    }

    /**
     * Add new client.
     *
     * @param client new client.
     * @return new client.
     */
    @Override
    public final Optional<Client> add(final Client client) {
        client.setClientContractDay_date(
                new Date(
                        new java.util.Date().getTime()));
        LOGGER.debug("add({})", client);
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        int result = namedParameterJdbcTemplate.update(insertSQL,
              new BeanPropertySqlParameterSource(client), generatedKeyHolder);
        LOGGER.debug("add client {count rows = {}, id = {}}",
                result, generatedKeyHolder.getKeys().get(fieldClientId));
        client.setClientContractId((Integer)
                generatedKeyHolder.getKeys().get(fieldClientId));
        return Optional.ofNullable(client);
    }

    /**
     * Edit client in base.
     *
     * @param client for editing.
     */
    @Override
    public final void update(final Client client) {
        if (client.getClientDeleted() == null) {
            client.setClientDeleted(false);
        }
        LOGGER.debug("update({})", client);
        Optional.of(namedParameterJdbcTemplate.update(updateSQL,
                new BeanPropertySqlParameterSource(client)))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new RuntimeException(failUpdate));
    }

    /**
     * Check status for update request.
     *
     * @param numRowsUpdated count updated rows.
     * @return true if update successful.
     */
    private boolean successfullyUpdated(final int numRowsUpdated) {
        return numRowsUpdated == 1;
    }

    /**
     * Change tariff for client.
     *
     * @param clientId for editing.
     * @param tariffId new tariff id for client.
     */
    @Override
    public final void updateTariff(final Integer clientId,
                                   final Integer tariffId) {
        LOGGER.debug("updateTariff({}, {})", clientId, tariffId);
        Client client = findById(clientId).get();
        client.setClient_to_idTariff(tariffId);
        update(client);
    }

    /**
     * Change locking status for client.
     *
     * @param clientId      for editing.
     * @param lockingStatus new locking status.
     */
    @Override
    public final void updateBlocking(final Integer clientId,
                                     final Boolean lockingStatus) {
        LOGGER.debug("updateBlocking({}, {})", clientId, lockingStatus);
        Client client = findById(clientId).get();
        client.setClientBlocked(lockingStatus);
        update(client);
    }

    /**
     * Delete client with specified id.
     *
     * @param clientId client for delete.
     */
    @Override
    public final void delete(final Integer clientId) {
        LOGGER.debug("delete({})", clientId);
        Client client = findById(clientId).get();
        client.setClientDeleted(true);
        update(client);
    }
}
