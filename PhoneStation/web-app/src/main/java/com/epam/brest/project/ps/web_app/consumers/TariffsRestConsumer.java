package com.epam.brest.project.ps.web_app.consumers;

import com.epam.brest.project.ps.model.Tariff;
import com.epam.brest.project.ps.service.TariffsService;
import com.epam.brest.project.ps.stub.TariffStub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class TariffsRestConsumer implements TariffsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TariffsRestConsumer.class);

    private String url;

    private RestTemplate restTemplate;

    public TariffsRestConsumer(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    /**
     * Get all tariffs.
     *
     * @return tariffs stream.
     */
    @Override
    public List<Tariff> findAll() {
        LOGGER.debug("findAll()");
        ResponseEntity responseEntity = restTemplate.getForEntity(url + "/all", List.class);
        return (List<Tariff>) responseEntity.getBody();
    }

    /**
     * Get all tariffs with count people.
     *
     * @return tariffs stream.
     */
    @Override
    public List<TariffStub> findAllStubs() {
        LOGGER.debug("findAllStubs()");
        ResponseEntity responseEntity = restTemplate.getForEntity(url + "/allStubs", List.class);
        return (List<TariffStub>) responseEntity.getBody();
    }

    /**
     * Get tariff by id.
     *
     * @param tariffId for getting.
     * @return tariff by tariffId.
     */
    @Override
    public Tariff findById(Integer tariffId) {
        LOGGER.debug("findById({})", tariffId);
        ResponseEntity<Tariff> responseEntity = restTemplate.getForEntity(url + "/" + tariffId, Tariff.class);
        return responseEntity.getBody();
    }

    /**
     * Add new tariff.
     *
     * @param tariff new tariff.
     */
    @Override
    public void add(Tariff tariff) {
        LOGGER.debug("add({})", tariff);
        restTemplate.postForEntity(url, tariff, Tariff.class);
    }

    /**
     * Edit tariff in base.
     *
     * @param tariff for editing.
     */
    @Override
    public void update(Tariff tariff) {
        LOGGER.debug("update({})", tariff);
        restTemplate.put(url, tariff, Tariff.class);
    }

    /**
     * Delete tariff with specified id.
     *
     * @param tariffId tariff for delete.
     */
    @Override
    public void delete(Integer tariffId) {
        LOGGER.debug("delete({})", tariffId);
        restTemplate.delete(url + "/" + tariffId);
    }
}
