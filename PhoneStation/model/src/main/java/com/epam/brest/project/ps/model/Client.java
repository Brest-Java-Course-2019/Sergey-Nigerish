package com.epam.brest.project.ps.model;

import java.sql.Date;

public class Client {
    private Integer clientContractId;
    private Date clientContractDay_date;
    private String clientFIO;
    private String clientAddress;
    private Boolean clientBlocked;
    private Integer client_to_idTariff;
    private Boolean clientDeleted;

    public Integer getClientContractId() {
        return clientContractId;
    }

    public void setClientContractId(Integer clientContractId) {
        this.clientContractId = clientContractId;
    }

    public Date getClientContractDay_date() {
        return clientContractDay_date;
    }

    public void setClientContractDay_date(Date clientContractDay_date) {
        this.clientContractDay_date = clientContractDay_date;
    }

    public String getClientFIO() {
        return clientFIO;
    }

    public void setClientFIO(String clientFIO) {
        this.clientFIO = clientFIO;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public Boolean getClientBlocked() {
        return clientBlocked;
    }

    public void setClientBlocked(Boolean clientBlocked) {
        this.clientBlocked = clientBlocked;
    }

    public Integer getClient_to_idTariff() {
        return client_to_idTariff;
    }

    public void setClient_to_idTariff(Integer client_to_idTariff) {
        this.client_to_idTariff = client_to_idTariff;
    }

    public Boolean getClientDeleted() {
        return clientDeleted;
    }

    public void setClientDeleted(Boolean clientDeleted) {
        this.clientDeleted = clientDeleted;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientContractId=" + clientContractId +
                ", clientContractDay_date=" + clientContractDay_date +
                ", clientFIO='" + clientFIO + "'" +
                ", clientAddress='" + clientAddress + "'" +
                ", clientBlocked=" + clientBlocked +
                ", client_to_idTariff=" + client_to_idTariff +
                ", clientDeleted=" + clientDeleted +
                "}";
    }
}