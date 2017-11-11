package com.muchencute.commons.email;

import com.muchencute.commons.email.client.HtmlEmailClient;
import com.muchencute.commons.email.client.SimpleEmailClient;
import junit.framework.TestCase;

public class EmailClientBuildersTest extends TestCase {

    private static final String HOST_NAME = "smtp.exmail.qq.com";

    private static final int PORT = 465;

    private static final String USERNAME = "pm@muchencute.com";

    private static final String PASSWORD = "at0769ATP";

    public void testHtml() throws Exception {

        HtmlEmailClient htmlEmailClient = EmailClientBuilders.html(
                HOST_NAME, PORT, USERNAME, PASSWORD
        ).build();

        htmlEmailClient.addTo("nothingmi@muchencute.com", "Nothing Mi");
        htmlEmailClient.setSubject("Hello");
        htmlEmailClient.setMessage("World");
        htmlEmailClient.setHtmlMessage("<h1>HTML Hello World</h1>");
        htmlEmailClient.send();

    }

    public void testSimple() throws Exception {

        SimpleEmailClient simpleEmailClient = EmailClientBuilders.simple(
                HOST_NAME, PORT, USERNAME, PASSWORD
        ).build();

        simpleEmailClient.addTo("nothingmi@muchencute.com", "Nothing Mi");
        simpleEmailClient.setSubject("Hello");
        simpleEmailClient.setMessage("World");
        simpleEmailClient.send();

    }

}