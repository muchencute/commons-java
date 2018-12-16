package com.muchencute.commons.regexp;

import junit.framework.Assert;
import junit.framework.TestCase;

import java.util.regex.Pattern;

import static com.muchencute.commons.regexp.RegexpUtils.*;

public class RegexpUtilsTest extends TestCase {

    public void testUsername() {

        Assert.assertTrue(Pattern.matches(USERNAME, "nothingmi"));
    }

    public void testPassword() {

        Assert.assertTrue(Pattern.matches(PASSWORD2, "hl74dxinF@4tBUg4v"));
        Assert.assertTrue(Pattern.matches(PASSWORD2, "abc123"));
        Assert.assertTrue(Pattern.matches(PASSWORD2, "Abc123"));
    }

    public void testEmail() {

        Assert.assertTrue(Pattern.matches(EMAIL, "nothingmi@muchencute.com"));
        Assert.assertTrue(Pattern.matches(EMAIL, "eileen.gu@cri-report.com"));
        Assert.assertTrue(Pattern.matches(EMAIL, "eileen.gu@abc.com.co"));
    }

    public void testPhone() {

        Assert.assertTrue(Pattern.matches(PHONE, "+8618620398354"));
        Assert.assertTrue(Pattern.matches(PHONE, "+86-18620398354"));
        Assert.assertTrue(Pattern.matches(PHONE, "+86-731-8993-2530"));
    }
}