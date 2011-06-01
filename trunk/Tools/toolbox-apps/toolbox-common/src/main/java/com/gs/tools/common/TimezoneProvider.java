/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gs.tools.common;

import com.gs.tools.common.model.TimeZoneData;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 *
 * @author 50120C1509
 */
public class TimezoneProvider {

    public static void main(String[] a) {

        TimeZoneData[] arr = systemTimeZoneData();
        for (TimeZoneData timeZoneData : arr) {
            System.out.println(timeZoneData);
        }
    }

    public static TimeZoneData[] systemTimeZoneData() {
        List<TimeZoneData> tzDatas = new ArrayList<TimeZoneData>();
        Date today = new Date();
        String[] zoneIds = TimeZone.getAvailableIDs();
        for (int i = 0; i < zoneIds.length; i++) {
            // Get time zone by time zone id
            TimeZone tz = TimeZone.getTimeZone(zoneIds[i]);
            TimeZoneData tzd = new TimeZoneData();
            tzd.setTimeZoneId(zoneIds[i]);
            // Get the display name
            String shortName = tz.getDisplayName(tz.inDaylightTime(today), TimeZone.SHORT);
            tzd.setShortName(shortName);
            String longName = tz.getDisplayName(tz.inDaylightTime(today), TimeZone.LONG);
            tzd.setLongName(longName);
            // Get the number of hours from GMT
            int rawOffset = tz.getRawOffset();

            int hour = rawOffset / (60 * 60 * 1000);
            tzd.setHourFromGMT(hour);
            int min = Math.abs(rawOffset / (60 * 1000)) % 60;
            tzd.setMinFromGMT(min);

            // Does the time zone have a daylight savings time period?
            boolean hasDST = tz.useDaylightTime();
            tzd.setHasDST(hasDST);
            // Is the time zone currently in a daylight savings time?
            boolean inDST = tz.inDaylightTime(today);
            tzd.setInDST(inDST);
            tzDatas.add(tzd);
        }
        TimeZoneData[] newArray = (TimeZoneData[]) Array.newInstance(TimeZoneData.class, tzDatas.size());
        return tzDatas.toArray(newArray);
    }
}

