package com.epam.brest.project.ps.service;

import com.epam.brest.project.ps.dao.ClientsDao;
import com.epam.brest.project.ps.model.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service implementation for client.
 */
@Transactional
public class ClientsServiceImpl implements ClientsService {

    /**
     * Connects logger.
     */
    private static final Logger LOGGER =
            LoggerFactory.getLogger(ClientsServiceImpl.class);

    /**
     * Variable for DAO.
     */
    private ClientsDao clientsDao;

    /**
     * Service constructor.
     *
     * @param dao of client objects.
     */
    public ClientsServiceImpl(final ClientsDao dao) {
        this.clientsDao = dao;
    }

    /**
     * Get all clients.
     *
     * @return clients stream.
     */
    @Override
    public final List<Client> findAll() {
        LOGGER.debug("findAll()");
        return clientsDao.findAll().collect(Collectors.toList());
    }

    /**
     * Return all clients filtering by date and blocking.
     *
     * @param blocking client status.
     * @param startDate first date.
     * @param endDate   last date.
     * @return clients stream filtering by date.
     */
    @Override
    public final List<Client> findAllByFilter(final String blocking,
                                              final Date startDate,
                                              final Date endDate) {
        LOGGER.debug("findAllByDate({}, {},{})", blocking, startDate, endDate);
        return clientsDao.findAllByFilter(blocking,
                                    startDate,
                                    endDate).collect(Collectors.toList());
    }

    /**
     * Return all clients filtering by blocking.
     *
     * @param blocking client status.
     * @return clients stream filtering by blocking.
     */
    @Override
    public List<Client> findAllByBlocking(final Boolean blocking) {
        LOGGER.debug("findAllByBlocking({})", blocking);
        return clientsDao.
                findAllByBlocking(blocking).
                collect(Collectors.toList());
    }

    /**
     * Get client by id.
     *
     * @param clientId for getting.
     * @return client by id.
     */
    @Override
    public final Client findById(final Integer clientId) {
        LOGGER.debug("findById({})", clientId);
        return clientsDao.findById(clientId).get();
    }

    /**
     * Add new client.
     *
     * @param client new client.
     */
    @Override
    public final void add(final Client client) {
        LOGGER.debug("add({})", client);
        clientsDao.add(client);
    }

    /**
     * Edit client in base.
     *
     * @param client for editing.
     */
    @Override
    public final void update(final Client client) {
        LOGGER.debug("update({})", client);
        clientsDao.update(client);
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
        clientsDao.updateTariff(clientId, tariffId);
    }

    /**
     * Change locking status for client.
     *
     * @param clientId      for editing.
     * @param lockingStatus new locking status.
     */
    @Override
    public void updateBlocking(final Integer clientId,
                                     final Boolean lockingStatus) {
        LOGGER.debug("updateBlocking({}, {})", clientId, lockingStatus);
        clientsDao.updateBlocking(clientId, lockingStatus);
    }

    /**
     * Delete client with specified id.
     *
     * @param clientId client for delete.
     */
    @Override
    public final void delete(final Integer clientId) {
        LOGGER.debug("delete({})", clientId);
        clientsDao.delete(clientId);
    }
}
