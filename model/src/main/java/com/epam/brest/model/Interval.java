package com.epam.brest.model;

import java.sql.Date;
import java.util.Objects;

/**
 * Interval class
 */
public class Interval {
    /**
     * Property dateFrom.
     */
    private Date dateFrom;
    /**
     * Property dateTo.
     */
    private Date dateTo;

    public Interval(Date dateFrom, Date dateTo) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public Interval() {
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    @Override
    public String toString() {
        return "Interval{" +
                "dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Interval interval = (Interval) o;
        return Objects.equals(dateFrom, interval.dateFrom) &&
                Objects.equals(dateTo, interval.dateTo);
    }

    @Override
    public int hashCode() {

        return Objects.hash(dateFrom, dateTo);
    }
}
