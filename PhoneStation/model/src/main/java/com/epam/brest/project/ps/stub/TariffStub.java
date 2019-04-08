package com.epam.brest.project.ps.stub;

/**
 * Model for tariffs stub.
 */
public class TariffStub {

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
     * Count clients for tariff.
     */
    private Integer tariffCountClients;

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
    public final String getTariffName() {
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

    /**
     * Getter for tariffCountClients.
     *
     * @return tariffCountClients.
     */
    public final Integer getTariffCountClients() {
        return tariffCountClients;
    }

    /**
     * Setter for tariffCountClients.
     *
     * @param countClients for tariff.
     */
    public final void setTariffCountClients(final Integer countClients) {
        this.tariffCountClients = countClients;
    }

    @Override
    public final String toString() {
        return "Tariff{"
                + "tariffId=" + tariffId
                + ", tariffName='" + tariffName + "'"
                + ", tariffDeleted=" + tariffDeleted
                + ", tariffCountClients=" + tariffCountClients
                + "}";
    }
}
