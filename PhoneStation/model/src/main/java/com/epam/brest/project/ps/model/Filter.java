package com.epam.brest.project.ps.model;

import java.sql.Date;

/**
 * Model for filtering variables.
 */
public class Filter {

    /**
     * Status blocking for clients.
     */
    private Boolean blocking;

    /**
     * Start date for search.
     */
    private Date startDate;

    /**
     * End date for search.
     */
    private Date endDate;

    /**
     * Getter for blocking.
     *
     * @return blocking.
     */
    public final Boolean getBlocking() {
        return blocking;
    }

    /**
     * Setter for blocking.
     *
     * @param status blocking.
     */
    public final void setBlocking(final Boolean status) {
        this.blocking = status;
    }

    /**
     * Getter for startDate.
     *
     * @return startDate.
     */
    public final Date getStartDate() {
        return startDate;
    }

    /**
     * Setter for startDate.
     *
     * @param start date.
     */
    public final void setStartDate(final Date start) {
        this.startDate = start;
    }

    /**
     * Getter for endDate.
     *
     * @return endDate.
     */
    public final Date getEndDate() {
        return endDate;
    }

    /**
     * Setter for endDate.
     *
     * @param end date.
     */
    public final void setEndDate(final Date end) {
        this.endDate = end;
    }

    @Override
    public final String toString() {
        return "Filter{"
                + "blocking=" + blocking
                + ", startDate=" + startDate
                + ", endDate=" + endDate
                + '}';
    }
}
