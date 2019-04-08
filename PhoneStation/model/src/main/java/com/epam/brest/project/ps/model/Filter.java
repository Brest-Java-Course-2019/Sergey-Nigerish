package com.epam.brest.project.ps.model;

import java.sql.Date;

public class Filter {
    private Boolean blocking;
    private Date startDate;
    private Date endDate;

    public Boolean getBlocking() {
        return blocking;
    }

    public void setBlocking(Boolean blocking) {
        this.blocking = blocking;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Filter{" +
                "blocking=" + blocking +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
