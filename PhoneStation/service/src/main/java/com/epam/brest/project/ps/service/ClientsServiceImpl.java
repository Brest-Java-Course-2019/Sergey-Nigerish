package com.epam.brest.project.ps.service;

import com.epam.brest.project.ps.dao.ClientsDao;
import com.epam.brest.project.ps.model.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
public class ClientsServiceImpl implements ClientsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientsServiceImpl.class);

    private ClientsDao dao;

    public ClientsServiceImpl(ClientsDao dao) {
        this.dao = dao;
    }

    /**
     * Get all clients.
     *
     * @return clients stream.
     */
    @Override
    public List<Client> findAll() {
        LOGGER.debug("findAll()");
        return dao.findAll().collect(Collectors.toList());
    }

    /**
     * Return all clients filtering by date.
     *
     * @param startDate first date.
     * @param endDate   last date.
     * @return clients stream filtering by date.
     */
    @Override
    public List<Client> findAllByDate(Date startDate, Date endDate) {
        LOGGER.debug("findAllByDate({}, {}) - input default", startDate, endDate);
        return dao.findAllByDate(startDate, endDate).collect(Collectors.toList());
    }

    /**
     * Return all clients filtering by blocking.
     *
     * @param blocking client status.
     * @return clients stream filtering by blocking.
     */
    @Override
    public List<Client> findAllByBlocking(Boolean blocking) {
        LOGGER.debug("findAllByBlocking({})", blocking);
        return dao.findAllByBlocking(blocking).collect(Collectors.toList());
    }

    /**
     * Get client by id.
     *
     * @param clientId for getting.
     * @return client by id.
     */
    @Override
    public Client findById(Integer clientId) {
        LOGGER.debug("findById({})", clientId);
        return dao.findById(clientId).get();
    }

    /**
     * Add new client.
     *
     * @param client new client.
     */
    @Override
    public void add(Client client) {
        LOGGER.debug("add({})", client);
        dao.add(client);
    }

    /**
     * Edit client in base.
     *
     * @param client for editing.
     */
    @Override
    public void update(Client client) {
        LOGGER.debug("update({})", client);
        dao.update(client);
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
        dao.updateTariff(clientId, tariffId);
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
        dao.updateBlocking(clientId, lockingStatus);
    }

    /**
     * Delete client with specified id.
     *
     * @param clientId client for delete.
     */
    @Override
    public void delete(Integer clientId) {
        LOGGER.debug("delete({})", clientId);
        dao.delete(clientId);
    }
}
