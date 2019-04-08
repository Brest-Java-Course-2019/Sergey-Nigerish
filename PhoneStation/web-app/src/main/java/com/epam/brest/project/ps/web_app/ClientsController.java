package com.epam.brest.project.ps.web_app;


import com.epam.brest.project.ps.model.Client;
import com.epam.brest.project.ps.model.Filter;
import com.epam.brest.project.ps.service.ClientsService;
import com.epam.brest.project.ps.service.TariffsService;
import com.epam.brest.project.ps.web_app.validators.ClientValidator;
import com.epam.brest.project.ps.web_app.validators.FilterValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;

/**
 * Clients controller.
 */
@Controller
public class ClientsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientsController.class);

    @Autowired
    private ClientsService clientsService;

    @Autowired
    private TariffsService tariffsService;

    @Autowired
    private ClientValidator clientValidator;

    @Autowired
    private FilterValidator filterValidator;

    /**
     * Goto clients list page.
     *
     * @return view name
     */
    @GetMapping(value = "/")
    public final String clientsList(Model model) {

        LOGGER.debug("findAll()");
        model.addAttribute("clients", clientsService.findAll());
        model.addAttribute("tariffs", tariffsService.findAll());
        return "clients.html";
    }

    /**
     * Return all clients filtering by date and blocking.
     *
     * @param filter params.
     * @return clients stream filtering.
     */
    @PostMapping(value = "/filter")
    public final String filteringClients(@Valid Filter filter,
//                                         BindingResult result,
                                         Model model) {
        LOGGER.debug("filteringClients({})", filter);
//        filterValidator.validate(filter, result);
//        if (result.hasErrors()) {
//            model.addAttribute("clients", clientsService.findAll());
//        } else {
//            model.addAttribute("clients", clientsService.findAllByFilter(filter.getBlocking(),
//                                                                            filter.getStartDate(),
//                                                                            filter.getEndDate()));
            model.addAttribute("clients", clientsService.findAllByBlocking(filter.getBlocking()));
//        }
        return "clients";
    }

    /**
     * Return all clients filtering by blocking.
     *
     * @param blocking client status.
     * @return clients stream filtering by blocking.
     */
    @GetMapping(value = "/clients/{blocking}")
    public final String filteringClientsByBlocking(@PathVariable Boolean blocking,
                                         Model model) {
        LOGGER.debug("filteringClientsByBlocking({})", blocking);
        model.addAttribute("clients", clientsService.findAllByBlocking(blocking));
        return "clients";
    }

    /**
     * Goto edit client page.
     *
     * @return view name
     */
    @GetMapping(value = "/client/{clientId}")
    public final String gotoEditClientPage(@PathVariable Integer clientId, Model model) {

        LOGGER.debug("gotoEditClientPage({})", clientId);
        Client client = clientsService.findById(clientId);
        model.addAttribute("client", client);
        model.addAttribute("tariffs", tariffsService.findAll());
        return "client";
    }

    /**
     * Update client into persistence storage.
     *
     * @return view name
     */
    @PostMapping(value = "/client")
    public String updateClient(@Valid Client client, BindingResult result) {

        LOGGER.debug("updateClient({})", client);
        clientValidator.validate(client, result);
        if (result.hasErrors()) {
            return "client";
        } else {
            this.clientsService.update(client);
            return "redirect:/";
        }
    }

    /**
     * Goto add client page.
     *
     * @return view name
     */
    @GetMapping(value = "/client")
    public final String gotoAddClientPage(Model model) {

        LOGGER.debug("gotoAddClientPage()");
        Client client = new Client();
        model.addAttribute("isNew", true);
        model.addAttribute("client", client);
        model.addAttribute("tariffs", tariffsService.findAll());
        return "client";
    }

    /**
     * Persist new client into persistence storage.
     *
     * @param client new client with filled data.
     * @param result binding result.
     * @return view name
     */
    @PostMapping(value = "/clientAdd")
    public String addClient(@Valid Client client, BindingResult result) {

        LOGGER.debug("addClient({})", client);
        clientValidator.validate(client, result);
        if (result.hasErrors()) {
            return "client";
        } else {
            this.clientsService.add(client);
            return "redirect:/";
        }
    }

    /**
     * Persist new tariff for client into persistence storage.
     *
     * @param clientId for editing.
     * @param tariffId new tariff id for client.
     */
    @GetMapping(value = "/updateTariff/{clientId}/{tariffId}")
    public void changeTariff(@PathVariable Integer clientId,
                             @PathVariable Integer tariffId) {
        LOGGER.debug("changeTariff({}, {})", clientId, tariffId);
        clientsService.updateTariff(clientId, tariffId);
    }

    /**
     * Persist new locking status for client into persistence storage.
     *
     * @param clientId      for editing.
     * @param lockingStatus new locking status.
     */
    @GetMapping(value = "/updateBlocking/{clientId}/{lockingStatus}")
    public void updateBlockingStatus(@PathVariable Integer clientId,
                                     @PathVariable Boolean lockingStatus) {
        LOGGER.debug("updateBlockingStatus({}, {})", clientId, lockingStatus);
        clientsService.updateBlocking(clientId, lockingStatus);
    }

    /**
     * Delete client from persistence storage.
     *
     * @param clientId for deleting.
     */
    @GetMapping(value = "/delete/{clientId}")
    public void deleteClientById(@PathVariable Integer clientId) {
        LOGGER.debug("deleteClientById({})", clientId);
        clientsService.delete(clientId);
    }
}
