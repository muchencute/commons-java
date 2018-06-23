package com.muchencute.commons.regexp;

import junit.framework.Assert;
import junit.framework.TestCase;

import java.util.regex.Pattern;

import static com.muchencute.commons.regexp.RegexpUtils.EMAIL;
import static com.muchencute.commons.regexp.RegexpUtils.PASSWORD;
import static com.muchencute.commons.regexp.RegexpUtils.USERNAME;

public class RegexpUtilsTest extends TestCase {

    public void testUsername() {

        Assert.assertTrue(Pattern.matches(USERNAME, "nothingmi"));
    }

    public void testPassword() {

        Assert.assertTrue(Pattern.matches(PASSWORD, "hl74dxinF@4tBUg4v"));
    }

    public void testEmail() {

        Assert.assertTrue(Pattern.matches(EMAIL, "nothingmi@muchencute.com"));
    }
}