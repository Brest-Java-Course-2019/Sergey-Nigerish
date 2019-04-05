package com.epam.brest.project.ps.model;

public class Tariff {
    private String tariffName;
    private Integer tariffId;
    private Boolean tariffDeleted;

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

    @Override
    public String toString() {
        return "Tariff{" +
                "tariffId=" + tariffId +
                ", tariffName='" + tariffName + "'" +
                ", tariffDeleted=" + tariffDeleted +
                "}";
    }
}
