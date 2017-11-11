package com.muchencute.commons.email.builder;

import com.muchencute.commons.email.client.SimpleEmailClient;

public class SimpleEmailClientBuilder extends AbstractEmailClientBuilder {

    public SimpleEmailClientBuilder(String hostName, int port, String username, String password) {

        super(hostName, port, username, password);
    }

    @Override
    public SimpleEmailClient build() {

        return new SimpleEmailClient(this);
    }

}
