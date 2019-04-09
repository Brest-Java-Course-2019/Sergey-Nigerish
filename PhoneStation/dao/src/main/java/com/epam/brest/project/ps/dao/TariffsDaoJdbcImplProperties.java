package com.epam.brest.project.ps.dao;

import com.epam.brest.project.ps.model.Tariff;
import com.epam.brest.project.ps.stub.TariffStub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Implementation for TariffsDao.
 */
public class TariffsDaoJdbcImplProperties implements TariffsDao {

    /**
     * Connects logger.
     */
    private static final Logger LOGGER =
            LoggerFactory.getLogger(TariffsDaoJdbcImplProperties.class);

    /**
     * Property namedParameterJdbcTemplate.
     */
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * SQL field tariffId for query.
     */
    @Value("${tariff.fieldTariffId}")
    private String fieldTariffId;

    /**
     * SQL field tariffName for query.
     */
    @Value("${tariff.fieldTariffName}")
    private String fieldTariffName;

    /**
     * SQL Select all tariffs String.
     */
    @Value("${tariff.selectAll}")
    private String selectAllSQL;

    /**
     * SQL Find by id tariff String.
     */
    @Value("${tariff.findById}")
    private String findByIdSQL;

    /**
     * SQL Check count clients fo tariff String.
     */
    @Value("${tariff.checkCount}")
    private String checkCountSQL;

    /**
     * SQL Insert new tariff String.
     */
    @Value("${tariff.insert}")
    private String insertSQL;

    /**
     * SQL Update tariff String.
     */
    @Value("${tariff.update}")
    private String updateSQL;

    /**
     * SQL Select all tariffs with count clients String.
     */
    @Value("${tariff.selectAllStubs}")
    private String selectAllStubsSQL;

    /**
     * Message if tariff exists in DB.
     */
    @Value("${tariff.alreadyExists}")
    private String alreadyExists;

    /**
     * Message if failed to update tariff in DB.
     */
    @Value("${tariff.failUpdate}")
    private String failUpdate;

    /**
     * TariffsDaoJdbcImpl setter method for parameterJdbcTemplate property.
     *
     * @param parameterJdbcTemplate input value.
     */
    public TariffsDaoJdbcImplProperties(
            final NamedParameterJdbcTemplate parameterJdbcTemplate) {

        this.namedParameterJdbcTemplate = parameterJdbcTemplate;
    }

    /**
     * Get all tariffs.
     *
     * @return tariffs stream.
     */
    @Override
    public final Stream<Tariff> findAll() {
        LOGGER.debug("findAll()");
        List<Tariff> tariff = namedParameterJdbcTemplate
                .query(selectAllSQL,
                        BeanPropertyRowMapper.newInstance(Tariff.class));
        return tariff.stream();
    }

    /**
     * Get all tariffs with count people.
     *
     * @return tariffs stream.
     */
    @Override
    public final Stream<TariffStub> findAllStubs() {
        LOGGER.debug("findAllStubs()");
        List<TariffStub> tariff = namedParameterJdbcTemplate
                .query(selectAllStubsSQL,
                        BeanPropertyRowMapper.newInstance(TariffStub.class));
        return tariff.stream();
    }

    /**
     * Get tariff by id.
     *
     * @param tariffId for getting.
     * @return tariff by tariffId.
     */
    @Override
    public final Optional<Tariff> findById(final Integer tariffId) {
        LOGGER.debug("findById({})", tariffId);
        Tariff tariff = namedParameterJdbcTemplate.queryForObject(
                findByIdSQL,
                new MapSqlParameterSource(fieldTariffId, tariffId),
                BeanPropertyRowMapper.newInstance(Tariff.class));
        return Optional.ofNullable(tariff);
    }

    /**
     * Add new tariff.
     *
     * @param tariff new tariff.
     * @return new tariff.
     */
    @Override
    public final Optional<Tariff> add(final Tariff tariff) {
        LOGGER.debug("add({})", tariff);
        return Optional.of(tariff)
            .filter(t -> tariffInBase(t.getTariffName()))
            .map(this::insertTariff)
            .orElseThrow(() -> new IllegalArgumentException(alreadyExists));
    }

    /**
     * Checks if there is a tariff in database.
     *
     * @param tariffName for checking.
     * @return return true, if tariff no in database.
     */
    private Boolean tariffInBase(final String tariffName) {
        LOGGER.debug("tariffInBase({})", tariffName);
        Integer tariffCount = namedParameterJdbcTemplate.queryForObject(
                checkCountSQL,
                new MapSqlParameterSource(fieldTariffName, tariffName),
                Integer.class);
        return tariffCount != null && tariffCount == 0;
    }

    /**
     * Persist new tariff in DB.
     *
     * @param tariff for persist.
     * @return updated tariff.
     */
    private Optional<Tariff> insertTariff(final Tariff tariff) {
        MapSqlParameterSource mapSqlParameterSource =
                                                new MapSqlParameterSource();
        mapSqlParameterSource.addValue(fieldTariffName, tariff.getTariffName());
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        int result = namedParameterJdbcTemplate.update(insertSQL,
                                                     mapSqlParameterSource,
                                                     generatedKeyHolder);
        LOGGER.debug("insertTariff {count rows = {}, id = {}}",
                                     result,
                                     generatedKeyHolder
                                            .getKeys()
                                            .get(fieldTariffId));
        tariff.setTariffId((Integer) generatedKeyHolder
                                            .getKeys()
                                            .get(fieldTariffId));
        return Optional.of(tariff);
    }

    /**
     * Edit tariff in base.
     *
     * @param tariff for editing.
     */
    @Override
    public final void update(final Tariff tariff) {
        LOGGER.debug("update({})", tariff);
        if (tariff.getTariffDeleted() == null) {
            tariff.setTariffDeleted(false);
        }
        Optional.of(namedParameterJdbcTemplate.update(updateSQL,
                new BeanPropertySqlParameterSource(tariff)))
                    .filter(this::successfullyUpdated)
                    .orElseThrow(() ->
                        new RuntimeException(failUpdate));
    }

    /**
     * Check status for update request.
     *
     * @param numRowsUpdated count updated rows.
     * @return true if update successful.
     */
    private boolean successfullyUpdated(final int numRowsUpdated) {
        return numRowsUpdated == 1;
    }

    /**
     * Delete tariff with specified id.
     *
     * @param tariffId tariff for delete.
     */
    @Override
    public final void delete(final Integer tariffId) {
        LOGGER.debug("delete({})", tariffId);
        Tariff tariff = findById(tariffId).get();
        tariff.setTariffDeleted(true);
        update(tariff);
    }
}
