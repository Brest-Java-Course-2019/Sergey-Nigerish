package com.epam.brest.project.ps.dao;

import com.epam.brest.project.ps.model.Tariff;
import com.epam.brest.project.ps.stub.TariffStub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class TariffsDaoJdbcImpl implements TariffsDao {


    private static final Logger LOGGER = LoggerFactory.getLogger(TariffsDaoJdbcImpl.class);

    private static final String SELECT_ALL_SQL = "SELECT tariffId, tariffName, tariffDeleted FROM tariffs WHERE tariffDeleted = false";
    private static final String FIND_BY_ID_SQL = "SELECT tariffId, tariffName, tariffDeleted FROM tariffs WHERE tariffId = :tariffId AND tariffDeleted = false";
    private static final String CHECK_COUNT_TARIFF_SQL = "SELECT COUNT(tariffId) FROM tariffs WHERE tariffName = :tariffName AND tariffDeleted = false";
    private static final String INSERT_SQL = "INSERT INTO tariffs (tariffName) VALUES (:tariffName)";
    private static final String UPDATE_SQL = "UPDATE tariffs SET tariffName = :tariffName, tariffDeleted = :tariffDeleted WHERE tariffId = :tariffId";
    private static final String SELECT_ALL_STUBS_SQL = "SELECT t.tariffId, t.tariffName, t.tariffDeleted, IFNULL (COUNT(c.clientContractId), 0) AS tariffCountClients FROM tariffs AS t " +
            "LEFT JOIN (SELECT clientContractId, client_to_idTariff FROM clients WHERE clientDeleted = false) AS c " +
            "ON (c.client_to_idTariff = t.tariffId) WHERE t.tariffDeleted = false GROUP BY t.tariffId";

    private static final String TARIFF_ID = "tariffId";
    private static final String TARIFF_NAME = "tariffName";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public TariffsDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {

        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Stream<Tariff> findAll() {
        LOGGER.debug("findAll()");
        List<Tariff> tariff = namedParameterJdbcTemplate
                .query(SELECT_ALL_SQL, BeanPropertyRowMapper.newInstance(Tariff.class));
        return tariff.stream();
    }

    @Override
    public Stream<TariffStub> findAllStubs() {
        LOGGER.debug("findAllStubs()");
        List<TariffStub> tariff = namedParameterJdbcTemplate
                .query(SELECT_ALL_STUBS_SQL, BeanPropertyRowMapper.newInstance(TariffStub.class));
        return tariff.stream();
    }

    @Override
    public Optional<Tariff> findById(final Integer tariffId) {
        LOGGER.debug("findById({})", tariffId);
        Tariff tariff = namedParameterJdbcTemplate.queryForObject(
                FIND_BY_ID_SQL,
                new MapSqlParameterSource(TARIFF_ID, tariffId),
                BeanPropertyRowMapper.newInstance(Tariff.class));
        return Optional.ofNullable(tariff);
    }

    @Override
    public Optional<Tariff> add(final Tariff tariff) {
        LOGGER.debug("add({})", tariff);
        return Optional.of(tariff)
                .filter(t -> tariffInBase(t.getTariffName()))
                .map(this::insertTariff)
                .orElseThrow(() -> new IllegalArgumentException("Tariff with the same name already exists in DB."));
    }

    private Boolean tariffInBase(final String tariffName) {
        LOGGER.debug("tariffInBase({})", tariffName);
        return namedParameterJdbcTemplate.queryForObject(
                CHECK_COUNT_TARIFF_SQL,
                new MapSqlParameterSource(TARIFF_NAME, tariffName),
                Integer.class) == 0;
    }

    private Optional<Tariff> insertTariff(final Tariff tariff) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(TARIFF_NAME, tariff.getTariffName());
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        int result = namedParameterJdbcTemplate.update(INSERT_SQL, mapSqlParameterSource, generatedKeyHolder);
        LOGGER.debug("insertTariff {count rows = {}, id = {}}", result, generatedKeyHolder.getKeys().get(TARIFF_ID));
        tariff.setTariffId((Integer) generatedKeyHolder.getKeys().get(TARIFF_ID));
        return Optional.of(tariff);
    }

    @Override
    public void update(final Tariff tariff) {
        LOGGER.debug("update({})", tariff);
        if (tariff.getTariffDeleted() == null) {
            tariff.setTariffDeleted(false);
        }
        Optional.of(namedParameterJdbcTemplate.update(UPDATE_SQL, new BeanPropertySqlParameterSource(tariff)))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to update tariff in DB"));
    }

    private boolean successfullyUpdated(final int numRowsUpdated) {
        return numRowsUpdated == 1;
    }

    @Override
    public void delete(final Integer tariffId) {
        LOGGER.debug("delete({})", tariffId);
        Tariff tariff = findById(tariffId).get();
        tariff.setTariffDeleted(true);
        update(tariff);
    }
}
