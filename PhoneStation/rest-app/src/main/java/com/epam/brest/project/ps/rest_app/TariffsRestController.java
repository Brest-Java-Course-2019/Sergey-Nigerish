package com.epam.brest.project.ps.rest_app;

import com.epam.brest.project.ps.model.Tariff;
import com.epam.brest.project.ps.service.TariffsService;
import com.epam.brest.project.ps.stub.TariffStub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/tariffs")
public class TariffsRestController implements TariffsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientsRestController.class);

    @Autowired
    private TariffsService tariffsService;

    /**
     * Get all tariffs.
     *
     * @return tariffs stream.
     */
    @Override
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Tariff> findAll() {
        LOGGER.debug("findAll()");
        return tariffsService.findAll();
    }

    /**
     * Get all tariffs with count people.
     *
     * @return tariffs stream.
     */
    @Override
    @RequestMapping(value = "/allStubs", method = RequestMethod.GET)
    public List<TariffStub> findAllStubs() {
        LOGGER.debug("findAllStubs()");
        return tariffsService.findAllStubs();
    }

    /**
     * Get tariff by id.
     *
     * @param tariffId for getting.
     * @return tariff by tariffId.
     */
    @Override
    @RequestMapping(value = "/{tariffId}", method = RequestMethod.GET)
    public Tariff findById(@PathVariable Integer tariffId) {
        LOGGER.debug("findById({})", tariffId);
        return tariffsService.findById(tariffId);
    }

    /**
     * Add new tariff.
     *
     * @param tariff new tariff.
     */
    @Override
    @RequestMapping(method = RequestMethod.POST)
    public void add(@RequestBody Tariff tariff) {
        LOGGER.debug("add({})", tariff);
        tariffsService.add(tariff);
    }

    /**
     * Edit tariff in base.
     *
     * @param tariff for editing.
     */
    @Override
    @RequestMapping(method = RequestMethod.PUT)
    public void update(@RequestBody Tariff tariff) {
        LOGGER.debug("update({})", tariff);
        tariffsService.update(tariff);
    }

    /**
     * Delete tariff with specified id.
     *
     * @param tariffId tariff for delete.
     */
    @Override
    @RequestMapping(value = "/{tariffId}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Integer tariffId) {
        LOGGER.debug("delete({})", tariffId);
        tariffsService.delete(tariffId);
    }
}
