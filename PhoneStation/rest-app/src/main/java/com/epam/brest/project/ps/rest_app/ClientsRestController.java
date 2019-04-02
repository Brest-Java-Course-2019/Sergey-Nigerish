package com.epam.brest.project.ps.rest_app;

import com.epam.brest.project.ps.model.Client;
import com.epam.brest.project.ps.service.ClientsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/clients")
public class ClientsRestController implements ClientsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientsRestController.class);

    @Autowired
    private ClientsService clientsService;

    /**
     * Get all clients.
     *
     * @return clients stream.
     */
    @Override
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Client> findAll() {
        LOGGER.debug("findAll()");
        return clientsService.findAll();
    }

    /**
     * Return all clients filtering by date and blocking.
     *
     * @param blocking client status.
     * @param startDate first date.
     * @param endDate   last date.
     * @return clients stream filtering.
     */
    @Override
    @RequestMapping(value = "/filter/{blocking}/{startDate}/{endDate}", method = RequestMethod.GET)
    public List<Client> findAllByFilter(@PathVariable Boolean blocking,
                                        @PathVariable Date startDate,
                                        @PathVariable Date endDate) {
        LOGGER.debug("findAllByFilter({}, {}, {} )", blocking, startDate, endDate);
        return clientsService.findAllByFilter(blocking, startDate, endDate);
    }

    /**
     * Return all clients filtering by blocking.
     *
     * @param blocking client status.
     * @return clients stream filtering by blocking.
     */
    @Override
    @RequestMapping(value = "/blockingFilter/{blocking}", method = RequestMethod.GET)
    public List<Client> findAllByBlocking(@PathVariable Boolean blocking) {
        LOGGER.debug("FindAllByBlocking({})", blocking);
        return clientsService.findAllByBlocking(blocking);
    }

    /**
     * Get client by id.
     *
     * @param clientId for getting.
     * @return client by id.
     */
    @Override
    @RequestMapping(value = "/{clientId}", method = RequestMethod.GET)
    public Client findById(@PathVariable Integer clientId) {
        LOGGER.debug("findById({})", clientId);
        return clientsService.findById(clientId);
    }

    /**
     * Add new client.
     *
     * @param client new client.
     */
    @Override
    @RequestMapping(method = RequestMethod.POST)
    public void add(@RequestBody Client client) {
        LOGGER.debug("add({})", client);
        clientsService.add(client);
    }

    /**
     * Edit client in base.
     *
     * @param client for editing.
     */
    @Override
    @RequestMapping(method = RequestMethod.PUT)
    public void update(@RequestBody Client client) {
        LOGGER.debug("update({})", client);
        clientsService.update(client);
    }

    /**
     * Change tariff for client.
     *
     * @param clientId for editing.
     * @param tariffId new tariff id for client.
     */
    @Override
    @RequestMapping(value = "/updateTariff/{clientId}/{tariffId}", method = RequestMethod.GET)
    public void updateTariff(@PathVariable Integer clientId,
                             @PathVariable Integer tariffId) {
        LOGGER.debug("updateTariff({}, {})", clientId, tariffId);
        clientsService.updateTariff(clientId, tariffId);
    }

    /**
     * Change locking status for client.
     *
     * @param clientId      for editing.
     * @param lockingStatus new locking status.
     */
    @Override
    @RequestMapping(value = "/updateBlocking/{clientId}/{lockingStatus}", method = RequestMethod.GET)
    public void updateBlocking(@PathVariable Integer clientId,
                               @PathVariable Boolean lockingStatus) {
        LOGGER.debug("updateBlocking({}, {})", clientId, lockingStatus);
        clientsService.updateBlocking(clientId, lockingStatus);
    }

    /**
     * Delete client with specified id.
     *
     * @param clientId client for delete.
     */
    @Override
    @RequestMapping(value = "/{clientId}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Integer clientId) {
        LOGGER.debug("delete({})", clientId);
        clientsService.delete(clientId);
    }
}
