package com.epam.brest.project.ps.model;

import java.sql.Date;

/**
 * Model for clients.
 */
public class Client {

    /**
     * Client id and contract №.
     */
    private Integer clientContractId;

    /**
     * Date when client contracted.
     */
    private Date clientContractDay_date;

    /**
     * Client full name.
     */
    private String clientFIO;

    /**
     * Client address.
     */
    private String clientAddress;

    /**
     * Client blocking status.
     */
    private Boolean clientBlocked;

    /**
     * Id tariff which is used.
     */
    private Integer client_to_idTariff;

    /**
     * Client delete status.
     */
    private Boolean clientDeleted;

    /**
     * Getter for clientContractId.
     *
     * @return clientContractId.
     */
    public final Integer getClientContractId() {
        return clientContractId;
    }

    /**
     * Setter for clientContractId.
     *
     * @param contractId client.
     */
    public final void setClientContractId(final Integer contractId) {
        this.clientContractId = contractId;
    }

    /**
     * Getter for clientContractDay_date.
     *
     * @return clientContractDay_date.
     */
    public final Date getClientContractDay_date() {
        return clientContractDay_date;
    }

    /**
     * Setter for clientContractDay_date.
     *
     * @param contractDay_date contract.
     */
    public final void setClientContractDay_date(final Date contractDay_date) {
        this.clientContractDay_date = contractDay_date;
    }

    /**
     * Getter clientFIO.
     *
     * @return clientFIO.
     */
    public String getClientFIO() {
        return clientFIO;
    }

    /**
     * Setter for clientFIO.
     *
     * @param fullName client.
     */
    public final void setClientFIO(final String fullName) {
        this.clientFIO = fullName;
    }

    /**
     * Getter for clientAddress.
     *
     * @return clientAddress.
     */
    public String getClientAddress() {
        return clientAddress;
    }

    /**
     * Setter for clientAddress.
     *
     * @param address client.
     */
    public final void setClientAddress(final String address) {
        this.clientAddress = address;
    }

    /**
     * Getter for clientBlocked.
     *
     * @return clientBlocked.
     */
    public final Boolean getClientBlocked() {
        return clientBlocked;
    }

    /**
     * Setter for clientBlocked.
     *
     * @param status blocking.
     */
    public final void setClientBlocked(final Boolean status) {
        this.clientBlocked = status;
    }

    /**
     * Getter for client_to_idTariff.
     *
     * @return client_to_idTariff.
     */
    public final Integer getClient_to_idTariff() {
        return client_to_idTariff;
    }

    /**
     * Setter for client_to_idTariff.
     *
     * @param idTariff for client.
     */
    public final void setClient_to_idTariff(final Integer idTariff) {
        this.client_to_idTariff = idTariff;
    }

    /**
     * Getter for clientDeleted.
     *
     * @return clientDeleted.
     */
    public final Boolean getClientDeleted() {
        return clientDeleted;
    }

    /**
     * Setter for clientDeleted.
     *
     * @param status deleted.
     */
    public final void setClientDeleted(final Boolean status) {
        this.clientDeleted = status;
    }

    @Override
    public final String toString() {
        return "Client{"
                + "clientContractId=" + clientContractId
                + ", clientContractDay_date=" + clientContractDay_date
                + ", clientFIO='" + clientFIO + "'"
                + ", clientAddress='" + clientAddress + "'"
                + ", clientBlocked=" + clientBlocked
                + ", client_to_idTariff=" + client_to_idTariff
                + ", clientDeleted=" + clientDeleted
                + "}";
    }
}
