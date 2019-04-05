package com.epam.brest.project.ps.stub;

public class TariffStub {
    private String tariffName;
    private Integer tariffId;
    private Boolean tariffDeleted;
    private Integer tariffCountClients;

    public Integer getTariffId() {
        return tariffId;
    }

    public void setTariffId(Integer tariffsId) {
        this.tariffId = tariffsId;
    }

    public String getTariffName() {
        return tariffName;
    }

    public void setTariffName(String tariffName) {
        this.tariffName = tariffName;
    }

    public Boolean getTariffDeleted() {
        return tariffDeleted;
    }

    public void setTariffDeleted(Boolean tariffDeleted) {
        this.tariffDeleted = tariffDeleted;
    }

    public Integer getTariffCountClients() {
        return tariffCountClients;
    }

    public void setTariffCountClients(Integer tariffCountClients) {
        this.tariffCountClients = tariffCountClients;
    }

    @Override
    public String toString() {
        return "Tariff{" +
                "tariffId=" + tariffId +
                ", tariffName='" + tariffName + "'" +
                ", tariffDeleted=" + tariffDeleted +
                ", tariffCountClients=" + tariffCountClients +
                "}";
    }
}
