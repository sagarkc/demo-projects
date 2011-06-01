/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gs.tools.common.model;

import java.util.TimeZone;

/**
 *
 * @author 50120C1509
 */
public class TimeZoneData {
    private TimeZone timeZone;
    private String timeZoneId;
    private String shortName;
    private String longName;
    private int hourFromGMT;
    private int minFromGMT;
    private boolean hasDST;
    private boolean inDST;
    private boolean positiveFromGMT;

    public TimeZoneData() {
    }

    public boolean isHasDST() {
        return hasDST;
    }

    public void setHasDST(boolean hasDST) {
        this.hasDST = hasDST;
    }

    public int getHourFromGMT() {
        return hourFromGMT;
    }

    public void setHourFromGMT(int hourFromGMT) {
        this.hourFromGMT = hourFromGMT;
    }

    public boolean isInDST() {
        return inDST;
    }

    public void setInDST(boolean inDST) {
        this.inDST = inDST;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public int getMinFromGMT() {
        return minFromGMT;
    }

    public void setMinFromGMT(int minFromGMT) {
        this.minFromGMT = minFromGMT;
    }

    public boolean isPositiveFromGMT() {
        return positiveFromGMT;
    }

    public void setPositiveFromGMT(boolean positiveFromGMT) {
        this.positiveFromGMT = positiveFromGMT;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public TimeZone getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    public String getTimeZoneId() {
        return timeZoneId;
    }

    public void setTimeZoneId(String timeZoneId) {
        this.timeZoneId = timeZoneId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TimeZoneData other = (TimeZoneData) obj;
        if ((this.timeZoneId == null) ? (other.timeZoneId != null) : !this.timeZoneId.equals(other.timeZoneId)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.timeZoneId != null ? this.timeZoneId.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return shortName + "<" + timeZoneId + ">" + " GMT " +
                hourFromGMT + ":" + minFromGMT ;
    }

    public String getLongString() {
        return longName + " GMT " +
                hourFromGMT + ":" + minFromGMT ;
    }

    
}
