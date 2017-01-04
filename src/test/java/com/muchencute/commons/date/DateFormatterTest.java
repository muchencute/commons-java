package com.muchencute.commons.date;

import junit.framework.TestCase;

import java.util.Date;

public class DateFormatterTest extends TestCase {

    public void testGetUTCDateTime() throws Exception {
        System.out.println(DateFormatter.getUTCDateTime());
    }

    public void testGetUTCDateTime1() throws Exception {
        System.out.println(DateFormatter.getUTCDateTime(new Date()));
    }

}