package com.muchencute.commons.email;

import com.muchencute.commons.email.builder.HtmlEmailClientBuilder;
import com.muchencute.commons.email.builder.SimpleEmailClientBuilder;

public final class EmailClientBuilders {

    private EmailClientBuilders() {

    }

    public static SimpleEmailClientBuilder simple(String hostName, int port, String username, String password) {

        return new SimpleEmailClientBuilder(hostName, port, username, password);
    }

    public static HtmlEmailClientBuilder html(String hostName, int port, String username, String password) {

        return new HtmlEmailClientBuilder(hostName, port, username, password);
    }
}
