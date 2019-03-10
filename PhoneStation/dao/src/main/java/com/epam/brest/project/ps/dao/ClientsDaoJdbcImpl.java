package com.epam.brest.project.ps.dao;

import com.epam.brest.project.ps.model.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

public class ClientsDaoJdbcImpl implements ClientsDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientsDaoJdbcImpl.class);

    private static final String SELECT_ALL_SQL = "SELECT clientContractId, clientFIO, clientAddress, " +
            "clientContractDay_date, clientBlocked, client_to_idTariff, clientDeleted FROM clients " +
            "WHERE clientDeleted = false";
    private static final String SELECT_BY_DATE_SQL = "SELECT clientContractId, clientFIO, clientAddress, " +
            "clientContractDay_date, clientBlocked, client_to_idTariff, clientDeleted FROM clients " +
            "WHERE clientDeleted = false AND :startDate <= clientContractDay_date AND clientContractDay_date <= :endDate";
    private static final String SELECT_BY_BLOCKING_SQL = "SELECT clientContractId, clientFIO, clientAddress, " +
            "clientContractDay_date, clientBlocked, client_to_idTariff, clientDeleted FROM clients " +
            "WHERE clientDeleted = false AND clientBlocked = :clientBlocked";
    private static final String FIND_BY_ID_SQL = "SELECT clientContractId, clientFIO, clientAddress, " +
            "clientContractDay_date, clientBlocked, client_to_idTariff, clientDeleted FROM clients " +
            "WHERE clientContractId = :clientContractId AND clientDeleted = false";
    private static final String INSERT_SQL = "INSERT INTO clients (clientFIO, clientAddress, " +
            "clientContractDay_date, clientBlocked, client_to_idTariff) " +
            "VALUES (:clientFIO, :clientAddress, :clientContractDay_date, :clientBlocked, :client_to_idTariff)";
    private static final String UPDATE_SQL = "UPDATE clients SET clientFIO = :clientFIO, " +
            "clientAddress =:clientAddress, clientContractDay_date = :clientContractDay_date, " +
            "clientBlocked =:clientBlocked, client_to_idTariff = :client_to_idTariff, " +
            "clientDeleted = :clientDeleted WHERE  clientContractId = :clientContractId";


    private static final String CLIENT_ID = "clientContractId";
    private static final String CLIENT_BLOCKED = "clientBlocked";

    private static final String START_DATE = "startDate";
    private static final String END_DATE = "endDate";
    private static final String FIRST_DATE = "1970-01-01";

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
        LOGGER.debug("findAll()");
        List<Client> client = namedParameterJdbcTemplate
                .query(SELECT_ALL_SQL, BeanPropertyRowMapper.newInstance(Client.class));
        return client.stream();
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
        LOGGER.debug("findAllByDate({}, {}) - input default", startDate, endDate);
        if (startDate == null) {
            startDate = Date.valueOf(FIRST_DATE);
        }
        if (endDate == null) {
            endDate = new Date(new java.util.Date().getTime());

        }
        LOGGER.debug("findAllByDate({}, {}) - input modified", startDate, endDate);
        MapSqlParameterSource namedParameters = new MapSqlParameterSource(START_DATE, startDate);
        namedParameters.addValue(END_DATE, endDate);
        List<Client> client = namedParameterJdbcTemplate.query(
                SELECT_BY_DATE_SQL,
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
    public Stream<Client> findAllByBlocking(Boolean blocking) {
        LOGGER.debug("findAllByBlocking({})", blocking);
        List<Client> client = namedParameterJdbcTemplate.query(
                SELECT_BY_BLOCKING_SQL,
                new MapSqlParameterSource(CLIENT_BLOCKED, blocking),
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
    public Optional<Client> findById(Integer clientId) {
        LOGGER.debug("findById({})", clientId);
        Client client = namedParameterJdbcTemplate.queryForObject(
                FIND_BY_ID_SQL,
                new MapSqlParameterSource(CLIENT_ID, clientId),
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
    public Optional<Client> add(Client client) {
        client.setClientContractDay_date(new Date(new java.util.Date().getTime()));
        LOGGER.debug("add({})", client);
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        int result = namedParameterJdbcTemplate.update(INSERT_SQL, new BeanPropertySqlParameterSource(client), generatedKeyHolder);
        LOGGER.debug("add client {count rows = {}, id = {}}", result, generatedKeyHolder.getKeys().get(CLIENT_ID));
        client.setClientContractId((Integer) generatedKeyHolder.getKeys().get(CLIENT_ID));
        return Optional.of(client);
    }

    /**
     * Edit client in base.
     *
     * @param client for editing.
     */
    @Override
    public void update(Client client) {
        if (client.getClientDeleted() == null) {
            client.setClientDeleted(false);
        }
        LOGGER.debug("update({})", client);
        Optional.of(namedParameterJdbcTemplate.update(UPDATE_SQL, new BeanPropertySqlParameterSource(client)))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to update client in DB"));
    }

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
    public void updateTariff(Integer clientId, Integer tariffId) {
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
    public void updateBlocking(Integer clientId, Boolean lockingStatus) {
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
    public void delete(Integer clientId) {
        LOGGER.debug("delete({})", clientId);
        Client client = findById(clientId).get();
        client.setClientDeleted(true);
        update(client);
    }
}
