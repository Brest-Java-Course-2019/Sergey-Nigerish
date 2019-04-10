package com.epam.brest.project.ps.web_app.consumers;

import com.epam.brest.project.ps.model.Client;
import com.epam.brest.project.ps.service.ClientsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.util.List;

public class ClientsRestConsumer implements ClientsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientsRestConsumer.class);

    private String url;

    private RestTemplate restTemplate;

    public ClientsRestConsumer(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    /**
     * Get all clients.
     *
     * @return clients stream.
     */
    @Override
    public List<Client> findAll() {
        LOGGER.debug("findAll({})", url);
        ResponseEntity responseEntity = restTemplate.getForEntity(url + "/all", List.class);
        return (List<Client>) responseEntity.getBody();
    }

    /**
     * Return all clients filtering by date and blocking.
     *
     * @param blocking  client status.
     * @param startDate first date.
     * @param endDate   last date.
     * @return clients stream filtering.
     */
    @Override
    public List<Client> findAllByFilter(String blocking, Date startDate, Date endDate) {
        LOGGER.debug("findAllByFilter({}, {}, {} )", blocking, startDate, endDate);
        ResponseEntity responseEntity = restTemplate.getForEntity(url + "/filter/" +
                blocking + "/" + startDate + "/" + endDate, List.class);
        return (List<Client>) responseEntity.getBody();
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
        ResponseEntity responseEntity = restTemplate.getForEntity(url + "/blockingFilter/" +
                blocking, List.class);
        return (List<Client>) responseEntity.getBody();
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
        ResponseEntity<Client> responseEntity = restTemplate.getForEntity(url + "/" + clientId, Client.class);
        return responseEntity.getBody();
    }

    /**
     * Add new client.
     *
     * @param client new client.
     */
    @Override
    public void add(Client client) {
        LOGGER.debug("add({})", client);
        restTemplate.postForEntity(url, client, Client.class);
    }

    /**
     * Edit client in base.
     *
     * @param client for editing.
     */
    @Override
    public void update(Client client) {
        LOGGER.debug("update({})", client);
        restTemplate.put(url, client, Client.class);
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
        restTemplate.getForEntity(url + "/updateTariff/" +
                clientId + "/" + tariffId, String.class);

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
        restTemplate.getForEntity(url + "/updateBlocking/" +
                clientId + "/" + lockingStatus, String.class);
    }

    /**
     * Delete client with specified id.
     *
     * @param clientId client for delete.
     */
    @Override
    public void delete(Integer clientId) {
        LOGGER.debug("delete({})", clientId);
        restTemplate.delete(url + "/" + clientId);
    }
}
