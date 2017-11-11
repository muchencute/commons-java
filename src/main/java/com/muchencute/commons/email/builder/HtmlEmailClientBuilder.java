package com.muchencute.commons.email.builder;

import com.muchencute.commons.email.client.HtmlEmailClient;

public class HtmlEmailClientBuilder extends AbstractEmailClientBuilder {

    public HtmlEmailClientBuilder(String hostName, int smtpPort, String username, String password) {

        super(hostName, smtpPort, username, password);
    }

    @Override
    public HtmlEmailClient build() {

        return new HtmlEmailClient(this);
    }
}
