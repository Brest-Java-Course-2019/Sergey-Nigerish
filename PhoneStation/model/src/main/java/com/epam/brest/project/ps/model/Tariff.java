package com.epam.brest.project.ps.model;

/**
 * Model for tariffs.
 */
public class Tariff {

    /**
     * Tariff name.
     */
    private String tariffName;

    /**
     * Tariff id.
     */
    private Integer tariffId;

    /**
     * Tariff delete status.
     */
    private Boolean tariffDeleted;

    /**
     * Getter for tariffId.
     *
     * @return tariffId.
     */
    public final Integer getTariffId() {
        return tariffId;
    }

    /**
     * Setter for tariffId.
     *
     * @param id tariff.
     */
    public final void setTariffId(final Integer id) {
        this.tariffId = id;
    }

    /**
     * Getter for tariffName.
     *
     * @return tariffName.
     */
    public String getTariffName() {
        return tariffName;
    }

    /**
     * Setter for tariffName.
     *
     * @param name tariff.
     */
    public final void setTariffName(final String name) {
        this.tariffName = name;
    }

    /**
     * Getter for tariffDeleted.
     *
     * @return tariffDeleted.
     */
    public final Boolean getTariffDeleted() {
        return tariffDeleted;
    }

    /**
     * Setter for tariffDeleted.
     *
     * @param status deleted.
     */
    public final void setTariffDeleted(final Boolean status) {
        this.tariffDeleted = status;
    }

    @Override
    public final String toString() {
        return "Tariff{"
                + "tariffId=" + tariffId
                + ", tariffName='" + tariffName + "'"
                + ", tariffDeleted=" + tariffDeleted
                + "}";
    }
}
