package com.epam.brest.project.ps.dao;

import com.epam.brest.project.ps.model.Tariff;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class TariffsDaoJpaImpl implements TariffsDao {


    private static final Logger LOGGER = LoggerFactory.getLogger(TariffsDaoJpaImpl.class);

    private static final String SELECT_SQL = "SELECT tariffId, tariffName, tariffDeleted FROM tariffs WHERE tariffDeleted = false";
    private static final String FIND_BY_ID_SQL = "SELECT tariffId, tariffName, tariffDeleted FROM tariffs WHERE tariffId = :tariffId AND tariffDeleted = false";
    private static final String CHECK_COUNT_TARIFF = "SELECT COUNT(tariffId) FROM tariffs WHERE tariffName = :tariffName";
    private static final String INSERT = "INSERT INTO tariffs (tariffName) VALUES (:tariffName)";
    private static final String CHECK_DELETE_STATUS = "SELECT tariffDeleted FROM tariffs WHERE tariffId = :tariffId";

    private static final String TARIFF_ID = "tariffId";
    private static final String TARIFF_NAME = "tariffName";
    private static final String TARIFF_DELETED = "tariffDeleted";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public TariffsDaoJpaImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {

        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Stream<Tariff> findAll() {
        LOGGER.debug("findAll()");
        List<Tariff> tariff = namedParameterJdbcTemplate
                .query(SELECT_SQL, BeanPropertyRowMapper.newInstance(Tariff.class));
        return tariff.stream();
    }

    @Override
    public Stream<Tariff> countUsers() {
        return null;
    }

    @Override
    public Optional<Tariff> findById(final Integer id) {
        LOGGER.debug("findById({})", id);
        Tariff tariff = namedParameterJdbcTemplate.queryForObject(
                FIND_BY_ID_SQL,
                new MapSqlParameterSource(TARIFF_ID, id),
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
                CHECK_COUNT_TARIFF,
                new MapSqlParameterSource(TARIFF_NAME, tariffName),
                Integer.class) == 0;
    }

    private Optional<Tariff> insertTariff(Tariff tariff) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(TARIFF_NAME, tariff.getTariffName());
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        int result = namedParameterJdbcTemplate.update(INSERT, mapSqlParameterSource, generatedKeyHolder);
        LOGGER.debug("insertTariff {count rows = {}, id = {}}", result, generatedKeyHolder.getKeys().get(TARIFF_ID));
        tariff.setTariffId((Integer) generatedKeyHolder.getKeys().get(TARIFF_ID));
        return Optional.of(tariff);
    }

    @Override
    public void update(Tariff tariff) {

    }

    @Override
    public void delete(Integer id) {

    }
}
